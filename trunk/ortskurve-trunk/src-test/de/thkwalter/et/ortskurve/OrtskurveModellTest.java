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
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

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
 * Die im Test verwendete Ortskurve
 */
private Ortskurve ortskurve;

/**
 * Die im Test verwendete Ortskurve der 2d-Ausgleichsrechnung
 */
private Ortskurve ortskurve2d;

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
   
   // Die im Test verwendete Ortskurve der 2d-Ausgleichsrechnung wird erzeugt.
   this.ortskurve2d = new Ortskurve(new Vector2D(1.0, -0.5), 1.0);
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
public void testRandpunkteZusammenstellen1() throws SecurityException, NoSuchMethodException, IllegalArgumentException, 
   IllegalAccessException, InvocationTargetException 
   {
   // Das Modell wird initialisiert.
   this.ortskurveModell.setMesspunkte(this.test_messpunkte);
   this.ortskurveModell.setOrtskurve(this.ortskurve);
   this.ortskurveModell.setOrtskurve2d(this.ortskurve2d);
   
   // Die zu testende Methode wird ausgeführt.
   Method method = OrtskurveModell.class.getDeclaredMethod("randpunkteZusammenstellen", (Class[]) null);
   method.setAccessible(true);
   Vector2D[] randpunkte = (Vector2D[]) method.invoke(this.ortskurveModell, (Object[]) null);
   
   // Es wird überprüft, ob die Anzahl der zusammengestellten Randpunkte korrekt ist.
   assertEquals(this.test_messpunkte.length + 8, randpunkte.length);
   
   // Es wird überprüft, ob die Randpunkte korrekt zusammengestellt worden sind.
   assertEquals(this.test_messpunkte[0], randpunkte[0]);
   assertEquals(this.test_messpunkte[1], randpunkte[1]);
   assertEquals(new Vector2D(0, 0), randpunkte[2]);
   assertEquals(new Vector2D(2, 0), randpunkte[3]);
   assertEquals(new Vector2D(1, 1), randpunkte[4]);
   assertEquals(new Vector2D(1, -1), randpunkte[5]);
   assertEquals(new Vector2D(0, -0.5), randpunkte[6]);
   assertEquals(new Vector2D(2, -0.5), randpunkte[7]);
   assertEquals(new Vector2D(1, 0.5), randpunkte[8]);
   assertEquals(new Vector2D(1, -1.5), randpunkte[9]);
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
public void testRandpunkteZusammenstellen2() throws SecurityException, NoSuchMethodException, IllegalArgumentException, 
   IllegalAccessException, InvocationTargetException 
   {
   // Das Modell wird initialisiert.
   this.ortskurveModell.setMesspunkte(this.test_messpunkte);
   this.ortskurveModell.setOrtskurve(this.ortskurve);
   
   // Die zu testende Methode wird ausgeführt.
   Method method = OrtskurveModell.class.getDeclaredMethod("randpunkteZusammenstellen", (Class[]) null);
   method.setAccessible(true);
   Vector2D[] randpunkte = (Vector2D[]) method.invoke(this.ortskurveModell, (Object[]) null);
   
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
   // Die Messpunkte werden im Objekt der zu testenden Klasse gespeichert.
   this.ortskurveModell.setMesspunkte(this.test_messpunkte);
   
   // Die Ortskurve wird im Objekt der zu testenden Klasse gespeichert.
   this.ortskurveModell.setOrtskurve(this.ortskurve);

   // Die zu testende Methode wird ausgeführt.
   this.ortskurveModell.grafikdatenBerechnen();
   
   // Die Grafikdarstellung der Ortskurve wird gelesen.
   Field ortskurveGrafikFeld = OrtskurveModell.class.getDeclaredField("ortskurveGrafik");
   ortskurveGrafikFeld.setAccessible(true);
   OrtskurveGrafik ortskurveGrafik = (OrtskurveGrafik) ortskurveGrafikFeld.get(this.ortskurveModell);

   // Es wird überprüft, ob die Grafikdarstellung der Ortskurve korrekt berechnet worden ist.
   assertEquals(275.0, ortskurveGrafik.getMittelpunktInPixeln().getX(), 275.0/1000);
   assertEquals(137.5, ortskurveGrafik.getMittelpunktInPixeln().getY(), 137.5/1000);
   assertEquals(114.58, ortskurveGrafik.getRadiusInPixeln(), 114.58/1000);
   
   // Die Grafikdarstellung der Messpunkte wird gelesen.
   Field messpunkteGrafikFeld = OrtskurveModell.class.getDeclaredField("messpunkteGrafik");
   messpunkteGrafikFeld.setAccessible(true);
   MesspunkteGrafik messpunkteGrafik = (MesspunkteGrafik) messpunkteGrafikFeld.get(this.ortskurveModell);
   
   // Es wird überprüft, ob die Grafikdarstellung der Messpunkte korrekt berechnet worden ist.
   assertEquals(389.6, messpunkteGrafik.getMesspunkteInPixeln()[0].getX(), 389.6/1000);
   assertEquals(137.5, messpunkteGrafik.getMesspunkteInPixeln()[0].getY(), 137.5/1000);
   assertEquals(275.0, messpunkteGrafik.getMesspunkteInPixeln()[1].getX(), 275.0/1000);
   assertEquals(22.92, messpunkteGrafik.getMesspunkteInPixeln()[1].getY(), 22.92/1000);
   
   // Die Koordinatenachsen wird gelesen.
   Field koordinatenachsenFeld = OrtskurveModell.class.getDeclaredField("koordinatenachsen");
   koordinatenachsenFeld.setAccessible(true);
   Koordinatenachsen koordinatenachsen = (Koordinatenachsen) koordinatenachsenFeld.get(this.ortskurveModell);
   
   // Es wird überprüft, ob die Koordinatenachsen korrekt berechnet worden sind.
   assertEquals(137.5, koordinatenachsen.getStartPunktXAchse().getX(), 137.5/1000);
   assertEquals(137.5, koordinatenachsen.getStartPunktXAchse().getY(), 137.5/1000);
   assertEquals(412.5, koordinatenachsen.getEndPunktXAchse().getX(), 412.5/1000);
   assertEquals(137.5, koordinatenachsen.getEndPunktXAchse().getY(), 137.5/1000);
   assertEquals(160.4, koordinatenachsen.getStartPunktYAchse().getX(), 160.4/1000);
   assertEquals(275.0, koordinatenachsen.getStartPunktYAchse().getY(), 275.0/1000);
   assertEquals(160.4, koordinatenachsen.getEndPunktYAchse().getX(), 160.4/1000);
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
   this.ortskurveModell.setMesspunkte(this.test_messpunkte);
   this.ortskurveModell.setOrtskurve(ortskurve);
   this.ortskurveModell.grafikdatenBerechnen();
   
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
   this.ortskurveModell.setMesspunkte(this.test_messpunkte);
   this.ortskurveModell.setOrtskurve(ortskurve);
   this.ortskurveModell.grafikdatenBerechnen();
   
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
   this.ortskurveModell.setMesspunkte(this.test_messpunkte);
   this.ortskurveModell.setOrtskurve(ortskurve);
   this.ortskurveModell.grafikdatenBerechnen();
   
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
 * Test für die Methode {@link OrtskurveModell#getOrtskurve2d()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetOrtskurve2d() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException 
   {   
   // Die Ortskurve der 2d-Berechnung wird im Testobjekt gespeichert.
   Field messpunkteFeld = OrtskurveModell.class.getDeclaredField("ortskurve2d");
   messpunkteFeld.setAccessible(true);
   messpunkteFeld.set(this.ortskurveModell, this.ortskurve);
   
   // Die zu testende Methode wird aufgerufen.
   Ortskurve ortskurve2d = this.ortskurveModell.getOrtskurve2d();
   
   // Es wird überprüft, ob die Messpunkte korrekt zurückgegeben werden. 
   assertEquals(this.ortskurve, ortskurve2d);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveModell#setOrtskurve2d(Ortskurve)}.
 */
@Test
public void testSetOrtskurve2d() 
   {
   // Die zu testende Methode wird ausgeführt.
   this.ortskurveModell.setOrtskurve2d(this.ortskurve);
   
   // Es wird überprüft, ob die Messpunkte korrekt im Objekt der zu testenden Klasse gespeichert worden sind.
   assertEquals(this.ortskurve, this.ortskurveModell.getOrtskurve2d());
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
 * Test der Methode {@link OrtskurveModell#randpunkteOrtskurveZusammenstellen(Ortskurve)}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testRandpunkteOrtskurveZusammenstellen() throws NoSuchMethodException, SecurityException, 
   IllegalAccessException, IllegalArgumentException, InvocationTargetException 
   {
   // Die zu testende Methode wird ausgeführt.
   Method methode = OrtskurveModell.class.getDeclaredMethod("randpunkteOrtskurveZusammenstellen", Ortskurve.class);
   methode.setAccessible(true);
   @SuppressWarnings("unchecked")
   ArrayList<Vector2D> randpunkteOrtskurve = (ArrayList<Vector2D>) methode.invoke(this.ortskurveModell, this.ortskurve);
   
   // Es wird überprüft, ob die Liste die korrekten Randpunkte enthält.
   assertEquals(4, randpunkteOrtskurve.size());   
   assertTrue(randpunkteOrtskurve.contains(new Vector2D(0, 0)));
   assertTrue(randpunkteOrtskurve.contains(new Vector2D(2, 0)));
   assertTrue(randpunkteOrtskurve.contains(new Vector2D(1, 1)));
   assertTrue(randpunkteOrtskurve.contains(new Vector2D(1, -1)));
   }
}