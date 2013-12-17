/**
 *  Copyright 2013 Th. K. Walter, Nürnberg.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package de.thkwalter.et.ersatzschaltbild;

import java.util.logging.Logger;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.thkwalter.et.ortskurve.Ortskurve;
import de.thkwalter.jsf.ApplicationRuntimeException;

/**
 * Diese Klasse repräsentiert die Ortskurve der Impedanz des Ersatzschaltbildes.
 * 
 * @author Th. K. Walter
 */
public class OrtskurveImpedanz
{

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(OrtskurveImpedanz.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet die Ortskurve der Impedanz.
 * 
 * @param ortskurve Die Stromortskurve
 * @param u_LL Die Netzspannung (Leiter-Leiter; in V)
 * @param schaltungstyp Der Schaltungstyp (Stern oder Dreieck)
 * 
 * @return Die Ortskurve der Impedanz
 */
public static Ortskurve ortskurveImpedanzBerechnen(Ortskurve ortskurve, double u_LL, Schaltungstyp schaltungstyp)
   {
   // Die Ortskurve für 1/Z(s) wird berechnet.
   Ortskurve skalierteOrtskurve = OrtskurveImpedanz.ortskurveInverseImpedanzBerechnen(ortskurve, u_LL, schaltungstyp);
   
   // Die skalierte Ortskurve wird invertiert und die berechnete Ortskurve der Impedanz zurückgegeben.
   return OrtskurveImpedanz.ortskurveInvertieren(skalierteOrtskurve);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Die Ortskurve der inversen Impedanz (in 1/Ohm) wird berechnet.
 * 
 * @param ortskurve Die Stromortskurve (in A)
 * @param u_LL Die Netzspannung (Leiter-Leiter; in V)
 * @param schaltungstyp Der Schaltungstyp (Stern oder Dreieck)
 * 
 * @return Die Ortskurve der inversen Impedanz (in 1/Ohm)
 */
private static Ortskurve ortskurveInverseImpedanzBerechnen(Ortskurve ortskurve, double u_LL, 
   Schaltungstyp schaltungstyp)
   {
   // In Abhängigkeit vom Schaltungstyp und Netzspannung wird der Skalierungsfaktor berechnet.
   double skalierungsfaktor = Double.NaN;
   if (Schaltungstyp.STERN == schaltungstyp)
      {
      skalierungsfaktor = u_LL / Math.sqrt(3.0);
      }
   else
      {
      skalierungsfaktor = u_LL * Math.sqrt(3.0);
      }
   
   // Die Ortskurve der inversen Impedanz wird berechnet und zurückgegeben.
   return ortskurve.skalierteOrtskurveBerechnen(skalierungsfaktor);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode invertiert eine kreisförmige Ortskurve.
 * 
 * @param ortskurve Die Ausgangskurve
 * 
 * @return Die invertierte Ortskurve
 */
private static Ortskurve ortskurveInvertieren(Ortskurve ortskurve)
   {
   // Die Ortskurvenparameter werden gelesen. 
   double m_re = ortskurve.getMittelpunktOrtskurve().getY();
   double m_im = - ortskurve.getMittelpunktOrtskurve().getX();
   double r = ortskurve.getRadiusOrtskurve();
   
   // Eine Hilfsgröße wird definiert. 
   double d_hilf = m_re*m_re + m_im*m_im - r*r;
   
   // Falls die Hilfsgröße gleich null ist.
   if (d_hilf == 0.0)
      {
      // Die Fehlermeldung für den Entwickler wird erzeugt und protokolliert.
      String fehlermeldung = "Die Ortskurve der inversen Impedanz geht durch den Ursprung!";
      OrtskurveImpedanz.logger.severe(fehlermeldung);
      
      // Die Ausnahme wird erzeugt und mit der Fehlermeldung für den Benutzer initialisiert.
      String jsfMeldung = "Die Ortskurve der inversen Impedanz geht durch den Ursprung! " +
         "Überprüfen Sie bitte die Messpunkte der Ortskurve des Ständerstroms auf Korrektheit.";
      ApplicationRuntimeException applicationRuntimeException = new ApplicationRuntimeException(jsfMeldung);
      
      // Die Ausnahme wird geworfen.
      throw applicationRuntimeException;
      }
   
   // Die Parameter der inversen Ortskurve werden berechent.
   double mx_re_invertiert = m_re / d_hilf;
   double my_im_invertiert = - m_im / d_hilf;
   double r_invertiert = r / d_hilf;
   
   // Die invertierte Ortskurve wird erzeugt und zurückgegeben.
   return new Ortskurve(new Vector2D(- my_im_invertiert, mx_re_invertiert), r_invertiert);
   }
}
