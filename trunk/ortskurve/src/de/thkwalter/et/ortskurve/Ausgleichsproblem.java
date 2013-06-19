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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.optim.InitialGuess;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.optim.SimpleVectorValueChecker;
import org.apache.commons.math3.optim.nonlinear.vector.ModelFunction;
import org.apache.commons.math3.optim.nonlinear.vector.Target;
import org.apache.commons.math3.optim.nonlinear.vector.Weight;
import org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer;

/**
 * Diese Klasse sucht mit Hilfe der Methoden der nichtlinearen Ausgleichsrechnung den Mittelpunkt und den Radius zu 
 * einer vorgegebenen Menge von Punkten.
 *
 * @author Th. Walter
 */
@RequestScoped
@ManagedBean
public class Ausgleichsproblem
{
private static Logger logger = Logger.getLogger(Ausgleichsproblem.class.getName());

private double x[] = {1.1, 0.0, -1.1, 0.0};
private double y[] = {0.0, 1.1, 0.0, -1.1};

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode löst das Ausgleichsproblem und berechnet den Mittelpunkt und des Radius der Ortskurve.
 * 
 * @return <tt>null</tt>
 */
public String problemLoesen()
   {
   Ausgleichsproblem.logger.entering("Ausgleichsproblem", "problemLoesen");
   
//   // Ein Objekt der Klasse, die den Lösungsalgorithmus kapselt wird erzeugt. 
//   GaussNewtonOptimizer gaussNewtonOptimizer = 
//         new GaussNewtonOptimizer(false, new SimpleVectorValueChecker(0.05, -1.0, 500));
//   
//   // Das Feld für die Gewichte der einzelnen Messpun
//   double[] gewichte = new double[this.x.length];
//   double[] zielwerte = new double[this.x.length];
//   
//   for (int i = 0; i < this.x.length; i++)
//      {
//      // Allen Messpunkten wird das gleiche Gewicht zugewiesen.
//      gewichte[i] = 1.0;
//      
//      // Die Zielwerte haben alle den Wert Null.
//      zielwerte[i] = 0.0;
//      }
//   
//   // Der Startpunkt der Iteration.
//   InitialGuess startpunkt = new  InitialGuess(new double[]{0,0,1.1});
//   
//   // 
//   MultivariateVectorFunction kreismodell = null;// new Strommesswerte(this.x, this.y);
//   
//   // Das Ausgleichsproblem wird gelöst.
//   PointVectorValuePair endwerte = gaussNewtonOptimizer.optimize(new Weight(gewichte), new Target(zielwerte), 
//      startpunkt, new MaxEval(100), new ModelFunction(kreismodell));
//   
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

public static void main(String[] args)
   {
   (new Ausgleichsproblem()).problemLoesen();
   }
}
