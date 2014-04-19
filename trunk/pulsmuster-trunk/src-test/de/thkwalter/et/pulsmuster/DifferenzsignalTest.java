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
package de.thkwalter.et.pulsmuster;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Differenzsignal}.
 * 
 * @author Th. K. Walter
 */
public class DifferenzsignalTest
{
/**
 * Der Prüfling der Klasse {@link Differenzsignal}
 */
private Differenzsignal differenzsignal;

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
   // Der Prüfling wird erzeugt.
   this.differenzsignal = new Differenzsignal(2.0944, 0.5, 2.3038, -0.5);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test des Konstruktors {@link Differenzsignal#Differenzsignal(double, double, double, double)} im Fall einer fallenden
 * Flanke.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testDifferenzsignal1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Es wird überprüft, ob der Prüfling erzeugt worden ist.
   assertNotNull(this.differenzsignal);
   // Die Steigung der Flanke wird gelesen.
   Field mFeld = Differenzsignal.class.getDeclaredField("m");
   mFeld.setAccessible(true);
   double m = mFeld.getDouble(this.differenzsignal);
   
   // Es wird überprüft, ob die Steigung der Flanke korrekt berechnet worden ist.
   assertEquals(-4.775, m, 4.775/1000.0);
   
   // Der Achsenabschnitt der Flanke wird gelesen.
   Field tFeld = Differenzsignal.class.getDeclaredField("t");
   tFeld.setAccessible(true);
   double t = tFeld.getDouble(this.differenzsignal);
   
   // Es wird überprüft, ob der Achsenabschnitt korrekt berechnet worden ist.
   assertEquals(10.5, t, 10.5/1000.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test des Konstruktors {@link Differenzsignal#Differenzsignal(double, double, double, double)} im Fall einer 
 * steigenden Flanke.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testDifferenzsignal2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Ein Prüfling wird erzeugt.
   Differenzsignal differenzsignal2 = new Differenzsignal(6.0737, -0.5, 6.2832, 0.5);
   
   // Es wird überprüft, ob der Prüfling erzeugt worden ist.
   assertNotNull(differenzsignal2);
   
   // Die Steigung der Flanke wird gelesen.
   Field mFeld = Differenzsignal.class.getDeclaredField("m");
   mFeld.setAccessible(true);
   double m = mFeld.getDouble(differenzsignal2);
   
   // Es wird überprüft, ob die Steigung der Flanke korrekt berechnet worden ist.
   assertEquals(4.775, m, 4.775/1000.0);
   
   // Der Achsenabschnitt der Flanke wird gelesen.
   Field tFeld = Differenzsignal.class.getDeclaredField("t");
   tFeld.setAccessible(true);
   double t = tFeld.getDouble(differenzsignal2);
   
   // Es wird überprüft, ob der Achsenabschnitt korrekt berechnet worden ist.
   assertEquals(-29.5, t, 29.5/1000.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Differenzsignal#value(double)}.
 */
@Test
public void testValue()
   {
   // Die zu testende Methode wird aufgerufen.
   double differenz = this.differenzsignal.value(2.2);
   
   // Es wird überprüft, ob der Wert des Differenzsignals korrekt berechnet wird.
   assertEquals(-0.8127, differenz, 0.8127 / 1000.0);
   }
}
