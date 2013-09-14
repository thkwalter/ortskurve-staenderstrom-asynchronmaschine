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
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Ausgleichsproblem}.
 *
 * @author Th. K. Walter
 */
public class AusgleichsproblemTest
{
/**
 * Ein Objekt der zu testenden Klasse.
 */
private Ausgleichsproblem ausgleichsproblem;

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
   this.ausgleichsproblem = new Ausgleichsproblem();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#problemLoesen()}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testProblemLoesen1() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Das Feld der Messpunkte wird initialisiert.
   this.ausgleichsproblem.setMesspunkte(new Vector2D[]{new Vector2D(0.1, 0.0), new Vector2D(2.0, 2.1), 
      new Vector2D(3.9, 0.0), new Vector2D(2.0, -2.1)});
   
   // Die zu testende Methode wird ausgeführt.
   this.ausgleichsproblem.problemLoesen();
   
   // Die berechnete x-Komponente des Mittelpunkts wird überprüft.
   Field mxAttribut = Ausgleichsproblem.class.getDeclaredField("mx");
   mxAttribut.setAccessible(true);
   assertEquals(2.0, mxAttribut.getDouble(this.ausgleichsproblem), 2.0/1000);
   
   // Die berechnete y-Komponente des Mittelpunkts wird überprüft.
   Field myAttribut = Ausgleichsproblem.class.getDeclaredField("my");
   myAttribut.setAccessible(true);
   assertEquals(0.0, myAttribut.getDouble(this.ausgleichsproblem), 2.0/1000);
   
   // Der berechnete Radius wird überprüft.
   Field rAttribut = Ausgleichsproblem.class.getDeclaredField("r");
   rAttribut.setAccessible(true);
   assertEquals(2.0, rAttribut.getDouble(this.ausgleichsproblem), 2.0/1000);
   
   // Es wird überprüft, ob das Flag korrekt gesetzt ist.
   assertTrue(this.ausgleichsproblem.isLoesungAnzeigen());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#problemLoesen()}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testProblemLoesen2() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Das Feld der Messpunkte wird initialisiert.
   this.ausgleichsproblem.setMesspunkte(new Vector2D[]{new Vector2D(-5.0, 0.0), new Vector2D(0.0, 5.0), 
      new Vector2D(5.0, 0.0)});
   
   // Die zu testende Methode wird ausgeführt.
   this.ausgleichsproblem.problemLoesen();
   
   // Die berechnete x-Komponente des Mittelpunkts wird überprüft.
   Field mxAttribut = Ausgleichsproblem.class.getDeclaredField("mx");
   mxAttribut.setAccessible(true);
   assertEquals(0.0, mxAttribut.getDouble(this.ausgleichsproblem), 1.0/1000);
   
   // Die berechnete y-Komponente des Mittelpunkts wird überprüft.
   Field myAttribut = Ausgleichsproblem.class.getDeclaredField("my");
   myAttribut.setAccessible(true);
   assertEquals(0.0, myAttribut.getDouble(this.ausgleichsproblem), 1.0/1000);
   
   // Der berechnete Radius wird überprüft.
   Field rAttribut = Ausgleichsproblem.class.getDeclaredField("r");
   rAttribut.setAccessible(true);
   assertEquals(5.0, rAttribut.getDouble(this.ausgleichsproblem), 5.0/1000);
   
   // Es wird überprüft, ob das Flag korrekt gesetzt ist.
   assertTrue(this.ausgleichsproblem.isLoesungAnzeigen());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#problemLoesen()}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testProblemLoesen3() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Das Feld der Messpunkte wird initialisiert.
   this.ausgleichsproblem.setMesspunkte(new Vector2D[]{new Vector2D(1.0, 0.0), new Vector2D(5.0, 4.0), 
      new Vector2D(9.0, 0.0), new Vector2D(5.0, -4.0)});
   
   // Die zu testende Methode wird ausgeführt.
   this.ausgleichsproblem.problemLoesen();
   
   // Die berechnete x-Komponente des Mittelpunkts wird überprüft.
   Field mxAttribut = Ausgleichsproblem.class.getDeclaredField("mx");
   mxAttribut.setAccessible(true);
   assertEquals(5.0, mxAttribut.getDouble(this.ausgleichsproblem), 5.0/1000);
   
   // Die berechnete y-Komponente des Mittelpunkts wird überprüft.
   Field myAttribut = Ausgleichsproblem.class.getDeclaredField("my");
   myAttribut.setAccessible(true);
   assertEquals(0.0, myAttribut.getDouble(this.ausgleichsproblem), 4.0/1000);
   
   // Der berechnete Radius wird überprüft.
   Field rAttribut = Ausgleichsproblem.class.getDeclaredField("r");
   rAttribut.setAccessible(true);
   assertEquals(4.0, rAttribut.getDouble(this.ausgleichsproblem), 4.0/1000);
   
   // Es wird überprüft, ob das Flag korrekt gesetzt ist.
   assertTrue(this.ausgleichsproblem.isLoesungAnzeigen());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#getOrtskurveModell()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetOrtskurveModell() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException 
   {
   // Das Datenmodell der Ortskurve wird erzeugt.
   OrtskurveModell ortskurveModell = new OrtskurveModell();
   
   // Das Datenmodell der Ortskurve wird an das Objekt der zu testenden Klasse übergeben.
   Field ortskurveFeld = Ausgleichsproblem.class.getDeclaredField("ortskurveModell");
   ortskurveFeld.setAccessible(true);
   ortskurveFeld.set(this.ausgleichsproblem, ortskurveModell);
   
   // Es wird überprüft, ob das Datenmodell der Ortskurve korrekt zurückgegeben wird.
   assertEquals(ortskurveModell, this.ausgleichsproblem.getOrtskurveModell());
   }
}
