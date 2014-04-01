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
package de.thkwalter.et.ersatzschaltbild;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import de.thkwalter.et.ortskurve.Ortskurve;
import de.thkwalter.jsf.ApplicationRuntimeException;

/**
 * Diese Klasse repräsentiert den Controller der Ersatzschaltbildberechnung.
 *
 * @author Th. K. Walter
 */
@RequestScoped
@ManagedBean
public class ErsatzschaltbildController
{
/**
 * Das Datenmodell der Ersatzschaltbildberechnung
 */
@ManagedProperty(value="#{ersatzschaltbildModell}")
private ErsatzschaltbildModell ersatzschaltbildModell;

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(ErsatzschaltbildController.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet das Ersatzschaltbild.
 * 
 * @return Die Zeichenkette <tt>null</tt>
 */
public String ersatzschaltbildBerechnen()
   {
   try
      {
      // Die Daten des Frontend-Modells werden protokolliert.
      ErsatzschaltbildController.logger.info(this.ersatzschaltbildModell.toString());
      
      // Das Ersatzschaltbild wird berechnet.
      this.ersatzschaltbildBerechnenIntern();
      
      // Die Daten des Frontend-Modells werden protokolliert.
      ErsatzschaltbildController.logger.info(this.ersatzschaltbildModell.toString());
      }
   
   // Falls eine Ausnahme geworfen worden ist, wird diese behandelt.
   catch (ApplicationRuntimeException exception)
      {
      // Die Referenz auf das Ersatzschaltbild wird auf null gesetzt, um die Ergebnisanzeige zu unterdrücken.
      this.ersatzschaltbildModell.setErsatzschaltbild(null);
      
      // Eine Fehlermeldung für die Oberfläche wird erstellt.
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
         exception.getMessage(), ""));
      
      // Der Nachrichtentext der Ausnahme wird protokolliert.
      ErsatzschaltbildController.logger.severe(exception.getMessage());
      }
   
   // Es wird wieder zur aufrufenden Seite weitergeleitet.
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Das Ersatzschaltbild wird berechnet.
 */
private void ersatzschaltbildBerechnenIntern()
   {
   // Die Ortskurve der Impedanz (in Ohm) wird berechnet.
   Ortskurve ortskurveImpedanz = OrtskurveImpedanz.ortskurveImpedanzBerechnen(
      this.ersatzschaltbildModell.getOrtskurve(), this.ersatzschaltbildModell.getU_LL(), 
      this.ersatzschaltbildModell.getSchaltungstyp());
   
   // Die Impedanzortskurve wird protokolliert.
   ErsatzschaltbildController.logger.fine("Ortskurve Impedanz: " + ortskurveImpedanz.toString());
   
   // Das Ersatzschaltbild wird erzeugt.
   Ersatzschaltbild ersatzschaltbild = new Ersatzschaltbild();
   
   // Der Ständerwicklungswiderstand (in Ohm) wird berechnet und im Ersatzschaltbild gespeichert.
   ersatzschaltbild.setR1(ortskurveImpedanz.getMittelpunktOrtskurve().getY());
   
   // Die Reaktanz x1 (in Ohm) wird berechnet und im Ersatzschaltbild gespeichert.
   double x1 = -ortskurveImpedanz.getMittelpunktOrtskurve().getX() + ortskurveImpedanz.getRadiusOrtskurve();
   ersatzschaltbild.setX1(x1);
   
   // Die Streureaktanz (in Ohm) wird berechnet und im Ersatzschaltbild gespeichert.
   double x_k = 0.5 * x1 * x1 / ortskurveImpedanz.getRadiusOrtskurve() - x1;
   ersatzschaltbild.setX_k(x_k);
   
   // Die synchrone Drehzahl (in Hz) wird berechnet.
   double n_s = this.ersatzschaltbildModell.getF1() / this.ersatzschaltbildModell.getP();
   
   // Der auf den Ständer bezogene Läuferwicklungswiderstand (in Ohm) wird berechnet und im Ersatzschaltbild 
   // gespeichert.
   Laeuferwicklungswiderstand r2Berechnen = new Laeuferwicklungswiderstand(
      this.ersatzschaltbildModell.getBetriebspunkte(), this.ersatzschaltbildModell.getOrtskurve(), 
      this.ersatzschaltbildModell.getU_LL(), this.ersatzschaltbildModell.getSchaltungstyp(), 
      ersatzschaltbild.getR1(), x1, x_k, n_s);
   ersatzschaltbild.setR2_strich(r2Berechnen.getR2());
   
   // Das Ersatzschaltbild wird zum Frontend-Modell der Ersatzschaltbildberechnung hinzugefügt.
   ersatzschaltbildModell.setErsatzschaltbild(ersatzschaltbild);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert das Datenmodell der Ersatzschaltbildberechnung in diesem Controller.
 * 
 * @param ersatzschaltbildModell Das Datenmodell der Ersatzschaltbildberechnung
 */
public void setErsatzschaltbildModell(ErsatzschaltbildModell ersatzschaltbildModell)
   {
   // Das Datenmodell der Ersatzschaltbildberechnung wird gespeichert.
   this.ersatzschaltbildModell = ersatzschaltbildModell;
   }
}
