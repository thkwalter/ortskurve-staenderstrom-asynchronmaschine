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

import java.util.HashSet;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.thkwalter.jsf.ApplicationRuntimeException;

/**
 * Diese Klasse ist der Controller der Ortskurvenberechnung.
 *
 * @author Th. Walter
 */
@RequestScoped
@ManagedBean(name="ausgleichsproblem")
public class OrtskurveController
{
/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(OrtskurveController.class.getName());

/**
 * Das Frontend-Modell der Ortskurvenberechnung.
 */
@ManagedProperty(value="#{ortskurveModell}")
private OrtskurveModell ortskurveModell;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode löst das Ausgleichsproblem und berechnet den Mittelpunkt und den Radius des Kreises.
 * 
 * @return <tt>null</tt>
 */
public String problemLoesen()
   {
   try
      {   
      // Die Daten des Frontend-Modells werden protokolliert.
      OrtskurveController.logger.info(this.ortskurveModell.toString());
      
      this.ortskurveModell.setOptimalerAusgleichskreis(null);
      
      // Die Messpunkte werden aus dem Frontend-Modell gelesen.
      Vector2D[] messpunkte = this.ortskurveModell.getMesspunkte();
      
      // Die eingegebenen Messpunkte werden validiert.
      this.messpunkteValidieren(messpunkte);
      
      // Die Startparameter werden bestimmt.
      double[] startpunkt = Startpunktbestimmung.startpunktBerechnen(messpunkte);
      
      // Die Ortskurve wird bestimmt.    
      Ortskurve ortskurve = this.ortskurveBestimmen(messpunkte, startpunkt);
      
      // Die Ortskurve wird im Datenmodell gespeichert.
      this.ortskurveModell.setOrtskurve(ortskurve);
      
      // Falls der Mittelpunkt einen negativen Realteil besitzt, wird das Ausgleichsproblem noch einmal unter der Rand-
      // bedingung gelöst, dass der Mittelpunkt auf der imaginären Achse liegt.
      if (ortskurve.getMittelpunktOrtskurve().getY() < 0)
         {
         // Der Startpunkt für die Lösung des 2d-Ausgleichsproblems wird festgelegt.
         double[] startpunkt2d = 
            new double[]{ortskurve.getMittelpunktOrtskurve().getX(), ortskurve.getRadiusOrtskurve()};
         
         // Das 2d-Ausgleichsproblem word gelöst.
         Ausgleichsproblem ausgleichsproblem = new Ausgleichsproblem(messpunkte);
         Ortskurve ortskurve2d = 
            ausgleichsproblem.ausgleichsproblemLoesen(startpunkt2d, Ausgleichsproblemtyp.ORTSKURVE_2d);
         
         // Die berechnete Ortskurve wird protokolliert.
         OrtskurveController.logger.info(ortskurve2d.toString());
         
         // Der optimale Ausgleichskreis mit negativem Realteil wird umgespeichert.
         this.ortskurveModell.setOptimalerAusgleichskreis(this.ortskurveModell.getOrtskurve());
         
         // Die Ortskurve mit Mittelpunkt auf der Imaginärachse wird im Frontend-Modell gespeichert.
         this.ortskurveModell.setOrtskurve(ortskurve2d);
         }
      
      // Die Daten der Grafik der Ortskurve werden berechnet.
      this.ortskurveModell.grafikdatenBerechnen();
      }
   
   // Falls eine Ausnahme geworfen worden ist, wird diese in eine FacesMessage umgewandelt.
   catch (ApplicationRuntimeException exception)
      {
      // Die Ortskurve wird zurückgesetzt, um ihre Anzeige zu vermeiden.
      this.ortskurveModell.setOrtskurve(null);
      
      // Eine Fehlermeldung für die Oberfläche wird erstellt.
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
         exception.getMessage(), ""));
      
      // Der Nachrichtentext der Ausnahme wird protokolliert.
      OrtskurveController.logger.info(exception.getMessage());
      }
   
   // Die Startseite wird wieder angezeigt.
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert das übergebene Datenmodell der Ortskurve in diesem Objekt.
 * 
 * @param ortskurveModell Das Datenmodell der Ortskurve
 */
public void setOrtskurveModell(OrtskurveModell ortskurveModell)
   {
   // Das Datenmodell der Ortskurve wird gespeichert.
   this.ortskurveModell = ortskurveModell;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt das Datenmodell der Ortskurvenberechnung zurück.
 * 
 * @return Das Datenmodell der Ortskurvenberechnung
 */
public OrtskurveModell getOrtskurveModell()
   {
   // Das Datenmodell der Ortskurvenberechnung wird zurückgegeben.
   return this.ortskurveModell;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode bestimmt die Ortskurve.
 * 
 * @param messpunkte Die Messpunkte
 * @param startpunkt Das erste Element ist die x-Komponente des Mittelpunkts, das zweite Element die y-Komponente des 
 * Mittelpunkts, das dritte Element der Radius.
 * 
 * @return Die Ortskurve
 */
private Ortskurve ortskurveBestimmen(Vector2D[] messpunkte, double[] startpunkt)
   {
   // Die Referenz auf die Ortskurve wird deklariert.
   Ortskurve ortskurve = null;
   
   // Falls nur drei Messpunkte eingegeben worden sind, entspricht der Startpunkt der Lösung.
   if (messpunkte.length == 3)
      {
      ortskurve = new Ortskurve(new Vector2D(startpunkt[0], startpunkt[1]), startpunkt[2]);
      }
   
   // Falls mehr als drei Messpunkte eingegeben worden sind, muss die Lösung durch eine nicht-lineare 
   // Ausgleichsrechnung bestimmt werden.
   else
      {
      Ausgleichsproblem ausgleichsproblem = new Ausgleichsproblem(messpunkte);
      ortskurve = ausgleichsproblem.ausgleichsproblemLoesen(startpunkt, Ausgleichsproblemtyp.ORTSKURVE_3d);
      }
   
   // Die berechnete Ortskurve wird protokolliert.
   OrtskurveController.logger.info(ortskurve.toString());
   
   // Die berechnete Ortskurve wird zurückgegeben.
   return ortskurve;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode validiert die eingegebenen Messpunkte. Falls die eingegebenen Messpunkte nicht valide sind, wird eine
 * Ausnahme geworfen.
 * 
 * @param messpunkte Die Messpunkte
 */
private void messpunkteValidieren(Vector2D[] messpunkte)
   {
   // Alle Messpunkte werden in ein HashSet eingefügt.
   HashSet<Vector2D> messpunktSet = new HashSet<Vector2D>();
   for (Vector2D messpunkt : messpunkte)
      {
      messpunktSet.add(messpunkt);
      }
   
   // Falls die Anzahl der Punkte im HashSet kleiner ist als die Anzahl der eingegebenen Messpunkte, so wurden 
   // Messpunkte doppelt eingegeben. 
   if (messpunktSet.size() < messpunkte.length)
      {
      // Die Fehlermeldung für den Entwickler wird erzeugt und protokolliert.
      String fehlermeldung = "Es wurden " + (messpunkte.length - messpunktSet.size()) + " Messpunkte doppelt " +
         " eingegeben!";
      OrtskurveController.logger.severe(fehlermeldung);
      
      // Die Zeichenkette für die Fehlermeldung wird deklariert.
      String jsfMeldung = "";
      
      // Falls nur ein Messpunkt doppelt eingegeben worden ist, ...
      if (messpunkte.length - messpunktSet.size() == 1)
         {
         // Die Zeichenkette für die Fehlermeldung wird festgelegt.
         jsfMeldung = "Sie haben einen Messpunkt doppelt eingegeben! Entfernen Sie bitte den doppelt eingegebenen " +
            "Messpunkt.";
         }
      
      // Falls mehrere Messpunkte doppelt eingegeben worden sind, ...
      else
         {
         // Die Zeichenkette für die Fehlermeldung wird festgelegt.
         jsfMeldung = "Sie haben " +  (messpunkte.length - messpunktSet.size()) + " Messpunkte " +
            "doppelt eingegeben! Entfernen Sie bitte die doppelt eingegebenen Messpunkte.";
         }
      
      // Die Ausnahme wird erzeugt und geworfen.         
      throw new ApplicationRuntimeException(jsfMeldung);
      }
   }
}
