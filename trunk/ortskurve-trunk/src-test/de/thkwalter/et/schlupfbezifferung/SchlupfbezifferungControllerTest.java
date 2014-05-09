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
 * Diese Klasse enthält Tests für die Klasse {@link SchlupfbezifferungController}.
 * 
 * @author Th. K. Walter
 */
public class SchlupfbezifferungControllerTest
{
/**
 * Der Prüfling der Klasse der Klasse {@link SchlupfbezifferungController}.
 */
private SchlupfbezifferungController schlupfbezifferungController;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode initialisiert die Tests.
 * 
 * @throws java.lang.Exception
 */
@Before
public void setUp() throws Exception
   {
   // Der Prüfling wird erzeugt.
   this.schlupfbezifferungController = new SchlupfbezifferungController();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test des Konstruktors {@link SchlupfbezifferungController#SchlupfbezifferungController()}.
 */
@Test
public void testSchlupfbezifferungController()
   {
   // Es wird überprüft, ob der Prüfling erzeugt worden ist.
   assertNotNull(this.schlupfbezifferungController);
   }
}
