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
package de.thkwalter.koordinatensystem;

import java.io.Serializable;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Diese Klasse repräsentiert die Koordinatenachsen.
 *
 * @author Th. K. Walter
 * @version 1.0
 */
public class Koordinatenachsen implements Serializable
{
/**
 * Der Startpunkt der x-Achse in Pixeln.
 */
private Vector2D startPunktXAchse;

/**
 * Der Endpunkt der x-Achse in Pixeln.
 */
private Vector2D endPunktXAchse;

/**
 * Der Startpunkt der y-Achse in Pixeln.
 */
private Vector2D startPunktYAchse;

/**
 * Der Endpunkt der y-Achse in Pixeln.
 */
private Vector2D endPunktYAchse;

/**
 * Die Serialisierungsnummer
 */
private static final long serialVersionUID = 8802946415396083197L;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor berechnet die Start- und Endpunkte der Koordinatenachsen in Pixeln.
 * 
 * @param wertebereich Der darszustellende Wertebereich
 * @param punktPixelKonverter Der Konverter zwischen Punkt- und Pixelkoordinaten
 */
public Koordinatenachsen(Wertebereich wertebereich, PunktPixelKonverter punktPixelKonverter)
   {
   // Die Anfangs- und Endpunkte der Koordinatenachsen werden berechnet.
   this.startPunktXAchse = punktPixelKonverter.getPixelKoordinaten(new Vector2D(wertebereich.getMinX(), 0.0));
   this.endPunktXAchse = punktPixelKonverter.getPixelKoordinaten(new Vector2D(wertebereich.getMaxX(), 0.0));
   this.startPunktYAchse = punktPixelKonverter.getPixelKoordinaten(new Vector2D(0.0, wertebereich.getMinY()));
   this.endPunktYAchse = punktPixelKonverter.getPixelKoordinaten(new Vector2D(0.0, wertebereich.getMaxY()));
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Startpunkt der x-Achse in Pixeln zurück.
 * 
 * @return Der Startpunkt der x-Achse in Pixeln.
 */
public Vector2D getStartPunktXAchse()
   {
   // Der Startpunkt der x-Achse in Pixeln wird zurückgegeben.
   return this.startPunktXAchse;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Endpunkt der x-Achse in Pixeln zurück.
 * 
 * @return Der Endpunkt der x-Achse in Pixeln.
 */
public Vector2D getEndPunktXAchse()
   {
   // Der Endpunkt der x-Achse in Pixeln wird zurückgegeben.
   return this.endPunktXAchse;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Startpunkt der y-Achse in Pixeln zurück.
 * 
 * @return Der Startpunkt der y-Achse in Pixeln.
 */
public Vector2D getStartPunktYAchse()
   {
   // Der Startpunkt der y-Achse in Pixeln wird zurückgegeben.
   return this.startPunktYAchse;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Endpunkt der y-Achse in Pixeln zurück.
 * 
 * @return Der Endpunkt der y-Achse in Pixeln.
 */
public Vector2D getEndPunktYAchse()
   {
   // Der Endpunkt der y-Achse in Pixeln wird zurückgegeben.
   return this.endPunktYAchse;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   // Die Zeichenkette, welche die Koordinatenachsen in Pixelkoordinaten repräsentiert, wird zusammengebaut.
   StringBuilder stringBuilder = new StringBuilder();
   stringBuilder.append("startPunktXAchse: ").append(this.startPunktXAchse);
   stringBuilder.append("endPunktXAchse: ").append(this.endPunktXAchse);
   stringBuilder.append("startPunktYAchse: ").append(this.startPunktYAchse);
   stringBuilder.append("endPunktYAchse: ").append(this.endPunktYAchse);

   // Die Zeichenkette, welche die Koordinatenachsen in Pixelkoordinaten repräsentiert, wird zurückgegeben.
   return stringBuilder.toString();
   }
}