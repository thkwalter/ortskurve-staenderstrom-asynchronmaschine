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
 * @version 1.0
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

/**
 * Die im Test verwendete Grafikdarstellung der Ortskurve
 */
private OrtskurveGrafik ortskurveGrafik;

/**
 * Der im Test verwendete Ortskurve
 */
private Ortskurve ortskurve;

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
   // Ein Objekt der zu testenden Klasse OrtskurveModell wird initialisiert.
   this.ortskurveModell = new OrtskurveModell();
   
   // Ein Konverter, der reale Koordinaten in Pixelkoordinaten konvertiert, wird erzeugt.
   Wertebereich wertebereich = new Wertebereich(10.0, 10.0, 0.0, -10.0);
   PunktPixelKonverter punktPixelKonverter = new PunktPixelKonverter(wertebereich, 200, 200);
   
   // Die im Test verwendete Grafikdarstellung der Messpunkte wird erzeugt.
   this.messpunkteGrafik = 
      new MesspunkteGrafik(new Vector2D[]{new Vector2D(2.0, 0.0), new Vector2D(1.0, 1.0)}, punktPixelKonverter);
   
   // Der im Test verwendete Ortskurve wird erzeugt.
   this.ortskurve = new Ortskurve(new Vector2D(1.0, 0.0), 1.0);
   
   // Die im Test verwendete Grafikdarstellung der Ortskurve wird erzeugt.
   this.ortskurveGrafik = new OrtskurveGrafik(new Vector2D(1.0, 0.0), 1.0, punktPixelKonverter);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveModell#getMesspunkteGrafik()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetMesspunkteGrafik() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException 
   {   
   // Die Grafikdarstellung der Messpunkte wird im Objekt der zu testenden Klasse OrtskurveModell gespeichert.
   Field messpunkteGrafikFeld = OrtskurveModell.class.getDeclaredField("messpunkteGrafik");
   messpunkteGrafikFeld.setAccessible(true);
   messpunkteGrafikFeld.set(this.ortskurveModell, this.messpunkteGrafik);
   
   // Es wird überprüft, ob die Grafikdarstellung des Messpunkte korrekt zurückgegeben wird. 
   assertEquals(this.messpunkteGrafik, this.ortskurveModell.getMesspunkteGrafik());
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
 * Test für die Methode {@link OrtskurveModell#getOrtskurveGrafik()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetOrtskurveGrafik() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException 
   {   
   // Die Grafikdarstellung der Ortskuve wird im Objekt der zu testenden Klasse OrtskurveModell gespeichert.
   Field ortskurveGrafikFeld = OrtskurveModell.class.getDeclaredField("ortskurveGrafik");
   ortskurveGrafikFeld.setAccessible(true);
   ortskurveGrafikFeld.set(this.ortskurveModell, this.ortskurveGrafik);
   
   // Es wird überprüft, ob die Grafikdarstellung der Ortskurve korrekt zurückgegeben wird. 
   assertEquals(this.ortskurveGrafik, this.ortskurveModell.getOrtskurveGrafik());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveModell#setOrtskurveGrafik(OrtskurveGrafik)}.
 */
@Test
public void testSetOrtskurveGrafik() 
   {
   // Die zu testende Methode wird ausgeführt.
   this.ortskurveModell.setOrtskurveGrafik(this.ortskurveGrafik);
   
   // Es wird überprüft, ob die Grafikdarstellung der Ortskurve korrekt im Objekt der zu testenden Klasse gespeichert
   // worden ist.
   assertEquals(this.ortskurveGrafik, this.ortskurveModell.getOrtskurveGrafik());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveModell#getOrtskurve()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetOrtskurve() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException 
   {   
   // Die Ortskuve wird im Objekt der zu testenden Klasse OrtskurveModell gespeichert.
   Field ortskurveFeld = OrtskurveModell.class.getDeclaredField("ortskurve");
   ortskurveFeld.setAccessible(true);
   ortskurveFeld.set(this.ortskurveModell, this.ortskurve);
   
   // Es wird überprüft, ob der Mittelpunkt der Ortskurve korrekt zurückgegeben wird. 
   assertEquals(this.ortskurve, this.ortskurveModell.getOrtskurve());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveModell#setOrtskurve(Ortskurve)}.
 */
@Test
public void testSetOrtskurve() 
   {
   // Die zu testende Methode wird ausgeführt.
   this.ortskurveModell.setOrtskurve(this.ortskurve);
   
   // Es wird überprüft, ob die Ortskurve korrekt im Objekt der zu testenden Klasse gespeichert worden ist.
   assertEquals(this.ortskurve, this.ortskurveModell.getOrtskurve());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link java.lang.Object#toString()}.
 */
@Test
public void testToString1()
   {
   // Das Objekt der zu testenden Klasse wird initalisiert.
   this.ortskurveModell.setOrtskurve(this.ortskurve);
   this.ortskurveModell.setMesspunkteGrafik(this.messpunkteGrafik);
   this.ortskurveModell.setOrtskurveGrafik(this.ortskurveGrafik);
   
   // Es wird überprüft, ob die Zeichenkette, die das zu testende Objekt repräsentiert, korrekt zusammengebaut wird.
   String meldung = "ortskurve: mittelpunktOrtskurve: {1; 0}; radiusOrtskurve: 1.0; messpunkteGrafik: " +
      "messpunkteInPixeln: {70; 100}; {60; 90}; ortskurveGrafik: mittelPunktInPixeln: {60; 100}; radiusInPixeln: 10.0";
   assertEquals(meldung, this.ortskurveModell.toString());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link java.lang.Object#toString()}.
 */
@Test
public void testToString2()
   {   
   // Es wird überprüft, ob die Zeichenkette, die das zu testende Objekt repräsentiert, korrekt zusammengebaut wird.
   String meldung = "ortskurve: messpunkteGrafik: ortskurveGrafik: ";
   assertEquals(meldung, this.ortskurveModell.toString());
   }
}
