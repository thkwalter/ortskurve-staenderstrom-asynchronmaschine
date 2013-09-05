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

import de.thkwalter.koordinatensystem.PunktPixelKonverter;
import de.thkwalter.koordinatensystem.Wertebereich;

/**
 * Diese Klasse enthält Tests für die Klasse {@link OrtskurveModell}.
 *
 * @author Th. K. Walter
 */
public class OrtskurveModellTest
{
/**
 * Ein Objekt der zu testenden Klasse {@link OrtskurveModell}.
 */
private OrtskurveModell ortskurveModell;

/**
 * Die im Test verwendete Grafikdarstellung der Messpunkte
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
   // Ein Objekt der zu testenden Klasse wird initialisiert.
   this.ortskurveModell = new OrtskurveModell();
   
   // Die im Test verwendete Grafikdarstellung der Messpunkte wird erzeugt.
   Wertebereich wertebereich = new Wertebereich(10.0, 10.0, 0.0, -10.0);
   PunktPixelKonverter punktPixelKonverter = new PunktPixelKonverter(wertebereich, 200, 200);
   this.messpunkteGrafik = 
      new MesspunkteGrafik(new Vector2D[]{new Vector2D(2.0, 0.0), new Vector2D(1.0, 1.0)}, punktPixelKonverter);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveModell#getMesspunkteGrafik()}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetMesspunkteGrafik() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException 
   {   
   // Die Grafikdarstellung der Messpunkte wird im Objekt der zu testenden Klasse gespeichert.
   Field messpunkteGrafikFeld = OrtskurveModell.class.getDeclaredField("messpunkteGrafik");
   messpunkteGrafikFeld.setAccessible(true);
   messpunkteGrafikFeld.set(this.ortskurveModell, this.messpunkteGrafik);
   
   // Es wird überprüft, ob die Grafikdarstellung des Messpunkte korrekt zurückgegeben wird. 
   assertEquals(messpunkteGrafik, this.ortskurveModell.getMesspunkteGrafik());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveModell#setMesspunkteGrafik(MesspunkteGrafik)}.
 */
@Test
public void testSetMesspunkteGrafik() 
   {
   // Die zu testende Methode wird ausgeführt.
   this.ortskurveModell.setMesspunkteGrafik(this.messpunkteGrafik);
   
   // Es wird überprüft, ob die Grafikdarstellung der Messpunkte korrekt im Objekt der zu testenden Klasse gespeichert
   // worden ist.
   assertEquals(this.messpunkteGrafik, this.ortskurveModell.getMesspunkteGrafik());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link java.lang.Object#toString()}.
 */
@Test
public void testToString()
   {
   // Das Objekt der zu testenden Klasse wird initalisiert.
   this.ortskurveModell.setMesspunkteGrafik(this.messpunkteGrafik);
   
   // Es wird überprüft, ob die Zeichenkette, die das zu testende Objekt repräsentiert, korrekt zusammengebaut wird.
   assertEquals("messpunkteGrafik: messpunkteInPixeln: {70; 100}; {60; 90}; ", this.ortskurveModell.toString());
   }

}
