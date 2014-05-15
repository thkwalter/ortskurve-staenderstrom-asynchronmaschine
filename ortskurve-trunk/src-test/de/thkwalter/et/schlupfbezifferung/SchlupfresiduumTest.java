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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Dieses Klasse enthält Tests für die Klasse {@link Schlupfresiduum}.
 * 
 * @author Th. K. Walter
 */
public class SchlupfresiduumTest
{
/**
 * Der Prüfling der Klasse {@link Schlupfresiduum}.
 */
private Schlupfresiduum schlupfresiduum;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode initialisiert die Tests.
 * 
 * @throws Exception
 */
@Before
public void setUp() throws Exception
   {
   // Der Prüfling wird erzeugt.
   this.schlupfresiduum = new Schlupfresiduum();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test des Konstruktors {@link Schlupfresiduum#Schlupfresiduum()}.
 */
@Test
public void testSchlupfresiduum()
   {
   // Es wird überprüft, ob der Prüfling erzeugt worden ist.
   assertNotNull(this.schlupfresiduum);
   }

}
