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
 * Diese Klasse repräsentiert die in verschiedenen Betriebspunkten gemessenen Ständerstromwerte.
 *
 * @author Th. K. Walter
 */
public class Strommesswerte implements MultivariateVectorFunction
{

/**
 * Dieses Feld enthält einen zweidimensionalen Vektor für jeden Betriebspunkt. Die x-Komponente dieses Vektors ist das 
 * Negative des Imaginärteils des Ständerstroms, die y-Komponente der Realteil des Ständerstroms.
 */
private Vector2D[] stromvektoren;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert die Stromvektoren.
 * 
 * @param stromvektoren Dieses Feld enthält einen zweidimensionalen Vektor für jeden Betriebspunkt. Die x-Komponente 
 * dieses Vektors ist das Negative des Imaginärteils des Ständerstroms, die y-Komponente der Realteil des Ständerstroms.
 */
public Strommesswerte(Vector2D[] stromvektoren)
   {
   this.stromvektoren = stromvektoren;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode brechnet für einen gegebenen Parametersatz der Ortskurve (Mittelpunkt, Radius des Kreises) und für 
 * jeden Strommesswert (Betriebspunkt) den Abstand des entsprechenden Messpunktes von der Ortskurve.
 * 
 * @param point Die Parameterwerte der Kreisgleichung der Ortskurve. Das 0-te Element ist die x-Koordinate des 
 * Kreismittelpunkts, das 1-te Element ist die y-Koordinate des Kreismittelpunkts, das 2-te Element ist der Radius des
 * Kreises.
 * 
 * @return Dieses Feld enthält für jeden Betriebspunkt den Abstand des entsprechenden Messpunktes von der Ortskurve.
 */
@Override
public double[] value(double[] point) throws IllegalArgumentException
   {
   // Der Vektor für den Mittelpunkt der Ortskurve wird erzeugt.
   Vector2D mittelpunkt = new Vector2D(point[0], point[1]);

   // Der Radius des Kreises wird gelesen.
   double radius = point[2];
   
   // Dieses Feld enthält für jeden Betriebspunkt den Abstand des entsprechenden Messpunktes von der Ortskurve.
   double[] abstaende = new double[this.stromvektoren.length]; 
   
   // Die Abstände der Stromkoordinaten vom Kreis wird für die verschiedenen Betriebspunkte berechnet.
   for (int i = 0; i < this.stromvektoren.length; i++)
      {
      abstaende[i] = this.stromvektoren[i].distance(mittelpunkt) - radius;
      }
   
   return abstaende;
   }

}
