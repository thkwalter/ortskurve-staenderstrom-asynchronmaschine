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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
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

// ---------------------------------------------------------------------------------------------------------------------

/**
 * Die in den Tests verwendeten Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten
 */
private double[] testSteigungen;

/**
 * Die in diesem test verwendeten Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden
 */
private Betriebspunkt[] testBetriebspunkte;

/**
 * Das in den Tests verwendete Inversionszentrum (in A)
 */
private Vector2D testInversionszentrum;

/**
 * Der in diesem Test verwendete Drehpunkt der Schlupfgerade (in A)
 */
private Vector2D testDrehpunktSchlupfgerade;

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
   // Die in den Tests verwendeten Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten werden erzeugt.
   this.testSteigungen = new double[] {-0.21148, -0.34511, -0.53453};
   
   // Die in den Tests verwendeten Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden, werden
   // erzeugt.
   this.testBetriebspunkte = new Betriebspunkt[3];
   testBetriebspunkte[0] = new Betriebspunkt(new Vector2D(1.8843, 0.22026), 3.2133E-3);
   testBetriebspunkte[1] = new Betriebspunkt(new Vector2D(1.6135, 1.2989), 3.3633E-2);
   testBetriebspunkte[2] = new Betriebspunkt(new Vector2D(1.6639, 2.7199), 7.9420E-2);
   
   // Das in den Tests verwendete Inversionszentrum (in A) wird erzeugt.
   this.testInversionszentrum = new Vector2D(9.2570, -1.3389);
   
   // Der in den Tests verwendete Drehpunkt der Schlupfgerade (in A) wird erzeugt.
   this.testDrehpunktSchlupfgerade = new Vector2D(6.0768, -2.6562);
   
   // Der Prüfling wird erzeugt.
   this.schlupfresiduum = new Schlupfresiduum(this.testSteigungen, this.testBetriebspunkte, this.testInversionszentrum,
      this.testDrehpunktSchlupfgerade);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test des Konstruktors {@link Schlupfresiduum#Schlupfresiduum()}.
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testSchlupfresiduum() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Es wird überprüft, ob der Prüfling erzeugt worden ist.
   assertNotNull(this.schlupfresiduum);
   
   // Es wird überprüft, ob die Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten korrekt
   // initialisiert worden sind.
   Field feldSteigungen = Schlupfresiduum.class.getDeclaredField("steigungen");
   feldSteigungen.setAccessible(true);
   double[] steigungen = (double[]) feldSteigungen.get(this.schlupfresiduum);
   assertArrayEquals(this.testSteigungen, steigungen, 0.0);
   
   // Es wird überprüft, ob die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden, korrekt
   // initialisiert worden sind.
   Field feldBetriebspunkte = Schlupfresiduum.class.getDeclaredField("betriebspunkte");
   feldBetriebspunkte.setAccessible(true);
   Betriebspunkt[] betriebspunkte = (Betriebspunkt[]) feldBetriebspunkte.get(this.schlupfresiduum);
   assertArrayEquals(this.testBetriebspunkte, betriebspunkte);
   
   // Es wird überprüft, ob das Inversionszentrum (in A) korrekt initialisiert worden ist.
   Field feldInversionszentrum = Schlupfresiduum.class.getDeclaredField("inversionszentrum");
   feldInversionszentrum.setAccessible(true);
   Vector2D inversionszentrum = (Vector2D) feldInversionszentrum.get(this.schlupfresiduum);
   assertEquals(this.testInversionszentrum, inversionszentrum);
   
   // Es wird überprüft, ob derDrehpunkt der Schlupfgeraden (in A) korrekt initialisiert worden ist.
   Field feldDrehpunktSchlupfgerade = Schlupfresiduum.class.getDeclaredField("drehpunktSchlupfgerade");
   feldDrehpunktSchlupfgerade.setAccessible(true);
   Vector2D drehpunktSchlupfgerade = (Vector2D) feldDrehpunktSchlupfgerade.get(this.schlupfresiduum);
   assertEquals(this.testDrehpunktSchlupfgerade, drehpunktSchlupfgerade);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Schlupfresiduum#value(double))}.
 */
@Test
public void testValue()
   {
   
   }
}
