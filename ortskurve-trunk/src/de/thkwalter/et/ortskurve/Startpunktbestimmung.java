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

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/**
 * Diese Klasse berechnet aus drei Punkten den Startpunkt für das Ausgleichsproblem. 
 *
 * @author Th. K. Walter
 */
public class Startpunktbestimmung
{
/**
 * Dieses Feld enthält die Messpunkte.
 */
private Vector2D[] messpunkte;

private static Logger logger = Logger.getLogger(Startpunktbestimmung.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert das Feld der Messpunkte.
 * 
 * @param messpunkte Das Feld der Messpunkte.
 */
public Startpunktbestimmung(Vector2D[] messpunkte)
   {
   this.messpunkte = messpunkte;
   
   this.startpunktBestimmen();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Der Startpunkt wird bestimmt.
 */
public double[] startpunktBestimmen()
   {
   // Die Koeffizienten der Koeffizientenmatrix werden zusammengestellt.
   double[][] koeffizienten = new double[3][];
   double[] zeile = null;
   double[] inhomogenitaet = new double[3];
   double x = Double.NaN;
   double y = Double.NaN;
   for (int i = 0; i < 3; i++)
      {
      zeile = new double[3];
      
      x = this.messpunkte[i].getX();
      y = this.messpunkte[i].getY();
      
      zeile[0] = 1;
      zeile[1] = - x;
      zeile[2] = - y;
      
      inhomogenitaet[i] = - (x*x + y*y);
      
      koeffizienten[i] = zeile;
      }
 
   // Die Koeffizientenmatrix wird erstellt.
   RealMatrix koeffizientenmatrix = new Array2DRowRealMatrix(koeffizienten);
   
   // Der Lösungsalgorithmus für das lineare Gleichungssystem wird erzeugt.
   DecompositionSolver alorithmus = new LUDecomposition(koeffizientenmatrix).getSolver();
   
   RealVector inhomogenitaetsvektor = new ArrayRealVector(inhomogenitaet, false);
   RealVector loesung = alorithmus.solve(inhomogenitaetsvektor);
   
   
   double xMittelpunkt = 0.5 * loesung.getEntry(1);
   double yMittelpunkt = 0.5 * loesung.getEntry(2);
   double radius = xMittelpunkt * xMittelpunkt + yMittelpunkt * yMittelpunkt - loesung.getEntry(0);
   
   Startpunktbestimmung.logger.info("x: " + xMittelpunkt);
   Startpunktbestimmung.logger.info("y" + yMittelpunkt);
   Startpunktbestimmung.logger.info("r" + radius);
   
   return new double[]{xMittelpunkt, yMittelpunkt, radius};
   }


public static void main(String[] args)
   {
  Startpunktbestimmung start =  new Startpunktbestimmung(new Vector2D[]{new Vector2D(0,0), new Vector2D(1,1), new Vector2D(2,0)});
  start.startpunktBestimmen();
   }
}
