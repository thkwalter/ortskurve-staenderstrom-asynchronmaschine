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

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.thkwalter.et.ortskurve.Ortskurve;

/**
 * Diese Klasse repräsentiert die Ortskurve der Impedanz des Ersatzschaltbildes.
 * 
 * @author Th. K. Walter
 */
public class OrtskurveImpedanz
{
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
   // In Abhängigkeit vom Schaltungstyp wird die Strangspannung berechnet.
   double u1 = Double.NaN;
   if (Schaltungstyp.STERN == schaltungstyp)
      {
      u1 = u_LL / Math.sqrt(3.0);
      }
   else
      {
      throw new RuntimeException("Noch nicht implementiert!");
      }
   
   // Die Ortskurve der inversen Impedanz wird berechnet und zurückgegeben.
   return ortskurve.skalierteOrtskurveBerechnen(u1);
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
   // Die Parameter a und b werden bestimmt. a wird reell gewählt.
   double r = ortskurve.getRadiusOrtskurve();
   Complex a = new Complex(2*r);
   Complex b = new Complex(ortskurve.getMittelpunktOrtskurve().getY() - 0.5 * a.getReal(), 
      - ortskurve.getMittelpunktOrtskurve().getX());
   
   // Eine Hilfsgröße wird ermittelt.
   Complex hilf = (b.add(a)).multiply(b).multiply(2);
   
   // Radius und Mittelpunkt des invertierten Kreises werden berechnet.
   double r_inv = a.divide(hilf).abs();
   Complex m_inv = (a.add(b.multiply(2))).divide(hilf);
   
   // Die neue Ortskurve wird erzeugt und zurückgegeben.
   return new Ortskurve(new Vector2D(- m_inv.getImaginary(), m_inv.getReal()), r_inv);
   }
}
