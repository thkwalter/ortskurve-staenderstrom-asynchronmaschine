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
package de.thkwalter.et.ersatzschaltbild;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Ersatzschaltbild}.
 * 
 * @author Th. K. Walter
 */
public class ErsatzschaltbildTest
{
/**
 * Ein Objekt der zu testenden Klasse {@link Ersatzschaltbild}.
 */
private Ersatzschaltbild ersatzschaltbild;

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
   // Das Objekt der zu testenden Klasse wird erzeugt.
   this.ersatzschaltbild = new Ersatzschaltbild();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test method for {@link Ersatzschaltbild#Ersatzschaltbild()}.
 */
@Test
public void testErsatzschaltbild()
   {
   // Es wird überprüft, ob das Testobjekt erzeugt worden ist.
   assertNotNull(this.ersatzschaltbild);
   
   // Es wird überprüft, ob die Reaktanz x_1 (in Ohm) korrekt initialisiert worden ist.
   assertTrue(Double.isNaN(this.ersatzschaltbild.getX1()));
   
   // Es wird überprüft, ob der ohmsche Widerstand des Ständers (in Ohm) korrekt initialisiert worden ist.
   assertTrue(Double.isNaN(this.ersatzschaltbild.getR1()));
   
   // Es wird überprüft, ob die Streureaktanz (in Ohm) korrekt initialisiert worden ist.
   assertTrue(Double.isNaN(this.ersatzschaltbild.getX_k()));
   
   // Es wird überprüft, ob der auf den Ständer bezogene ohmsche Läuferwiderstand (in Ohm) korrekt initialisiert 
   // worden ist.
   assertTrue(Double.isNaN(this.ersatzschaltbild.getR2_strich()));
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Ersatzschaltbild#getX1()}.
 */
@Test
public void testGetX1() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
   IllegalAccessException
   {
   // Die im Test verwendete Reaktanz x_1 (in Ohm) wird im Datenmodell gespeichert.
   Field feld = Ersatzschaltbild.class.getDeclaredField("x1");
   feld.setAccessible(true);
   feld.setDouble(this.ersatzschaltbild, 25.0);

   // Die zu testende Methode wird aufgerufen.
   double x1 = this.ersatzschaltbild.getX1();

   // Es wird überprüft, ob die Reaktanz x_1 (in Ohm) korrekt zurückgegeben worden ist.
   assertEquals(25.0, x1, 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Ersatzschaltbild#setX1(double)}.
 */
@Test
public void testSetX1()
   {
   // Die zu testende Methode wird aufgerufen.
   this.ersatzschaltbild.setX1(25.0);
   
   // Es wird überprüft, ob die Reaktanz x_1 (in Ohm) korrekt gespeichert worden ist.
   assertEquals(25.0, this.ersatzschaltbild.getX1(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ersatzschaltbild#getR1()}.
 */
@Test
public void testGetR1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Der im Test verwendete ohmsche Widerstand des Ständers (in Ohm) wird im Datenmodell gespeichert.
   Field feld = Ersatzschaltbild.class.getDeclaredField("r1");
   feld.setAccessible(true);
   feld.setDouble(this.ersatzschaltbild, 10.0);
   
   // Die zu testende Methode wird aufgerufen.
   double r1 = this.ersatzschaltbild.getR1();
   
   // Es wird überprüft, ob der ohmsche Widerstand des Ständers (in Ohm) korrekt zurückgegeben worden ist.
   assertEquals(10.0, r1, 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Ersatzschaltbild#setR1(double)}.
 */
@Test
public void testSetR1()
   {
   // Die zu testende Methode wird aufgerufen.
   this.ersatzschaltbild.setR1(12.0);
   
   // Es wird überprüft, ob der ohmsche Widerstand des Ständers (in Ohm) korrekt gespeichert worden ist.
   assertEquals(12.0, this.ersatzschaltbild.getR1(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Ersatzschaltbild#getX_k()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetX_k() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Die im Test verwendete Streureaktanz (in Ohm) wird im Datenmodell gespeichert.
   Field feld = Ersatzschaltbild.class.getDeclaredField("x_k");
   feld.setAccessible(true);
   feld.setDouble(this.ersatzschaltbild, 6.0);
   
   // Die zu testende Methode wird aufgerufen.
   double x_k = this.ersatzschaltbild.getX_k();
   
   // Es wird überprüft, ob die Streureaktanz (in Ohm) korrekt zurückgegeben worden ist.
   assertEquals(6.0, x_k, 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Ersatzschaltbild#setX_k(double)}.
 */
@Test
public void testSetX_k()
   {
   // Die zu testende Methode wird aufgerufen.
   this.ersatzschaltbild.setX_k(3.4);
   
   // Es wird überprüft, ob die Streureaktanz (in Ohm) korrekt gespeichert worden ist.
   assertEquals(3.4, this.ersatzschaltbild.getX_k(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Ersatzschaltbild#getR2_strich()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetR2_strich() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Der im Test verwendete, auf den Ständer bezogene ohmsche Läuferwiderstand (in Ohm) wird im Datenmodell 
   // gespeichert.
   Field feld = Ersatzschaltbild.class.getDeclaredField("r2_strich");
   feld.setAccessible(true);
   feld.setDouble(this.ersatzschaltbild, 2.9);
   
   // Die zu testende Methode wird aufgerufen.
   double r2_strich = this.ersatzschaltbild.getR2_strich();
   
   // Es wird überprüft, ob der auf den Ständer bezogene ohmsche Widerstand (in Ohm) korrekt zurückgegeben worden ist.
   assertEquals(2.9, r2_strich, 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Ersatzschaltbild#setR2_strich(double)}.
 */
@Test
public void testSetR2_strich()
   {
   // Die zu testende Methode wird aufgerufen.
   this.ersatzschaltbild.setR2_strich(38.5);
   
   // Es wird überprüft, ob der auf den Ständer bezogene ohmsche Widerstand (in Ohm) korrekt gespeichert worden ist.
   assertEquals(38.5, this.ersatzschaltbild.getR2_strich(), 0.0);
   }
}
