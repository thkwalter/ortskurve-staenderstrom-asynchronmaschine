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

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Diese Klasse repräsentiert die Ortskurve.
 *
 * @author Th. K. Walter
 * @version 1.0
 */
public class Ortskurve
{
/**
 * Der Mittelpunkt der Ortskurve
 */
private Vector2D mittelpunktOrtskurve;

/**
 * Der Radius der Ortskurve
 */
private double radiusOrtskurve;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die übergebenen Parameter in diesem Objekt.
 * 
 * @param mittelpunktOrtskurve Der Mittelpunkt der Ortskurve
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
 * Diese Methode gibt den Mittelpunkt der Ortskurve zurück.
 * 
 * @return Der Mittelpunkt der Ortskurve
 */
public Vector2D getMittelpunktOrtskurve()
   {
   // Der Mittelpunkt der Ortskurve wird zurückgegeben.
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
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   // Die Zeichenkette, welche die Ortskurve repräsentiert, wird zusammengebaut.
   StringBuilder stringBuilder = new StringBuilder();
   stringBuilder.append("mittelpunktOrtskurve: ").append(this.mittelpunktOrtskurve).append("; radiusOrtskurve: ").
      append(this.radiusOrtskurve).append("; ");
   
   // Die Zeichenkette, welche die Ortskurve repräsentiert, wird zurückgegeben.
   return stringBuilder.toString();
   }
}
