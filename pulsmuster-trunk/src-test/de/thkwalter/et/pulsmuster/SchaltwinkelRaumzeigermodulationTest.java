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
   
   // Es wird überprüft, ob die Schaltwinkel korrekt berechnet worden sind.
   double[] schaltwinkel = this.schaltwinkelRaumzeigermodulation.getSchaltwinkel();
   
   // Es wird überprüft, ob die korrekte Anzahl von Schaltwinkeln berechnet worden ist.
   assertEquals(30, schaltwinkel.length);
   
   // Es wird überprüft, ob die korrekten Schaltwinkel berechnet worden sind.
   double[] korrekteSchaltwinkel = {0.09952, 0.3312, 0.4986, 0.7695, 0.9014, 1.2007, 1.3108, 1.6231, 1.7285, 2.0365,
      2.1555, 2.4423, 2.5906, 2.8428, 3.0311, 3.2411, 3.4728, 3.6402, 3.9111, 4.0430, 4.3423, 4.4524, 4.7647, 4.8701,
      5.1781, 5.2970, 5.5839, 5.7322, 5.9844, 6.1727};
   for (int i = 0; i < schaltwinkel.length; i++)
      {
      assertEquals(korrekteSchaltwinkel[i], schaltwinkel[i], korrekteSchaltwinkel[i]/1000.0);
      }
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
