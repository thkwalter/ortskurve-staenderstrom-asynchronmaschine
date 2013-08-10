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

/**
 * Dieses Feld enthält die Messpunkte, welche für die Startpunktbestimmung benutzt werden.
 */
private Vector2D[] messpunkteStartpunktbestimmung;

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(Startpunktbestimmung.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor bestimmt aus den Messpunkten den Startpunkt für die nichtlineare Ausgleichsrechnung.
 * 
 * @param messpunkte Das Feld der Messpunkte
 */
public Startpunktbestimmung(Vector2D[] messpunkte)
   {
   // Der Einsprung in die Methode wird protokolliert.
   Startpunktbestimmung.logger.entering("Startpunktbestimmung", "Startpunktbestimmung");
   
   // Falls weniger als drei Messpunkte existieren, wird eine JSFAusnahme geworfen.
   if (messpunkte == null || messpunkte.length < 3)
      {
      // Die Anzahl der Messpunkte wird bestimmt.
      int anzahlMesspunkte = messpunkte == null ? 0 : messpunkte.length;
      
      // Die Fehlermeldung für den Entwickler wird erzeugt und protokolliert.
      String fehlermeldung = "Es existieren nur " + anzahlMesspunkte + " Messpunkte!";
      Startpunktbestimmung.logger.severe(fehlermeldung);
      
      // Die Ausnahme wird erzeugt und mit der Fehlermeldung für den Benutzer initialisiert.
      String jsfMeldung = "Um einen Kreis berechnen zu können, werden mindestens drei Messpunkte benötigt. " +
         "Es stehen jedoch nur " + anzahlMesspunkte + " Messpunkte zur Verfügung! " +
         "Fügen Sie bitte weitere Messpunkte hinzu.";
      ApplicationRuntimeException applicationRuntimeException = new ApplicationRuntimeException(jsfMeldung);
      
      // Das vorzeitige Verlassen dieser Methode wird protokolliert.
      Startpunktbestimmung.logger.throwing("Startpunktbestimmung", "Startpunktbestimmung", applicationRuntimeException);
      
      throw applicationRuntimeException;
      }
   
   // Die Messpunkte werden initialisiert.
   this.messpunkte = messpunkte;
   
   // Das Feld der Messpunkte, welche für die Startpunktbestimmung verwendet werden, wird initialisiert.
   this.messpunkteStartpunktbestimmung = new Vector2D[3];
  
   // Der Rücksprung aus der Methode wird protokolliert.
   Startpunktbestimmung.logger.exiting("Startpunktbestimmung", "Startpunktbestimmung");
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
         "Berechnungsalgorithmus benötigt jedoch eine reguläre Matrix! Sortieren Sie bitte die eingegebenen " +
         "Messpunkte so um, dass in den ersten drei Zeilen andere Werte stehen.";
      ApplicationRuntimeException applicationRuntimeException = new ApplicationRuntimeException(jsfMeldung);
      
      // Das vorzeitige Verlassen dieser Methode wird protokolliert.
      Startpunktbestimmung.logger.throwing("Startpunktbestimmung", "startpunktBestimmen", applicationRuntimeException);
      
      throw applicationRuntimeException;
      }
   
   // Der Startpunkt wird aus der Läsung des linearen Gleichungssystems bestimmt.
   double xMittelpunkt = 0.5 * loesung.getEntry(1);
   double yMittelpunkt = 0.5 * loesung.getEntry(2);
   double radius = Math.sqrt(xMittelpunkt * xMittelpunkt + yMittelpunkt * yMittelpunkt - loesung.getEntry(0));
   
   // Der Startpunkt wird protokolliert.
   Startpunktbestimmung.logger.fine("x: " + xMittelpunkt);
   Startpunktbestimmung.logger.fine("y: " + yMittelpunkt);
   Startpunktbestimmung.logger.fine("r: " + radius);
   
   Startpunktbestimmung.logger.exiting("Startpunktbestimmung", "startpunktBestimmen");
   
   // Der Startpunkt wird zurückgegeben.
   return new double[]{xMittelpunkt, yMittelpunkt, radius};
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode fügt die beiden Messpunkte mit der größten bzw. kleinsten y-Komponente zu dem Feld der Messpunkte
 * hinzu, die zur Startpunktbestimmung verwendet werden.
 */
private void minUndMaxMesspunktHinzufuegen()
   {
   // Der Einsprung in die Methode wird protokolliert.
   Startpunktbestimmung.logger.entering("Startpunktbestimmung", "minUndMaxMesspunktHinzufuegen");
   
   // Die gesuchten Messpunkte.
   Vector2D messpunktMinRealteil = null;
   Vector2D messpunktMaxRealteil = null;
   
   // Die y-Komponente eines Messwertes.
   double realteil = Double.NaN;
   
   // Der minimale und der maximale Wert der y-Komponente.
   double minRealteil = Double.MAX_VALUE;
   double maxRealteil = Double.MIN_VALUE;
   
   // Eine Schleife über alle Messpunkte.
   for (Vector2D messpunkt : this.messpunkte)
      {
      // Die y-Komponente des Messpunktes wird gelesen.
      realteil = messpunkt.getY();
      
      // Falls die y-Komponente des Messpunkts größer als die bisherige maximale y-Komponente ist, ...
      if (realteil > maxRealteil)
         {
         // Die maximale y-Komponente eines Messpunkts wird aktualisiert.  
         maxRealteil = realteil;
         
         // Der Messpunkt mit der maximalen y-Komponente wird aktualisiert.
         messpunktMaxRealteil = messpunkt;
         }
      
      
      // Falls die y-Komponente des Messpunkts kleiner als die bisherige minimale y-Komponente ist, ...
      if (realteil < minRealteil)
         {
         // Die minimale y-Komponente eines Messpunkts wird aktualisiert.
         minRealteil = realteil;
         
         // Der Messpunkt mit der minimalen y-Komponente wird aktualisiert.
         messpunktMinRealteil = messpunkt;
         }
      }
   
   // Die gesuchten Messpunkte werden protokolliert.
   Startpunktbestimmung.logger.finer("Messpunkt mit maximalem Realteil: " + messpunktMaxRealteil.toString());
   Startpunktbestimmung.logger.finer("Messpunkt mit minimalem Realteil: " + messpunktMinRealteil.toString());
   
   // Der gesuchten Messpunkte werden zu dem Feld der Messpunkte, welche für die Startpunktbestimmung benutzt werden, 
   // hinzugefügt.
   this.messpunkteStartpunktbestimmung[0] = messpunktMaxRealteil;
   this.messpunkteStartpunktbestimmung[1] = messpunktMinRealteil;
   
   // Der Mittelwert des Wertebereichs der y-Komponenten aller Messpunkte wird bestimmt.
   double mittelwert = 0.5 * (minRealteil + maxRealteil);
   
   // Der Messpunkt, dessen y-Komponente am nähesten zum Mittelwert des Wertebereichs der y-Komponenten aller 
   // Messpunkte liegt, wird bestimmt. Dieser Messpunkt wird dann zu dem Feld der Messpunkte, die zur 
   // Startpunktbestimmung verwendet werden, hinzugefügt.
   this.mittlerenMesspunktHinzufuegen(mittelwert);
   
   // Der Rücksprung aus der Methode wird protokolliert.
   Startpunktbestimmung.logger.exiting("Startpunktbestimmung", "minUndMaxMesspunktHinzufuegen");
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode bestimmt den Messpunkt, dessen y-Komponente am nähesten zum Mittelwert des Wertebereichs der 
 * y-Komponenten aller Messpunkte liegt. Dieser Messpunkt wird dann zu dem Feld der Messpunkte, die zur 
 * Startpunktbestimmung verwendet werden, hinzugefügt.
 * 
 * @param mittelwert Der Mittelwert des Wertebereichs der y-Komponenten aller Messpunkte
 */
private void mittlerenMesspunktHinzufuegen(double mittelwert)
   {
   // Der Einsprung in die Methode wird protokolliert.
   Startpunktbestimmung.logger.entering("Startpunktbestimmung", "mittlerenMesspunktBestimmen");
   
   // Der gesuchte Messpunkt.
   Vector2D messpunktMittlererRealteil = null;
   
   // Der minimale Abstand eines Messpunkts zum Mittelwert.
   double minAbstandZumMittelwert = Double.MAX_VALUE;
   
   // Der Abstand eines Messpunkts zum Mittelwert.
   double abstandZumMittelwert = Double.NaN;
   
   // Eine Schleife über alle Messpunkte.
   for (Vector2D messpunkt : this.messpunkte)
      {
      // Der Abstand des aktuellen Messpunkts zum Mittelwert wird berechnet.
      abstandZumMittelwert = Math.abs(messpunkt.getY() - mittelwert);
      
      // Falls der Abstand des aktuellen Messpunkts zum Mittelwert kleiner als das bisherige Minimum ist, ...
      if (abstandZumMittelwert < minAbstandZumMittelwert)
         {
         // Der minimale Abstand eines Messpunkts zum Mittelwert wird aktualisiert.
         minAbstandZumMittelwert = abstandZumMittelwert;
         
         // Der gesuchte Punkt wird aktualisiert.
         messpunktMittlererRealteil = messpunkt;
         }
      }
   
   // Der gesuchte Messpunkt wird protokolliert.
   Startpunktbestimmung.logger.finer("Messpunkt mit mittlerem Realteil: " + messpunktMittlererRealteil.toString());
   
   // Der gesuchte Messpunkt wird zu dem Feld der Messpunkte, welche für die Startpunktbestimmung benutzt werden, 
   // hinzugefügt.
   this.messpunkteStartpunktbestimmung[2] = messpunktMittlererRealteil;
   
   // Der Rücksprung aus der Methode wird protokolliert.
   Startpunktbestimmung.logger.exiting("Startpunktbestimmung", "mittlerenMesspunktBestimmen");
   }
}
