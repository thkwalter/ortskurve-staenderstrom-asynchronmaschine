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
package de.thkwalter.et.schlupfbezifferung;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Dieser Klasse steuert die Bestimmung der Schlupfbezifferung.
 * 
 * @author Th. K. Walter
 */
public class SchlupfbezifferungController
{
/**
 * Das Datenmodell der Schlupfbezifferungsbestimmung.
 */
private SchlupfbezifferungModell schlupfbezifferungModell;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode bestimmt die Schlupfbezifferung.
 */
private void schlupfbezifferungBestimmenIntern()
   {
   // Das Inversionszentrum (in A) wird berechnet und im Datenmodell der Schlupfbezifferungsbestimmung gespeichert.
   this.schlupfbezifferungModell.setInversionszentrum(this.inversionszentrumBerechnen());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet das Inversionszentrum (in A). Das Inversionszentrum liegt auf der gedrehten Ortskurve unter 
 * dem Polarwinkel von 315 Grad.
 * 
 * @return Das Inversionzentrum (in A)
 */
private Vector2D inversionszentrumBerechnen()
   {
   // Der Mittelpunkt der gedrehten Ortskurve (in A) wird gelesen.
   Vector2D mittelpunktOrtskurve = this.schlupfbezifferungModell.getOrtskurve().getMittelpunktOrtskurve();
   double m_x = mittelpunktOrtskurve.getX();
   double m_y = mittelpunktOrtskurve.getY();
   
   // Der Radius der Ortskurve (in A) wird gelesen.
   double r = this.schlupfbezifferungModell.getOrtskurve().getRadiusOrtskurve();
   
   // Das Inversionszentrum (in A) wird berechnet. 
   double x_0 = m_x + r * Math.cos(7 * Math.PI / 4);
   double y_0 = m_y + r * Math.sin(7 * Math.PI / 4);
   Vector2D inversionszentrum = new Vector2D(x_0, y_0);
   
   // Das Inversionszentrum (in A) wird zurückgegeben.
   return inversionszentrum;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet den Drehpunkt der Schlupfgerade (in A). Der Drehpunkt liegt auf der gedrehten Ortskurve 
 * senkrecht unter dem Mittelpunkt.
 * 
 * @return Der Drehpunkt der Schlupfgerade (in A)
 */
private Vector2D drehpunktSchlupfgeradeBerechnen()
   {
   // Der Mittelpunkt der gedrehten Ortskurve (in A) wird gelesen.
   Vector2D mittelpunktOrtskurve = this.schlupfbezifferungModell.getOrtskurve().getMittelpunktOrtskurve();
   
   // Der Radius der Ortskurve (in A) wird gelesen.
   double r = this.schlupfbezifferungModell.getOrtskurve().getRadiusOrtskurve();
   
   // Der Drehpunkt der Schupfgerade (in A) wird berechnet.
   Vector2D drehpunkt = mittelpunktOrtskurve.subtract(new Vector2D(0.0, r));
   
   // Der Drehpunkt der Schlupfgerade (in A) wird zurückgegeben.
   return drehpunkt;
   }
}
