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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import de.thkwalter.koordinatensystem.Koordinatenachsen;

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
private Vector2D[] test_messpunkte;

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
   this.test_messpunkte = new Vector2D[]{new Vector2D(2.0, 0.0), new Vector2D(1.0, 1.0)};
   
   // Der im Test verwendete Ortskurve wird erzeugt.
   this.ortskurve = new Ortskurve(new Vector2D(1.0, 0.0), 1.0);
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
 * 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testRandpunkteZusammenstellen() throws SecurityException, NoSuchMethodException, IllegalArgumentException, 
   IllegalAccessException, InvocationTargetException 
   {
   // Das Modell wird initialisiert.
   this.ortskurveModell.setOrtskurve(this.ortskurve);
   
   // Die zu testende Methode wird ausgeführt.
   Method method = OrtskurveModell.class.getDeclaredMethod("randpunkteZusammenstellen", Vector2D[].class);
   method.setAccessible(true);
   Vector2D[] randpunkte = (Vector2D[]) method.invoke(this.ortskurveModell, (Object) this.test_messpunkte);
   
   // Es wird überprüft, ob die Anzahl der zusammengestellten Randpunkte korrekt ist.
   assertEquals(this.test_messpunkte.length + 4, randpunkte.length);
   
   // Es wird überprüft, ob die Randpunkte korrekt zusammengestellt worden sind.
   assertEquals(this.test_messpunkte[0], randpunkte[0]);
   assertEquals(this.test_messpunkte[1], randpunkte[1]);
   assertEquals(new Vector2D(0, 0), randpunkte[2]);
   assertEquals(new Vector2D(2, 0), randpunkte[3]);
   assertEquals(new Vector2D(1, 1), randpunkte[4]);
   assertEquals(new Vector2D(1, -1), randpunkte[5]);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveModell#grafikdatenBerechnen(Vector2D[])}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGrafikdatenBerechnen() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   { 
   // Die Ortskurve wird im Objekt der zu testenden Klasse gespeichert.
   this.ortskurveModell.setOrtskurve(this.ortskurve);

   // Die zu testende Methode wird ausgeführt.
   this.ortskurveModell.grafikdatenBerechnen(this.test_messpunkte);
   
   // Die Grafikdarstellung der Ortskurve wird gelesen.
   Field ortskurveGrafikFeld = OrtskurveModell.class.getDeclaredField("ortskurveGrafik");
   ortskurveGrafikFeld.setAccessible(true);
   OrtskurveGrafik ortskurveGrafik = (OrtskurveGrafik) ortskurveGrafikFeld.get(this.ortskurveModell);

   // Es wird überprüft, ob die Grafikdarstellung der Ortskurve korrekt berechnet worden ist.
   assertEquals(270.0, ortskurveGrafik.getMittelpunktInPixeln().getX(), 270.0/1000);
   assertEquals(135.0, ortskurveGrafik.getMittelpunktInPixeln().getY(), 135.0/1000);
   assertEquals(112.5, ortskurveGrafik.getRadiusInPixeln(), 112.5/1000);
   
   // Die Grafikdarstellung der Messpunkte wird gelesen.
   Field messpunkteGrafikFeld = OrtskurveModell.class.getDeclaredField("messpunkteGrafik");
   messpunkteGrafikFeld.setAccessible(true);
   MesspunkteGrafik messpunkteGrafik = (MesspunkteGrafik) messpunkteGrafikFeld.get(this.ortskurveModell);
   
   // Es wird überprüft, ob die Grafikdarstellung der Messpunkte korrekt berechnet worden ist.
   assertEquals(382.5, messpunkteGrafik.getMesspunkteInPixeln()[0].getX(), 382.5/1000);
   assertEquals(135.0, messpunkteGrafik.getMesspunkteInPixeln()[0].getY(), 135.0/1000);
   assertEquals(270.0, messpunkteGrafik.getMesspunkteInPixeln()[1].getX(), 270.0/1000);
   assertEquals(22.5, messpunkteGrafik.getMesspunkteInPixeln()[1].getY(), 22.5/1000);
   
   // Die Koordinatenachsen wird gelesen.
   Field koordinatenachsenFeld = OrtskurveModell.class.getDeclaredField("koordinatenachsen");
   koordinatenachsenFeld.setAccessible(true);
   Koordinatenachsen koordinatenachsen = (Koordinatenachsen) koordinatenachsenFeld.get(this.ortskurveModell);
   
   // Es wird überprüft, ob die Koordinatenachsen korrekt berechnet worden sind.
   assertEquals(135.0, koordinatenachsen.getStartPunktXAchse().getX(), 135.0/1000);
   assertEquals(135.0, koordinatenachsen.getStartPunktXAchse().getY(), 135.0/1000);
   assertEquals(405.0, koordinatenachsen.getEndPunktXAchse().getX(), 405.0/1000);
   assertEquals(135.0, koordinatenachsen.getEndPunktXAchse().getY(), 135.0/1000);
   assertEquals(157.5, koordinatenachsen.getStartPunktYAchse().getX(), 157.5/1000);
   assertEquals(270.0, koordinatenachsen.getStartPunktYAchse().getY(), 270.0/1000);
   assertEquals(157.5, koordinatenachsen.getEndPunktYAchse().getX(), 157.5/1000);
   assertEquals(0.0, koordinatenachsen.getEndPunktYAchse().getY(), 0.0);
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
   // Die Grafikdarstellung der Ortskurve wird initialisiert.
   this.ortskurveModell.setOrtskurve(ortskurve);
   this.ortskurveModell.grafikdatenBerechnen(this.test_messpunkte);
   
   // Die Grafikdarstellung der Messpunkte wird gelesen.
   Field messpunkteGrafikFeld = OrtskurveModell.class.getDeclaredField("messpunkteGrafik");
   messpunkteGrafikFeld.setAccessible(true);
   MesspunkteGrafik messpunkteGrafik = (MesspunkteGrafik) messpunkteGrafikFeld.get(this.ortskurveModell);
   
   // Es wird überprüft, ob die Grafikdarstellung des Messpunkte korrekt zurückgegeben wird. 
   assertEquals(messpunkteGrafik, this.ortskurveModell.getMesspunkteGrafik());
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
   this.ortskurveModell.grafikdatenBerechnen(this.test_messpunkte);
   
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
 * Test für die Methode {@link OrtskurveModell#getKoordinatenachsen()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetKoordinatenachsen() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException 
   {   
   // Die Grafikdarstellung der Ortskurve wird initialisiert.
   this.ortskurveModell.setOrtskurve(ortskurve);
   this.ortskurveModell.grafikdatenBerechnen(this.test_messpunkte);
   
   // Die Grafikdarstellung der Ortskurve wird gelesen.
   Field koordinatenachsenFeld = OrtskurveModell.class.getDeclaredField("koordinatenachsen");
   koordinatenachsenFeld.setAccessible(true);
   Koordinatenachsen koordinatenachsen = (Koordinatenachsen) koordinatenachsenFeld.get(this.ortskurveModell);
   
   // Es wird überprüft, ob die Grafikdarstellung der Ortskurve korrekt zurückgegeben wird. 
   assertEquals(koordinatenachsen, this.ortskurveModell.getKoordinatenachsen());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveModell#getMesspunkte()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetMesspunkte() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException 
   {   
   // Die Messpunkte werden im Objekt der zu testenden Klasse gespeichert.
   Field messpunkteFeld = OrtskurveModell.class.getDeclaredField("messpunkte");
   messpunkteFeld.setAccessible(true);
   messpunkteFeld.set(this.ortskurveModell, this.test_messpunkte);
   
   // Die zu testende Methode wird aufgerufen.
   Vector2D[] messpunkte = this.ortskurveModell.getMesspunkte();
   
   // Es wird überprüft, ob die Messpunkte korrekt zurückgegeben werden. 
   assertEquals(this.test_messpunkte.length, messpunkte.length);
   for (int i = 0; i < this.test_messpunkte.length; i++)
      {
      assertEquals(this.test_messpunkte[i], messpunkte[i]);
      }
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveModell#setMesspunkte(Vector2D[])}.
 */
@Test
public void testSetMesspunkte() 
   {
   // Die zu testende Methode wird ausgeführt.
   this.ortskurveModell.setMesspunkte(this.test_messpunkte);
   
   // Es wird überprüft, ob die Messpunkte korrekt im Objekt der zu testenden Klasse gespeichert worden sind.
   Vector2D[] messpunkte = this.ortskurveModell.getMesspunkte();
   assertEquals(this.test_messpunkte.length, messpunkte.length);
   for (int i = 0; i < this.test_messpunkte.length; i++)
      {
      assertEquals(this.test_messpunkte[i], messpunkte[i]);
      }
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
   this.ortskurveModell.grafikdatenBerechnen(test_messpunkte);
   this.ortskurveModell.setMesspunkte(this.test_messpunkte);
   
   // Es wird überprüft, ob die Zeichenkette, die das zu testende Objekt repräsentiert, korrekt zusammengebaut wird.
   String meldung = "xPixelGrafik: " + this.ortskurveModell.getxPixelGrafik() + "; yPixelGrafik: " +
      this.ortskurveModell.getyPixelGrafik() + "; ortskurve: mittelpunktOrtskurve: {1; 0}; radiusOrtskurve: 1.0; " +
      "messpunkte: {2; 0}; {1; 1}; messpunkteGrafik: messpunkteInPixeln: {382,5; 135}; {270; 22,5}; ortskurveGrafik: mittelPunktInPixeln: " +
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
       this.ortskurveModell.getyPixelGrafik() + "; ortskurve: messpunkte: messpunkteGrafik: ortskurveGrafik: ";
   assertEquals(meldung, this.ortskurveModell.toString());
   }
}
