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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import de.thkwalter.et.ortskurve.Ortskurve;

/**
 * Diese Klasse enthält Tests für die Klasse {@link SchlupfbezifferungController}.
 * 
 * @author Th. K. Walter
 */
public class SchlupfbezifferungControllerTest
{
/**
 * Der Prüfling der Klasse der Klasse {@link SchlupfbezifferungController}.
 */
private SchlupfbezifferungController schlupfbezifferungController;

// ---------------------------------------------------------------------------------------------------------------------

/**
 * Das in den Tests verwendete Datenmodell der Schlupfbezifferungsbestimmung
 */
private SchlupfbezifferungModell testSchlupfbezifferungModell;

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
   this.schlupfbezifferungController = new SchlupfbezifferungController();
   
   // Die in den Tests verwendete Ortskurve wird erzeugt und initialisiert.
   Ortskurve testOrtskurve = new Ortskurve(new Vector2D(6.0768, 1.8413), 4.4975);
   
   // Das in den Tests verwendete Datenmodell der Schlupfbezifferungsbestimmung wird erzeugt und initialisiert.
   this.testSchlupfbezifferungModell = new SchlupfbezifferungModell();
   this.testSchlupfbezifferungModell.setOrtskurve(testOrtskurve);
   
   // Das in den Tests verwendete Datenmodell der Schlupfbezifferungsbestimmung wird im Controller der 
   // Schlupfbezifferungsbestimmung gespeichert.
   Field feldSchlupfbezifferungModell = SchlupfbezifferungController.class.getDeclaredField("schlupfbezifferungModell");
   feldSchlupfbezifferungModell.setAccessible(true);
   feldSchlupfbezifferungModell.set(this.schlupfbezifferungController, this.testSchlupfbezifferungModell);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test des Konstruktors {@link SchlupfbezifferungController#SchlupfbezifferungController()}.
 */
@Test
public void testSchlupfbezifferungController()
   {
   // Es wird überprüft, ob der Prüfling erzeugt worden ist.
   assertNotNull(this.schlupfbezifferungController);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link SchlupfbezifferungController#inversionszentrumBerechnen}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testInversionszentrumBerechnen() throws NoSuchMethodException, SecurityException, IllegalAccessException, 
   IllegalArgumentException, InvocationTargetException
   {
   // Die zu testende Methode wird aufgerufen.
   Method methode = 
      SchlupfbezifferungController.class.getDeclaredMethod("inversionszentrumBerechnen", (Class<?>[]) null);
   methode.setAccessible(true);
   Vector2D inversionszentrum = (Vector2D) methode.invoke(this.schlupfbezifferungController, (Object[]) null);
   
   // Es wird überprüft, ob das Inversionszentrum korrekt berechnet worden ist.
   assertEquals(9.257, inversionszentrum.getX(), 9.257 / 1000.0);
   assertEquals(-1.339, inversionszentrum.getY(), 1.339 / 1000.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link SchlupfbezifferungController#schlupfbezifferungBestimmenIntern()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testSchlupfbezifferungBestimmenIntern() throws NoSuchMethodException, SecurityException, 
   IllegalAccessException, IllegalArgumentException, InvocationTargetException
   {
   // Die zu testende Methode wird aufgerufen.
   Method methode = 
      SchlupfbezifferungController.class.getDeclaredMethod("schlupfbezifferungBestimmenIntern", (Class<?>[]) null);
   methode.setAccessible(true);
   methode.invoke(this.schlupfbezifferungController, (Object[]) null);
   
   // Es wird überprüft, ob das berechnete Inversionszentrum korrekt berechnet und im Datenmodell der
   // Schlupfbezifferungsbestimmung gespeichert worden ist.
   assertEquals(9.257, this.testSchlupfbezifferungModell.getInversionszentrum().getX(), 9.257 / 1000.0);
   assertEquals(-1.339, this.testSchlupfbezifferungModell.getInversionszentrum().getY(), 1.339 / 1000.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link SchlupfbezifferungController#drehpunktSchlupfgeradeBerechnen()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testDrehpunktSchlupfgeradeBerechnen() throws NoSuchMethodException, SecurityException, 
   IllegalAccessException, IllegalArgumentException, InvocationTargetException
   {
   // Die zu testende Methode wird aufgerufen.
   Method methode = 
      SchlupfbezifferungController.class.getDeclaredMethod("drehpunktSchlupfgeradeBerechnen", (Class<?>[]) null);
   methode.setAccessible(true);
   Vector2D drehpunktSchlupfgerade = (Vector2D) methode.invoke(this.schlupfbezifferungController, (Object[]) null);
   
   // Es wird überprüft, ob das Inversionszentrum korrekt berechnet worden ist.
   assertEquals(6.077, drehpunktSchlupfgerade.getX(), 6.077 / 1000.0);
   assertEquals(-2.656, drehpunktSchlupfgerade.getY(), 2.656 / 1000.0);
   }

}
