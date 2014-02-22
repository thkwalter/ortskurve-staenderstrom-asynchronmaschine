/**
 *  Copyright 2014 Th. K. Walter, Nürnberg.
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
package de.thkwalter.et.ortskurve;

import java.util.logging.Logger;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.thkwalter.et.ersatzschaltbild.Betriebspunkt;
import de.thkwalter.jsf.ApplicationRuntimeException;

/**
 * Diese Klasse repräsentiert den Leerlauf.
 * 
 * @author Th. K. Walter
 */
public class Leerlauf
{
/**
 * Dieser Betriebspunkt repräsentiert den Leerlauf.
 */
private Betriebspunkt leerlaufpunkt;

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(Leerlauf.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor berechnet den Leerlaufpunkt.
 * 
 * @param ortskurve Die Ortskurve, für welche der Leerlaufpunkt berechnet wird.
 */
public Leerlauf(Ortskurve ortskurve)
   {
   // x-Koordinate des Kreismittelpunkts.
   double x = this.schnittpunktOrtskuveXAchseBerechnen(ortskurve) / 2.0;
   
   // Die Schrittweite wird deklariert.
   double schritt = x;
   
   // Der Abstand der Ortskurve zum Kreismittelpunkt wird deklariert.
   double d = Double.NaN;
   
   // Die relative Abweichung der beiden Strecken wird deklariert.
   double relativeAbweichung = Double.NaN;
   
   Vector2D leerlaufVektor = null;
   Vector2D xAchseOrtskurve = null;
       
   // Die Iteration zur Bestimmung des Leerlaufpunkts
   do
      {
      // Der Strahl vom Kreismittelpunkt zum Mittelpunkt der Ortskurve wird berechnet.
      Vector2D mittelpunkt = new Vector2D(x, 0);
      Vector2D strahl = ortskurve.getMittelpunktOrtskurve().subtract(mittelpunkt);
      
      // Der Abstand der Ortskurve zum Kreismittelpunkt wird bestimmt.
      d = strahl.getNorm() - ortskurve.getRadiusOrtskurve();
      
      // Der Vektor zus Schnittpunkt von Strahl und Ortskurve wird bestimmt.
      xAchseOrtskurve = strahl.scalarMultiply((d - ortskurve.getRadiusOrtskurve()) / d);
      leerlaufVektor = mittelpunkt.add(xAchseOrtskurve);
      
      // Die relative Abweichung wird berechnet.
      relativeAbweichung = (d - x) / x;
      
      // Die Schrittweite wird für die nächste Iteration halbiert.
      schritt = schritt / 2.0;
      
      // Die x-Koordinate des Kreismittelpunkts des nächsten Iterationsschritts wird berechnet.
      x = relativeAbweichung > 0 ? x + schritt : x - schritt;
      }
   while(relativeAbweichung < 1E-5);
   
   this.leerlaufpunkt = new Betriebspunkt(new Complex(leerlaufVektor.getY(), -leerlaufVektor.getX()));
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet den Schnittpunkt der Ortskurve mit der x-Achse.
 * 
 * @param ortskurve Die Ortskurve
 * 
 * @return Die x-Koordinate des Schnittpunkts
 */
private double schnittpunktOrtskuveXAchseBerechnen(Ortskurve ortskurve)
   {
   // Die x-Komponente des Mittelpunkts der Ortskurve (in gedrehter Darstellung; in A) wird gelesen.
   double mx = ortskurve.getMittelpunktOrtskurve().getX();
   
   // Die y-Komponente des Mittelpunkts der Ortskurve (in gedrehter Darstellung; in A) wird gelesen.
   double my = ortskurve.getMittelpunktOrtskurve().getY();   
   
   // Der Radius der Ortskurve (in A) wird gelesen. 
   double r = ortskurve.getRadiusOrtskurve();
   
   // Eine Hilfsgröße wird berechnet.
   double hilf = r * r - my * my;
   
   // Falls die Hilfsgröße kleiner Null ist, existiert kein Schnittpunkt der Ortskurve mit der x-Achse.
   if (hilf < 0)
      {
      // Die Fehlermeldung für den Entwickler wird erzeugt und protokolliert.
      String fehlermeldung = "Die Ortskurve schneidet nicht die x-Achse!";
      Leerlauf.logger.severe(fehlermeldung);
      
      // Die Ausnahme wird erzeugt und mit der Fehlermeldung für den Benutzer initialisiert.
      String jsfMeldung = "Die Ortskurve schneidet nicht die Imaginärachse!";
      ApplicationRuntimeException applicationRuntimeException = new ApplicationRuntimeException(jsfMeldung);
      
      throw applicationRuntimeException;
      }
   
   // Der Schnittpunkt wird berechnet und zurückgegeben.
   return  mx - Math.sqrt(hilf);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString() 
 */
@Override
public String toString()
   {
   // Die Zeichenkette, die den Leerlauf repräsentiert wird erzeugt und zurückgegeben.
   return "Leerlauf [" + (leerlaufpunkt != null ? "leerlaufpunkt=" + leerlaufpunkt : "") + "]";
   }
}
