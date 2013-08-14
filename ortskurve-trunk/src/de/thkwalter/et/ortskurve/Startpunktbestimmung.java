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
   Startpunktbestimmung.logger.info("x: " + xMittelpunkt);
   Startpunktbestimmung.logger.info("y: " + yMittelpunkt);
   Startpunktbestimmung.logger.info("r: " + radius);
   
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
   
   // Die Messpunkte mit der größten und kleinsten x-Komponente werden bestimmt.
   Vector2D maxXMesspunkt = this.maxMesspunktBestimmen(xListe);
   Vector2D minXMesspunkt = this.minMesspunktBestimmen(xListe);
   
   // Die Messpunkte mit der größten und kleinsten y-Komponente werden bestimmt.
   Vector2D maxYMesspunkt = this.maxMesspunktBestimmen(yListe);
   Vector2D minYMesspunkt = this.minMesspunktBestimmen(yListe);
   
   // Die Messpunkte, die bei der Startpunktbestimmung verwendet werden, werden deklariert.
   Vector2D maxMesspunkt = null;
   Vector2D minMesspunkt = null;
   Vector2D mittlererMesspunkt = null;
   
   // Falls der Wertebereich der x-Komponente der Messpunkte größer ist als der Wertebereich der y-Komponente, ...
   if (maxXMesspunkt.getX() - minXMesspunkt.getX() > maxYMesspunkt.getY() - minYMesspunkt.getY())
      {
      // Die Messpunkte mit der größten und kleinsten x-Kompomnente werden bei der Startpunktbestimmung verwendet.
      maxMesspunkt = maxXMesspunkt;
      minMesspunkt = minXMesspunkt;
      mittlererMesspunkt = 
         this.mittlerenMesspunktBestimmen(xListe, 0.5 * (maxXMesspunkt.getX() + minXMesspunkt.getX()));
      }
   
   // Falls der Wertebereich der x-Komponente der Messpunkte nicht größer ist als der Wertebereich der y-Komponente, ...
   else
      {
      // Die Messpunkte mit der größten und kleinsten y-Kompomnente werden bei der Startpunktbestimmung verwendet.
      maxMesspunkt = maxYMesspunkt;
      minMesspunkt = minYMesspunkt;
      mittlererMesspunkt = 
         this.mittlerenMesspunktBestimmen(yListe, 0.5 * (maxYMesspunkt.getY() + minYMesspunkt.getY()));
      }
   
   // Die Messpunkte, die für die Startpunktbestimmung verwendet werden, werden protokolliert.
   Startpunktbestimmung.logger.info("Messpunkt mit größter Komponente: " + maxMesspunkt.toString());
   Startpunktbestimmung.logger.info("Messpunkt mit kleinster Komponente: " + minMesspunkt.toString());
   Startpunktbestimmung.logger.info("Messpunkt mit mittlerer Komponente: " + mittlererMesspunkt.toString());
   
   // Der Rücksprung aus der Methode wird protokolliert.
   Startpunktbestimmung.logger.exiting("Startpunktbestimmung", "messpunkteAuswaehlen");
   
   // Die Messpunkte, die zur Startpunktbestimmung verwendet werden, werden zurückgegeben.
   return new Vector2D[]{maxMesspunkt, minMesspunkt, mittlererMesspunkt};
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode bestimmt den Messpunkt, dessen x- bzw. y-Komponente am nähesten zum Mittelwert aus der größten und der 
 * kleinsten auftreteten x- bzw. y-Komponente liegt. 
 * 
 * @param messpunkte Eine Liste der x- bzw. y-Komponenten der Messpunkte
 * @param mittelwert Der Mittelwert aus der größten und der kleinsten auftreteten x- bzw. y-Komponente
 * 
 * @return Der Messpunkt, dessen x- bzw. y-Komponente am nähesten zum Mittelwert aus der größten und der 
 * kleinsten auftreteten x- bzw. y-Komponente liegt
 */
private Vector2D mittlerenMesspunktBestimmen(ArrayList<? extends KomponenteMesspunkt> liste, double mittelwert)
   {
   // Der Einsprung in die Methode wird protokolliert.
   Startpunktbestimmung.logger.entering("Startpunktbestimmung", "mittlerenMesspunktBestimmen");
   
   // Der gesuchte Messpunkt.
   KomponenteMesspunkt mittlereKomponenteMesspunkt = null;
   
   // Der kleinste bisher gefundene Abstand der x- bzw. y-Komponente eines Messpunkts zum Mittelwert.
   double minAbstandZumMittelwert = Double.POSITIVE_INFINITY;
   
   // Der Abstand der x- bzw. y-Komponente eines Messpunkts zum Mittelwert.
   double abstandZumMittelwert = Double.NaN;
   
   // Eine Schleife über alle Messpunkte.
   for (KomponenteMesspunkt komponenteMesspunkt : liste)
      {
      // Der Abstand der x- bzw. y-Komponente eines Messpunkts zum Mittelwert wird berechnet.
      abstandZumMittelwert = Math.abs(komponenteMesspunkt.getWert() - mittelwert);
      
      // Falls der Abstand der x- bzw. y-Komponente des aktuellen Messpunkts zum Mittelwert kleiner als das bisherige 
      // Minimum ist, ...
      if (abstandZumMittelwert < minAbstandZumMittelwert)
         {
         // Der minimale Abstand der x- bzw. y-Komponente eines Messpunkts zum Mittelwert wird aktualisiert.
         minAbstandZumMittelwert = abstandZumMittelwert;
         
         // Der gesuchte Punkt wird aktualisiert.
         mittlereKomponenteMesspunkt = komponenteMesspunkt;
         }
      }
   
   // Der Rücksprung aus der Methode wird protokolliert.
   Startpunktbestimmung.logger.exiting("Startpunktbestimmung", "mittlerenMesspunktBestimmen");
   
   // Der gesuchte Messpunkt wird zurückgegeben.
   return mittlereKomponenteMesspunkt.getMesspunkt();
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
private Vector2D maxMesspunktBestimmen(ArrayList<? extends KomponenteMesspunkt> liste)
   {
   // Der Einsprung in die Methode wird protokolliert.
   Startpunktbestimmung.logger.entering("Startpunktbestimmung", "maxMesspunktBestimmen");
   
   // Die Messpunkte mit der größten x- bzw. y-Komponente.
   KomponenteMesspunkt maxKomponenteMesspunkt = null;
   
   // Die x- bzw. y-Komponente eines Messwertes.
   double wert = Double.NaN;
   
   // Die größte bisher gefundene x- bzw. y-Komponente.
   double maxWert = Double.NEGATIVE_INFINITY;
   
   // Eine Schleife über alle Messpunkte in der Liste
   for (KomponenteMesspunkt komponenteMesspunkt : liste)
      {
      // Die x- bzw. y-Komponente des Messpunktes wird gelesen.
      wert = komponenteMesspunkt.getWert();
      
      // Falls die x- bzw. y-Komponente des Messpunkts größer als die größte bisher gefundene x- bzw. y-Komponente, ...
      if (wert > maxWert)
         {
         // Die größte bisher gefundene x- bzw. y-Komponente wird aktualisiert.  
         maxWert = wert;
         
         // Der Messpunkt mit der größten x- bzw. y-Komponente wird aktualisiert.
         maxKomponenteMesspunkt = komponenteMesspunkt;
         } 
      }
   
   // Der Messpunkt mit der größten x- bzw. y-Komponente wird aus der Liste entfernt.
   liste.remove(maxKomponenteMesspunkt);
   
   // Der Rücksprung aus der Methode wird protokolliert.
   Startpunktbestimmung.logger.exiting("Startpunktbestimmung", "maxMesspunktBestimmen");
   
   // Der Messpunkt mit der größten x- bzw. y-Komponente wird zurückgegeben.
   return maxKomponenteMesspunkt.getMesspunkt();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode bestimmt den Messpunkt mit dem kleinsten Wert der x- bzw. y-Komponente.
 * 
 * @param liste Eine Liste der x- bzw. y-Komponenten der Messpunkte
 * 
 * @return Der Messpunkt mit dem kleinstenbgh Wert der x- bzw. y-Komponente
 */
private Vector2D minMesspunktBestimmen(ArrayList<? extends KomponenteMesspunkt> liste)
   {
   // Der Einsprung in die Methode wird protokolliert.
   Startpunktbestimmung.logger.entering("Startpunktbestimmung", "minMesspunktBestimmen");
   
   // Die Messpunkte mit der kleinsten x- bzw. y-Komponente.
   KomponenteMesspunkt minKomponenteMesspunkt = null;
   
   // Die x- bzw. y-Komponente eines Messwertes.
   double wert = Double.NaN;
   
   // Die kleinste bisher gefundene x- bzw. y-Komponente.
   double minWert = Double.POSITIVE_INFINITY;
   
   // Eine Schleife über alle Messpunkte in der Liste
   for (KomponenteMesspunkt komponenteMesspunkt : liste)
      {
      // Die x- bzw. y-Komponente des Messpunktes wird gelesen.
      wert = komponenteMesspunkt.getWert();
      
      // Falls die x- bzw. y-Komponente des Messpunkts kleiner als die kleinste bisher gefundene x- bzw. y-Komponente, ...
      if (wert < minWert)
         {
         // Die größte bisher gefundene x- bzw. y-Komponente wird aktualisiert.  
         minWert = wert;
         
         // Der Messpunkt mit der kleinsten x- bzw. y-Komponente wird aktualisiert.
         minKomponenteMesspunkt = komponenteMesspunkt;
         } 
      }
   
   // Der Messpunkt mit der kleinsten x- bzw. y-Komponente wird aus der Liste entfernt.
   liste.remove(minKomponenteMesspunkt);
   
   // Der Rücksprung aus der Methode wird protokolliert.
   Startpunktbestimmung.logger.exiting("Startpunktbestimmung", "minMesspunktBestimmen");
   
   // Der Messpunkt mit der größten x- bzw. y-Komponente wird zurückgegeben.
   return minKomponenteMesspunkt.getMesspunkt();
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
