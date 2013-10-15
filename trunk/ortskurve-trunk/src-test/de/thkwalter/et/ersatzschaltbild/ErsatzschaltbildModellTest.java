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
package de.thkwalter.et.ersatzschaltbild;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import de.thkwalter.et.ortskurve.Ausgleichsproblem;
import de.thkwalter.et.ortskurve.Ortskurve;
import de.thkwalter.et.ortskurve.OrtskurveModell;

/**
 * Diese Klasse enthält Tests für die Klasse {@link ErsatzschaltbildModell}.
 * 
 * @author Th. K. Walter
 * @version 1.2
 */
public class ErsatzschaltbildModellTest
{
/**
 * Ein Objekt der zu testenden Klasse {@link ErsatzschaltbildModell}
 */
private ErsatzschaltbildModell ersatzschaltbildModell;

/**
 * Die im Test verwendete Ortskurve
 */
private Ortskurve testOrtskurve;

/**
 * Die im Test verwendete Liste der Betriebspunkte
 */
private ArrayList<Betriebspunkt> testBetriebspunkte;

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
   // Das Objekt der zu testenden Klasse wird erzeugt.
   this.ersatzschaltbildModell = new ErsatzschaltbildModell();
   
   // Die im Test verwendete Ortskurve wird initialisiert.
   this.testOrtskurve = new Ortskurve(new Vector2D(2, 0), 2);
   
   // Die im Test verwendete Liste der Betriebspunkte wird initialisiert.
   this.testBetriebspunkte = new ArrayList<>();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link ErsatzschaltbildModell#ErsatzschaltbildModell()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testErsatzschaltbildModell() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Es wird überprüft, ob das Datenmodell des Ersatzschaltbilds erzeugt worden ist.
   assertNotNull(this.ersatzschaltbildModell);
   
   // Es wird überprüft, ob die Liste der Betriebspunkte initialisiert worden ist.
   assertNotNull(this.ersatzschaltbildModell.getBetriebspunkte());
   
   // Die Frequenz des Ständerstroms (in Hz) wird gelesen.
   Field feld = ErsatzschaltbildModell.class.getDeclaredField("f1");
   feld.setAccessible(true);
   Double f1 = feld.getDouble(this.ersatzschaltbildModell);
   
   // Es wird überprüft, ob die Frequenz des Ständerstroms initialisiert worden ist.
   assertTrue(Double.isNaN(f1));
   
   // Die effektive Leiter-Leiter-Spannung (in V) wird gelesen.
   feld = ErsatzschaltbildModell.class.getDeclaredField("u1");
   feld.setAccessible(true);
   Double u1 = feld.getDouble(this.ersatzschaltbildModell);
   
   // Es wird überprüft, ob die Leiter-Leiter-Spannung (in V) korrekt initialisiert worden ist.
   assertTrue(Double.isNaN(u1));
   
   // Die Polpaarzahl wird gelesen.
   feld = ErsatzschaltbildModell.class.getDeclaredField("p");
   feld.setAccessible(true);
   Integer p = feld.getInt(this.ersatzschaltbildModell);
   
   // Es wird überprüft, ob die Polpaarzahl initialisiert worden ist.
   assertEquals(Integer.MIN_VALUE, p.intValue());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#datenUebernehmen(Ausgleichsproblem)}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testDatenUebernehmen() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
   {
   // Die im Test verwendete Ortskurve wird erzeugt.
   Ortskurve testOrtskurve = new Ortskurve(new Vector2D(2.0, 0.0), 1.5);
   
   // Das im Test verwendete Datenmodell der Ortskurvenberechnung wird erzeugt.
   OrtskurveModell testOrtskurveModell = new OrtskurveModell();
   testOrtskurveModell.setOrtskurve(testOrtskurve);
   testOrtskurveModell.setMesspunkte(new Vector2D[]{new Vector2D(0.5, 0.0), new Vector2D(2.0, 1.5)});
   
   // Der im Test verwendete Controller der Ortskurvenberechnung wird initialisiert.
   Ausgleichsproblem testAusgleichsproblem = new Ausgleichsproblem();
   testAusgleichsproblem.setOrtskurveModell(testOrtskurveModell);
   
   // Die zu testende Methode wird aufgerufen.
   Method methode = ErsatzschaltbildModell.class.getDeclaredMethod("datenUebernehmen", Ausgleichsproblem.class);
   methode.setAccessible(true);
   methode.invoke(this.ersatzschaltbildModell, testAusgleichsproblem);
   
   // Es wird überprüft, ob die Ortskurve korrekt übernommen worden ist.
   assertEquals(testOrtskurve, this.ersatzschaltbildModell.getOrtskurve());
   
   // Es wird überprüft, ob die Messpunkte korrekt übernommen worden sind.
   assertEquals(2, this.ersatzschaltbildModell.getBetriebspunkte().size());
   assertEquals(new Complex(1.5, -2.0), 
      ((Betriebspunkt) this.ersatzschaltbildModell.getBetriebspunkte().get(1)).getI1());
   assertEquals(new Complex(0.0, -0.5), 
      ((Betriebspunkt) this.ersatzschaltbildModell.getBetriebspunkte().get(0)).getI1());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#getOrtskurve()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetOrtskurve() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Eine Ortskurve wird im Datenmodell gespeichert.
   Field ortskurveFeld = ErsatzschaltbildModell.class.getDeclaredField("ortskurve");
   ortskurveFeld.setAccessible(true);
   ortskurveFeld.set(this.ersatzschaltbildModell, this.testOrtskurve);
   
   // Die zu testende Methode wird aufgerufen.
   Ortskurve ortskurve = this.ersatzschaltbildModell.getOrtskurve();
   
   // Es wird überprüft, ob die Ortskurve korrekt zurückgegeben worden ist.
   assertEquals(this.testOrtskurve, ortskurve);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#setOrtskurve(Ortskurve)}.
 */
@Test
public void testSetOrtskurve()
   {
   // Die zu testende Methode wird aufgerufen.
   this.ersatzschaltbildModell.setOrtskurve(this.testOrtskurve);
   
   // Es wird überprüft, ob die Ortskurve korrekt gespeichert worden ist.
   assertEquals(this.testOrtskurve, this.ersatzschaltbildModell.getOrtskurve());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#getBetriebspunkte()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetBetriebspunkte() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Die im Test verwendete Liste der Betriebspunkte wird im Datenmodell gespeichert.
   Field betriebspunkteFeld = ErsatzschaltbildModell.class.getDeclaredField("betriebspunkte");
   betriebspunkteFeld.setAccessible(true);
   betriebspunkteFeld.set(this.ersatzschaltbildModell, this.testBetriebspunkte);
   
   // Die zu testende Methode wird aufgerufen.
   ArrayList<Betriebspunkt> betriebspunkte = this.ersatzschaltbildModell.getBetriebspunkte();
   
   // Es wird überprüft, ob die Ortskurve korrekt zurückgegeben worden ist.
   assertEquals(this.testBetriebspunkte, betriebspunkte);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#setBetriebspunkte(java.util.ArrayList)}.
 */
@Test
public void testSetBetriebspunkte()
   {
   // Die zu testende Methode wird aufgerufen.
   this.ersatzschaltbildModell.setBetriebspunkte(this.testBetriebspunkte);
   
   // Es wird überprüft, ob die Liste der Betriebspunkte korrekt gespeichert worden ist.
   assertEquals(this.testBetriebspunkte, this.ersatzschaltbildModell.getBetriebspunkte());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#getU1()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetU1_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
   {
   // Die im Test verwendete Leiter-Leiter-Spannung (in V) wird im Datenmodell gespeichert.
   Field u1Feld = ErsatzschaltbildModell.class.getDeclaredField("u1");
   u1Feld.setAccessible(true);
   u1Feld.setDouble(this.ersatzschaltbildModell, 400.0);
   
   // Die zu testende Methode wird aufgerufen.
   double u1 = this.ersatzschaltbildModell.getU1();
   
   // Es wird überprüft, ob die Leiter-Leiter-Spannung (in V) korrekt zurückgegeben worden ist.
   assertEquals(400.0, u1, 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#getU1()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetU1_2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
   {
   // Die im Test verwendete Leiter-Leiter-Spannung (in V) wird im Datenmodell gespeichert.
   Field u1Feld = ErsatzschaltbildModell.class.getDeclaredField("u1");
   u1Feld.setAccessible(true);
   u1Feld.setDouble(this.ersatzschaltbildModell, Double.NaN);
   
   // Die zu testende Methode wird aufgerufen.
   assertNull(this.ersatzschaltbildModell.getU1());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test method for {@link de.thkwalter.et.ersatzschaltbild.ErsatzschaltbildModell#setU1(double)}.
 */
@Test
public void testSetU1()
   {
   // Die zu testende Methode wird aufgerufen.
   this.ersatzschaltbildModell.setU1(400.0);
   
   // Es wird überprüft, ob die Leiter-Leiter-Spannung (in V) korrekt gespeichert worden ist.
   assertEquals(400.0, this.ersatzschaltbildModell.getU1(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#getF1()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetF1_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Die im Test verwendete Frequenz des Ständerstroms (in Hz) wird im Datenmodell gespeichert.
   Field f1Feld = ErsatzschaltbildModell.class.getDeclaredField("f1");
   f1Feld.setAccessible(true);
   f1Feld.setDouble(this.ersatzschaltbildModell, 50.0);
   
   // Die zu testende Methode wird aufgerufen.
   double f1 = this.ersatzschaltbildModell.getF1();
   
   // Es wird überprüft, ob die Frequenz des Ständerstroms (in Hz) korrekt zurückgegeben worden ist.
   assertEquals(50.0, f1, 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#getF1()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetF1_2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Die im Test verwendete Frequenz des Ständerstroms (in Hz) wird im Datenmodell gespeichert.
   Field f1Feld = ErsatzschaltbildModell.class.getDeclaredField("f1");
   f1Feld.setAccessible(true);
   f1Feld.setDouble(this.ersatzschaltbildModell, Double.NaN);
   
   // Es wird überprüft, ob die Frequenz des Ständerstroms (in Hz) korrekt zurückgegeben worden ist.
   assertNull(this.ersatzschaltbildModell.getF1());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#setF1(double)}.
 */
@Test
public void testSetF1()
   {
   // Die zu testende Methode wird aufgerufen.
   this.ersatzschaltbildModell.setF1(50.0);
   
   // Es wird überprüft, ob die Frequenz des Ständerstroms (in Hz) korrekt gespeichert worden ist.
   assertEquals(50.0, this.ersatzschaltbildModell.getF1(), 50.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#getP()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetP_1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Die im Test verwendete Polpaarzahl wird im Datenmodell gespeichert.
   Field pFeld = ErsatzschaltbildModell.class.getDeclaredField("p");
   pFeld.setAccessible(true);
   pFeld.setInt(this.ersatzschaltbildModell, 2);
   
   // Die zu testende Methode wird aufgerufen.
   int p = this.ersatzschaltbildModell.getP();
   
   // Es wird überprüft, ob die Polpaarzahl korrekt zurückgegeben worden ist.
   assertEquals(2, p);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#getP()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetP_2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Die im Test verwendete Polpaarzahl wird im Datenmodell gespeichert.
   Field pFeld = ErsatzschaltbildModell.class.getDeclaredField("p");
   pFeld.setAccessible(true);
   pFeld.setInt(this.ersatzschaltbildModell, Integer.MIN_VALUE);
   
   // Es wird überprüft, ob die Polpaarzahl korrekt zurückgegeben worden ist.
   assertNull(this.ersatzschaltbildModell.getP());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#setP(int)}.
 */
@Test
public void testSetP()
   {
   // Die zu testende Methode wird aufgerufen.
   this.ersatzschaltbildModell.setP(3);
   
   // Es wird überprüft, ob die Polpaarzahl korrekt gespeichert worden ist.
   assertEquals(3, this.ersatzschaltbildModell.getP().intValue());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#toString()}.
 */
@Test
public void testToString1()
   {
   // Das Objekt der zu testenden Klasse wird initalisiert.
   this.ersatzschaltbildModell.setOrtskurve(this.testOrtskurve);
   this.ersatzschaltbildModell.setBetriebspunkte(this.testBetriebspunkte);
   this.testBetriebspunkte.add(new Betriebspunkt(new Complex(2, 0)));
   this.ersatzschaltbildModell.setF1(50d);
   this.ersatzschaltbildModell.setU1(400d);
   this.ersatzschaltbildModell.setP(1);
   
   // Es wird überprüft, ob die Zeichenkette, die das zu testende Objekt repräsentiert, korrekt zusammengebaut wird.
   String meldung = "ErsatzschaltbildModell [ortskurve=mittelpunktOrtskurve: {2; 0}; radiusOrtskurve: 2.0; , " +
      "betriebspunkte=[Betriebspunkt [i1=(2.0, 0.0), n=NaN]], u1=400.0, f1=50.0, p=1]";
   assertEquals(meldung, this.ersatzschaltbildModell.toString());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildModell#toString()}.
 */
@Test
public void testToString2()
   {   
   // Es wird überprüft, ob die Zeichenkette, die das zu testende Objekt repräsentiert, korrekt zusammengebaut wird.
   String meldung = "ErsatzschaltbildModell [betriebspunkte=[], u1=NaN, f1=NaN, p=-2147483648]";
   assertEquals(meldung, this.ersatzschaltbildModell.toString());
   }
}
