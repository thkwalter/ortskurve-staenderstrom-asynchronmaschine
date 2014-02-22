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
package de.thkwalter.et.ortskurve;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import de.thkwalter.jsf.ApplicationRuntimeException;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Leerlauf}.
 * 
 * @author Th. K. Walter
 */
public class LeerlaufTest 
{
/**
 * Der Prüfling der Klasse {@link Leerlauf}
 */
private Leerlauf leerlauf;

// =====================================================================================================================
// =====================================================================================================================

/* 
 * Diese Methode initialisiert die Tests.
 */
@Before
public void setUp() throws Exception
   {
   // Der Prüfling wird erzeugt.
   this.leerlauf = new Leerlauf(new Ortskurve(new Vector2D(11.0, 6.0), 10.0));
   }

// =====================================================================================================================
// =====================================================================================================================

///**
// * Test für der Konstruktor {@link Leerlauf#Leerlauf(Ortskurve)}.
// */
//@Test
//public void testLeerlauf()
//   {
//   fail("Not yet implemented");
//   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Leerlauf#schnittpunktOrtskuveXAchseBerechnen(Ortskurve)}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testSchnittpunktOrtskuveXAchseBerechnen1() throws NoSuchMethodException, SecurityException, 
   IllegalAccessException, IllegalArgumentException, InvocationTargetException
   {
   // Die zu testende Methode wird aufgerufen.
   Method methode = Leerlauf.class.getDeclaredMethod("schnittpunktOrtskuveXAchseBerechnen", Ortskurve.class);
   methode.setAccessible(true);
   double schnittpunkt = (Double) methode.invoke(this.leerlauf, new Ortskurve(new Vector2D(11.0, 6.0), 10.0));
   
   // Es wird überprüft, ob der Schnittpunkt korrekt berechnet worden ist.
   assertEquals(3.0, schnittpunkt, 3.0 / 1000.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Leerlauf#schnittpunktOrtskuveXAchseBerechnen(Ortskurve)} für den Fall, dass die Ortskurve
 * nicht die x-Achse schneidet.
 * 
 * @throws Throwable 
 */
@Test(expected=ApplicationRuntimeException.class)
public void testSchnittpunktOrtskuveXAchseBerechnen2() throws Throwable
   {
   try
      {
      // Die zu testende Methode wird aufgerufen.
      Method methode = Leerlauf.class.getDeclaredMethod("schnittpunktOrtskuveXAchseBerechnen", Ortskurve.class);
      methode.setAccessible(true);
      methode.invoke(this.leerlauf, new Ortskurve(new Vector2D(11.0, 6.0), 1.0));
      }
   catch (InvocationTargetException invocationTargetException)
      {
      throw invocationTargetException.getCause();
      }
   }

// =====================================================================================================================
// =====================================================================================================================

///**
// * Test der Methode {@link Leerlauf#toString()}.
// */
//@Test
//public void testToString()
//   {
//   fail("Not yet implemented");
//   }
}
