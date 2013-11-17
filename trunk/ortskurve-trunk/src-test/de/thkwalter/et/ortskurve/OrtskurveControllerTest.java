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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link OrtskurveController}.
 *
 * @author Th. K. Walter
 */
public class OrtskurveControllerTest
{
/**
 * Ein Objekt der zu testenden Klasse.
 */
private OrtskurveController ortskurveController;

/**
 * Das im Test verwendete Datenmodell der Ortskurve.
 */
private OrtskurveModell testOrtskurveModell;

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
   this.ortskurveController = new OrtskurveController();
   
   // Das im Test verwendete Datenmodell der Ortskurve wird erzeugt und im Objekt der zu testenden Klasse gespeichert.
   this.testOrtskurveModell = new OrtskurveModell();
   Field ortskurveModellFeld = OrtskurveController.class.getDeclaredField("ortskurveModell");
   ortskurveModellFeld.setAccessible(true);
   ortskurveModellFeld.set(this.ortskurveController, this.testOrtskurveModell);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveController#getOrtskurveModell()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetOrtskurveModell() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Das Datenmodell der Ortskurvenberechnung wird im Controller gespeichert.
   Field ortskurveModellFeld = OrtskurveController.class.getDeclaredField("ortskurveModell");
   ortskurveModellFeld.setAccessible(true);
   ortskurveModellFeld.set(this.ortskurveController, this.testOrtskurveModell);
   
   // Die zu testende Methode wird aufgerufen.
   OrtskurveModell ortskurveModell = this.ortskurveController.getOrtskurveModell();
   
   // Es wird überprüft, ob das Datenmodell der Ortskurvenberechnung korrekt zurückgegeben worden ist.
   assertEquals(this.testOrtskurveModell, ortskurveModell);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveController#setOrtskurveModell(OrtskurveModell)}.
 */
@Test
public void testSetOrtskurveModell()
   {
   // Die zu testende Methode wird aufgerufen.
   this.ortskurveController.setOrtskurveModell(this.testOrtskurveModell);
   
   // Es wird überprüft, ob das Datenmodell der Ortskurvenberechnung korrekt gespeichert worden ist.
   assertEquals(this.testOrtskurveModell, this.ortskurveController.getOrtskurveModell());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveController#problem2dLoesen()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testProblem2dLoesen() throws NoSuchMethodException, SecurityException, IllegalAccessException, 
   IllegalArgumentException, InvocationTargetException
   {
   // Die im Test verwendeten Messpunkte werden erzeugt
   // Vector2D[] testMesspunkte = new Vector2D[]{new Vector2D(1.0, -0.5), new Vector2D(2.0, 0.5), new Vector2D(3.0, -0.5)};
   
   // Die im Test verwendeten Messpunkte werden im Frontend-Modell gespeichert.
   // this.testOrtskurveModell.setMesspunkte(testMesspunkte);
   
   // Die zu testende Methode wird aufgerufen.
   Method methode = OrtskurveController.class.getDeclaredMethod("problem2dLoesen", new Class[0]);
   methode.setAccessible(true);
   Ortskurve ortskurve2d = (Ortskurve) methode.invoke(this.ortskurveController, new Object[0]);
   
   // Es wird überprüft, ob die Ortskurve der 2d-Berechnung korrekt erstellt worden ist.
   assertNotNull(ortskurve2d);
   }
}
