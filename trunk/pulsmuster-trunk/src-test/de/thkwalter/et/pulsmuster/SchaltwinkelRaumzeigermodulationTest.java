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
package de.thkwalter.et.pulsmuster;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link SchaltwinkelRaumzeigermodulation}.
 * 
 * @author Th. K. Walter
 */
public class SchaltwinkelRaumzeigermodulationTest
{
/**
 * Der Prüfling
 */
private SchaltwinkelRaumzeigermodulation schaltwinkelRaumzeigermodulation;

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
   // Die in den Tests verwendete Pulszahl wird festgelegt.
   int testPulszahl = 15;
   
   // Der Prüfling wird erzeugt.
   this.schaltwinkelRaumzeigermodulation = new SchaltwinkelRaumzeigermodulation(testPulszahl);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test des Konstruktors {@link SchaltwinkelRaumzeigermodulation#SchaltwinkelRaumzeigermodulation()}
 */
@Test
public void testSchaltwinkelRaumzeigermodulation()
   {
   // Es wird überprüft, ob der Prüfling erzeugt worden ist.
   assertNotNull(this.schaltwinkelRaumzeigermodulation);
   
   // Es wird überprüft, ob die absolute Genauigkeit der berechneten Schaltwinkel (im Bogenmaß), bei der die Iteration 
   // abgebrochen wird, korrekt initialisiert worden ist.
   
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link SchaltwinkelRaumzeigermodulation#getSchaltwinkel()}
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetSchaltwinkel() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Die in diesem Test verwendeten Schaltwinkel (im Bogenmaß) werden erzeugt.
   double[] testSchaltwinkel = new double[0];
   
   // Die in diesem Test verwendeten Schaltwinkel (im Bogenmaß) werden im Prüfling gespeichert.
   Field schaltwinkelFeld = SchaltwinkelRaumzeigermodulation.class.getDeclaredField("schaltwinkel");
   schaltwinkelFeld.setAccessible(true);
   schaltwinkelFeld.set(this.schaltwinkelRaumzeigermodulation, testSchaltwinkel);
   
   // Es wird überprüft, ob die Schaltwinkel (im Bogenmaß) korrekt zurückgegeben werden.
   assertEquals(testSchaltwinkel, this.schaltwinkelRaumzeigermodulation.getSchaltwinkel());
   }
}
