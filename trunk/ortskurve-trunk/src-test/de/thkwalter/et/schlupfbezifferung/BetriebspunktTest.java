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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Dieses Klasse enthält Tests für die Klasse {@link Betriebspunkt}.
 * 
 * @author Th. K. Walter
 */
public class BetriebspunktTest
{
/**
 * Der Prüfling der Klasse {@link Betriebspunkt}.
 */
private Betriebspunkt betriebspunkt;

// ---------------------------------------------------------------------------------------------------------------------

/**
 * Die in diesem Test verwendete Ständerstromstärke (in A)
 */
private Vector2D test_i_1;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode initialisert die Tests.
 * 
 * @throws java.lang.Exception
 */
@Before
public void setUp() throws Exception
   {
   // Die in diesem Test verwendete Ständerstromstärke (in A) wird erzeugt.
   this.test_i_1 = new Vector2D(1.0, 0.5);
   
   // Der Prüfling wird erzeugt.
   this.betriebspunkt = new Betriebspunkt(this.test_i_1, 0.5);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test des Konstruktors {@link Betriebspunkt#Betriebspunkt()}
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testBetriebspunkt() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Es wird überprüft, ob der Prüfling erzeugt worden ist.
   assertNotNull(this.betriebspunkt);
   
   // Die im Prüfling gespeicherte, komplexe Ständerstromstärke (in A) wird gelesen.
   Field feld_i_1 = Betriebspunkt.class.getDeclaredField("i_1");
   feld_i_1.setAccessible(true);
   Vector2D i_1 = (Vector2D) feld_i_1.get(this.betriebspunkt);
   
   // Es wird überprüft, ob die komplexe Ständerstromstärke (in A) korrekt initialisert worden ist.
   assertEquals(this.test_i_1, i_1);
   
   // Der im Prüfling gespeicherte Schlupf wird gelesen.
   Field feld_s = Betriebspunkt.class.getDeclaredField("s");
   feld_s.setAccessible(true);
   double s = feld_s.getDouble(this.betriebspunkt);
   
   // Es wird überprüft, ob der Schlupf korrekt initialisert worden ist.
   assertEquals(0.5, s, 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Betriebspunkt#getI_1()}.
 */
@Test
public void testGetI_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Es wird überprüft, ob die komplexe Ständerstromstärke (in A) korrekt zurückgegeben wird.
   assertEquals(this.test_i_1, this.betriebspunkt.getI_1());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Betriebspunkt#getS()}.
 */
@Test
public void testGetS() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Es wird überprüft, ob der Schlupf korrekt zurückgegeben wird.
   assertEquals(0.5, this.betriebspunkt.getS(), 0.0);
   }
}
