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
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.apache.commons.math3.complex.Complex;
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
   // Der Prüfling wird erzeugt.
   this.betriebspunkt = new Betriebspunkt(1.0, 0.5);
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
   Field i_1Feld = Betriebspunkt.class.getDeclaredField("i_1");
   i_1Feld.setAccessible(true);
   Complex i_1 = (Complex) i_1Feld.get(this.betriebspunkt);
   
   // Es wird überprüft, ob die komplexe Ständerstromstärke (in A) korrekt initialisert worden ist.
   assertEquals(new Complex(0.5, -1.0), i_1);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Betriebspunkt#getI_1x()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetI_1x() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException 
   {
   // Die in diesem Test verwendete komplexe Ständerstromstärke (in A) wird erzeugt.
   Complex testI_1 = new Complex(4.0, 2.0);
   
   // Die in diesem Test verwendete komplexe Ständerstromstärke (in A) wird im Prüfling gespeichert.
   Field i_1Feld = Betriebspunkt.class.getDeclaredField("i_1");
   i_1Feld.setAccessible(true);
   i_1Feld.set(this.betriebspunkt, testI_1);
   
   // Es wird überprüft, ob die x-Komponente der komplexen Ständerstromstärke (in A) korrekt zurückgegeben wird.
   assertEquals(-2.0, this.betriebspunkt.getI_1x(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Betriebspunkt#getI_1y()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetI_1y() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException 
   {
   // Die in diesem Test verwendete komplexe Ständerstromstärke (in A) wird erzeugt.
   Complex testI_1 = new Complex(4.0, 2.0);
   
   // Die in diesem Test verwendete komplexe Ständerstromstärke (in A) wird im Prüfling gespeichert.
   Field i_1Feld = Betriebspunkt.class.getDeclaredField("i_1");
   i_1Feld.setAccessible(true);
   i_1Feld.set(this.betriebspunkt, testI_1);
   
   // Es wird überprüft, ob die x-Komponente der komplexen Ständerstromstärke (in A) korrekt zurückgegeben wird.
   assertEquals(4.0, this.betriebspunkt.getI_1y(), 0.0);
   
   // Es wird überprüft, ob der Schlupf korrekt initialisiert worden ist.
   assertTrue(Double.isNaN(this.betriebspunkt.getS()));
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Betriebspunkt#getS()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetS() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
   {
   // Der in diesem Test verwendete Schlupf wird im Prüfling gespeichert.
   Field sFeld = Betriebspunkt.class.getDeclaredField("s");
   sFeld.setAccessible(true);
   sFeld.set(this.betriebspunkt, new Double(0.1));
   
   // Es wird überprüft, ob der Schlupf korrekt zurückgegeben wird.
   assertEquals(0.1, this.betriebspunkt.getS(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Betriebspunkt#setS(double)}.
 */
@Test
public void testSetS() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Die zu testende Methode wird aufgerufen.
   this.betriebspunkt.setS(0.1);
   
   // Es wird überprüft, ob der Schlupf korrekt im Prüfling gespeichert worden ist.
   assertEquals(0.1, this.betriebspunkt.getS(), 0.1);
   }
}
