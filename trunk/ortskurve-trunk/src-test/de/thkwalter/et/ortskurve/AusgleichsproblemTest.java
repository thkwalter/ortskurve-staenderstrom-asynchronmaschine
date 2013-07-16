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
 * Die Zeichenkette, welche die Messpunkte enthält.
 */
String eingabefeld;


/**
 * Das Objekt, das für die Tests verwendet wird.
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
   // Die Zeichenkette, welche die Messpunkte enthält, wird erzeugt.
   this.eingabefeld = "-0.1, 0.0\n1.0, 0.9\n2.1, 0.0\n1.0, -0.9";
   
   // Das Objekt, das für die Tests verwendet wird, wird erzeugt.
   this.ausgleichsproblem = new Ausgleichsproblem();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#setEingabefeld(String)}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testSetEingabefeld() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Die zu testende Methode wird aufgerufen.
   this.ausgleichsproblem.setEingabefeld(this.eingabefeld);
   
   // Das Feld der Messpunkte wird gelesen.
   // Der Wert des Attributs messwerte wird gelesen.   
   Field messpunkteFeld = Ausgleichsproblem.class.getDeclaredField("messpunkte");
   messpunkteFeld.setAccessible(true);
   Vector2D[] messpunkte = (Vector2D[]) messpunkteFeld.get(this.ausgleichsproblem);
   
   // Es wir überprüft, ob das Feld der Messpunkte korrekt erzeugt worden ist.
   assertEquals(4, messpunkte.length);
   assertEquals(new Vector2D(-0.1, 0.0), messpunkte[0]);
   assertEquals(new Vector2D(1.0, 0.9), messpunkte[1]);
   assertEquals(new Vector2D(2.1, 0.0), messpunkte[2]);
   assertEquals(new Vector2D(1.0, -0.9), messpunkte[3]);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#getEingabefeld()}.
 */
@Test
public void testGetEingabefeld()
   {
   assertEquals("", this.ausgleichsproblem.getEingabefeld());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#problemLoesen()}.
 */
@Test
public void testProblemLoesen()
   {
   // Das Feld der Messpunkte wird initialisiert.
   this.ausgleichsproblem.setEingabefeld(this.eingabefeld);
   
   // Die zu testende Methode wird ausgeführt.
   this.ausgleichsproblem.problemLoesen();
   
   // Die berechneten Kreisparameter werden überprüft.
   assertEquals("1.00", this.ausgleichsproblem.getMx());
   assertEquals("0.00", this.ausgleichsproblem.getMy());
   assertEquals("1.00", this.ausgleichsproblem.getR());
   }
}
