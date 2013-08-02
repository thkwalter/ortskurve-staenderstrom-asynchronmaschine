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
import org.apache.commons.math3.linear.SingularMatrixException;

import de.thkwalter.jsf.ApplicationRuntimeException;

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
   Startpunktbestimmung.logger.entering("Startpunktbestimmung", "startpunktBestimmen");
   
   // Falls weniger als drei Messpunkte existieren, wird eine JSFAusnahme geworfen.
   if (this.messpunkte.length < 3)
      {
      // Die Fehlermeldung für den Entwickler wird erzeugt und protokolliert.
      String fehlermeldung = "Es existieren nur " + this.messpunkte.length + " Messpunkte!";
      Startpunktbestimmung.logger.severe(fehlermeldung);
      
      // Die Ausnahme wird erzeugt und mit der Fehlermeldung für den Benutzer initialisiert.
      String jsfMeldung = "Um einen Kreis berechnen zu können, werden mindestens drei Messpunkte benötigt. " +
         "Es stehen jedoch nur " + this.messpunkte.length + " Messpunkte zur Verfügung! " +
         "Bitte fügen Sie weitere Messpunkte hinzu.";
      ApplicationRuntimeException applicationRuntimeException = new ApplicationRuntimeException(jsfMeldung);
      
      // Das vorzeitige Verlassen dieser Methode wird protokolliert.
      Startpunktbestimmung.logger.throwing("Startpunktbestimmung", "startpunktBestimmen", applicationRuntimeException);
      
      throw applicationRuntimeException;
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
   RealVector loesung = null;
   try
      {
      loesung = alorithmus.solve(inhomogenitaetsvektor);
      }
   catch (SingularMatrixException singularMatrixException)
      {
      // Die Fehlermeldung für den Entwickler wird erzeugt und protokolliert.
      String fehlermeldung = "Die Matrix aus den Punkten " + this.messpunkte[0] + ", " + this.messpunkte[1] + " und " +
         this.messpunkte[2] + " ist singulär.";
      Startpunktbestimmung.logger.severe(fehlermeldung);
      
      // Die Ausnahme wird erzeugt und mit der Fehlermeldung für den Benutzer initialisiert.
      String jsfMeldung = "Eine von den ersten drei Messpunkten abhängige Matrix ist singuär. Der " +
         "Berechnungsalgorithmus benötigt jedoch eine reguläre Matrix! Bitte sortieren Sie ihre eingegebenen " +
         "Messpunkte so um, dass in den ersten drei Zeilen andere Werte stehen.";
      ApplicationRuntimeException applicationRuntimeException = new ApplicationRuntimeException(jsfMeldung);
      
      // Das vorzeitige Verlassen dieser Methode wird protokolliert.
      Startpunktbestimmung.logger.throwing("Startpunktbestimmung", "startpunktBestimmen", applicationRuntimeException);
      
      throw applicationRuntimeException;
      }
   
   // Der Startpunkt wird aus der Läsung des linearen Gleichungssystems bestimmt.
   double xMittelpunkt = 0.5 * loesung.getEntry(1);
   double yMittelpunkt = 0.5 * loesung.getEntry(2);
   double radius = xMittelpunkt * xMittelpunkt + yMittelpunkt * yMittelpunkt - loesung.getEntry(0);
   
   // Der Startpunkt wird protokolliert.
   Startpunktbestimmung.logger.fine("x: " + xMittelpunkt);
   Startpunktbestimmung.logger.fine("y: " + yMittelpunkt);
   Startpunktbestimmung.logger.fine("r: " + radius);
   
   Startpunktbestimmung.logger.exiting("Startpunktbestimmung", "startpunktBestimmen");
   
   // Der Startpunkt wird zurückgegeben.
   return new double[]{xMittelpunkt, yMittelpunkt, radius};
   }
}
