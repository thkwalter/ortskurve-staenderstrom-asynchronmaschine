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
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
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

import de.thkwalter.jsf.ApplicationRuntimeException;

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

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(Ausgleichsproblem.class.getName());

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
 * @param startpunkt Der Startpunkt der Ausgleichsrechnung
 * @param ausgleichsproblemtyp Der Typ des Ausgleichsproblems
 * 
 * @return Die berechnete Ortskurve
 */
public Ortskurve ausgleichsproblemLoesen(double[] startpunkt, Ausgleichsproblemtyp ausgleichsproblemtyp)
   {
   // Die Referenzen auf die Modellgleichungen und die Jakobimatrix werden deklariert.
   MultivariateVectorFunction modellgleichungen = null;
   MultivariateMatrixFunction jakobiMatrix = null;
   
   // Falls das zweidimensionale Ausgleichsproblem gelöst werden soll, ...
   if (ausgleichsproblemtyp == Ausgleichsproblemtyp.ORTSKURVE_2d)
      {
      
      }
   
   // Falls das dreidimensionale Ausgleichsproblem gelöst werden soll, ...
   else if (ausgleichsproblemtyp == Ausgleichsproblemtyp.ORTSKURVE_3d)
      {
      // Die Modellgleichungen (die Kreisgleichungen) werden erzeugt.
      modellgleichungen = new Modellgleichungen(this.messpunkte);
      
      // Die Jakobi-Matrix der Modellgleichungen (der Kreisgleichungen) wird erzeugt.
      jakobiMatrix = new Jakobimatrix(this.messpunkte);
      }
   
   // Das Ausgleichsproblem wird gelöst, wobei höchstens 200 Iterationsschritte durchgeführt werden.
   double[] ortskurvenparameter = null;
   try
      {
      PointVectorValuePair endParameter = this.gaussNewtonOptimizer.optimize(new Weight(gewichte), 
         new Target(zielwerte), new  InitialGuess(startpunkt), new MaxEval(200), new ModelFunction(modellgleichungen), 
         new ModelFunctionJacobian(jakobiMatrix));
      
      ortskurvenparameter = endParameter.getPoint();
      }
   
   // Falls das Verfahren nicht konvergiert hat, wird die entsprechende Ausnahme gefangen.
   catch (TooManyEvaluationsException e)
      {
      // Die Fehlermeldung für den Entwickler wird erzeugt und protokolliert.
      String fehlermeldung = "Der Gauss-Newton-Algorithmus konvergiert nicht!";
      Ausgleichsproblem.logger.severe(fehlermeldung);
      
      // Die Ausnahme wird erzeugt und mit der Fehlermeldung für den Benutzer initialisiert.
      String jsfMeldung = "Der Gauss-Newton-Algorithmus zur Berechnung der Ortskurve konvergiert nicht! " +
         "Überprüfen Sie bitte, ob die eingegebenen Punkte annähernd auf einem Kreis liegen.";
      ApplicationRuntimeException applicationRuntimeException = new ApplicationRuntimeException(jsfMeldung);
      
      throw applicationRuntimeException;
      }
   
   // Die Referenz auf die Ortskurve wird deklariert.
   Ortskurve ortskurve = null;
   
   // Falls das zweidimensionale Ausgleichsproblem gelöst werden soll, ...
   if (ausgleichsproblemtyp == Ausgleichsproblemtyp.ORTSKURVE_2d)
      {
      ortskurve = new Ortskurve(new Vector2D(ortskurvenparameter[0], 0.0), ortskurvenparameter[1]);
      }
   
   // Falls das dreidimensionale Ausgleichsproblem gelöst werden soll, ...
   else if (ausgleichsproblemtyp == Ausgleichsproblemtyp.ORTSKURVE_3d)
      {
      ortskurve = new Ortskurve(new Vector2D(ortskurvenparameter[0], ortskurvenparameter[1]), ortskurvenparameter[2]);
      }
   
   // Die berechnete Ortskurve wird zurückgegeben.
   return ortskurve;
   }
}
