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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Assert;
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
   
   // Die in den Tests verwendeten Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden, werden
   // erzeugt.
   Betriebspunkt[] testBetriebspunkte = new Betriebspunkt[3];
   testBetriebspunkte[0] = new Betriebspunkt(new Vector2D(1.8843, 0.22026), 3.2133E-3);
   testBetriebspunkte[1] = new Betriebspunkt(new Vector2D(1.6135, 1.2989), 3.3633E-2);
   testBetriebspunkte[2] = new Betriebspunkt(new Vector2D(1.6639, 2.7199), 7.9420E-2);
   
   // Das in den Tests verwendete Datenmodell der Schlupfbezifferungsbestimmung wird erzeugt und initialisiert.
   this.testSchlupfbezifferungModell = new SchlupfbezifferungModell();
   this.testSchlupfbezifferungModell.setOrtskurve(testOrtskurve);
   this.testSchlupfbezifferungModell.setBetriebspunkte(testBetriebspunkte);
   
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
   
   // Es wird überprüft, ob das berechnete Inversionszentrum (in A) korrekt berechnet und im Datenmodell der
   // Schlupfbezifferungsbestimmung gespeichert worden ist.
   assertEquals(9.257, this.testSchlupfbezifferungModell.getInversionszentrum().getX(), 9.257 / 1000.0);
   assertEquals(-1.339, this.testSchlupfbezifferungModell.getInversionszentrum().getY(), 1.339 / 1000.0);
   
   // Es wird überprüft, ob der Drehpunkt der Schlupfgeraden (inA) korrekt berechnet und im Datenmodell der
   // Schlupfbezifferungsbestimmung gespeichert worden ist.
   assertEquals(6.077, this.testSchlupfbezifferungModell.getDrehpunktSchlupfgerade().getX(), 6.077 / 1000.0);
   assertEquals(-2.656, this.testSchlupfbezifferungModell.getDrehpunktSchlupfgerade().getY(), 2.656 / 1000.0);
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

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link SchlupfbezifferungController#steigungenBerechnen()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testSteigungenBerechnen() throws NoSuchMethodException, SecurityException, 
   IllegalAccessException, IllegalArgumentException, InvocationTargetException
   {      
   // Das Inversionszentrum (in A) wird berechnet und im Datenmodell der Schlupfbezifferungsbestimmung gespeichert.
   Method methodeInversionszentrumBerechnen = 
      SchlupfbezifferungController.class.getDeclaredMethod("inversionszentrumBerechnen", (Class<?>[]) null);
   methodeInversionszentrumBerechnen.setAccessible(true);
   Vector2D inversionszentrum = 
      (Vector2D) methodeInversionszentrumBerechnen.invoke(this.schlupfbezifferungController, (Object[]) null);
   this.testSchlupfbezifferungModell.setInversionszentrum(inversionszentrum);
   
   // Die zu testende Methode wird aufgerufen.
   Method methode = SchlupfbezifferungController.class.getDeclaredMethod("steigungenBerechnen", (Class<?>[]) null);
   methode.setAccessible(true);
   double[] steigungen = (double[]) methode.invoke(this.schlupfbezifferungController, (Object[]) null);
   
   // Es wird überprüft, ob die Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten korrekt 
   // berechnet worden sind
   assertEquals(3, steigungen.length);
   assertEquals(-0.2115, steigungen[0], 0.2115 / 1000.0);
   assertEquals(-0.3451, steigungen[1], 0.3451 / 1000.0);
   assertEquals(-0.5345, steigungen[2], 0.5345 / 1000.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link SchlupfbezifferungController#steigungenBerechnen()} für den Fall, dass die Ausnahme 
 * geworfen wird
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 */
@Test
public void testSteigungenBerechnenMitException() throws NoSuchMethodException, SecurityException, 
   IllegalAccessException, IllegalArgumentException, InvocationTargetException
   {         
   // Das Inversionszentrum (in A) wird berechnet und im Datenmodell der Schlupfbezifferungsbestimmung gespeichert.
   Method methodeInversionszentrumBerechnen = 
      SchlupfbezifferungController.class.getDeclaredMethod("inversionszentrumBerechnen", (Class<?>[]) null);
   methodeInversionszentrumBerechnen.setAccessible(true);
   Vector2D inversionszentrum = 
      (Vector2D) methodeInversionszentrumBerechnen.invoke(this.schlupfbezifferungController, (Object[]) null);
   this.testSchlupfbezifferungModell.setInversionszentrum(inversionszentrum);
   
   // Ein in diesem Test verwendeter Betriebspunkt wird erzeugt und im Datenmodell der Schlupfbezifferungsbestimmung
   // gespeichert.
   Betriebspunkt testBetriebspunkt = new Betriebspunkt(new Vector2D(inversionszentrum.getX(), 1.2989), 3.3633E-2);
   this.testSchlupfbezifferungModell.getBetriebspunkte()[1] = testBetriebspunkt;
   
   // Die zu testende Methode wird aufgerufen.
   try
      {
      Method methode = SchlupfbezifferungController.class.getDeclaredMethod("steigungenBerechnen", (Class<?>[]) null);
      methode.setAccessible(true);
      methode.invoke(this.schlupfbezifferungController, (Object[]) null);
      
      // Falls keine Ausnahme geworfen worden ist, liegt ein Fehler vor.
      Assert.fail("Eine Ausnahme hätte geworfen werden müssen!");
      }
   catch (Exception e)
      {
      System.out.println(e.getCause().getMessage());
      
      // Anhand des Fehlertextes wird überprüft, ob die korrekte Ausnahme geworfen worden ist.
      assertTrue(e.getCause().getMessage().contains("liegt über dem Inversionszentrum"));
      }
   }
}
