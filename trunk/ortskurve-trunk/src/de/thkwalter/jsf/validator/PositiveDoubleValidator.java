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
package de.thkwalter.jsf.validator;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Dieser {@link Validator} überprüft, ob die eingegebene Zahl positiv ist.
 * 
 * @author Th. K. Walter
 */
@FacesValidator("positiveDoubleValidator")
public class PositiveDoubleValidator implements Validator
{
/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(PositiveDoubleValidator.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.validator.Validator#validate(FacesContext, UIComponent, java.lang.Object)
 */
public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException
   {
   // Der Wert wird in einen double umgewandelt.
   double wert = Double.parseDouble(object.toString());
   
   // Falls der Wert kleiner oder gleich Null ist
   if (wert <= 0)
      {
      // Das Label wird gelesen.
      String label = (String) uiComponent.getAttributes().get("label");
      
      // Die Fehlermeldung für den Entwickler wird erzeugt und protokolliert.
      String fehlermeldung = 
         "Der Wert der Größe " + label + " muss positiv sein! Die Größe hat jedoch den Wert: " + wert + ".";
      PositiveDoubleValidator.logger.log(Level.SEVERE, fehlermeldung);
      
      // Die Meldung für die Oberfläche und eine Ausnahme werden erzeugt und mit der Fehlermeldung für den Benutzer
      // initialisiert.
      String jsfMeldung = 
         "Der Wert der Größe " + label + " muss positiv sein! Geben Sie bitte einen positiven Wert ein.";
      FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, jsfMeldung, "");
      ValidatorException validatorException = new ValidatorException(facesMessage);
      
      // Die ValidatorException wird geworfen.
      throw validatorException;
      }
   }

}
