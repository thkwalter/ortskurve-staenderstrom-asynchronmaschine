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
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import de.thkwalter.koordinatensystem.PunktPixelKonverter;
import de.thkwalter.koordinatensystem.Wertebereich;

/**
 * Diese Klasse enthält Tests für die Klasse {@link OrtskurveGrafik}.
 *
 * @author Th. K. Walter
 * @version 1.0
 */
public class OrtskurveGrafikTest
{
/**
 * Ein Objekt der zu testenden Klasse {@link OrtskurveGrafik}
 */
private OrtskurveGrafik ortskurveGrafik;

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
   
   // Der Konverter, der reale Koordinaten in Pixelkoordinaten umrechnet, wird erzeugt.
   PunktPixelKonverter punktPixelKonverter = new PunktPixelKonverter(wertebereich, 200, 200);
   
   // Das Objekt der zu testenden Klasse wird erzeugt.
   this.ortskurveGrafik = new OrtskurveGrafik(new Ortskurve(new Vector2D(1.0, 0.0), 1.0), punktPixelKonverter);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link OrtskurveGrafik#OrtskurveGrafik(Vector2D, double, PunktPixelKonverter)}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testOrtskurveGrafik() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException 
   {
   // Es wird überprüft, ob das Objekt der zu testenden Klasse erzeugt worden ist.
   assertNotNull(this.ortskurveGrafik);
   
   // Der Mittelpunkt in Pixelkoordinaten wird gelesen.
   Field mittelPunktInPixelnFeld = OrtskurveGrafik.class.getDeclaredField("mittelpunktInPixeln"); 
   mittelPunktInPixelnFeld.setAccessible(true);
   Vector2D mittelPunktInPixeln = (Vector2D) mittelPunktInPixelnFeld.get(this.ortskurveGrafik);
   
   // Es wird überprüft, ob der Mittelpunkt in Pixelkoordinaten korrekt berechnet worden ist.
   assertEquals(new Vector2D(60, 100), mittelPunktInPixeln);
   
   // Der Radius in Pixeln wird gelesen.
   Field radiusInPixelnFeld = OrtskurveGrafik.class.getDeclaredField("radiusInPixeln"); 
   radiusInPixelnFeld.setAccessible(true);
   double radiusInPixeln = (Double) radiusInPixelnFeld.get(this.ortskurveGrafik);
   
   // Es wird überprüft, ob der Mittelpunkt in Pixelkoordinaten korrekt berechnet worden ist.
   assertEquals(10, radiusInPixeln, 10/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveGrafik#getMittelpunkt()}.
 */
@Test
public void testGetMittelpunkt() 
   {
   // Es wird überprüft, ob der Mittelpunkt in Pixelkoordinaten korrekt zurückgegeben wird. 
   assertEquals(new Vector2D(60, 100), this.ortskurveGrafik.getMittelpunktInPixeln());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveGrafik#getRadius()}.
 */
@Test
public void testGetRadius()
   {
   // Es wird überprüft, ob der Radius in Pixeln korrekt zurückgegeben wird. 
   assertEquals(10, this.ortskurveGrafik.getRadiusInPixeln(), 10/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveGrafik#toString()}.
 */
@Test
public void testToString()
   {
   // Es wird überprüft, ob die Zeichenkette, die das Objekt repräsentiert, korrekt zusammengebaut wird.
   assertEquals("mittelPunktInPixeln: {60; 100}; radiusInPixeln: 10.0", this.ortskurveGrafik.toString());
   }
}
