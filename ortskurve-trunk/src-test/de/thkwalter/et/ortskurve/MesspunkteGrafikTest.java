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

import de.thkwalter.koordinatensystem.PunktPixelKonverter;
import de.thkwalter.koordinatensystem.Wertebereich;

/**
 * Diese Klasse enthält Tests für die Klasse {@link MesspunkteGrafik}.
 *
 * @author Th. K. Walter
 */
public class MesspunkteGrafikTest
{
/**
 * Ein Objekt der zu testenden Klasse {@link MesspunkteGrafik}
 */
private MesspunkteGrafik messpunkteGrafik;

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
   // Der darzustellende Wertebereich wird definiert.
   Wertebereich wertebereich = new Wertebereich(10.0, 10.0, 0.0, -10.0);
   
   // Der Konverter zwischen realen Koordinaten und ihren Pixelkoordinaten wird erzeugt.
   PunktPixelKonverter punktPixelKonverter = new PunktPixelKonverter(wertebereich, 200, 200);
   
   // Das Objekt der zu testenden Klasse wird erzeugt.
   this.messpunkteGrafik = 
      new MesspunkteGrafik(new Vector2D[]{new Vector2D(2.0, 0.0), new Vector2D(1.0, 1.0)}, punktPixelKonverter);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link MesspunkteGrafik#MesspunkteGrafik(Vector2D[], PunktPixelKonverter)}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testMesspunkteGrafik() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Es wird überprüft, ob das Objekt der zu testenden Klasse erzeugt worden ist.
   assertNotNull(this.messpunkteGrafik);
   
   // Die Messpunkte in Pixelkoordinaten werden gelesen.
   Field messpunkteInPixelnFeld = MesspunkteGrafik.class.getDeclaredField("messpunkteInPixeln"); 
   messpunkteInPixelnFeld.setAccessible(true);
   Vector2D[] messpunkteInPixeln = (Vector2D[]) messpunkteInPixelnFeld.get(this.messpunkteGrafik);
   
   // Es wird überprüft, ob die korrekte Anzahl von Messpunkten in Pixelkoordinaten berechnet worden ist.
   assertEquals(2, messpunkteInPixeln.length);
   
   // Es wird überprüft, ob die Pixelkoordinaten korrekt berechnet worden sind.
   assertEquals(new Vector2D(70, 100), messpunkteInPixeln[0]);
   assertEquals(new Vector2D(60, 90), messpunkteInPixeln[1]);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link MesspunkteGrafik#getMesspunkteInPixeln()}.
 */
@Test
public void testGetMesspunkteInPixeln()
   {
   // Es wird überprüft, ob die Messwerte in Pixelkoordinaten korrekt zurückgegeben werden. 
   Vector2D[] messpunkteInPixeln = this.messpunkteGrafik.getMesspunkteInPixeln();
   assertEquals(new Vector2D(70, 100), messpunkteInPixeln[0]);
   assertEquals(new Vector2D(60, 90), messpunkteInPixeln[1]);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link MesspunkteGrafik#toString()}.
 */
@Test
public void testToString()
   {
   // Es wird überprüft, ob die Zeichenkette, die das Objekt repräsentiert, korrekt zusammengebaut wird.
   assertEquals("messpunkteInPixeln: {70; 100}; {60; 90}; ", this.messpunkteGrafik.toString());
   }
}
