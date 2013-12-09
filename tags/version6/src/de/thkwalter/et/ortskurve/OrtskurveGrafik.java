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

import java.io.Serializable;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.thkwalter.koordinatensystem.PunktPixelKonverter;

/**
 * Diese Klasse repräsentiert die Grafikdarstellung der Ortskurve.
 *
 * @author Th. K. Walter
 * @version 1.0
 */
public class OrtskurveGrafik implements Serializable
{
/**
 * Die Pixelkoordinaten des Mittelpunkts der Ortskurve.
 */
private Vector2D mittelpunktInPixeln;

/**
 * Der Radius der Ortskurve in Pixeln.
 */
private double radiusInPixeln;

/**
 * Die Serialisierungsnummmer
 */
private static final long serialVersionUID = -4732030078188550953L;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor berechnet die Grafikdarstellung der Ortskurve.
 * 
 * @param ortskurve Die Ortskurve
 * @param punktPixelKonverter Ein Konverter, der Punkte in Pixelkoordinaten konveriert
 */
public OrtskurveGrafik(Ortskurve ortskurve, PunktPixelKonverter punktPixelKonverter)
   {
   // Die Pixelkoordinaten des Mittelpunkts und der Radius in Pixeln werden berechnet.
   this.radiusInPixeln = punktPixelKonverter.getLaengeInPixeln(ortskurve.getRadiusOrtskurve());
   this.mittelpunktInPixeln = punktPixelKonverter.getPixelKoordinaten(ortskurve.getMittelpunktOrtskurve());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Pixelkoordinaten des Mittelpunkts der Ortskurve zurück.
 * 
 * @return Die Pixelkoordinaten des Mittelpunkts der Ortskurve
 */
public Vector2D getMittelpunktInPixeln()
   {
   // Die Pixelkoordinaten des Mittelpunkts werden zurückgegeben.
   return this.mittelpunktInPixeln;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Radius der Ortskurve in Pixeln zurück.
 * 
 * @return Der Radius der Ortskurve in Pixeln
 */
public double getRadiusInPixeln()
   {
   // Der Radius in Pixeln wird zurückgegeben.
   return this.radiusInPixeln;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   // Die Zeichenkette, welche die Grafikdarstellung der Ortskurve repräsentiert, wird zusammengebaut.
   StringBuilder stringBuilder = new StringBuilder();
   stringBuilder.append("mittelPunktInPixeln: ").append(this.mittelpunktInPixeln).append("; radiusInPixeln: ").
      append(this.radiusInPixeln);
   
   // Die Zeichenkette, welche die Grafikdarstellung der Ortskurve repräsentiert, wird zurückgegeben.
   return stringBuilder.toString();
   }
}
