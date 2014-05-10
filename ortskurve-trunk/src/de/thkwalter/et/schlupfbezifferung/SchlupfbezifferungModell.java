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

/**
 * Der Drehpunkt der Schlupfgerade (in A)
 */
private Vector2D drehpunktSchlupfgerade;

/**
 * Die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden.
 */
private Betriebspunkt[] betriebspunkte;

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

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Drehpunkt der Schlupfgeraden (in A) zurück.
 * 
 * @return Der Drehpunkt der Schlupfgeraden (in A).
 */
public Vector2D getDrehpunktSchlupfgerade()
   {
   // Der Drehpunkt der Schlupfgeraden (in A) wird zurückgegeben.
   return this.drehpunktSchlupfgerade;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert den Drehpunkt der Schlupfgeraden (in A) in diesem Datenmodell.
 * 
 * @param drehpunktSchlupfgerade Der Drehpunkt der Schlupfgeraden (in A).
 */
public void setDrehpunktSchlupfgerade(Vector2D drehpunktSchlupfgerade)
   {
   // Der Drehpunkt der Schlupfgeraden (in A) wird in diesem Datenmodell gespeichert.
   this.drehpunktSchlupfgerade = drehpunktSchlupfgerade;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Betriebspunkte zurück, die zur Bestimmung der Schlupfbezifferung verwendet werden.
 * 
 * @return Die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden.
 */
public Betriebspunkt[] getBetriebspunkte()
   {
   // Die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden, werden zurückgegeben.
   return this.betriebspunkte;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden, in diesem
 * Datenmodell.
 * 
 * @param betriebspunkte Die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden.
 */
public void setBetriebspunkte(Betriebspunkt[] betriebspunkte)
   {
   // Die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden, werden in diesem Datenmodell
   // gespeichert.
   this.betriebspunkte = betriebspunkte;
   }
}
