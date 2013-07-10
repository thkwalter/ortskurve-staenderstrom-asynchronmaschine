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

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(Startpunktbestimmung.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert das Feld der Messpunkte und berechnet den Startpunkt.
 * 
 * @param messpunkte Das Feld der Messpunkte.
 */
public Startpunktbestimmung(Vector2D[] messpunkte)
   {
   this.messpunkte = messpunkte;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet den Startpunkt aus den ersten drei Messpunkten.
 * 
 * @return Der Startpunkt. Die erste Komponente des Feldes repräsentiert die x-Komponente des Mittelpunktes, die zweite
 * Komponente die y-Komponente, die dritte Komponente den Radius.
 */
public double[] startpunktBestimmen()
   {
   // Falls weniger als drei Messpunkte existieren, wird eine Ausnahme geworfen.
   if (this.messpunkte.length < 3)
      {
      String fehlermeldung = "Die Berechnung des Startpunktes kann nicht durchgeführt werden.\n\n" +
               "Ursache: Es existieren weniger als drei Messpunkte.";
      
      Startpunktbestimmung.logger.severe("Es existieren weniger als drei Messpunkte.");
      
      throw new RuntimeException(fehlermeldung);
      }
   
   // Die Felder für die Koeffizientenmatrix und die Inhomogenität werden definiert.
   double[][] koeffizienten = new double[3][];
   double[] inhomogenitaet = new double[3];
   
   // Einige Hilfsgrößen werden deklariert.
   double[] zeile = null;
   double x = Double.NaN;
   double y = Double.NaN;
   
   // In der folgenden Schleife werden die Koeffizientenmatrix und die Inhomogenität initialisiert.
   for (int i = 0; i < 3; i++)
      {
      // Die x- und y-Komponente eines Punktes werden gelesen.
      x = this.messpunkte[i].getX();
      y = this.messpunkte[i].getY();
      
      // Eine Zeile der Koeffizientenmatrix wird initialisiert
      zeile = new double[3];
      zeile[0] = 1;
      zeile[1] = - x;
      zeile[2] = - y;
      koeffizienten[i] = zeile;
      
      // Eine Komponente des Inhomogenitätsvektors wird initialisiert.
      inhomogenitaet[i] = - (x*x + y*y);
      }
 
   // Die Koeffizientenmatrix wird erzeugt.
   RealMatrix koeffizientenmatrix = new Array2DRowRealMatrix(koeffizienten);
   
   // Der Inhomogenitätsvektor wird erzeugt.
   RealVector inhomogenitaetsvektor = new ArrayRealVector(inhomogenitaet, false);
   
   // Der Lösungsalgorithmus für das lineare Gleichungssystem wird erzeugt.
   DecompositionSolver alorithmus = new LUDecomposition(koeffizientenmatrix).getSolver();
   
   // Das inhomogene Gleichungssystem wird gelöst.
   RealVector loesung = alorithmus.solve(inhomogenitaetsvektor);
   
   // Der Startpunkt wird aus der Läsung des linearen Gleichungssystems bestimmt.
   double xMittelpunkt = 0.5 * loesung.getEntry(1);
   double yMittelpunkt = 0.5 * loesung.getEntry(2);
   double radius = xMittelpunkt * xMittelpunkt + yMittelpunkt * yMittelpunkt - loesung.getEntry(0);
   
   // Der Startpunkt wird protokolliert.
   Startpunktbestimmung.logger.fine("x: " + xMittelpunkt);
   Startpunktbestimmung.logger.fine("y: " + yMittelpunkt);
   Startpunktbestimmung.logger.fine("r: " + radius);
   
   // Der Startpunkt wird zurückgegeben.
   return new double[]{xMittelpunkt, yMittelpunkt, radius};
   }
}
