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

import java.util.ArrayList;
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
 * Der berechnete Startpunkt, ein Feld aus drei double-Elementen. Das erste Element ist die x-Komponente des 
 * Mittelpunkts, das zweite Element die y-Komponente des Mittelpunkts, das dritte Element der Radius.
 */
private double[] startpunkt;

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
   
   // Die Messpunkte, die zur Startpunktbestimmung verwendet werden
   Vector2D[] messpunkteZurStartpunktbestimmung = null;
   
   // Falls nur drei Messpunkte existieren, werden diese zur Startpunktbestimmung verwendet.
   if (messpunkte.length == 3)
      {
      messpunkteZurStartpunktbestimmung = messpunkte;
      }
   
   // Falls mehr als drei Messpunkte existieren, wird bestimmt, welche davon zur Startpunktbestimmung verwendet werden.
   else
      {
      // Die Messpunkte, die zur Startpunktbestimmung verwendet werden, werden ausgewählt.
      messpunkteZurStartpunktbestimmung = this.messpunkteAuswaehlen(messpunkte);    
      }
   
   
   // Der Startpunkt wird bestimmt.
   this.startpunkt = this.startpunktBestimmen(messpunkteZurStartpunktbestimmung);
  
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
private double[] startpunktBestimmen(Vector2D[] messpunkteZurStartpunktbestimmung)
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
      x = messpunkteZurStartpunktbestimmung[i].getX();
      y = messpunkteZurStartpunktbestimmung[i].getY();
      
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
      String fehlermeldung = "Die Matrix aus den Punkten " + messpunkteZurStartpunktbestimmung[0] + ", " + 
         messpunkteZurStartpunktbestimmung[1] + " und " + messpunkteZurStartpunktbestimmung[2] + " ist singulär.";
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
 * Diese Methode bestimmt die Messpunkte, die zur Startpunktbestimmung verwendet werden. Das sind die beiden
 * Messpunkte mit dem größten bzw. kleinsten y-Komponente (Realteil des Stroms) und der Messpunkt, dessen y-Komponente 
 * am nähesten zum Mittelwert aus der größten und der kleinsten auftreteten y-Komponente liegt.
 */
private Vector2D[] messpunkteAuswaehlen(Vector2D[] messpunkte)
   {
   // Der Einsprung in die Methode wird protokolliert.
   Startpunktbestimmung.logger.entering("Startpunktbestimmung", "messpunkteAuswaehlen");
   
   // Zwei Listen mit den Messpunkten werden erstellt.
   ArrayList<XKomponenteMesspunkt> xListe = new ArrayList<XKomponenteMesspunkt>();
   ArrayList<YKomponenteMesspunkt> yListe = new ArrayList<YKomponenteMesspunkt>();
   
   // Die Listen werden mit den Messpunkten initialisiert.
   for (Vector2D messpunkt : messpunkte)
      {
      xListe.add(new XKomponenteMesspunkt(messpunkt));
      yListe.add(new YKomponenteMesspunkt(messpunkt));
      }
   
   // Die Messpunkte mit der größten und kleinsten y-Komponente.
   Vector2D messpunktMinRealteil = null;
   Vector2D messpunktMaxRealteil = null;
   
   // Die y-Komponente eines Messwertes.
   double realteil = Double.NaN;
   
   // Die kleinste und die größte y-Komponente.
   double minRealteil = Double.POSITIVE_INFINITY;
   double maxRealteil = Double.NEGATIVE_INFINITY;
   
   // Eine Schleife über alle Messpunkte.
   for (Vector2D messpunkt : messpunkte)
      {
      
      // Falls die y-Komponente des Messpunkts kleiner als die bisher kleinste y-Komponente ist, ...
      if (realteil < minRealteil)
         {
         // Die kleinste y-Komponente eines Messpunkts wird aktualisiert.
         minRealteil = realteil;
         
         // Der Messpunkt mit der kleinsten y-Komponente wird aktualisiert.
         messpunktMinRealteil = messpunkt;
         }
      }
   
   // Die Messpunkte mit der größten und der kleinsten y-Komponente werden protokolliert.
   Startpunktbestimmung.logger.info("Messpunkt mit maximalem Realteil: " + messpunktMaxRealteil.toString());
   Startpunktbestimmung.logger.info("Messpunkt mit minimalem Realteil: " + messpunktMinRealteil.toString());
   
   // Der Mittelwert aus der größten und der kleinsten y-Komponenten wird bestimmt.
   double mittelwert = 0.5 * (minRealteil + maxRealteil);
   
   // den Messpunkt, dessen y-Komponente (Realteil des Stroms) am nähesten zum Mittelwert aus der größten und der 
   // kleinsten auftreteten y-Komponente liegt, wird bestimmt.
   Vector2D mittlererMesspunkt = this.mittlerenMesspunktAuswaehlen(messpunkte, mittelwert);
   
   // Der Rücksprung aus der Methode wird protokolliert.
   Startpunktbestimmung.logger.exiting("Startpunktbestimmung", "messpunkteAuswaehlen");
   
   // Die Messpunkte, die zur Startpunktbestimmung verwendet werden, werden zurückgegeben.
   return new Vector2D[]{messpunktMaxRealteil, messpunktMinRealteil, mittlererMesspunkt};
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode bestimmt den Messpunkt, dessen y-Komponente (Realteil des Stroms) am nähesten zum Mittelwert aus der
 * größten und der kleinsten auftreteten y-Komponente liegt. 
 * 
 * @param messpunkte Das Feld der Messpunkte
 * @param mittelwert Der Mittelwert des Wertebereichs der y-Komponenten aller Messpunkte
 * 
 * @return Der Messpunkt, dessen y-Komponente (Realteil des Stroms) am nähesten zum Mittelwert aus der
 * größten und der kleinsten auftreteten y-Komponente liegt
 */
private Vector2D mittlerenMesspunktAuswaehlen(Vector2D[] messpunkte, double mittelwert)
   {
   // Der Einsprung in die Methode wird protokolliert.
   Startpunktbestimmung.logger.entering("Startpunktbestimmung", "mittlerenMesspunktAuswaehlen");
   
   // Der gesuchte Messpunkt.
   Vector2D messpunktMittlererRealteil = null;
   
   // Der minimale Abstand eines Messpunkts zum Mittelwert.
   double minAbstandZumMittelwert = Double.POSITIVE_INFINITY;
   
   // Der Abstand des aktuellen Messpunkts zum Mittelwert.
   double abstandZumMittelwert = Double.NaN;
   
   // Eine Schleife über alle Messpunkte.
   for (Vector2D messpunkt : messpunkte)
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
   Startpunktbestimmung.logger.info("Messpunkt mit mittlerem Realteil: " + messpunktMittlererRealteil.toString());
   
   // Der Rücksprung aus der Methode wird protokolliert.
   Startpunktbestimmung.logger.exiting("Startpunktbestimmung", "mittlerenMesspunktAuswaehlen");
   
   // Der gesuchte Messpunkt wird zurückgegeben.
   return messpunktMittlererRealteil;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode bestimmt den Messpunkt mit dem größten Wert der x- bzw. y-Komponente.
 * 
 * @param liste Eine Liste der x- bzw. y-Komponenten der Messpunkte
 * 
 * @return Der Messpunkt mit dem größten Wert der x- bzw. y-Komponente
 */
public Vector2D maxMesspunktBestimmen(ArrayList<XKomponenteMesspunkt> liste)
   {
   // Der Einsprung in die Methode wird protokolliert.
   Startpunktbestimmung.logger.entering("Startpunktbestimmung", "maxMesspunktBestimmen");
   
   // Die Messpunkte mit der größten x-Komponente.
   XKomponenteMesspunkt maxXKomponenteMesspunkt = null;
   
   // Die x-Komponente eines Messwertes.
   double xWert = Double.NaN;
   
   // Die größte bisher gefundene x-Komponente.
   double maxXWert = Double.NEGATIVE_INFINITY;
   
   // Eine Schleife über alle Messpunkte in der Liste
   for (XKomponenteMesspunkt xKomponenteMesspunkt : liste)
      {
      // Die x-Komponente des Messpunktes wird gelesen.
      xWert = xKomponenteMesspunkt.getWert();
      
      // Falls die x-Komponente des Messpunkts größer als die größte bisher gefundene x-Komponente, ...
      if (xWert > maxXWert)
         {
         // Die größte bisher gefundene x-Komponente wird aktualisiert.  
         maxXWert = xWert;
         
         // Der Messpunkt mit der größten y-Komponente wird aktualisiert.
         maxXKomponenteMesspunkt = xKomponenteMesspunkt;
         } 
      }
   
   // Der Messpunkt mit der größten x-Komponente wird aus der Liste entfernt.
   liste.remove(maxXKomponenteMesspunkt);
   
   // Der Rücksprung aus der Methode wird protokolliert.
   Startpunktbestimmung.logger.exiting("Startpunktbestimmung", "maxMesspunktBestimmen");
   
   // Der Messpunkt mit der größten x-Komponente wird zurückgegeben.
   return maxXKomponenteMesspunkt.getMesspunkt();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den berechneten Startpunkt zurück.
 * 
 * @return Ein Feld aus drei double-Elementen. Das erste Element ist die x-Komponente des Mittelpunkts, das zweite 
 * Element die y-Komponente des Mittelpunkts, das dritte Element der Radius.
 */
public double[] getStartpunkt()
   {
   return this.startpunkt;
   }
}
