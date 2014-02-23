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
   // Die x-Koordinate des Mittelpunkts des Hilfskreises für die nächste Iteration wird initialisiert.
   double mx_hilf = this.schnittpunktOrtskuveXAchseBerechnen(ortskurve) / 2.0;
   
   // Die Schrittweite wird initialisiert.
   double schritt = mx_hilf;
   
   // Der Abstand der Ortskurve zum Mittelpunkt des Hilfskreises wird deklariert.
   double d = Double.NaN;
   
   // Die relative Abweichung zwischen der Streckenlänge vom Ursprung zum Mittelpunkt des Hilfskreises und der 
   // Streckenlänge vom Mittelpunkt des Hilfskreises zur Ortskurve wird deklariert.
   double relativeAbweichung = Double.NaN;
   
   // Der Vektor zum Leerlaufpunkt wird deklariert.
   Vector2D leerlaufVektor = null;
   
   // Der Vektor vom Mittelpunkt des Hilfskreises zur Ortskurve wird deklariert.
   Vector2D mittepunktHilfskeisOrtskurve = null;
   
   // Der Vektor zum Mittelpunkt des Hilfskreises wird deklariert.
   Vector2D mittelpunktHilfskreis = null;
   
   // Der Vektor vom Mittelpunkt des Hilfskreises zum Mittelpunkt der Ortskurve wird deklariert.
   Vector2D verbindungsvektorMittelpunkte = null;
       
   // Die Iteration zur Bestimmung des Leerlaufpunkts
   do
      {
      // Der Strahl vom Kreismittelpunkt zum Mittelpunkt der Ortskurve wird berechnet.
      mittelpunktHilfskreis = new Vector2D(mx_hilf, 0);
      verbindungsvektorMittelpunkte = ortskurve.getMittelpunktOrtskurve().subtract(mittelpunktHilfskreis);
      
      // Der Abstand der Ortskurve vom Mittelpunkt des Hilfskreises wird berechnet.
      d = verbindungsvektorMittelpunkte.getNorm() - ortskurve.getRadiusOrtskurve();
      
      // Der Vektor zus Schnittpunkt von Strahl und Ortskurve wird bestimmt.
      mittepunktHilfskeisOrtskurve = verbindungsvektorMittelpunkte.scalarMultiply((d - ortskurve.getRadiusOrtskurve()) / d);
      leerlaufVektor = mittelpunktHilfskreis.add(mittepunktHilfskeisOrtskurve);
      
      // Die relative Abweichung wird berechnet.
      relativeAbweichung = (d - mx_hilf) / mx_hilf;
      
      // Die Schrittweite wird für die nächste Iteration halbiert.
      schritt = schritt / 2.0;
      
      // Die x-Koordinate des Mittelpunkts des Hilfskreises wird für die nächste Iteration berechnet.
      mx_hilf = relativeAbweichung > 0 ? mx_hilf + schritt : mx_hilf - schritt;
      }
   while(relativeAbweichung < 1E-5);
   
   // Der Leerlaufpunkt wird gespeichert.
   this.leerlaufpunkt = new Betriebspunkt(new Complex(leerlaufVektor.getY(), -leerlaufVektor.getX()));
   
   // Das Ergebnis wir protokolliert.
   Leerlauf.logger.info("Leerlauf: " + this.leerlaufpunkt.toString());
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
 * Diese Methode gibt den Ständerstrom im Leerlauf (in A) zurück.
 * 
 * @return Der Ständerstrom im Leerlauf (in A)
 * 
 * @see de.thkwalter.et.ersatzschaltbild.Betriebspunkt#getI1()
 */
public Complex getI1()
   {
   // Den Ständerstrom im Leerlauf (in A) wird zurückgegeben.
   return this.leerlaufpunkt.getI1();
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
