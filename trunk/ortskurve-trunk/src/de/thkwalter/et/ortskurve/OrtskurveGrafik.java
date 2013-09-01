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
 * Diese Klasse repräsentiert die Grafikdarstellung der Ortskurve.
 *
 * @author Th. K. Walter
 */
public class OrtskurveGrafik
{
/**
 * Die Pixelkoordinaten des Mittelpunkts der Ortskurve.
 */
private Vector2D mittelpunktInPixeln;

/**
 * Der Radius der Ortskurve in Pixeln.
 */
private double radiusInPixeln;

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(OrtskurveGrafik.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor berechnet die Grafikdarstellung der Ortskurve.
 */
public OrtskurveGrafik(Vector2D realerMittelpunkt, double realerRadius, PunktPixelKonverter punktPixelKonverter)
   {
   // Die an den Konstruktor übergebenen Parameter werden protokolliert.
   OrtskurveGrafik.logger.fine("realerMittelpunkt: " + realerMittelpunkt);
   OrtskurveGrafik.logger.fine("realerRadius: " + realerRadius);
   OrtskurveGrafik.logger.fine("punktPixelKonverter: " + punktPixelKonverter.toString());
   
   // Die Pixelkoordinaten des Mittelpunkts und der Radius in Pixeln werden berechnet.
   this.radiusInPixeln = punktPixelKonverter.getLaengeInPixeln(realerRadius);
   this.mittelpunktInPixeln = punktPixelKonverter.getPixelKoordinaten(realerMittelpunkt);
   
   // Die Pixelkoordinaten des Mittelpunkts und der Radius in Pixeln werden protokolliert.
   OrtskurveGrafik.logger.fine(this.toString());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Pixelkoordinaten des Mittelpunkts der Ortskurve zurück.
 * 
 * @return Die Pixelkoordinaten des Mittelpunkts der Ortskurve.
 */
public Vector2D getMittelpunktInPixeln()
   {
   OrtskurveGrafik.logger.finer("mittelpunktInPixeln: " + this.mittelpunktInPixeln);
   
   return this.mittelpunktInPixeln;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Radius der Ortskurve in Pixeln zurück.
 * 
 * @return Der Radius der Ortskurve in Pixeln.
 */
public double getRadiusInPixeln()
   {
   OrtskurveGrafik.logger.finer("radiusInPixeln: " + this.radiusInPixeln);
   
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
   // Die Zeichenkette, die das Objekt repräsentiert, wird zusammengebaut.
   StringBuilder stringBuilder = new StringBuilder();
   stringBuilder.append("mittelPunktInPixeln: ").append(this.mittelpunktInPixeln).append("; radiusInPixeln: ").
      append(this.radiusInPixeln);
   
   // Die Zeichenkette, die das Objekt repräsentiert, wird zurückgegeben.
   return stringBuilder.toString();
   }
}
