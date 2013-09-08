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
 * Diese Klasse konvertiert Punkte in Pixelangaben zur Darstellung in einem Koordinatensystem.
 *
 * @author Th. K. Walter
 */
public class PunktPixelKonverter
{
/**
 * Der Umrechnungsfaktor von der gemessenen Größe in Pixel.
 */
private double skalierungsfaktor;

/** 
 * Der Ursprung des Koordinatensystems in Pixel-Koordinaten.
 */
private Vector2D ursprungInPixel;

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(PunktPixelKonverter.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert den Konverter.
 * 
 * @param wertebereich Der Wertebereich, den das Koordinatensystem darstellen können muss.
 * @param anzahlPixelX Die maximale Anzahl von Pixeln in x-Richtung.
 * @param anzahlPixelY Die maximale Anzahl von Pixeln in y-Richtung.
 */
public PunktPixelKonverter(Wertebereich wertebereich, double anzahlPixelX, double anzahlPixelY)
   {
   // Der Einsprung in den Konstruktor wird protokolliert.
   PunktPixelKonverter.logger.entering("PunktPixelKonverter", "PunktPixelKonverter");
   
   // Die maximalen Skalierungsfaktoren für die einzelnen Dimensionen werden berechnet.
   double skalierungsfaktorX = anzahlPixelX / (wertebereich.getMaxX() - wertebereich.getMinX());
   double skalierungsfaktorY = anzahlPixelY / (wertebereich.getMaxY() - wertebereich.getMinY());
   
   // Falls der Skalierungsfaktor in x-Richtung kleiner ist als der in y-Richtung, ...
   if (skalierungsfaktorX < skalierungsfaktorY)
      {
      // Der Skalierungsfaktor in x-Richtung wird als Skalierungsfaktor für das Koordinatensystem verwendet.
      this.skalierungsfaktor = skalierungsfaktorX;
      
      // Der Ursprung in x-Richtung in Pixeln wird berechnet.
      double ursprungPixelX = this.skalierungsfaktor * Math.abs(wertebereich.getMinX());
      
      // Die Anzahl der in y-Richtung benötigten Pixel wird berechnet.
      double benoetigtePixelY = (wertebereich.getMaxY() - wertebereich.getMinY()) * this.skalierungsfaktor;
      
      // Die Anzahl der überzähligen Pixel in y-Richtung wird berechnet.
      double ueberzaehligePixelY = anzahlPixelY - benoetigtePixelY;
      
      // Der Ursprung in y-Richtung in Pixeln wird berechnet.
      double ursprungPixelY = 0.5 * ueberzaehligePixelY + wertebereich.getMaxY() * this.skalierungsfaktor;
      
      // Der Ursprung in Pixelkoordinaten wird initialisiert.
      this.ursprungInPixel = new Vector2D(ursprungPixelX, ursprungPixelY);
      }
   
   // Falls der Skalierungsfaktor in x-Richtung nicht kleiner ist als der in y-Richtung, ...
   else
      {
      // Der Skalierungsfaktor in y-Richtung wird als Skalierungsfaktor für das Koordinatensystem verwendet.
      this.skalierungsfaktor = skalierungsfaktorY;
      
      // Der Ursprung in y-Richtung in Pixeln wird berechnet.
      double ursprungPixelY = this.skalierungsfaktor * wertebereich.getMaxY();
      
      // Die Anzahl der in x-Richtung benötigten Pixel wird berechnet.
      double benoetigtePixelX = (wertebereich.getMaxX() - wertebereich.getMinX()) * this.skalierungsfaktor;
      
      // Die Anzahl der überzähligen Pixel in x-Richtung wird berechnet.
      double ueberzaehligePixelX = anzahlPixelX - benoetigtePixelX;
      
      // Der Ursprung in x-Richtung in Pixeln wird berechnet.
      double ursprungPixelX = 0.5 * ueberzaehligePixelX + Math.abs(wertebereich.getMinX()) * this.skalierungsfaktor;
      
      // Der Ursprung in Pixelkoordinaten wird initialisiert.
      this.ursprungInPixel = new Vector2D(ursprungPixelX, ursprungPixelY);
      } 
   
   // Der Skalierungsfaktor wird protokolliert.
   PunktPixelKonverter.logger.fine("Skalierungsfaktor: " + this.skalierungsfaktor);
   
   // Der Urpsrung in Pixelkoordinaten wird protokolliert.
   PunktPixelKonverter.logger.fine("Ursprung in Pixelkoordinaten: " + this.ursprungInPixel);
  
   // Der Rücksprung aus dem Konstruktor wird protokolliert.
   PunktPixelKonverter.logger.exiting("PunktPixelKonverter", "PunktPixelKonverter");
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet zu einem Punkt die Koordinaten in Pixeln.
 * 
 * @param punkt Der Punkt.
 * 
 * @return Die Koordinaten in Pixeln.
 */
public Vector2D getPixelKoordinaten(Vector2D punkt)
   {
   // Der Einsprung in den Konstruktor wird protokolliert.
   PunktPixelKonverter.logger.entering("PunktPixelKonverter", "getPixelKoordinaten");
   
   // Die Koordinaten in Pixeln werden berechnet.
   double xPixel = this.ursprungInPixel.getX() + punkt.getX() * this.skalierungsfaktor;
   double yPixel = this.ursprungInPixel.getY() - punkt.getY() * this.skalierungsfaktor;
   
   // Der Rücksprung aus dem Konstruktor wird protokolliert.
   PunktPixelKonverter.logger.exiting("PunktPixelKonverter", "getPixelKoordinaten");
   
   // Die Koordinaten in Pixeln werden zurückgegeben.
   return new Vector2D(xPixel, yPixel);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet zu einer realen Länge die entsprechende Länge in Pixeln.
 * 
 * @param realeLaenge Die reale Länge.
 * 
 * @return Die Länge in Pixeln.
 */
public double getLaengeInPixeln(double realeLaenge)
   {
   // Der Parameter dieser Methode wird protokolliert.
   PunktPixelKonverter.logger.fine("realeLaenge: " + realeLaenge);
   
   // Der Länge in Pixeln wird berechnet.
   double laengeInPixeln = realeLaenge * this.skalierungsfaktor;
   
   // Die Länge in Pixeln wird protokolliert.
   PunktPixelKonverter.logger.fine("laengeInPixeln: " + laengeInPixeln);
   
   // Die Länge in Pixeln wird zurückgegeben.
   return laengeInPixeln;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   return this.skalierungsfaktor + "; " + this.ursprungInPixel.toString();
   }
}
