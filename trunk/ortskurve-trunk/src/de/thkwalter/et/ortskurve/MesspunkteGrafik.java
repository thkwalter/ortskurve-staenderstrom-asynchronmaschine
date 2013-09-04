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

import java.util.logging.Logger;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.thkwalter.koordinatensystem.PunktPixelKonverter;

/**
 * Diese Klasse repräsentiert die Grafikdarstellung der Messpunkte.
 *
 * @author Th. K. Walter
 */
public class MesspunkteGrafik
{
/**
 * Die Messpunkte in Pixelkoordinaten.
 */
private Vector2D[] messpunkteInPixeln;

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(MesspunkteGrafik.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor berechnet die Grafikdarstellung der Messpunkte.
 * 
 * @param messpunkte Die Messpunkte
 * @param punktPixelKonverter Der Konverter, der reale Koordinaten in Pixelkoordinaten umrechnet
 */
public MesspunkteGrafik(Vector2D[] messpunkte, PunktPixelKonverter punktPixelKonverter)
   {
   // Die an den Konstruktor übergebenen Parameter werden protokolliert.
   MesspunkteGrafik.logger.fine("messpunkte: " + messpunkte.toString());
   MesspunkteGrafik.logger.fine("punktPixelKonverter: " + punktPixelKonverter.toString());
   
   // Das Feld für die Messpunkte in Pixelkoordinaten wird erzeugt.
   this.messpunkteInPixeln = new Vector2D[messpunkte.length];
   
   // In dieser Schleife werden die Pixelkoordinaten für alle Messpunkte berechnet.
   for (int i = 0; i < messpunkte.length; i++)
      {
      this.messpunkteInPixeln[i] = punktPixelKonverter.getPixelKoordinaten(messpunkte[i]);
      }
   
   // Die berechneten Messpunkte in Pixelkoordinaten werden protokolliert.
   MesspunkteGrafik.logger.fine("messpunkteInPixeln: " + this.messpunkteInPixeln);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Messpunkte in Pixelkoordinaten zurück.
 * 
 * @return Die Messpunkte in Pixelkoordinaten
 */
public Vector2D[] getMesspunkteInPixeln()
   { 
   // Die Messpunkte in Pixelkoordinaten werden protokolliert.
   MesspunkteGrafik.logger.finer("messpunkteInPixeln: " + this.messpunkteInPixeln);
   
   // Die Messpunkte in Pixelkoordinaten werden zurückgegeben.   
   return this.messpunkteInPixeln;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   // Die Zeichenkette, die dieses Objekt repräsentiert, wird zusammengebaut.
   StringBuilder stringBuilder = new StringBuilder();
   for (int i = 0; i < this.messpunkteInPixeln.length; i++)
      {
      stringBuilder.append(messpunkteInPixeln).append("; ");
      }

   // Die Zeichenkette, die dieses Objekt repräsentiert, wird zurückgegeben.
   return stringBuilder.toString();
   }
}
