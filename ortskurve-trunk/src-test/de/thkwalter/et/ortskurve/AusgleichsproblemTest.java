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
package de.thkwalter.et.ortskurve;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Ausgleichsproblem}.
 * 
 * @author Th. K. Walter
 * @version 1.0
 */
public class AusgleichsproblemTest
{
/**
 * Das Testobjekt der Klasse {@link Ausgleichsproblem}
 */
private Ausgleichsproblem ausgleichsproblem;

/**
 * Die in den Tests verwendeten Stromstärken (in A) im Format (-Im I1, Re I1).
 */
private Vector2D[] testMesspunkte;

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
   // Die in den Tests verwendeten Stromstärken (in A) im Format (-Im I1, Re I1) werden erzeugt.
   this.testMesspunkte = new Vector2D[]{new Vector2D(1.1, 0.0), new Vector2D(2.0, 1.1), new Vector2D(2.9, 0.0), 
      new Vector2D(2.0, -1.1)};
   
   // Das Testobjekt wird erzeugt.
   this.ausgleichsproblem = new Ausgleichsproblem(this.testMesspunkte);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test des Konstruktors {@link Ausgleichsproblem#Ausgleichsproblem()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testAusgleichsproblem() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Es wird überprüft, ob das Testobjekt erzeugt worden ist.
   assertNotNull(this.ausgleichsproblem);
   
   // Die in den verschiedenen Betriebspunkten gemessenen Stromstärken (in A) im Format (-Im I1, Re I1) werden gelesen.
   Field feld = Ausgleichsproblem.class.getDeclaredField("messpunkte");
   feld.setAccessible(true);
   Vector2D[] messpunkte = (Vector2D[]) feld.get(this.ausgleichsproblem);
   
   // Es wird überprüft, ob die in den verschiedenen Betriebspunkten gemessenen Stromstärken (in A) im Format 
   // (-Im I1, Re I1) korrekt initialisiert worden sind.
   assertArrayEquals(this.testMesspunkte, messpunkte);
   
   // Die Gewichte in der Ausgleichsrechnung werden gelesen.
   feld = Ausgleichsproblem.class.getDeclaredField("gewichte");
   feld.setAccessible(true);
   double[] gewichte = (double[]) feld.get(this.ausgleichsproblem);
   
   // Es wird überprüft, ob die Gewichte in der Ausgleichsrechnung korrekt erzeugt worden sind.
   for (double gewicht : gewichte)
      {
      assertEquals(1.0, gewicht, 0.0);
      }
   
   // Die Zielwerte in der Ausgleichsrechnung werden gelesen.
   feld = Ausgleichsproblem.class.getDeclaredField("zielwerte");
   feld.setAccessible(true);
   double[] zielwerte = (double[]) feld.get(this.ausgleichsproblem);
   
   // Es wird überprüft, ob die Gewichte in der Ausgleichsrechnung korrekt erzeugt worden sind.
   for (double zielwert : zielwerte)
      {
      assertEquals(0.0, zielwert, 0.0);
      }
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Ausgleichsproblem#ausgleichsproblemLoesen()} für den Fall, dass das 3d-Ausgleichsproblem
 * gelöst werden soll.
 */
@Test
public void testAusgleichsproblemLoesen1()
   {
   // Der in diesem Test verwendete Startpunkt wird erzeugt.
   double[] testStartpunkt = new double[]{1.7, 0.3, 1.3};
   
   // Die zu testende Methode wird aufgerufen.
   Ortskurve ortskurve = this.ausgleichsproblem.ausgleichsproblemLoesen(testStartpunkt,
      Ausgleichsproblemtyp.ORTSKURVE_3d);
   
   // Es wird überprüft, ob die Ortskurve korrekt berechnet worden ist.
   assertNotNull(ortskurve);
   assertEquals(2.0, ortskurve.getMittelpunktOrtskurve().getX(), 2.0/1000);
   assertEquals(0.0, ortskurve.getMittelpunktOrtskurve().getY(), 2.0/1000);
   assertEquals(1.0, ortskurve.getRadiusOrtskurve(), 1.0/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Ausgleichsproblem#ausgleichsproblemLoesen()} für den Fall, dass das 2d-Ausgleichsproblem
 * gelöst werden soll.
 */
@Test
public void testAusgleichsproblemLoesen2()
   {
   // Die in den Tests verwendeten Stromstärken (in A) im Format (-Im I1, Re I1) werden erzeugt.
   this.testMesspunkte = new Vector2D[]{new Vector2D(1.1, -0.5), new Vector2D(2.0, 0.6), new Vector2D(2.9, -0.5), 
      new Vector2D(2.0, -1.6)};
   
   // Das Testobjekt wird erzeugt.
   this.ausgleichsproblem = new Ausgleichsproblem(this.testMesspunkte);
   
   // Der in diesem Test verwendete Startpunkt wird erzeugt.
   double[] testStartpunkt = new double[]{1.8, 1};
   
   // Die zu testende Methode wird aufgerufen.
   Ortskurve ortskurve = this.ausgleichsproblem.ausgleichsproblemLoesen(testStartpunkt,
      Ausgleichsproblemtyp.ORTSKURVE_2d);
   
   // Es wird überprüft, ob die Ortskurve korrekt berechnet worden ist.
   assertNotNull(ortskurve);
   assertEquals(2.0, ortskurve.getMittelpunktOrtskurve().getX(), 2.0/1000);
   assertEquals(0.0, ortskurve.getMittelpunktOrtskurve().getY(), 2.0/1000);
   }
}
