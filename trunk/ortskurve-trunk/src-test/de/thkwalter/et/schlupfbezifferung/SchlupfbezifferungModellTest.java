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

import de.thkwalter.et.ortskurve.Ortskurve;

/**
 * Diese Klasse enthält Tests für die Klasse {@link SchlupfbezifferungModell}.
 * 
 * @author Th. K. Walter
 */
public class SchlupfbezifferungModellTest
{
/**
 * Der Prüfling der Klasse {@link SchlupfbezifferungModell}.
 */
private SchlupfbezifferungModell schlupfbezifferungModell;

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
   this.schlupfbezifferungModell = new SchlupfbezifferungModell();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test des Konstruktors {@link SchlupfbezifferungModell#SchlupfbezifferungModell()}.
 */
@Test
public void testSchlupfbezifferungModell()
   {
   // Es wird überprüft, ob der Prüfling erzeugt worden ist.
   assertNotNull(this.schlupfbezifferungModell);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link SchlupfbezifferungModell#getInversionszentrum()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetInversionzentrum() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Das in diesem Test verwendete Inversionszentrum (in A) wird erzeugt.
   Vector2D testInversionszentrum = new Vector2D(0.0, 0.0);
   
   // Das in diesem Test verwendete Inversionszentrum (in A) wird im Datenmodell gespeichert.
   Field feldInversionszentrum = SchlupfbezifferungModell.class.getDeclaredField("inversionszentrum");
   feldInversionszentrum.setAccessible(true);
   feldInversionszentrum.set(this.schlupfbezifferungModell, testInversionszentrum);
   
   // Es wird überprüft, ob das Inversionszentrum (in A) korrekt zurückgegeben wird.
   assertEquals(testInversionszentrum, this.schlupfbezifferungModell.getInversionszentrum());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link SchlupfbezifferungModell#setInversionszentrum(Vector2D)}.
 */
@Test
public void testSetInversionzentrum()
   {
   // Das in diesem Test verwendete Inversionszentrum (in A) wird erzeugt.
   Vector2D testInversionszentrum = new Vector2D(0.0, 0.0);
   
   // Die zu testende Methode wird aufgerufen.
   this.schlupfbezifferungModell.setInversionszentrum(testInversionszentrum);
   
   // Es wird überprüft, ob das Inversionszentrum (in A) korrekt im Datenmodell der Schlupfbezifferungsbestimmung
   // gespeichert worden ist.
   assertEquals(testInversionszentrum, this.schlupfbezifferungModell.getInversionszentrum());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link SchlupfbezifferungModell#getOrtskurve()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetOrtskurve() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Die in diesem Test verwendete Ortskurve wird erzeugt.
   Ortskurve testOrtskurve = new Ortskurve(new Vector2D(0.0, 0.0), 1.0);
   
   // Das in diesem Test verwendete Ortskurve wird im Datenmodell gespeichert.
   Field feldOrtskurve = SchlupfbezifferungModell.class.getDeclaredField("ortskurve");
   feldOrtskurve.setAccessible(true);
   feldOrtskurve.set(this.schlupfbezifferungModell, testOrtskurve);
   
   // Es wird überprüft, ob die Ortskurve korrekt zurückgegeben wird.
   assertEquals(testOrtskurve, this.schlupfbezifferungModell.getOrtskurve());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link SchlupfbezifferungModell#setOrtskurve(Ortskurve)}.
 */
@Test
public void testSetOrtskurve()
   {
   // Die in diesem Test verwendete Ortskurve wird erzeugt.
   Ortskurve testOrtskurve = new Ortskurve(new Vector2D(0.0, 0.0), 1.0);
   
   // Die zu testende Methode wird aufgerufen.
   this.schlupfbezifferungModell.setOrtskurve(testOrtskurve);
   
   // Es wird überprüft, ob die Ortskurve korrekt im Datenmodell der Schlupfbezifferungsbestimmung gespeichert worden 
   // ist.
   assertEquals(testOrtskurve, this.schlupfbezifferungModell.getOrtskurve());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link SchlupfbezifferungModell#getDrehpunktSchlupfgerade()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetDrehpunktSchlupfgerade() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Der in diesem Test verwendete Drehpunkt der Schlupfgerade (in A) wird erzeugt.
   Vector2D testDrehpunktSchlupfgerade = new Vector2D(0.0, 0.0);
   
   // Der in diesem Test verwendete Drehpunkt der Schlupfgerade (in A) wird im Datenmodell gespeichert.
   Field feldDrehpunktSchlupfgerade = SchlupfbezifferungModell.class.getDeclaredField("drehpunktSchlupfgerade");
   feldDrehpunktSchlupfgerade.setAccessible(true);
   feldDrehpunktSchlupfgerade.set(this.schlupfbezifferungModell, testDrehpunktSchlupfgerade);
   
   // Es wird überprüft, ob die Ortskurve korrekt zurückgegeben wird.
   assertEquals(testDrehpunktSchlupfgerade, this.schlupfbezifferungModell.getDrehpunktSchlupfgerade());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link SchlupfbezifferungModell#setDrehpunktSchlupfgerade(Vector2D)}.
 */
@Test
public void testSetDrehpunktSchlupfgerade()
   { 
   // Der in diesem Test verwendete Drehpunkt der Schlupfgerade (in A) wird erzeugt.
   Vector2D testDrehpunktSchlupfgerade = new Vector2D(0.0, 0.0);
   
   // Die zu testende Methode wird aufgerufen.
   this.schlupfbezifferungModell.setDrehpunktSchlupfgerade(testDrehpunktSchlupfgerade);
   
   // Es wird überprüft, ob der Drehpunkt der Schlupfgerade (in A) korrekt im Datenmodell der 
   // Schlupfbezifferungsbestimmung gespeichert worden ist.
   assertEquals(testDrehpunktSchlupfgerade, this.schlupfbezifferungModell.getDrehpunktSchlupfgerade());
   }
}
