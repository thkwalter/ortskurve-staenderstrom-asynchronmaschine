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
package de.thkwalter.jsf.converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Dieser Konverter konvertiert zwischen einer Zeichenketten im CSV-Format und einem Feld von {@link Vector2D}-Objekten.
 * Zwei Datensätze der Zeichenkette sind jeweils durch einen Zeilenumbruch getrennt. Jeder Datensatz besteht aus zwei
 * Datenfeldern, die durch ein Komma getrennt sind. Jedes Datenfeld besteht aus einem double-Wert.
 *
 * @author Th. K. Walter
 */
@FacesConverter("CSVConverter")
public class CSVConverter implements Converter
{
/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(CSVConverter.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode konvertiert eine Zeichenketten im CSV-Format in ein Feld von {@link Vector2D}-Objekten. Zwei Datensätze 
 * der Zeichenkette sind jeweils durch einen Zeilenumbruch getrennt. Jeder Datensatz besteht aus zwei Datenfeldern, die 
 * durch ein Komma getrennt sind. Jedes Datenfeld besteht aus einem double-Wert.
 *  
 * @param facesContext Das Kontext-Objekt
 * @param uiComponent Die UI-Komponente, welche die Quelle der Zeichenkette ist.
 * @param eingabe Die Zeichenkette, die konvertiert werden soll.
 * 
 * @return Ein Feld von {@link Vector2D}-Objekten.
 *  
 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, 
 * java.lang.String)
 */
@Override
public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String eingabe)
   {
   CSVConverter.logger.entering("CSVConverter", "getAsObject");
   
   // Das Feld, das die Vektoren speichern soll, wird deklariert.
   Vector2D[] vektoren = null;
   
   try
      {
      // Die Zeichenkette wird in Zeilen zerlegt.
      String[] zeilen = eingabe.trim().split("\n");
      
      // Das Feld, das die Vektoren speichern soll, wird erzeugt.
      vektoren = new Vector2D[zeilen.length];
      
      // Einige Variablen und Referenzen werden deklariert.
      String[] datenfelder = null;
      double x = Double.NaN;
      double y = Double.NaN;
      
      // In dieser Schleife werden die einzelnen Zeilen in Vektoren umgewandelt.
      for (int i = 0; i < zeilen.length; i++)
         {
         // Die einzelnen Zeilen werden in Datenfelder zerlegt.
         datenfelder = zeilen[i].split(",");
         
         // Die Zeichenketten der Datenfelder werden in double-Werte umgewandelt.
         x = Double.parseDouble(datenfelder[0].trim());
         y = Double.parseDouble(datenfelder[1].trim());
         
         // Ein Vektor wird erzeugt und dem Feld der Vektoren hinzugefügt.
         vektoren[i] = new Vector2D(x, y);
         
         // Der Vektor wird protokolliert.
         CSVConverter.logger.fine(vektoren[i].toString());
         }
      }
   
   // Falls eine Ausnahme geworfen worden ist, wird diese behandelt.
   catch (Exception exception)
      {
      // Die Fehlermeldung wird erstellt und protokolliert.
      String fehlermeldung = "Die eingegebene Zeichenkette ("+ eingabe 
         + ") besitzt nicht das richtige Format! Fehlertext: " + exception.getMessage();
      CSVConverter.logger.log(Level.SEVERE, fehlermeldung);
      
      // Eine Fehlermeldung für die Oberfläche wird erstellt.
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
          "die Messpunkte nicht im richtigen Format eingegeben worden sind", exception.getMessage()));
      
      // Eine ConverterException wird geworfen.
      throw new ConverterException(exception);
      }
   
   CSVConverter.logger.exiting("CSVConverter", "getAsObject");
   
   // Das Feld, das die Vektoren speichert, wird zurückgegeben.
   return vektoren;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode konvertiert ein Feld von {@link Vector2D}-Objekten in eine Zeichenketten im CSV-Format. Zwei Datensätze 
 * der Zeichenkette sind jeweils durch einen Zeilenumbruch getrennt. Jeder Datensatz besteht aus zwei Datenfeldern, die 
 * durch ein Komma getrennt sind. Jedes Datenfeld besteht aus einem double-Wert.
 *  
 * @param facesContext Das Kontext-Objekt
 * @param uiComponent Die UI-Komponente, welche das Ziel der Zeichenkette ist.
 * @param vektoren Das übergebene Feld von {@link Vector2D}-Objekten.
 * 
 * @return Eine Zeichenketten im CSV-Format.
 * 
 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, 
 * java.lang.Object)
 */
@Override
public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object vektoren)
   {
   CSVConverter.logger.entering("CSVConverter", "getAsString");
   
   // Der StringBuffer wird mit einer leeren Zeichenkette initialisiert.
   StringBuffer stringBuffer = new StringBuffer("");
   
   try
      {
      // Falls das Feld nicht null ist, wird es konvertiert.
      if (vektoren != null)
         {
         // Das übergebene Object wird in ein Feld von Vector2D-Objekten umgewandelt.
         Vector2D[] punkte = (Vector2D[]) vektoren;
         
         // In dieser Schleife werden alle Vektoren zu einer Zeichenkette verknüpft.
         for (Vector2D punkt : punkte)
            {
            stringBuffer.append(punkt.getX()).append(",").append(punkt.getY()).append("\n");
            }
         }
      }
   
   // Falls eine Ausnahme geworfen worden ist, wird diese behandelt.
   catch (Exception exception)
      {
      // Die Fehlermeldung wird erstellt und protokolliert.
      String fehlermeldung = "Das Feld der Vektoren kann nicht konvertiert werden! Fehlertext: " + 
         exception.getMessage();
      CSVConverter.logger.log(Level.SEVERE, fehlermeldung);
            
      // Eine ConverterException wird geworfen.
      throw new ConverterException(exception.getMessage());
      }
   
   // Die zusammengebaute Zeichenkette wird protokolliert.
   String ausgabe = stringBuffer.toString();
   CSVConverter.logger.fine(ausgabe);
   
   CSVConverter.logger.exiting("CSVConverter", "getAsString");
   
   // Die zusammengebaute Zeichenkette wird zurückgegeben.
   return stringBuffer.toString();
   }

}
