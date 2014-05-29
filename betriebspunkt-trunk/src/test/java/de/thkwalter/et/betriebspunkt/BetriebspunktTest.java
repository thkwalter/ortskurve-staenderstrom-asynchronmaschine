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

import org.apache.commons.math3.complex.Complex;
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
   this.betriebspunkt = new Betriebspunkt(3.2546, 396.98, 1.9259, Schaltungstyp.STERN);
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
   
   // Es wird überprüft, ob der effektive Leiterstrom (in A) korrekt initialisiert worden ist.
   assertEquals(3.2546, this.betriebspunkt.i_L(), 0.0);
   
   // Es wird überprüft, ob die effektive Leiter-Leiterspannung (in A) korrekt zurückgegeben wird.
   assertEquals(396.98, this.betriebspunkt.u_LL(), 0.0);
   
   // Es wird überprüft, ob die elektrische Leistung (in kW) korrekt zurückgegeben wird.
   assertEquals(1.9259, this.betriebspunkt.p_el(), 0.0);
   
   // Es wird überprüft, ob die Scheinleistung (in kVA) korrekt zurückgegeben wird.
   assertEquals(2.238, this.betriebspunkt.p_s(), 2.238 / 1000.0);
   
   // Es wird überprüft, ob die Phasenverschiebung zwischen Strangstrom und Strangspannung korrekt zurückgegeben wird.
   assertEquals(0.8606, this.betriebspunkt.cosPhi(), 0.8606 / 1000.0);
   
   // Es wird überprüft, ob der komplexe Zeiger des Strangstroms (in A) korrekt zurückgegeben wird.
   assertEquals(2.801, this.betriebspunkt.z_i_s().getReal(), 2.801 / 1000.0);
   assertEquals(-1.657, this.betriebspunkt.z_i_s().getImaginary(), 1.657 / 1000.0);
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
   // Der in diesem Test verwendete effektive Leiterstrom (in A) wird im Prüfling gespeichert.
   Field i_L_Feld = Betriebspunkt.class.getDeclaredField("i_L");
   i_L_Feld.setAccessible(true);
   i_L_Feld.setDouble(this.betriebspunkt, 2.8769);
   
   // Es wird überprüft, ob der effektive Leiterstrom (in A) korrekt zurückgegeben wird.
   assertEquals(2.8769, this.betriebspunkt.i_L(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Betriebspunkt#u_LL()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void test_u_LL() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
   {
   // Die in diesem Test verwendete effektive Leiter-Leiterspannung (in V) wird im Prüfling gespeichert.
   Field u_LL_Feld = Betriebspunkt.class.getDeclaredField("u_LL");
   u_LL_Feld.setAccessible(true);
   u_LL_Feld.setDouble(this.betriebspunkt, 397.72);
   
   // Es wird überprüft, ob die effektive Leiter-Leiterspannung (in A) korrekt zurückgegeben wird.
   assertEquals(397.72, this.betriebspunkt.u_LL(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Betriebspunkt#p_el()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void test_p_el() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
   {
   // Die in diesem Test verwendete elektrische Leistung (in kW) wird im Prüfling gespeichert.
   Field p_el_Feld = Betriebspunkt.class.getDeclaredField("p_el");
   p_el_Feld.setAccessible(true);
   p_el_Feld.setDouble(this.betriebspunkt, 1.6462);
   
   // Es wird überprüft, ob die elektrische Leistung (in kW) korrekt zurückgegeben wird.
   assertEquals(1.6462, this.betriebspunkt.p_el(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Betriebspunkt#p_s()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void test_p_s() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
   {
   // Die in diesem Test verwendete Scheinleistung (in kVA) wird im Prüfling gespeichert.
   Field p_s_Feld = Betriebspunkt.class.getDeclaredField("p_s");
   p_s_Feld.setAccessible(true);
   p_s_Feld.setDouble(this.betriebspunkt, 1.9820);
   
   // Es wird überprüft, ob die Scheinleistung (in kVA) korrekt zurückgegeben wird.
   assertEquals(1.9820, this.betriebspunkt.p_s(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Betriebspunkt#cosPhi()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void test_cosPhi() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Die in diesem Test verwendete Phasenverschiebung zwischen Strangstrom und Strangspannung wird im Prüfling 
   // gespeichert.
   Field cosPhi_Feld = Betriebspunkt.class.getDeclaredField("cosPhi");
   cosPhi_Feld.setAccessible(true);
   cosPhi_Feld.setDouble(this.betriebspunkt, 0.83062);
   
   // Es wird überprüft, ob die Phasenverschiebung zwischen Strangstrom und Strangspannung korrekt zurückgegeben wird.
   assertEquals(0.83062, this.betriebspunkt.cosPhi(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Betriebspunkt#z_i_s()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void test_z_i_s() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Der in diesem Test verwendete komplexe Zeiger des Strangstroms (in A) wird erzeugt.
   Complex test_z_i_s = new Complex(2.3897, -1.6020);
   
   // Der in diesem Test verwendete komplexe Zeiger des Strangstroms (in A) wird im Prüfling gespeichert.
   Field z_i_s_Feld = Betriebspunkt.class.getDeclaredField("z_i_s");
   z_i_s_Feld.setAccessible(true);
   z_i_s_Feld.set(this.betriebspunkt, test_z_i_s);
   
   // Es wird überprüft, ob der komplexe Zeiger des Strangstroms (in A) korrekt zurückgegeben wird.
   assertEquals(test_z_i_s, this.betriebspunkt.z_i_s());
   }
}
