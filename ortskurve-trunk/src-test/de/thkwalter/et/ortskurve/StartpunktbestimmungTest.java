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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import de.thkwalter.jsf.ApplicationRuntimeException;

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
   this.testMesspunkte = new Vector2D[]{new Vector2D(0.0, 0.0), new Vector2D(2.0, 2.0), new Vector2D(4.0, 0.0)};
   
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
public void testStartpunktbestimmung1() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
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
 * Test für die Methode {@link Startpunktbestimmung#Startpunktbestimmung(Vector2D[])}.
 * 
 * @throws ApplicationRuntimeException 
 */
@Test(expected=ApplicationRuntimeException.class)
public void testStartpunktbestimmung2() throws ApplicationRuntimeException
   {   
   // Es wird überprüft, ob der Konstruktor eine Ausnahme wirft, wenn weniger als drei Messpunkte übergeben werden.
   new Startpunktbestimmung(new Vector2D[]{new Vector2D(0.0, 0.0), new Vector2D(1.0, 1.0)});
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#Startpunktbestimmung(Vector2D[])}.
 * 
 * @throws ApplicationRuntimeException 
 */
@Test(expected=ApplicationRuntimeException.class)
public void testStartpunktbestimmung3() throws ApplicationRuntimeException
   {   
   // Es wird überprüft, ob der Konstruktor eine Ausnahme wirft, wenn null übergeben wird.
   new Startpunktbestimmung(null);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#startpunktBestimmen()}.
 * @throws ApplicationRuntimeException 
 */
@Test
public void testStartpunktBestimmen1() throws ApplicationRuntimeException
   {
   // Der Startpunkt wird berechnet.
   double[] startpunkt = this.startpunktbestimmung.startpunktBestimmen();
   
   assertEquals(2.0, startpunkt[0], 2.0/1000);
   assertEquals(0.0, startpunkt[1], 2.0/1000);
   assertEquals(2.0, startpunkt[2], 2.0/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#startpunktBestimmen()}.
 * 
 * @throws ApplicationRuntimeException 
 */
@Test(expected=ApplicationRuntimeException.class)
public void testStartpunktBestimmen4() throws ApplicationRuntimeException
   {   
   // Das Objekt, das für diesen Test verwendet wird, wird erzeugt.
   Startpunktbestimmung lokaleStartpunktbestimmung = 
      new Startpunktbestimmung(new Vector2D[]{new Vector2D(1.0, 0.0), new Vector2D(2.0, 0.0), new Vector2D(3.0, 0.0)});
   
   // Es wird getestet, ob eine ApplicationRuntimeException geworfen wird, falls die Matrix
   lokaleStartpunktbestimmung.startpunktBestimmen();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#mittlerenMesspunktAuswaehlen(Vector2D[], double)}.
 * 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 * @throws NoSuchFieldException 
 */
@Test
public void testMittlerenMesspunktAuswaehlen1() throws SecurityException, NoSuchMethodException, 
   IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException
   {
   // Der Messpunkt, der von der Methode gefunden werden soll.
   Vector2D[] messpunkte = new Vector2D[]{new Vector2D(0.0, 0.1), new Vector2D(1.0, 1.0), new Vector2D(1.0, -1.0)};
   
   // Die zu testende Methode wird aufgerufen
   Method methode = 
      Startpunktbestimmung.class.getDeclaredMethod("mittlerenMesspunktAuswaehlen", Vector2D[].class, double.class);
   methode.setAccessible(true);
   Vector2D mittlererMesspunkt = (Vector2D) methode.invoke(this.startpunktbestimmung, messpunkte, 0.0);
   
   // Es wird geprüft, ob der korrekte Messpunkt gefunden worden ist.
   assertEquals(messpunkte[0], mittlererMesspunkt);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#mittlerenMesspunktAuswaehlen(Vector2D[], double)}.
 * 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 * @throws NoSuchFieldException 
 */
@Test
public void testMittlerenMesspunktAuswaehlen2() throws SecurityException, NoSuchMethodException, 
   IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException
   {
   // Der Messpunkt, der von der Methode gefunden werden soll.
   Vector2D[] messpunkte = 
      new Vector2D[]{new Vector2D(1.0, 1.0), new Vector2D(1.0, -1.0), new Vector2D(0.0, -0.1), new Vector2D(0.0, -0.2)};
   
   // Die zu testende Methode wird aufgerufen
   Method methode = 
      Startpunktbestimmung.class.getDeclaredMethod("mittlerenMesspunktAuswaehlen", Vector2D[].class, double.class);
   methode.setAccessible(true);
   Vector2D mittlererMesspunkt = (Vector2D) methode.invoke(this.startpunktbestimmung, messpunkte, 0.0);
   
   // Es wird geprüft, ob der korrekte Messpunkt gefunden worden ist.
   assertEquals(messpunkte[2], mittlererMesspunkt);
   }
}