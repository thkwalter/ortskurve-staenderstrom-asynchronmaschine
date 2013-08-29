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
package de.thkwalter.koordinatensystem;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link PunktPixelKonverter}.
 *
 * @author Th. K. Walter
 */
public class PunktPixelKonverterTest
{
/**
 * Test für den Konstruktor {@link PunktPixelKonverter#PunktPixelKonverter(Wertebereich, double, double)}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testPunktPixelKonverter1() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Der darzustellende Wertebereich wird definiert.
   Wertebereich wertebereich = new Wertebereich(10.0, 10.0, 0.0, -10.0);
   
   // Der zu testende Konstruktor wird aufgerufen.
   PunktPixelKonverter punktPixelKonverter = new PunktPixelKonverter(wertebereich, 200, 200);
   
   // E wird überprüft, ob der korrekte Skalierungsfaktor berechnet worden ist.
   Field skalierungsfaktorFeld = PunktPixelKonverter.class.getDeclaredField("skalierungsfaktor");
   skalierungsfaktorFeld.setAccessible(true);
   double skalierungsfaktor = (Double) skalierungsfaktorFeld.get(punktPixelKonverter);
   assertEquals(10.0, skalierungsfaktor, 10.0/1000);
   
   // Es wird überprüft, ob der korrekte Ursprung in Pixeln berechnet worden ist.
   Field ursprungInPixelFeld = PunktPixelKonverter.class.getDeclaredField("ursprungInPixel");
   ursprungInPixelFeld.setAccessible(true);
   Vector2D ursprungInPixel = (Vector2D) ursprungInPixelFeld.get(punktPixelKonverter);
   assertEquals(50, ursprungInPixel.getX(), 50/1000);
   assertEquals(100, ursprungInPixel.getY(), 100/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link PunktPixelKonverter#PunktPixelKonverter(Wertebereich, double, double)}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testPunktPixelKonverter2() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Der darzustellende Wertebereich wird definiert.
   Wertebereich wertebereich = new Wertebereich(10.0, 5.0, -30.0, -15.0);
   
   // Der zu testende Konstruktor wird aufgerufen.
   PunktPixelKonverter punktPixelKonverter = new PunktPixelKonverter(wertebereich, 200, 200);
   
   // E wird überprüft, ob der korrekte Skalierungsfaktor berechnet worden ist.
   Field skalierungsfaktorFeld = PunktPixelKonverter.class.getDeclaredField("skalierungsfaktor");
   skalierungsfaktorFeld.setAccessible(true);
   double skalierungsfaktor = (Double) skalierungsfaktorFeld.get(punktPixelKonverter);
   assertEquals(5.0, skalierungsfaktor, 5.0/1000);
   
   // Es wird überprüft, ob der korrekte Ursprung in Pixeln berechnet worden ist.
   Field ursprungInPixelFeld = PunktPixelKonverter.class.getDeclaredField("ursprungInPixel");
   ursprungInPixelFeld.setAccessible(true);
   Vector2D ursprungInPixel = (Vector2D) ursprungInPixelFeld.get(punktPixelKonverter);
   assertEquals(150, ursprungInPixel.getX(), 150/1000);
   assertEquals(75, ursprungInPixel.getY(), 75/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link PunktPixelKonverter#PunktPixelKonverter(Wertebereich, double, double)}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testPunktPixelKonverter3() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Der darzustellende Wertebereich wird definiert.
   Wertebereich wertebereich = new Wertebereich(0.0, 5.0, -20.0, -5.0);
   
   // Der zu testende Konstruktor wird aufgerufen.
   PunktPixelKonverter punktPixelKonverter = new PunktPixelKonverter(wertebereich, 200, 200);
   
   // E wird überprüft, ob der korrekte Skalierungsfaktor berechnet worden ist.
   Field skalierungsfaktorFeld = PunktPixelKonverter.class.getDeclaredField("skalierungsfaktor");
   skalierungsfaktorFeld.setAccessible(true);
   double skalierungsfaktor = (Double) skalierungsfaktorFeld.get(punktPixelKonverter);
   assertEquals(10.0, skalierungsfaktor, 10.0/1000);
   
   // Es wird überprüft, ob der korrekte Ursprung in Pixeln berechnet worden ist.
   Field ursprungInPixelFeld = PunktPixelKonverter.class.getDeclaredField("ursprungInPixel");
   ursprungInPixelFeld.setAccessible(true);
   Vector2D ursprungInPixel = (Vector2D) ursprungInPixelFeld.get(punktPixelKonverter);
   assertEquals(200, ursprungInPixel.getX(), 200/1000);
   assertEquals(100, ursprungInPixel.getY(), 100/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link PunktPixelKonverter#PunktPixelKonverter(Wertebereich, double, double)}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testPunktPixelKonverter4() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Der darzustellende Wertebereich wird definiert.
   Wertebereich wertebereich = new Wertebereich(20.0, 7.5, 0.0, -2.5);
   
   // Der zu testende Konstruktor wird aufgerufen.
   PunktPixelKonverter punktPixelKonverter = new PunktPixelKonverter(wertebereich, 200, 200);
   
   // E wird überprüft, ob der korrekte Skalierungsfaktor berechnet worden ist.
   Field skalierungsfaktorFeld = PunktPixelKonverter.class.getDeclaredField("skalierungsfaktor");
   skalierungsfaktorFeld.setAccessible(true);
   double skalierungsfaktor = (Double) skalierungsfaktorFeld.get(punktPixelKonverter);
   assertEquals(10.0, skalierungsfaktor, 10.0/1000);
   
   // Es wird überprüft, ob der korrekte Ursprung in Pixeln berechnet worden ist.
   Field ursprungInPixelFeld = PunktPixelKonverter.class.getDeclaredField("ursprungInPixel");
   ursprungInPixelFeld.setAccessible(true);
   Vector2D ursprungInPixel = (Vector2D) ursprungInPixelFeld.get(punktPixelKonverter);
   assertEquals(0, ursprungInPixel.getX(), 0.0);
   assertEquals(125, ursprungInPixel.getY(), 125/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link PunktPixelKonverter#PunktPixelKonverter(Wertebereich, double, double)}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testPunktPixelKonverter5() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Der darzustellende Wertebereich wird definiert.
   Wertebereich wertebereich = new Wertebereich(2.5, 0.0, -2.5, -10.0);
   
   // Der zu testende Konstruktor wird aufgerufen.
   PunktPixelKonverter punktPixelKonverter = new PunktPixelKonverter(wertebereich, 200, 200);
   
   // E wird überprüft, ob der korrekte Skalierungsfaktor berechnet worden ist.
   Field skalierungsfaktorFeld = PunktPixelKonverter.class.getDeclaredField("skalierungsfaktor");
   skalierungsfaktorFeld.setAccessible(true);
   double skalierungsfaktor = (Double) skalierungsfaktorFeld.get(punktPixelKonverter);
   assertEquals(20.0, skalierungsfaktor, 20.0/1000);
   
   // Es wird überprüft, ob der korrekte Ursprung in Pixeln berechnet worden ist.
   Field ursprungInPixelFeld = PunktPixelKonverter.class.getDeclaredField("ursprungInPixel");
   ursprungInPixelFeld.setAccessible(true);
   Vector2D ursprungInPixel = (Vector2D) ursprungInPixelFeld.get(punktPixelKonverter);
   assertEquals(100, ursprungInPixel.getX(), 100/1000);
   assertEquals(0, ursprungInPixel.getY(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link PunktPixelKonverter#PunktPixelKonverter(Wertebereich, double, double)}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testPunktPixelKonverter6() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Der darzustellende Wertebereich wird definiert.
   Wertebereich wertebereich = new Wertebereich(2.5, 20.0, -2.5, 0.0);
   
   // Der zu testende Konstruktor wird aufgerufen.
   PunktPixelKonverter punktPixelKonverter = new PunktPixelKonverter(wertebereich, 200, 200);
   
   // E wird überprüft, ob der korrekte Skalierungsfaktor berechnet worden ist.
   Field skalierungsfaktorFeld = PunktPixelKonverter.class.getDeclaredField("skalierungsfaktor");
   skalierungsfaktorFeld.setAccessible(true);
   double skalierungsfaktor = (Double) skalierungsfaktorFeld.get(punktPixelKonverter);
   assertEquals(10.0, skalierungsfaktor, 10.0/1000);
   
   // Es wird überprüft, ob der korrekte Ursprung in Pixeln berechnet worden ist.
   Field ursprungInPixelFeld = PunktPixelKonverter.class.getDeclaredField("ursprungInPixel");
   ursprungInPixelFeld.setAccessible(true);
   Vector2D ursprungInPixel = (Vector2D) ursprungInPixelFeld.get(punktPixelKonverter);
   assertEquals(100, ursprungInPixel.getX(), 100/1000);
   assertEquals(200, ursprungInPixel.getY(), 200/1000);
   }
}
