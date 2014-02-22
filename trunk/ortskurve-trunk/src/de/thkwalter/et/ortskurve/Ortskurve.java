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

/**
 * Diese Klasse repräsentiert die Ortskurve.
 *
 * @author Th. K. Walter
 * @version 1.0
 */
public class Ortskurve implements Serializable
{
/**
 * Der Mittelpunkt der Ortskurve in gedrehter Darstellung.
 */
private Vector2D mittelpunktOrtskurve;

/**
 * Der Radius der Ortskurve
 */
private double radiusOrtskurve;

/**
 * Die Serialisierungsnummer
 */
private static final long serialVersionUID = 7836381800872312359L;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Kontruktor erzeugt eine Ortskurve.
 * 
 * @param mittelpunktOrtskurve Der Mittelpunkt der Ortskurve im gedrehten Koordinatensystem.
 * @param radiusOrtskurve Der Radius der Ortskurve
 */
public Ortskurve(Vector2D mittelpunktOrtskurve, double radiusOrtskurve)
   {
   // Die Attribute werden initialisiert.
   this.mittelpunktOrtskurve = mittelpunktOrtskurve;
   this.radiusOrtskurve = radiusOrtskurve;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Mittelpunkt der Ortskurve in gedrehter Darstellung zurück.
 * 
 * @return Der Mittelpunkt der Ortskurve in gedrehter Darstellung
 */
public Vector2D getMittelpunktOrtskurve()
   {
   // Der Mittelpunkt der Ortskurve wird in gedrehter Darstellung zurückgegeben.
   return this.mittelpunktOrtskurve;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Radius der Ortskurve zurück.
 * 
 * @return Der Radius der Ortskurve
 */
public double getRadiusOrtskurve()
   {
   // Der Radius der Ortskurve wird zurückgegeben.
   return this.radiusOrtskurve;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet eine skalierte Ortskurve.
 * 
 * @param skalierungsfaktor Der Faktor, mit dem die Ortskurve, die durch dieses Objekt repräsentiert wird, 
 * skaliert wird.
 *
 * @return Die skalierte Ortskurve
 */
public Ortskurve skalierteOrtskurveBerechnen(double skalierungsfaktor)
   {
   // Die skalierten Werte werden berechnet.
   double radius_skaliert = this.radiusOrtskurve / skalierungsfaktor;
   double mx_skaliert = this.getMittelpunktOrtskurve().getX() / skalierungsfaktor;
   double my_skaliert = this.getMittelpunktOrtskurve().getY() / skalierungsfaktor;
   
   // Die skalierte Ortskurve wird erstellt und zurückgegeben.
   return new Ortskurve(new Vector2D(mx_skaliert, my_skaliert), radius_skaliert);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   // Die Zeichenkette, welche die Ortskurve repräsentiert, wird erzeugt.
   StringBuilder builder = new StringBuilder();
   
   // Die Zeichenkette, welche die Ortskurve repräsentiert, wird zusammengebaut.
   builder.append("Ortskurve [mittelpunktOrtskurve=").append(mittelpunktOrtskurve).append(", radiusOrtskurve=")
      .append(radiusOrtskurve).append("]");
   
   // Die Zeichenkette, welche die Ortskurve repräsentiert, wird zurückgegeben. 
   return builder.toString();
   }
}
