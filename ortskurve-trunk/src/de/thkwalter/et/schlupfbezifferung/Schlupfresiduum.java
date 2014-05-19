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
package de.thkwalter.et.schlupfbezifferung;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Das Residuum des Schlupfs eines Betriebspunkts in Abhängigkeit vom Steigungswinkel der Schlupfgeraden.
 * 
 * @author Th. K. Walter
 */
public class Schlupfresiduum implements UnivariateFunction
{
/**
 * Die Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten
 */
private double[] steigungen;

/**
 * Die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden
 */
private Betriebspunkt[] betriebspunkte;

/**
 * Das Inversionszentrum (in A)
 */
private Vector2D inversionszentrum;

/**
 * Der Drehpunkt der Schlupfgerade (in A)
 */
private Vector2D drehpunktSchlupfgerade;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode initialisiert das Residuum des Schlupfs eines Betriebspunkts in Abhängigkeit vom Steigungswinkel der 
 * Schlupfgeraden.
 * 
 * @param steigungen Die Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten
 * @param betriebspunkte Die Betriebspunkte
 * @param inversionszentrum Das Inversionszentrum (in A)
 * @param drehpunktSchlupfgerade Der Drehpunkt der Schlupfgerade (in A)
 */
public Schlupfresiduum(double[] steigungen, Betriebspunkt[] betriebspunkte, Vector2D inversionszentrum, 
   Vector2D drehpunktSchlupfgerade)
   {
   // Die Attribute werden initialisiert.
   this.steigungen = steigungen;
   this.betriebspunkte = betriebspunkte;
   this.inversionszentrum = inversionszentrum;
   this.drehpunktSchlupfgerade = drehpunktSchlupfgerade;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet das Residuum des Schlupfs eines Betriebspunkts in Abhängigkeit vom Steigungswinkel der 
 * Schlupfgeraden.
 * 
 * @param phi Der Steigungswinkel der Schlupfgeraden
 * 
 * @return Das Residuum des Schlupfs eines Betriebspunkts
 */
@Override
public double value(double phi)
   {
   // Die Variable für das Residuum des Schlupfs eines Betriebspunkts wird deklariert.
   double residuum = Double.NaN;
   
   // Das Residuum des Schlupfs eines Betriebspunkts wird zurückgegeben.
   return residuum;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet die Schnittpunkte der Schlupfgeraden mit den Strahlen vom Inversionszentrum zu den 
 * Betriebspunkten.
 * 
 * @param phi Der Steigungswinkel der Schlupfgeraden
 * 
 * @return Die Schnittpunkte der Schlupfgeraden mit den Strahlen vom Inversionszentrum zu den Betriebspunkten
 */
private Vector2D[] schnittpunkteBerechnen(double phi)
   {
   // Das Feld der Schnittpunkte der Schlupfgeraden mit den Strahlen vom Inversionszentrum zu den Betriebspunkten wird 
   // erzeugt.
   Vector2D[] schnittpunkte = new Vector2D[3];
   
   // Die Schnittpunkte der Schlupfgeraden mit den Strahlen vom Inversionszentrum zu den Betriebspunkten werden 
   // zurückgegeben.
   return schnittpunkte;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet die x-Komponenten der Schnittpunkte der Schlupfgeraden mit den Strahlen vom Inversionszentrum 
 * zu den Betriebspunkten.
 * 
 * @param phi Der Steigungswinkel der Schlupfgeraden
 * 
 * @return Die x-Komponenten der Schnittpunkte der Schlupfgeraden mit den Strahlen vom Inversionszentrum zu den 
 * Betriebspunkten.
 */
private double[] xKomponentenSchnittpunktBerechnen(double phi)
   {
   // Das Feld für die x-Komponenten der Schnittpunkte der Schlupfgeraden mit den Strahlen vom Inversionszentrum zu den 
   // Betriebspunkten wird deklariert.
   double[] xKomponentenSchnittpunkt = new double[3];
   
   // Die x-Komponente des Drehpunkts der Schlupfgeraden wird gelesen.
   double xDrehpunktSchlupfgerade = this.drehpunktSchlupfgerade.getX();
   
   // Falls der Steigungswinkel der Schlupfgeraden nahe bei pi liegt, wird die Steigung der Schlupfgeraden unendlich 
   // groß.
   if (Math.abs((phi- Math.PI)/Math.PI) < 1E-6)
      {
      // Die x-Komponente der Schnittpunkte ist mit der x-Komponente des Drehpunkts identisch.
      xKomponentenSchnittpunkt[0] = xDrehpunktSchlupfgerade;
      xKomponentenSchnittpunkt[1] = xDrehpunktSchlupfgerade;
      xKomponentenSchnittpunkt[2] = xDrehpunktSchlupfgerade;
      }
   
   // Falls der Steigungswinkel endlich ist, ...
   else
      {
      // Eine Hilfsgröße wird berechnet.
      double hilf = 
         this.drehpunktSchlupfgerade.getY() - Math.tan(phi) * xDrehpunktSchlupfgerade - this.inversionszentrum.getY();
      
      // In der folgenden Schleife werden die Schnittpunkte der Schlupfgeraden mit den Strahlen vom Inversionszentrum zu 
      // den Betriebspunkten berechnet.
      for (int i = 0; i < this.steigungen.length; i++)
         {
         xKomponentenSchnittpunkt[i] = 
            (hilf + this.steigungen[i] * this.inversionszentrum.getX()) / (this.steigungen[i] - Math.tan(phi));
         }
      }
   
   // Die x-Komponenten der Schnittpunkte der Schlupfgeraden mit den Strahlen vom Inversionszentrum zu den 
   // Betriebspunkten wird zurückgegeben.
   return xKomponentenSchnittpunkt;
   }
}
