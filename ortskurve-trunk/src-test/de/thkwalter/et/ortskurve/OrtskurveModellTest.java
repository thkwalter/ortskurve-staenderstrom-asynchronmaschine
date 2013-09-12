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
 * Die im Test verwendeten Messpunkte
 */
private Vector2D[] messpunkte;

/**
 * Die im Test verwendete Grafikdarstellung der Messpunkte
 */
private MesspunkteGrafik messpunkteGrafik;

/**
 * Der im Test verwendete Konverter zwischen Punkten und Pixelkoordinaten.
 */
private PunktPixelKonverter punktPixelKonverter;

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
   
   // Die im Test verwendeten Messpunkte werden erzeugt.
   this.messpunkte = new Vector2D[]{new Vector2D(2.0, 0.0), new Vector2D(1.0, 1.0)};
   
   // Ein Konverter, der reale Koordinaten in Pixelkoordinaten konvertiert, wird erzeugt.
   Wertebereich wertebereich = new Wertebereich(10.0, 10.0, 0.0, -10.0);
   this.punktPixelKonverter = 
      new PunktPixelKonverter(wertebereich, this.ortskurveModell.getxPixelGrafik(), 
         this.ortskurveModell.getyPixelGrafik());
   
   // Die im Test verwendete Grafikdarstellung der Messpunkte wird erzeugt.
   this.messpunkteGrafik = 
      new MesspunkteGrafik(this.messpunkte, punktPixelKonverter);
   
   // Der im Test verwendete Ortskurve wird erzeugt.
   this.ortskurve = new Ortskurve(new Vector2D(1.0, 0.0), 1.0);
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
   // Die Grafikdarstellung der Ortskurve wird initialisiert.
   this.ortskurveModell.setOrtskurve(ortskurve);
   this.ortskurveModell.grafikdatenBerechnen(this.messpunkte);
   
   // Die Grafikdarstellung der Ortskurve wird gelesen.
   Field ortskurveGrafikFeld = OrtskurveModell.class.getDeclaredField("ortskurveGrafik");
   ortskurveGrafikFeld.setAccessible(true);
   OrtskurveGrafik ortskurveGrafik = (OrtskurveGrafik) ortskurveGrafikFeld.get(this.ortskurveModell);
   
   // Es wird überprüft, ob die Grafikdarstellung der Ortskurve korrekt zurückgegeben wird. 
   assertEquals(ortskurveGrafik, this.ortskurveModell.getOrtskurveGrafik());
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
 * Test für die Methode {@link OrtskurveModell#randpunkteZusammenstellen(Vector2D[])}.
 */
@Test
public void testRandpunkteZusammenstellen() 
   {
   // Das Modell wird initialisiert.
   this.ortskurveModell.setOrtskurve(this.ortskurve);
   
   // Die zu testende Methode wird ausgeführt.
   Vector2D[] randpunkte = this.ortskurveModell.randpunkteZusammenstellen(this.messpunkte);
   
   // Es wird überprüft, ob die Anzahl der zusammengestellten Randpunkte korrekt ist.
   assertEquals(this.messpunkte.length + 4, randpunkte.length);
   
   // Es wird überprüft, ob die Randpunkte korrekt zusammengestellt worden sind.
   assertEquals(this.messpunkte[0], randpunkte[0]);
   assertEquals(this.messpunkte[1], randpunkte[1]);
   assertEquals(new Vector2D(0, 0), randpunkte[2]);
   assertEquals(new Vector2D(2, 0), randpunkte[3]);
   assertEquals(new Vector2D(1, 1), randpunkte[4]);
   assertEquals(new Vector2D(1, -1), randpunkte[5]);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveModell#getXPixelGrafik()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetXPixelGrafik() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException 
   {
   // Die Anzahl der Pixel der Grafik in x-Richtung wird gelesen.
   Field xPixelGrafikFeld = OrtskurveModell.class.getDeclaredField("xPixelGrafik");
   xPixelGrafikFeld.setAccessible(true);
   int xPixelGrafik = xPixelGrafikFeld.getInt(this.ortskurveModell);
   
   // Es wird überprüft, ob die korrekte Anzahl der Pixel der Grafik in x-Richtung zurückgegeben wird.
   assertEquals(xPixelGrafik, this.ortskurveModell.getxPixelGrafik());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveModell#getYPixelGrafik()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetYPixelGrafik() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException 
   {
   // Die Anzahl der Pixel der Grafik in x-Richtung wird gelesen.
   Field yPixelGrafikFeld = OrtskurveModell.class.getDeclaredField("yPixelGrafik");
   yPixelGrafikFeld.setAccessible(true);
   int yPixelGrafik = yPixelGrafikFeld.getInt(this.ortskurveModell);
   
   // Es wird überprüft, ob die korrekte Anzahl der Pixel der Grafik in x-Richtung zurückgegeben wird.
   assertEquals(yPixelGrafik, this.ortskurveModell.getyPixelGrafik());
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
   this.ortskurveModell.grafikdatenBerechnen(messpunkte);
   
   // Es wird überprüft, ob die Zeichenkette, die das zu testende Objekt repräsentiert, korrekt zusammengebaut wird.
   String meldung = "xPixelGrafik: " + this.ortskurveModell.getxPixelGrafik() + "; yPixelGrafik: " +
      this.ortskurveModell.getyPixelGrafik() + "; ortskurve: mittelpunktOrtskurve: {1; 0}; radiusOrtskurve: 1.0; " +
      "messpunkteGrafik: messpunkteInPixeln: {229,5; 135}; {216; 121,5}; ortskurveGrafik: mittelPunktInPixeln: " +
      "{270; 135}; radiusInPixeln: 112.5";
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
   String meldung = "xPixelGrafik: " + this.ortskurveModell.getxPixelGrafik() + "; yPixelGrafik: " +
       this.ortskurveModell.getyPixelGrafik() + "; ortskurve: messpunkteGrafik: ortskurveGrafik: ";
   assertEquals(meldung, this.ortskurveModell.toString());
   }
}
