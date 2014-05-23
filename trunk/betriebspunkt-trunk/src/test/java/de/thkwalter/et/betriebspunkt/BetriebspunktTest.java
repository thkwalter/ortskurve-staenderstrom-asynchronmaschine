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
package de.thkwalter.et.betriebspunkt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Betriebspunkt}.
 * 
 * @author Th. K. Walter
 */
public class BetriebspunktTest
{
/**
 * Ein Prüfling der Klasse {@link Betriebspunkt}.
 */
private Betriebspunkt betriebspunkt;

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
   this.betriebspunkt = new Betriebspunkt(3.2546);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test des Konstruktors {@link Betriebspunkt#Betriebspunkt()}.
 */
@Test
public void testBetriebspunkt()
   {
   // Es wird überprüft, ob der Prüfling erzeugt worden ist.
   assertNotNull(this.betriebspunkt);
   
   // Es wird überprüft, ob der Leiterstrom (in A) korrekt initialisiert worden ist.
   assertEquals(3.2546, this.betriebspunkt.i_L(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Betriebspunkt#i_L()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void test_i_L() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
   {
   // Der in diesem Test verwendete Leiterstrom (in A) wird im Prüfling gespeichert.
   Field betriebspunktFeld = Betriebspunkt.class.getDeclaredField("i_L");
   betriebspunktFeld.setAccessible(true);
   betriebspunktFeld.setDouble(this.betriebspunkt, 2.8769);
   
   // Es wird überprüft, ob der Leiterstrom (in A) korrekt zurückgegeben wird.
   assertEquals(2.8769, this.betriebspunkt.i_L(), 0.0);
   }
}
