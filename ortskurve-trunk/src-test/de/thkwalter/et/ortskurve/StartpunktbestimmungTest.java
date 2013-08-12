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
 * Test für den Konstruktor {@link Startpunktbestimmung#Startpunktbestimmung(Vector2D[])}.
 * 
 * @throws ApplicationRuntimeException 
 */
@Test(expected=ApplicationRuntimeException.class)
public void testStartpunktbestimmung1() throws ApplicationRuntimeException
   {   
   // Es wird überprüft, ob der Konstruktor eine Ausnahme wirft, wenn null übergeben wird.
   new Startpunktbestimmung(null);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link Startpunktbestimmung#Startpunktbestimmung(Vector2D[])}.
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
 * Test für den Konstruktor {@link Startpunktbestimmung#Startpunktbestimmung(Vector2D[])}.
 */
@Test
public void testStartpunktbestimmung3() 
   {
   // Die Startpunktbestimmung wird mit genau drei Messpunkten durchgeführt.
   Startpunktbestimmung startpunktbestimmung = 
      new Startpunktbestimmung(new Vector2D[]{new Vector2D(0.0, 0.0), new Vector2D(2.0, 2.0), new Vector2D(4.0, 0.0)});
   
   // Es wird überprüft, ob der Startpunkt korrekt bestimmt worden ist.
   assertEquals(2.0, startpunktbestimmung.getStartpunkt()[0], 2.0/1000);
   assertEquals(0.0, startpunktbestimmung.getStartpunkt()[1], 2.0/1000);
   assertEquals(2.0, startpunktbestimmung.getStartpunkt()[2], 2.0/1000); 
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#startpunktBestimmen(Vector2D[])}.
 * 
 * @throws ApplicationRuntimeException 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testStartpunktBestimmen1() throws ApplicationRuntimeException, SecurityException, NoSuchMethodException, 
   IllegalArgumentException, IllegalAccessException, InvocationTargetException
   {
   Vector2D[] messpunkteZurStartpunktbestimmung = 
      new Vector2D[]{new Vector2D(0.0, 0.0), new Vector2D(2.0, 2.0), new Vector2D(4.0, 0.0)};
   
   // Die zu testende Methode wird aufgerufen
   Method methode = 
      Startpunktbestimmung.class.getDeclaredMethod("startpunktBestimmen", Vector2D[].class);
   methode.setAccessible(true);
   double[] startpunkt = 
      (double[]) methode.invoke(this.startpunktbestimmung, (Object) messpunkteZurStartpunktbestimmung);
   
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
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test(expected=ApplicationRuntimeException.class)
public void testStartpunktBestimmen4() throws Throwable
   {   
   Vector2D[] messpunkteZurStartpunktbestimmung = 
      new Vector2D[]{new Vector2D(1.0, 0.0), new Vector2D(2.0, 0.0), new Vector2D(3.0, 0.0)};
         
   // Die zu testende Methode wird aufgerufen
   Method methode = 
      Startpunktbestimmung.class.getDeclaredMethod("startpunktBestimmen", Vector2D[].class);
   methode.setAccessible(true);
   try
      {
      methode.invoke(this.startpunktbestimmung, (Object) messpunkteZurStartpunktbestimmung);
      }
   catch (InvocationTargetException invocationTargetException)
      {
      throw invocationTargetException.getCause();
      }
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
   // Die Messpunkte, die für den Test verwendet werden.
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
   // Die Messpunkte, die für den Test verwendet werden.
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

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#messpunkteAuswaehlen(Vector2D[])}.
 * 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 * @throws NoSuchFieldException 
 */
@Test
public void testMesspunkteAuswaehlen() throws SecurityException, NoSuchMethodException, 
   IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException
   {
   // Die Messpunkte, die für den Test verwendet werden.
   Vector2D[] messpunkte = 
      new Vector2D[]{new Vector2D(1.0, 1.0), new Vector2D(1.0, -1.0), new Vector2D(0.0, -0.1), new Vector2D(0.0, -0.2)};
   
   // Die zu testende Methode wird aufgerufen
   Method methode = 
      Startpunktbestimmung.class.getDeclaredMethod("messpunkteAuswaehlen", Vector2D[].class);
   methode.setAccessible(true);
   Vector2D[] messpunkteZurStartpunktbestimmung = 
      (Vector2D[]) methode.invoke(this.startpunktbestimmung, (Object) messpunkte);
   
   // Es wird geprüft, ob die korrekten Messpunkt gefunden worden sind.
   assertEquals(messpunkte[0], messpunkteZurStartpunktbestimmung[0]);
   assertEquals(messpunkte[1], messpunkteZurStartpunktbestimmung[1]);
   assertEquals(messpunkte[2], messpunkteZurStartpunktbestimmung[2]);
   }
}