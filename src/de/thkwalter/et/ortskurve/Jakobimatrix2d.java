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

import java.util.logging.Logger;

import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.thkwalter.jsf.ApplicationRuntimeException;

/**
 * Diese Klasse repräsentiert die Jakobi-Matrix der 2d-Modellgleichungen (der Kreisgleichungen).
 * 
 * @author Th. K. Walter
 */
public class Jakobimatrix2d implements MultivariateMatrixFunction
{
/**
 * Dieses Feld enthält die Messpunkte.
 */
private Vector2D[] messpunkte;

/**
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(Jakobimatrix2d.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert das Feld der Messpunkte.
 * 
 * @param messpunkte Das Feld der Messpunkte.
 */
public Jakobimatrix2d(Vector2D[] messpunkte)
   {
   this.messpunkte = messpunkte;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieses Feld berechnet die Jakobi-Matrix der Modellgleichungen (der Kreisgleichungen).
 * 
 * @param kreisparameter Die Parameterwerte der Kreisgleichungen. Das 0-te Element ist die x-Koordinate des
 *        Kreismittelpunkts, das 1-te Element ist der Radius des Kreises.
 * 
 * @return Die Jakobi-Matrix der Modellgleichungen. Der erste Index des Feldes läuft über die Gleichungen, der zweite
 *         Index über die Kreisparameter.
 * 
 * @see org.apache.commons.math3.analysis.MultivariateMatrixFunction#value(double[])
 */
@Override
public double[][] value(double[] kreisparameter)
   {
   // Der Vektor für den Mittelpunkt der Ortskurve wird erzeugt.
   Vector2D mittelpunkt = new Vector2D(kreisparameter[0], 0.0);

   // Das Feld für die Jakobi-Matrix wird deklariert.
   double[][] jakobiMatrix = new double[this.messpunkte.length][2];

   // In dieser Schleife wird die Jakobi-Matrix initialisiert.
   double abstandMesspunktMittelpunkt = Double.NaN;
   for (int i = 0; i < this.messpunkte.length; i++)
      {
      // Der Abstand des Messpunktes vom Mittelpunkt wird berechnet.
      abstandMesspunktMittelpunkt = this.messpunkte[i].distance(mittelpunkt);

      // Falls der Messpunkt mit dem Mittelpunkt identisch ist wird eine JSFAusnahme geworfen, da sonst das Inverse
      // des Abstands unendlich groß wird. Da der Vergleich zweier double-Wert jedoch sinnlos ist, wird der Abstand mit 
      // einem Prozent des aktuell vermuteten Radius verglichen.
      if (abstandMesspunktMittelpunkt < 0.01 * kreisparameter[1])
         {
         // Die Fehlermeldung für den Entwickler wird erzeugt und protokolliert.
         String fehlermeldung = "Der Punkt " + this.messpunkte[i].toString() + " ist fast identisch mit dem " +
            " Mittelpunkt " + mittelpunkt.toString() + "!";
         Jakobimatrix2d.logger.severe(fehlermeldung);
         
         // Die Ausnahme wird erzeugt und mit der Fehlermeldung für den Benutzer initialisiert.
         String jsfMeldung = "Der Punkt " + this.messpunkte[i].toString() + 
            " scheint in der Nähe des Kreismittelpunktes zu liegen! Überprüfen Sie bitte diesen Punkt.";
         ApplicationRuntimeException applicationRuntimeException = new ApplicationRuntimeException(jsfMeldung);
         
         throw applicationRuntimeException;
         }

      // Das Inverse des Abstands des Messpunkts vom Mittelpunkt wird berechnet.
      double inverserAbstandMesspunktMittelpunkt = 1.0 / abstandMesspunktMittelpunkt;

      // Die Elemente der Jakobi-Matrix werden initialisiert.
      jakobiMatrix[i][0] = inverserAbstandMesspunktMittelpunkt * (mittelpunkt.getX() - this.messpunkte[i].getX());
      jakobiMatrix[i][1] = -1.0;
      }

   return jakobiMatrix;
   }
}
