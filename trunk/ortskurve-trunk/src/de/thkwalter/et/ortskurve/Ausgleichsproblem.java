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

import java.util.Locale;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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
 * Kreises zu einer vorgegebenen Menge von Messpunkten.
 *
 * @author Th. Walter
 */
@RequestScoped
@ManagedBean
public class Ausgleichsproblem
{
/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(Ausgleichsproblem.class.getName());

/**
 * Dieses Feld enthält die Messpunkte.
 */
private Vector2D[] messpunkte;

/**
 * Die x-Komponente des Mittelpunkts des Kreises.
 */
private double mx;

/**
 * Die y-Komponente des Mittelpunkts des Kreises.
 */
private double my;

/**
 * Der Radius des Kreises.
 */
private double r;

/**
 * Dieses Flag zeigt an, ob die Lösung des Ausgleichsproblems angezeigt werden soll. 
 */
private boolean loesungAnzeigen;

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
   
   try
      {
      // Ein Objekt der Klasse, die den Lösungsalgorithmus kapselt wird erzeugt. Es wird festgelegt, dass der 
      // Algorithmus QR-Zerlegung zur Lösung des linearen Problems benutzen soll. Die Lösung des Ausgleichsproblems gilt 
      // als gefunden, wenn sich kein Residuum zwischen zwei Iterationsschritten um mehr als 1 Prozent ändert.
      GaussNewtonOptimizer gaussNewtonOptimizer = 
            new GaussNewtonOptimizer(false, new SimpleVectorValueChecker(0.01, -1.0));
      
      // Das Feld für die Gewichte der einzelnen Punkte wird deklariert.
      double[] gewichte = new double[this.messpunkte.length];
      
      // Das folgende Feld enthält die Werte, welche die Modellgleichungen ergeben sollten.
      double[] zielwerte = new double[this.messpunkte.length];
      
      // In der folgenden Schleife über alle Messpunkte werden die oben deklarierten Felder initialisiert.
      for (int i = 0; i < this.messpunkte.length; i++)
         {
         // Allen Messpunkten wird das gleiche Gewicht zugewiesen.
         gewichte[i] = 1.0;
         
         // Die Zielwerte haben alle den Wert Null, da die Kreisgleichung für alle Punkte diesen Wert ergeben sollte.
         zielwerte[i] = 0.0;
         }
      
      // Die Startparameter werden bestimmt.
      Startpunktbestimmung startpunktbestimmung = new Startpunktbestimmung(this.messpunkte);
      double[] startpunkt = startpunktbestimmung.startpunktBestimmen();
      
      // Ein Objekte der Klasse, welche die Modellgleichungen (der Kreisgleichungen) repräsentiert, wird erzeugt.
      Modellgleichungen strommesswerte = new Modellgleichungen(this.messpunkte);
      
      // Ein Objekt der Klasse, welche die Jakobi-Matrix der Modellgleichungen (der Kreisgleichungen) repräsentiert, 
      // wird erzeugt.
      Jakobimatrix jakobiMatrix = new Jakobimatrix(this.messpunkte);
      
      // Das Ausgleichsproblem wird gelöst, wobei höchstens 200 Iterationsschritte durchgeführt werden.
      PointVectorValuePair endParameter = gaussNewtonOptimizer.optimize(new Weight(gewichte), new Target(zielwerte), 
         new  InitialGuess(startpunkt), new MaxEval(200), new ModelFunction(strommesswerte), 
         new ModelFunctionJacobian(jakobiMatrix));
      
      // Die Kreisparameter werden gelesen und protokolliert.
      this.mx = endParameter.getPoint()[0];
      this.my = endParameter.getPoint()[1];
      this.r = endParameter.getPoint()[2];
      Ausgleichsproblem.logger.fine("Mittelpunkt: (" + this.mx + ", " + this.my + "); Radius: " + this.r);
      
      // Das Flag wird auf true gesetzt, so dass die Lösung des Ausgleichsproblems angezeigt wird. 
      this.loesungAnzeigen = true;
      }
   
   // Falls eine Ausnahme geworfen worden ist, wird diese in eine FacesMessage umgewandelt.
   catch (Exception exception)
      {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
         exception.getMessage(), ""));
      }
   
   Ausgleichsproblem.logger.exiting("Ausgleichsproblem", "problemLoesen");
   
   // Die Startseite wird wieder angezeigt.
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode übergibt den Inhalt des Texteingabefeldes (nach der Konvertierung in ein Feld von 
 * {@link Vector2D}-Messpunkten) an die Geschäftslogik.
 * 
 * @param messpunkte Das Feld von {@link Vector2D}-Objekten.
 */
public void setMesspunkte(Vector2D[] messpunkte)
   {      
   this.messpunkte = messpunkte;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode übergibt die Messpunkte (nach der Konvertierung in eine Zeichenkette) an das Texteingabefeld.
 * 
 * @param messpunkte Das Feld von {@link Vector2D}-Objekten.
 */
public Vector2D[] getMesspunkte()
   {
   return this.messpunkte;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die x-Komponente des Mittelpunkts des Kreises zurück.
 * 
 * @return Die x-Komponente des Mittelpunkts des Kreises.
 */
public String getMx()
   {
   return String.format(Locale.US, "%1$.2f", this.mx);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die y-Komponente des Mittelpunkts des Kreises zurück.
 * 
 * @return Die y-Komponente des Mittelpunkts des Kreises.
 */
public String getMy()
   {
   return String.format(Locale.US, "%1$.2f", this.my);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Radius des Kreises zurück.
 * 
 * @return Der Radius des Kreises.
 */
public String getR()
   {
   return String.format(Locale.US, "%1$.2f", this.r);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt zurück, ob die Lösung des Ausgleichsproblems angezeigt werden soll.
 * 
 * @return <tt>true</tt>, falls die Lösung des Ausgleichsproblems angezeigt werden soll; <tt>false</tt>, falls die 
 * Lösung des Ausgleichsproblems nicht angezeigt werden soll.
 */
public boolean isLoesungAnzeigen()
   {
   return this.loesungAnzeigen;
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
}
