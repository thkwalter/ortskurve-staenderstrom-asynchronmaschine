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

import java.util.logging.Logger;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Diese Klasse repräsentiert die Korrdinatenachsen.
 *
 * @author Th. K. Walter
 */
public class Koordinatenachsen
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

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(Koordinatenachsen.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor berechnet die Koordinatenachsen.
 * 
 * @param wertebereich Der darszustellende Wertebereich
 * @param punktPixelKonverter Der Konverter zwischen Punkt- und Pixelkoordinaten
 */
public Koordinatenachsen(Wertebereich wertebereich, PunktPixelKonverter punktPixelKonverter)
   {
   // Die Parameter, die an den Konstruktor übergeben werden, werden protokolliert.
   Koordinatenachsen.logger.fine(wertebereich.toString());
   Koordinatenachsen.logger.fine(punktPixelKonverter.toString());
   
   // Die Anfangs- und Endpunkte der Koordinatenachsen werden berechnet.
   this.startPunktXAchse = punktPixelKonverter.getPixelKoordinaten(new Vector2D(wertebereich.getMinX(), 0.0));
   this.endPunktXAchse = punktPixelKonverter.getPixelKoordinaten(new Vector2D(wertebereich.getMaxX(), 0.0));
   this.startPunktYAchse = punktPixelKonverter.getPixelKoordinaten(new Vector2D(0.0, wertebereich.getMinY()));
   this.endPunktYAchse = punktPixelKonverter.getPixelKoordinaten(new Vector2D(0.0, wertebereich.getMaxY()));
   
   // Der neu berechnete Zustand dieses Objekts wird protokolliert.
   Koordinatenachsen.logger.fine(this.toString());
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
   Koordinatenachsen.logger.finer(this.startPunktXAchse.toString());
   
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
   Koordinatenachsen.logger.finer(this.endPunktXAchse.toString());
   
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
   Koordinatenachsen.logger.finer(this.startPunktYAchse.toString());
   
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
   Koordinatenachsen.logger.finer(this.endPunktYAchse.toString());
   
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
   return this.startPunktXAchse + "; " + this.endPunktXAchse + "; " + this.startPunktYAchse + "; " + 
      this.endPunktYAchse;
   }
}