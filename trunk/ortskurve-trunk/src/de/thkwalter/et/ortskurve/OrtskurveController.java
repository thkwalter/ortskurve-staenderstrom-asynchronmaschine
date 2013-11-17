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
import javax.servlet.http.HttpSession;

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
      
      // Die Messpunkte werden aus dem Frontend-Modell gelesen.
      Vector2D[] messpunkte = this.ortskurveModell.getMesspunkte();
      
      // Die Variablen für die Ortskurve werden deklariert.
      double mx = Double.NaN;
      double my = Double.NaN;
      double r = Double.NaN;
      
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
      
      // Die Startparameter werden bestimmt.
      Startpunktbestimmung startpunktbestimmung = new Startpunktbestimmung(messpunkte);
      double[] startpunkt = startpunktbestimmung.getStartpunkt();
      
      // Falls nur drei Messpunkte eingegeben worden sind, entspricht der Startpunkt der Lösung.
      if (messpunkte.length == 3)
         {
         mx = startpunkt[0];
         my = startpunkt[1];
         r = startpunkt[2];
         }
      
      // Falls mehr als drei Messpunkte eingegeben worden sind, muss die Lösung durch eine nicht-lineare 
      // Ausgleichsrechnung bestimmt werden.
      else
         {
         // Ein Objekt der Klasse, die den Lösungsalgorithmus kapselt wird erzeugt. Es wird festgelegt, dass der 
         // Algorithmus QR-Zerlegung zur Lösung des linearen Problems benutzen soll. Die Lösung des Ausgleichsproblems  
         // gilt als gefunden, wenn sich kein Residuum zwischen zwei Iterationsschritten um mehr als 1 Prozent ändert.
         GaussNewtonOptimizer gaussNewtonOptimizer = 
               new GaussNewtonOptimizer(false, new SimpleVectorValueChecker(0.01, -1.0));
         
         // Das Feld für die Gewichte der einzelnen Punkte wird deklariert.
         double[] gewichte = new double[messpunkte.length];
         
         // Das folgende Feld enthält die Werte, welche die Modellgleichungen ergeben sollten.
         double[] zielwerte = new double[messpunkte.length];
         
         // In der folgenden Schleife über alle Messpunkte werden die oben deklarierten Felder initialisiert.
         for (int i = 0; i < messpunkte.length; i++)
            {
            // Allen Messpunkten wird das gleiche Gewicht zugewiesen.
            gewichte[i] = 1.0;
            
            // Die Zielwerte haben alle den Wert Null, da die Kreisgleichung für alle Punkte diesen Wert ergeben sollte.
            zielwerte[i] = 0.0;
            }
         
         // Ein Objekte der Klasse, welche die Modellgleichungen (der Kreisgleichungen) repräsentiert, wird erzeugt.
         Modellgleichungen strommesswerte = new Modellgleichungen(messpunkte);
         
         // Ein Objekt der Klasse, welche die Jakobi-Matrix der Modellgleichungen (der Kreisgleichungen) repräsentiert, 
         // wird erzeugt.
         Jakobimatrix jakobiMatrix = new Jakobimatrix(messpunkte);
         
         // Das Ausgleichsproblem wird gelöst, wobei höchstens 200 Iterationsschritte durchgeführt werden.
         PointVectorValuePair endParameter = null;
         try
            {
            endParameter = gaussNewtonOptimizer.optimize(new Weight(gewichte), new Target(zielwerte), 
               new  InitialGuess(startpunkt), new MaxEval(200), new ModelFunction(strommesswerte), 
               new ModelFunctionJacobian(jakobiMatrix));
            }
         catch (TooManyEvaluationsException e)
            {
            // Die Fehlermeldung für den Entwickler wird erzeugt und protokolliert.
            String fehlermeldung = "Der Gauss-Newton-Algorithmus konvergiert nicht!";
            OrtskurveController.logger.severe(fehlermeldung);
            
            // Die Ausnahme wird erzeugt und mit der Fehlermeldung für den Benutzer initialisiert.
            String jsfMeldung = "Der Gauss-Newton-Algorithmus zur Berechnung der Ortskurve konvergiert nicht! " +
               "Überprüfen Sie bitte, ob die eingegebenen Punkte annähernd auf einem Kreis liegen.";
            ApplicationRuntimeException applicationRuntimeException = new ApplicationRuntimeException(jsfMeldung);
            
            throw applicationRuntimeException;
            }
         
         // Die Lösung wird gelesen.
         mx = endParameter.getPoint()[0];
         my = endParameter.getPoint()[1];
         r = endParameter.getPoint()[2];
         }
      
      // Falls der Mittelpunkt einen negativen Realteil besitzt, wird das Ausgleichsproblem noch einmal unter der Rand-
      // bedingung gelöst, dass der Mittelpunkt auf der imaginären Achse liegt.
      if (my < 0)
         {
         this.problem2dLoesen();
         }
      
      // Die Lösung wird protokolliert.
      OrtskurveController.logger.info("Mittelpunkt: (" + mx + ", " + my + "); Radius: " + r);
      
      // Die Ortskurve wird erstellt und im Datenmodell gespeichert.
      Ortskurve ortskurve = new Ortskurve(new Vector2D(mx, my), r);
      this.ortskurveModell.setOrtskurve(ortskurve);
      
      // Die Daten der Grafik der Ortskurve werden berechnet.
      this.ortskurveModell.grafikdatenBerechnen();
      
      // Das Flag wird auf true gesetzt, so dass die Lösung des Ausgleichsproblems angezeigt wird. 
      HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
      session.setAttribute("ortskurveAnzeigen", "true"); 
      }
   
   // Falls eine Ausnahme geworfen worden ist, wird diese in eine FacesMessage umgewandelt.
   catch (ApplicationRuntimeException exception)
      {
      // Das Flag wird auf true gesetzt, so dass die Lösung des Ausgleichsproblems angezeigt wird. 
      HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
      session.setAttribute("ortskurveAnzeigen", "false"); 
      
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
 * Diese Methode bestimmt die x-Komponente des Mittelpunkts und den Radius unter der Bedingung, dass die y-Komponente
 * gleich 0 ist.
 * 
 * @return Die berechnete Ortskurve. Der Mittelpunkt liegt auf der x-Achse.
 */
private Ortskurve problem2dLoesen()
   {
   // Die Ortskurve der 2d-Berechnung wird erzeugt.
   Ortskurve ortskurve2d = new Ortskurve(new Vector2D(2.0 ,0.0), 1.0);
   
   // Die berechnete Ortskurve wird zurückgegeben.
   return ortskurve2d;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt zurück, ob Meldungen angezeigt werden sollen.
 * 
 * @return <tt>true</tt>, falls Meldungen angezeigt werden sollen; <tt>false</tt>, falls die Meldungen nicht angezeigt 
 * werden sollen.
 */
public boolean isMeldungenAnzeigen()
   {
   return FacesContext.getCurrentInstance().getMessageList(null).size() > 0;
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
}
