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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.junit.Before;
import org.junit.Test;

import de.thkwalter.et.ortskurve.Ortskurve;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Laeuferwicklungswiderstand}.
 * 
 * @author Th. K. Walter
 */
public class LaeuferwicklungswiderstandTest
{
/**
 * Das Testobjekt der Klasse {@link Laeuferwicklungswiderstand}
 */
private Laeuferwicklungswiderstand laeuferwicklungswiderstand;

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
   this.laeuferwicklungswiderstand = new Laeuferwicklungswiderstand(this.testBetriebspunkte, this.testOrtskurve, 400.0, Schaltungstyp.STERN, 35.5,
      213.2, 106.6, 50);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test des Konstruktors {@link Laeuferwicklungswiderstand#R2Berechnen(ArrayList)}.
 */
@Test
public void testR2Berechnen()
   {
   // Es wird überprüft, ob das Testobjekt korrekt erzeugt worden ist.
   assertNotNull(this.laeuferwicklungswiderstand);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Laeuferwicklungswiderstand#aufOrtskurveProjezieren(Betriebspunkt, Complex, double)}.
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
      Laeuferwicklungswiderstand.class.getDeclaredMethod("aufOrtskurveProjezieren", Betriebspunkt.class, Complex.class, double.class);
   methode.setAccessible(true);
   Complex projezierterI1 = 
      (Complex) methode.invoke(this.laeuferwicklungswiderstand, originalBetriebspunkt, testMittelpunkt, testRadiusOrtskurve);
   
   // Es wird überprüft, ob der projezierte Strommesspunkt korrekt berechnet worden ist.
   assertEquals(1.207, projezierterI1.getReal(), 1.207/1000.0);
   assertEquals(-2.707, projezierterI1.getImaginary(), 2.707/1000.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Laeuferwicklungswiderstand#aufOrtskurveProjezieren(Betriebspunkt, Complex, double)}.
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
      Laeuferwicklungswiderstand.class.getDeclaredMethod("aufOrtskurveProjezieren", Betriebspunkt.class, Complex.class, double.class);
   methode.setAccessible(true);
   Complex projezierterI1 = 
         (Complex) methode.invoke(this.laeuferwicklungswiderstand, originalBetriebspunkt, testMittelpunkt, testRadiusOrtskurve);
   
   // Es wird überprüft, ob der projezierte Betriebspunkt korrekt berechnet worden ist.
   assertEquals(-0.5, projezierterI1.getReal(), 0.5/1000.0);
   assertEquals(-2.0, projezierterI1.getImaginary(), 2.0/1000.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Laeuferwicklungswiderstand#r_2_komplex(z_1, r_1, x_1, x_k, s)}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testr_2_komplex() throws NoSuchMethodException, SecurityException, IllegalAccessException, 
   IllegalArgumentException, InvocationTargetException
   {
   // Die zu testende Methode wird aufgerufen.
   Method methode = Laeuferwicklungswiderstand.class.getDeclaredMethod("r_2_komplex", Complex.class, double.class, double.class, 
      double.class, double.class);
   methode.setAccessible(true);
   Complex r_2_komplex = 
      (Complex) methode.invoke(this.laeuferwicklungswiderstand, new Complex(42.18, 71.37), 35.5, 213.2, 106.6, 1.0);
   
   // Es wird überprüft, ob der auf den Ständer bezogene, ohmsche Läuferwicklungswiderstand korrekt berechnet worden 
   // ist.
   assertEquals(15.0, r_2_komplex.getReal(), 15.0 / 100);
   assertEquals(0.0, r_2_komplex.getImaginary(), 15.0 / 100);
   
   // Die zu testende Methode wird aufgerufen.
   r_2_komplex = (Complex) methode.invoke(this.laeuferwicklungswiderstand, new Complex(48.75, 72.30), 35.5, 213.2, 106.6, 0.5);
      
   // Es wird überprüft, ob der auf den Ständer bezogene, ohmsche Läuferwicklungswiderstand korrekt berechnet worden 
   // ist.
   assertEquals(15.0, r_2_komplex.getReal(), 15.0 / 100);
   assertEquals(0.0, r_2_komplex.getImaginary(), 15.0 / 100);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Laeuferwicklungswiderstand#getR2()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetR2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
   {
   // Das Objekt, das im Test für die Statistikberechnungen genutzt wird, wird erzeugt und initialisiert.
   DescriptiveStatistics testDescriptiveStatistics = new DescriptiveStatistics();
   testDescriptiveStatistics.addValue(16.0);
   
   // Das Objekt, das im Test für die Statistikberechnungen genutzt wird, wird gespeichert.
   Field feld = Laeuferwicklungswiderstand.class.getDeclaredField("descriptiveStatistics");
   feld.setAccessible(true);
   feld.set(this.laeuferwicklungswiderstand, testDescriptiveStatistics);
   
   // Die zu testende Methode wird aufgerufen.
   double r2 = this.laeuferwicklungswiderstand.getR2();
   
   // Es wird überprüft, ob der auf den Ständer bezogene, ohmsche Läuferwicklungswiderstand (in Ohm) korrekt 
   // zurückgegeben worden ist.
   assertEquals(16.0, r2, 16.0/1000);
   }
}
