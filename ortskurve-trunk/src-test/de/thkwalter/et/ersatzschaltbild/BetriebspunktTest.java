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
 * Ein Objekt der zu testenden Klasse {@link Betriebspunkt}
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
   // Das Objekt der zu testenden Klasse wird erzeugt.
   this.betriebspunkt = new Betriebspunkt();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Betriebspunkt#Betriebspunkt()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testBetriebspunkt() 
   {
   // Es wird überprüft, ob der Betriebspunkt erzeugt worden ist.
   assertNotNull(this.betriebspunkt);
   
   // Es wird überprüft, ob der Ständerstrom (in A) korrekt initialisiert worden ist.
   assertEquals(Complex.NaN, this.betriebspunkt.getI1());
   
   // Es wird überprüft, ob die Drehzahl (in Hz) korrekt initialisiert worden ist.
   assertEquals(Double.NaN, this.betriebspunkt.getN(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Betriebspunkt#getI1()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetI1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
   {
   // Ein Ständerstrom wird in diesem Objekt gespeichert.
   Field i1Feld = Betriebspunkt.class.getDeclaredField("i1");
   i1Feld.setAccessible(true);
   i1Feld.set(this.betriebspunkt, new Complex(1, 1));
   
   // Die zu testende Methode wird aufgerufen.
   Complex i1 = this.betriebspunkt.getI1();
   
   // Es wird überprüft, ob der Ständerstrom korrekt zurückgegeben worden ist.
   assertEquals(new Complex(1, 1), i1);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Betriebspunkt#setI1(Complex)}.
 */
@Test
public void testSetI1()
   {
   // Die zu testende Methode wird aufgerufen.
   this.betriebspunkt.setI1(new Complex(2, 1));
   
   // Es wird überprüft, ob der Ständerstrom korrekt gespeichert worden ist.
   assertEquals(new Complex(2, 1), this.betriebspunkt.getI1());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Betriebspunkt#getN()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetN() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Eine Drehzahl wird in diesem Objekt gespeichert.
   Field nFeld = Betriebspunkt.class.getDeclaredField("n");
   nFeld.setAccessible(true);
   nFeld.set(this.betriebspunkt, 100.0);
   
   // Die zu testende Methode wird aufgerufen.
   double n = this.betriebspunkt.getN();
   
   // Es wird überprüft, ob die Drehzahl korrekt zurückgegeben worden ist.
   assertEquals(100.0, n, 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Betriebspunkt#setN(double)}.
 */
@Test
public void testSetN()
   {
   // Die zu testende Methode wird aufgerufen.
   this.betriebspunkt.setN(1000.0);
   
   // Es wird überprüft, ob die Drehzahl korrekt gespeichert worden ist.
   assertEquals(1000.0, this.betriebspunkt.getN(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link java.lang.Object#toString()}.
 */
@Test
public void testToString1()
   {
   // Das Objekt der zu testenden Klasse wird initalisiert.
   this.betriebspunkt.setI1(new Complex(1, 5));
   this.betriebspunkt.setN(100.0);
   
   // Es wird überprüft, ob die Zeichenkette, die das zu testende Objekt repräsentiert, korrekt zusammengebaut wird.
   String meldung = "Betriebspunkt [i1=(1.0, 5.0), n=100.0]";
   assertEquals(meldung, this.betriebspunkt.toString());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link java.lang.Object#toString()}.
 */
@Test
public void testToString2()
   {   
   // Es wird überprüft, ob die Zeichenkette, die das zu testende Objekt repräsentiert, korrekt zusammengebaut wird.
   String meldung = "Betriebspunkt [i1=(NaN, NaN), n=NaN]";
   assertEquals(meldung, this.betriebspunkt.toString());
   }
}
