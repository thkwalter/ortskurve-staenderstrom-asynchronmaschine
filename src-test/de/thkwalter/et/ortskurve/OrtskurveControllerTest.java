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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import de.thkwalter.jsf.ApplicationRuntimeException;

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
 * Test für die Methode {@link OrtskurveController#messpunkteValidieren(Vector2D[])}.
 * 
 * @throws Throwable 
 */
@Test(expected=ApplicationRuntimeException.class)
public void testMesspunkteValidieren1() throws Throwable
   {
   // Die in diesem Test verwendeten Messpunkte werden erzeugt.
   Vector2D[] testMesspunkte = new Vector2D[]{new Vector2D(1.0, 0.0), new Vector2D(3.0, 0.0), new Vector2D(1, 0)};
   
   try
      {
      // Die zu testende Methode wird aufgerufen.
      Method methode = OrtskurveController.class.getDeclaredMethod("messpunkteValidieren", Vector2D[].class);
      methode.setAccessible(true);
      methode.invoke(this.ortskurveController, (Object) testMesspunkte);
      }
   
   // Die InvocationTargetException wird gefangen und die ursprüngliche Ausnahme weitergeworfen.
   catch (InvocationTargetException invocationTargetException)
      {
      throw invocationTargetException.getCause();
      }
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveController#messpunkteValidieren(Vector2D[])}.
 * 
 * @throws Throwable 
 */
@Test
public void testMesspunkteValidieren2() throws Throwable
   {
   // Die in diesem Test verwendeten Messpunkte werden erzeugt.
   Vector2D[] testMesspunkte = new Vector2D[]{new Vector2D(1.0, 0.0), new Vector2D(3.0, 0.0), new Vector2D(2, 1)};
   
   // Die zu testende Methode wird aufgerufen.
   Method methode = OrtskurveController.class.getDeclaredMethod("messpunkteValidieren", Vector2D[].class);
   methode.setAccessible(true);
   methode.invoke(this.ortskurveController, (Object) testMesspunkte);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveController#ortskurveBestimmen(Vector2D[], double[])}.
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testOrtskurveBestimmen() throws NoSuchMethodException, SecurityException, IllegalAccessException, 
   IllegalArgumentException, InvocationTargetException
   {
   // Die in diesem Test verwendeten Messpunkte werden deklariert.
   Vector2D[] testMesspunkte = new Vector2D[]{new Vector2D(2.0, 0.0), new Vector2D(3.0, 1.0), new Vector2D(3.0, -1.0)};
   
   // Der in diesem Test verwendete Startpunkt wird deklariert.
   double[] testStartpunkt = new double[]{3.0, 0.0, 1.0};
   
   // Die zu testenden Methode wird aufgerufen.
   Method methode = OrtskurveController.class.getDeclaredMethod("ortskurveBestimmen", Vector2D[].class, double[].class);
   methode.setAccessible(true);
   Ortskurve ortskurve = (Ortskurve) methode.invoke(this.ortskurveController, testMesspunkte, testStartpunkt);
   
   // Es wird überprüft, ob der Startpunkt als Ortskurve zurückgegeben worden ist.
   assertEquals(testStartpunkt[0], ortskurve.getMittelpunktOrtskurve().getX(), 0.0);
   assertEquals(testStartpunkt[1], ortskurve.getMittelpunktOrtskurve().getY(), 0.0);
   assertEquals(testStartpunkt[2], ortskurve.getRadiusOrtskurve(), 0.0);
   }
}
