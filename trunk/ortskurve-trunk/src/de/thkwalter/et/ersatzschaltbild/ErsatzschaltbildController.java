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
      }
   
   // Falls eine Ausnahme geworfen worden ist, wird diese in eine FacesMessage umgewandelt.
   catch (ApplicationRuntimeException exception)
      {
      // Die Referenz auf das Ersatzschaltbild wird auf null gesetzt, um die Ergebnisanzeige zu unterdrücken.
      this.ersatzschaltbildModell.setErsatzschaltbild(null);
      
      // Eine Fehlermeldung für die Oberfläche wird erstellt.
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
         exception.getMessage(), ""));
      
      // Der Nachrichtentext der Ausnahme wird protokolliert.
      ErsatzschaltbildController.logger.info(exception.getMessage());
      }
   
   // Es wird wieder zur Seite ersatzschaltbild.xhtml weitergeleitet.
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Das Ersatzschaltbild wird berechnet.
 */
private void ersatzschaltbildBerechnenIntern()
   {
   // Die Ortskurve der Impedanz wird berechnet.
   Ortskurve ortskurveImpedanz = OrtskurveImpedanz.ortskurveImpedanzBerechnen(
      this.ersatzschaltbildModell.getOrtskurve(), this.ersatzschaltbildModell.getU_LL(), 
      this.ersatzschaltbildModell.getSchaltungstyp());
   
   // Die Repräsentation des Ersatzschaltbilds wird erzeugt.
   Ersatzschaltbild ersatzschaltbild = new Ersatzschaltbild();
   ersatzschaltbild.setR1(ortskurveImpedanz.getMittelpunktOrtskurve().getY());
   
   // Das Ersatzschaltbild wird zum Frontend-Modell hinzugefügt.
   ersatzschaltbildModell.setErsatzschaltbild(ersatzschaltbild);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert das übergebene Datenmodell des Ersatzschaltbildes in diesem Controller.
 * 
 * @param ersatzschaltbildModell Das Datenmodell des Ersatzschaltbildes
 */
public void setErsatzschaltbildModell(ErsatzschaltbildModell ersatzschaltbildModell)
   {
   // Das Datenmodell der Ortskurve wird gespeichert.
   this.ersatzschaltbildModell = ersatzschaltbildModell;
   }
}
