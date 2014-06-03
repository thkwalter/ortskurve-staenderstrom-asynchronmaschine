/**
 *  Copyright 2014 Th. K. Walter, Nürnberg.
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
package de.thkwalter.jsf.converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Dieser Konverter konvertiert <tt>null</tt> in <tt>0.0</tt>.
 * 
 * @author Th. K. Walter
 */
@FacesConverter("NullZeroConverter")
public class NullZeroConverter implements Converter
{
/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(NullZeroConverter.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/* 
 * Diese Methode konvertiert einen {@link String} in einen {@link Double}, wobei eine leere Zeichenkette auf den Wert 
 * <tt>null</tt> abgebildet wird.
 * 
 * @param facesContext Das Kontext-Objekt
 * @param uiComponent Die UI-Komponente, welche die Quelle der Zeichenkette ist.
 * @param doubleString Die Zeichenkette, die konvertiert werden soll.
 * 
 * @return Die Zeichenkette als Gleitkommazahl
 * 
 * @see Converter#getAsObject(FacesContext, UIComponent, String)
 */
@Override
public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String doubleString)
   {
   // Der Wert der zurückgegeben wird, wird mit null initialisiert.
   Double doubleValue = null;
   
   // Falls der Benutzer einen Wert eingegeben hat, ...
   if (doubleString != null && doubleString.length() > 0)
      {
      try
         {
         // Die Zeichenkette wird in eine Gleitkommazahl umgewandelt.
         doubleValue = Double.parseDouble(doubleString);
         }
      
      // Falls sich die Zeichenkette nicht in eine Gleitkommazahl umwandeln lässt.
      catch (NumberFormatException e)
         {
         // Die Fehlermeldung für den Entwickler wird erzeugt und protokolliert.
         String fehlermeldung = "Die eingegebene Zeichenkette ("+ doubleString + ") ist keine reelle Zahl!";
         NullZeroConverter.logger.log(Level.SEVERE, fehlermeldung);
         
         // Die Meldung für die Oberfläche und eine Ausnahme werden erzeugt und mit der Fehlermeldung für den Benutzer
         // initialisiert.
         String jsfMeldung = "Der eingegebene Wert ist keine reelle Zahl! Geben Sie bitte eine reelle Zahl ein.";
         facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, jsfMeldung, ""));
         ConverterException converterException = new ConverterException(jsfMeldung);
         
         // Die ConverterException wird geworfen.
         throw converterException;
         }
      }
   
   // Die Gleitkommazahl oder der Wert null wird zurückgegeben.
   return doubleValue;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode konvertiert einen {@link Double} in einen {@link String}, wobei der Wert <tt>null</tt> auf die leere
 * Zeichenkette abgebildet wird.
 * 
 * @return Die Gleitkommazahl als Zeichenkette
 * 
 * @see Converter#getAsString(FacesContext, UIComponent, Object)
 */
@Override
public String getAsString(FacesContext arg0, UIComponent arg1, Object doubleValue)
   {
   // Die Gleitkommazahl wird in eine Zeichenkette konvertiert und zurückgegeben. Ist die Gleitkommazahl null, so wird
   // eine leere Zeichenkette zurückgegeben.
   return doubleValue != null ? ((Double) doubleValue).toString() : "";
   }
}
