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

import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.optim.SimpleVectorValueChecker;
import org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer;

/**
 * Diese Klasse kapselt die Lösung eines nichtlinearen Ausgleichsproblems mit Hilfe des Gauß-Newton-Verfahrens. Das 
 * Verfahren ist für die Berechnung der Ortskurvenparameter optimiert.
 * 
 * @author Th. K. Walter
 * @version 1.0
 */
public class Ausgleichsproblem
{
/**
 * Der Gauß-Newton-Algorithmus
 */
private GaussNewtonOptimizer gaussNewtonOptimizer;

/**
 * Die in den verschiedenen Betriebspunkten gemessenen Stromstärken (in A) im Format (-Im I1, Re I1)
 */
private Vector2D[] messpunkte;

/**
 * Die Gewichte in der Ausgleichsrechnung
 */
private double[] gewichte;

/**
 * Die Zielwerte in der Ausgelichsrechnung
 */
private double[] zielwerte;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert den Gauß-Newton-Algorithmus.
 * 
 * @param messpunkte Die in den verschiedenen Betriebspunkten gemessenen Stromstärken (in A) im Format (-Im I1, Re I1).
 */
public Ausgleichsproblem(Vector2D[] messpunkte)
   {
   // Die in den verschiedenen Betriebspunkten gemessenen Stromstärken (in A) im Format (-Im I1, Re I1) werden 
   // initialisiert.
   this.messpunkte = messpunkte;
   
   // Der Gauß-Newton-Algorithmus wird erzeugt. Es wird festgelegt, dass der Algorithmus QR-Zerlegung benutzen soll. 
   // Die Lösung des Ausgleichsproblems gilt als gefunden, wenn sich kein Residuum zwischen zwei Iterationsschritten um 
   // mehr als 1 Prozent ändert.
   this.gaussNewtonOptimizer = new GaussNewtonOptimizer(false, new SimpleVectorValueChecker(0.01, -1.0));
   
   // Die Zielwerte und die Gewichte in der Ausgleichsrechnung werden erzeugt.
   this.gewichte = new double[messpunkte.length];
   this.zielwerte = new double[messpunkte.length];
   for (int i = 0; i < messpunkte.length; i++)
      {
      gewichte[i] = 1.0;
      zielwerte[i] = 0.0;
      }
   }
   
// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode löst das Ausgleichsproblem.
 * 
 * @param ausgleichsproblemtyp Der Typ des Ausgleichsproblems
 * 
 * @return Die berechnete Ortskurve
 */
public Ortskurve ausgleichsproblemLoesen(Ausgleichsproblemtyp ausgleichsproblemtyp)
   {
   MultivariateVectorFunction modellgleichungen = null;
   MultivariateMatrixFunction jakobiMatrix = null;
   
   if (ausgleichsproblemtyp == Ausgleichsproblemtyp.ORTSKURVE_2d)
      {
      
      }
   else if (ausgleichsproblemtyp == Ausgleichsproblemtyp.ORTSKURVE_2d)
      {
      // Ein Objekte der Klasse, welche die Modellgleichungen (der Kreisgleichungen) repräsentiert, wird erzeugt.
      modellgleichungen = new Modellgleichungen(this.messpunkte);
      
      // Ein Objekt der Klasse, welche die Jakobi-Matrix der Modellgleichungen (der Kreisgleichungen) repräsentiert, 
      // wird erzeugt.
      jakobiMatrix = new Jakobimatrix(this.messpunkte);
      }
   
   return new Ortskurve(new Vector2D(2.0 ,0.0), 1.0);
   }

}
