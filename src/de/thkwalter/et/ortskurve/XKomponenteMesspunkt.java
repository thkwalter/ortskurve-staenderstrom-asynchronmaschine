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
 * Diese Klasse repräsentiert die x-Komponente eines Messpunkts.
 *
 * @author Th. K. Walter
 */
public class XKomponenteMesspunkt extends KomponenteMesspunkt
{
/**
 * Dieser Konstruktor initialisier den Messpunkt, zu dem die Komponente gehört.
 * 
 * @param messpunkt Der Messpunkt, zu dem die Komponente gehört.
 * 
 * @see de.thkwalter.et.ortskurve.KomponenteMesspunkt#Messpunkt(Vector2D)
 */
public XKomponenteMesspunkt(Vector2D messpunkt)
   {
   super(messpunkt);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Wert der x-Komponente des Messpunkts zurück.
 * 
 * @return Der Wert einer x-Komponente des Messpunkts.
 * 
 * @see de.thkwalter.et.ortskurve.KomponenteMesspunkt#getWert()
 */
@Override
public double getWert()
   {
   return this.messpunkt.getX();
   }
}
