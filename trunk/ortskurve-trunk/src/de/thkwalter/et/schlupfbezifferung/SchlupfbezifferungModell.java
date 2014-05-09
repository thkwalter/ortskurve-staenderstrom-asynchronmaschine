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

import de.thkwalter.et.ortskurve.Ortskurve;

/**
 * Diese Klasse ist das Datenmodell der Schlupfbezifferungsbestimmung.
 * 
 * @author Th. K. Walter
 */
public class SchlupfbezifferungModell
{
/**
 * Die Ortskurve
 */
private Ortskurve ortskurve;

// ---------------------------------------------------------------------------------------------------------------------

/**
 * Der Inversionszentrum (in A)
 */
private Vector2D inversionszentrum;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt das Inversionszentrum (in A) zurück.
 * 
 * @return Das Inversionszentrum (in A)
 */
public Vector2D getInversionszentrum()
   {
   // Das Inversionszentrum (in A) wird zurückgegeben.
   return this.inversionszentrum;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert das Inversionszenrum (in A) in diesem Datenmodell.
 * 
 * @param inversionszentrum Das Inversionszentrum (in A)
 */
public void setInversionszentrum(Vector2D inversionszentrum)
   {
   // Das Inversionszentrum (in A) wird in diesem Datenmodell gespeichert.
   this.inversionszentrum = inversionszentrum;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Ortskurve zurück.
 * 
 * @return Die Ortskurve
 */
public Ortskurve getOrtskurve()
   {
   // Die Ortskurve wird zurückgegeben.
   return this.ortskurve;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die Ortskurve in diesem Datenmodell.
 * 
 * @param ortskurve Die Ortskurve
 */
public void setOrtskurve(Ortskurve ortskurve)
   {
   // Die Ortskurve wird in diesem Datenmodell gespeichert.
   this.ortskurve = ortskurve;
   }
}
