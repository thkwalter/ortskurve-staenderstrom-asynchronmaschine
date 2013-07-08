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

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Startpunktbestimmung}.
 *
 * @author Th. K. Walter
 */
public class StartpunktbestimmungTest
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
private Startpunktbestimmung startpunktbestimmung;

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
   this.testMesspunkte = new Vector2D[]{new Vector2D(0.0, 0.0), new Vector2D(1.0, 1.0), new Vector2D(2.0, 0.0)};
   
   // Das Objekt, das für die Tests verwendet wird, wird erzeugt.
   this.startpunktbestimmung = new Startpunktbestimmung(this.testMesspunkte);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link Startpunktbestimmung(Vector2D[])}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void test() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
   {
   // Der Wert des Attributs messwerte wird gelesen.   
   Field messpunkteFeld = Startpunktbestimmung.class.getDeclaredField("messpunkte");
   messpunkteFeld.setAccessible(true);
   Vector2D[] messpunkte = (Vector2D[]) messpunkteFeld.get(this.startpunktbestimmung);
   
   // Die Messpunkte werden überprüft.
   assertArrayEquals(messpunkte, this.testMesspunkte);  
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#startpunktBestimmen()}.
 */
@Test
public void testStartpunktBestimmen()
   {
   // Der Startpunkt wird berechnet.
   double[] startpunkt = this.startpunktbestimmung.startpunktBestimmen();
   
   assertEquals(1.0, startpunkt[0], 1.0/1000);
   assertEquals(0.0, startpunkt[1], 0.0);
   assertEquals(1.0, startpunkt[2], 1.0/1000);
   }
}
