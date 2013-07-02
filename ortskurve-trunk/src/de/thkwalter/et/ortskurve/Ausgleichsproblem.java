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

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.optim.InitialGuess;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.optim.SimpleVectorValueChecker;
import org.apache.commons.math3.optim.nonlinear.vector.ModelFunction;
import org.apache.commons.math3.optim.nonlinear.vector.ModelFunctionJacobian;
import org.apache.commons.math3.optim.nonlinear.vector.Target;
import org.apache.commons.math3.optim.nonlinear.vector.Weight;
import org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer;

/**
 * Diese Klasse sucht mit Hilfe der Methoden der nichtlinearen Ausgleichsrechnung den Mittelpunkt und den Radius eines
 * Kreises zu einer vorgegebenen Menge von Punkten.
 *
 * @author Th. Walter
 */
@RequestScoped
@ManagedBean
public class Ausgleichsproblem
{
private static Logger logger = Logger.getLogger(Ausgleichsproblem.class.getName());

private Vector2D[] punkte = null;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode löst das Ausgleichsproblem und berechnet den Mittelpunkt und den Radius des Kreises.
 * 
 * @return <tt>null</tt>
 */
public String problemLoesen()
   {
   Ausgleichsproblem.logger.entering("Ausgleichsproblem", "problemLoesen");
   
   // Ein Objekt der Klasse, die den Lösungsalgorithmus kapselt wird erzeugt. 
   GaussNewtonOptimizer gaussNewtonOptimizer = 
         new GaussNewtonOptimizer(false, new SimpleVectorValueChecker(0.05, -1.0));
   
   // Das Feld für die Gewichte der einzelnen Punkte wird deklariert.
   double[] gewichte = new double[this.punkte.length];
   
   // Das folgende Feld enthält die Werte, welche die parametrisierte Modellgleichung ergeben sollte.
   double[] zielwerte = new double[this.punkte.length];
   
   // In der folgenden Schleife über alle Punkte werden die oben deklarierten Felder initialisiert.
   for (int i = 0; i < this.punkte.length; i++)
      {
      // Allen Punkten wird das gleiche Gewicht zugewiesen.
      gewichte[i] = 1.0;
      
      // Die Zielwerte haben alle den Wert Null, da die Kreisgleichung für alle Punkte diesen Wert ergeben sollte.
      zielwerte[i] = 0.0;
      }
   
   // TWA Die Startparameter müssen aus drei Punkten ermittelt werden.
   InitialGuess startparameter = new  InitialGuess(new double[]{1.1, 0.1, 1.1});
   
   // Ein Objekte der Klasse, welche die in verschiedenen Betriebspunkten gemessenen Ständerstromwerte repräsentiert, 
   // wird erzeugt.
   Modellgleichungen strommesswerte = new Modellgleichungen(this.punkte);
   
   Jakobimatrix jakobiMatrix = new Jakobimatrix(this.punkte);
   
   // Das Ausgleichsproblem wird gelöst.
   PointVectorValuePair endParameter = gaussNewtonOptimizer.optimize(new Weight(gewichte), new Target(zielwerte), 
      startparameter, new MaxEval(100), new ModelFunction(strommesswerte), new ModelFunctionJacobian(jakobiMatrix));
   
   
   double mx = endParameter.getPoint()[0];
   double my = endParameter.getPoint()[1];
   double r = endParameter.getPoint()[2];
   
   Ausgleichsproblem.logger.info("Mittelpunkt: (" + mx + ", " + my + "); Radius: " + r);
   
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================


public String getEingabefeld()
   {
   return "";
   }

// =====================================================================================================================
// =====================================================================================================================


public void setEingabefeld(String eingabefeld)
   {   
   String[] zeilen = eingabefeld.split("\n");
   String[] datenfelder = null;
   this.punkte = new Vector2D[zeilen.length];
   
   double x = Double.NaN;
   double y = Double.NaN;
   
   for (int i = 0; i < zeilen.length; i++)
      {
      datenfelder = zeilen[i].split(",");
      
      x = Double.parseDouble(datenfelder[0].trim());
      y = Double.parseDouble(datenfelder[1].trim());
      
      this.punkte[i] = new Vector2D(x, y);
      Ausgleichsproblem.logger.info(this.punkte[i].toString());
      }
   
   
   }
}
