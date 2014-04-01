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

/**
 * Diese Klasse enthält Tests für die Klasse {@link Ortskurve}.
 *
 * @author Th. K. Walter
 * @version 1.0
 */
public class OrtskurveTest
{
/**
 * Ein Objekt der zu testenden Klasse {@link Ortskurve}
 */
private Ortskurve ortskurve;

/**
 * Der im Test verwendete Mittelpunkt der Ortskurve
 */
private Vector2D testMittelpunktOrtskurve;

/**
 * Der im Test verwendete Radius der Ortskurve
 */
private double testRadiusOrtskurve;

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
   // Die im Test verwendeten Daten werden erzeugt.
   this.testMittelpunktOrtskurve = new Vector2D(1.0, 0.0);
   this.testRadiusOrtskurve = 1.0;
   
   // Das Objekt der zu testenden Klasse wird erzeugt.
   this.ortskurve = new Ortskurve(this.testMittelpunktOrtskurve, this.testRadiusOrtskurve);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Test überprüft den Konstruktor {@link Ortskurve#Ortskurve(Vector2D, double)}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testOrtskurve() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Es wird überprüft, ob das Objekt der zu testenden Klasse erzeugt worden ist.
   assertNotNull(this.ortskurve);
   
   // Der Mittelpunkt der Ortskurve wird gelesen.
   Field mittelpunktOrtskurveFeld = Ortskurve.class.getDeclaredField("mittelpunktOrtskurve"); 
   mittelpunktOrtskurveFeld.setAccessible(true);
   Vector2D mittelpunktOrtskurve = (Vector2D) mittelpunktOrtskurveFeld.get(this.ortskurve);
   
   // Es wird überprüft, ob der Mittelpunkt der Ortskurve korrekt initialisiert worden ist.
   assertEquals(this.testMittelpunktOrtskurve, mittelpunktOrtskurve);
   
   // Der Radius der Ortskurve wird gelesen.
   Field radiusOrtskurveFeld = Ortskurve.class.getDeclaredField("radiusOrtskurve"); 
   radiusOrtskurveFeld.setAccessible(true);
   double radiusOrtskurve = radiusOrtskurveFeld.getDouble(this.ortskurve);
   
   // Es wird überprüft, ob der Mittelpunkt der Ortskurve korrekt initialisiert worden ist.
   assertEquals(this.testRadiusOrtskurve, radiusOrtskurve, 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ortskurve#getMittelpunktOrtskurve()}.
 */
@Test
public void testGetMittelpunktOrtskurve()
   {
   // Es wird überprüft, ob der Mittelpunkt der Ortskurve korrekt zurückgegeben wird. 
   assertEquals(this.testMittelpunktOrtskurve, this.ortskurve.getMittelpunktOrtskurve());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ortskurve#getRadiusOrtskurve()}.
 */
@Test
public void testGetRadiusOrtskurve()
   {
   // Es wird überprüft, ob der Radius der Ortskurve korrekt zurückgegeben wird. 
   assertEquals(this.testRadiusOrtskurve, this.ortskurve.getRadiusOrtskurve(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ortskurve#skalierteOrtskurveBerechnen(double)}.
 */
@Test
public void testSkalierteOrtskurveBerechnen()
   {
   // Die zu testende Methode wird aufgerufen.
   Ortskurve skalierteOrtskurve = this.ortskurve.skalierteOrtskurveBerechnen(2.0);
   
   // Es wird überprüft, ob die skalierte Ortskurve korrekt berechnet worden ist.
   assertEquals(0.5, skalierteOrtskurve.getRadiusOrtskurve(), 0.5/1000.0);
   assertEquals(0.5, skalierteOrtskurve.getMittelpunktOrtskurve().getX(), 0.5/1000.0);
   assertEquals(0.0, skalierteOrtskurve.getMittelpunktOrtskurve().getY(), 0.5/1000.0);
   }
}
