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

import java.lang.reflect.Field;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import de.thkwalter.jsf.ApplicationRuntimeException;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Jakobimatrix}.
 *
 * @author Th. K. Walter
 */
public class JakobimatrixTest
{
/**
 * Die Messpunkte, die für den Test verwendet werden.
 */
private Vector2D[] testMesspunkte;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Das Objekt, das für die Tests verwendet wird.
 */
private Jakobimatrix jakobimatrix;

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
   // Die Messpunkte werden erzeugt.
   this.testMesspunkte = new Vector2D[]{new Vector2D(0.0, 0.0), new Vector2D(2.0, 1.0)};
   
   // Das Objekt, das für die Tests verwendet wird, wird erzeugt.
   this.jakobimatrix = new Jakobimatrix(this.testMesspunkte);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link Jakobimatrix(Vector2D[])}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testJakobimatrix() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Der Wert des Attributs messwerte wird gelesen.   
   Field messpunkteFeld = Jakobimatrix.class.getDeclaredField("messpunkte");
   messpunkteFeld.setAccessible(true);
   Vector2D[] messpunkte = (Vector2D[]) messpunkteFeld.get(this.jakobimatrix);
   
   // Die Messpunkte werden überprüft.
   assertArrayEquals(messpunkte, this.testMesspunkte);  
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Jakobimatrix#value(double[])}.
 */
@Test
public void testValue1()
   {
   // Die Jakobimatrix wird berechnet.
   double[][] jmatrix = this.jakobimatrix.value(new double[]{1.0, 0.0, 1.0});
   
   // Die Dimensionen der Jakobimatrix werden überprüft.
   assertEquals(3, jmatrix[0].length);
   assertEquals(3, jmatrix[1].length);
   assertEquals(2, jmatrix.length);
   
   // Die Elemente der Jakobimatrix werden überprüft.
   assertEquals(1.0, jmatrix[0][0], 0.0);
   assertEquals(0.0, jmatrix[0][1], 0.0);
   assertEquals(-1.0, jmatrix[0][2], 0.0);
   assertEquals(-0.7071, jmatrix[1][0], 0.7071/1000);
   assertEquals(-0.7071, jmatrix[1][1], 0.7071/1000);
   assertEquals(-1.0, jmatrix[1][2], 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Jakobimatrix#value(double[])}.
 */
@Test(expected=ApplicationRuntimeException.class)
public void testValue2()
   {
   // Es wird getestet, ob eine RuntimeException geworfen wird, wenn ein Messpunkt und der Mittelpunkt übereinstimmen.
   this.jakobimatrix.value(new double[]{2.0, 1.009, 1.0});
   }
}
