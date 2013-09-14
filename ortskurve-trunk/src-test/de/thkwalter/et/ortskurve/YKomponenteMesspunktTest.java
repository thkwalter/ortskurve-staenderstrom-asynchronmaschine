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

import static org.junit.Assert.assertEquals;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link YKomponenteMesspunkt}.
 *
 * @author Th. K. Walter
 */
public class YKomponenteMesspunktTest
{
/**
 * Test für die Methode {@link de.thkwalter.et.ortskurve.XKomponenteMesspunkt#getWert()}.
 */
@Test
public void testGetWert()
   {
   // Ein Messpunkt wird erzeugt.
   Vector2D messpunkt = new Vector2D(1.0, 2.0);
   
   // Das Objekt, das für die Tests verwendet wird, wird erzeugt.
   KomponenteMesspunkt yKomponenteMesspunkt = new YKomponenteMesspunkt(messpunkt);
   
   // Es wird geprüft, ob die Methode getWert() den Wert der x-Komponente zurückgibt.
   assertEquals(messpunkt.getY(), yKomponenteMesspunkt.getWert(), messpunkt.getY() / 1000);
   }
}
