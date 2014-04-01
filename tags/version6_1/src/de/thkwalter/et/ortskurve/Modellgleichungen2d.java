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
package de.thkwalter.et.ortskurve;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Diese Klasse repräsentiert die Modellgleichungen (die Kreisgleichungen).
 * 
 * @author Th. K. Walter
 */
public class Modellgleichungen2d implements MultivariateVectorFunction
{
/**
 * Dieses Feld enthält die Messpunkte.
 */
private Vector2D[] messpunkte;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert das Feld der Messpunkte.
 * 
 * @param messpunkte Das Feld der Messpunkte.
 */
public Modellgleichungen2d(Vector2D[] messpunkte)
   {
   this.messpunkte = messpunkte;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode brechnet die Werte der Modellgleichungen (der Kreisgleichungen).
 * 
 * @param kreisparameter Die Parameterwerte der Kreisgleichungen. Das 0-te Element ist die x-Koordinate des
 *        Kreismittelpunkts, das 1-te Element ist die y-Koordinate des Kreismittelpunkts, das 2-te Element ist der
 *        Radius des Kreises.
 * 
 * @return Die Werte der Modellgleichungen. Der Index des Feldes läuft über die Gleichungen.
 * 
 * @see org.apache.commons.math3.analysis.MultivariateVectorFunction#value(double[])
 */
@Override
public double[] value(double[] kreisparameter)
   {   
   // Der Vektor für den Mittelpunkt der Ortskurve wird erzeugt.
   Vector2D mittelpunkt = new Vector2D(kreisparameter[0], 0.0);

   // Der Radius des Kreises wird gelesen.
   double radius = kreisparameter[1];
   
   // Dieses Feld enthält für jeden Betriebspunkt den Abstand des entsprechenden Messpunktes von der Ortskurve.
   double[] abstaende = new double[this.messpunkte.length]; 
   
   // Die Abstände der Messpunkte vom Kreis werden berechnet und protokolliert.
   for (int i = 0; i < this.messpunkte.length; i++)
      {
      abstaende[i] = this.messpunkte[i].distance(mittelpunkt) - radius;
      }
   
   return abstaende;
   }
}
