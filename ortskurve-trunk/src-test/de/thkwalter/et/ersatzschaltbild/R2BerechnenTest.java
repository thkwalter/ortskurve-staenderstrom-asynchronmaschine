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
package de.thkwalter.et.ersatzschaltbild;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import de.thkwalter.et.ortskurve.Ortskurve;

/**
 * Diese Klasse enthält Tests für die Klasse {@link R2Berechnen}.
 * 
 * @author Th. K. Walter
 */
public class R2BerechnenTest
{
/**
 * Das Testobjekt der Klasse {@link R2Berechnen}
 */
private R2Berechnen r2Berechnen;

/**
 * Die in den Tests verwendeten Betriebspunkte
 */
private ArrayList<Betriebspunkt> testBetriebspunkte;

/**
 * Die in den Tests verwendete Ortskurve
 */
private Ortskurve testOrtskurve;

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
   // Das im Test verwendete Feld der Betriebspunkte wird erstellt.
   this.testBetriebspunkte = new ArrayList<>();
   
   // Ein im Test verwendeter Betriebspunkte wird erstellt und zum Feld der Betriebspunkte hinzugefügt.
   Betriebspunkt betriebspunkt = new Betriebspunkt(new Complex(0.1774324324, -1.0645945946));
   betriebspunkt.setN(50.0);
   this.testBetriebspunkte.add(betriebspunkt);
   
   // Ein im Test verwendeter Betriebspunkte wird erstellt und zum Feld der Betriebspunkte hinzugefügt.
   betriebspunkt = new Betriebspunkt(new Complex(1.4031595206, -2.3741399308));
   betriebspunkt.setN(0.0);
   this.testBetriebspunkte.add(betriebspunkt);
   
   // Ein im Test verwendeter Betriebspunkte wird erstellt und zum Feld der Betriebspunkte hinzugefügt.
   betriebspunkt = new Betriebspunkt(new Complex(1.4658019328, -2.1740196055));
   betriebspunkt.setN(25.0);
   this.testBetriebspunkte.add(betriebspunkt);
   
   // Die im Test verwendete Stromortskurve wird erzeugt.
   this.testOrtskurve = new Ortskurve(new Vector2D(2.0, 0.5), 1.0);
   
   // Das Testobjekt wird erzeugt.
   this.r2Berechnen = new R2Berechnen(this.testBetriebspunkte, this.testOrtskurve);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test des Konstruktors {@link R2Berechnen#R2Berechnen(ArrayList)}.
 */
@Test
public void testR2Berechnen()
   {
   // Es wird überprüft, ob das Testobjekt korrekt erzeugt worden ist.
   assertNotNull(this.r2Berechnen);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link R2Berechnen#aufOrtskurveProjezieren(Betriebspunkt, Complex, double)}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testAufOrtskurveProjezieren1() throws NoSuchMethodException, SecurityException, IllegalAccessException, 
   IllegalArgumentException, InvocationTargetException
   {
   // Der im Test verwendete Mittelpunkt der Stromortskurve (in realen Koordinaten) wird erzeugt.
   Complex testMittelpunkt = new Complex(0.5, -2.0);
   
   // Der im Test verwendete Radius der Stromortskurve wird erzeugt.
   double testRadiusOrtskurve = 1.0;
   
   // Ein Betriebspunkt, der nicht auf der Kreislinie liegt, wird erzeugt.
   Betriebspunkt originalBetriebspunkt = new Betriebspunkt(new Complex(1.5, -3.0));
   originalBetriebspunkt.setN(1000.0);
   
   // Die zu testende Methode wird ausgeführt.
   Method methode = 
      R2Berechnen.class.getDeclaredMethod("aufOrtskurveProjezieren", Betriebspunkt.class, Complex.class, double.class);
   methode.setAccessible(true);
   Complex projezierterI1 = 
      (Complex) methode.invoke(this.r2Berechnen, originalBetriebspunkt, testMittelpunkt, testRadiusOrtskurve);
   
   // Es wird überprüft, ob der projezierte Strommesspunkt korrekt berechnet worden ist.
   assertEquals(1.207, projezierterI1.getReal(), 1.207/1000.0);
   assertEquals(-2.707, projezierterI1.getImaginary(), 2.707/1000.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link R2Berechnen#aufOrtskurveProjezieren(Betriebspunkt, Complex, double)}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testAufOrtskurveProjezieren2() throws NoSuchMethodException, SecurityException, IllegalAccessException, 
   IllegalArgumentException, InvocationTargetException
   {
   // Der im Test verwendete Mittelpunkt der Stromortskurve (in realen Koordinaten) wird erzeugt.
   Complex testMittelpunkt = new Complex(0.5, -2.0);
   
   // Der im Test verwendete Radius der Stromortskurve wird erzeugt.
   double testRadiusOrtskurve = 1.0;
   
   // Ein Betriebspunkt, der nicht auf der Kreislinie liegt, wird erzeugt.
   Betriebspunkt originalBetriebspunkt = new Betriebspunkt(new Complex(-0.3, -2.0));
   originalBetriebspunkt.setN(1000.0);
   
   // Die zu testende Methode wird ausgeführt.
   Method methode = 
      R2Berechnen.class.getDeclaredMethod("aufOrtskurveProjezieren", Betriebspunkt.class, Complex.class, double.class);
   methode.setAccessible(true);
   Complex projezierterI1 = 
         (Complex) methode.invoke(this.r2Berechnen, originalBetriebspunkt, testMittelpunkt, testRadiusOrtskurve);
   
   // Es wird überprüft, ob der projezierte Betriebspunkt korrekt berechnet worden ist.
   assertEquals(-0.5, projezierterI1.getReal(), 0.5/1000.0);
   assertEquals(-2.0, projezierterI1.getImaginary(), 2.0/1000.0);
   }
}
