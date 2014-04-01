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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import de.thkwalter.et.ortskurve.Ortskurve;

/**
 * Diese Klasse enthält Tests für die Klasse {@link ErsatzschaltbildController}.
 * 
 * @author Th. K. Walter
 * @version 1.2
 */
public class ErsatzschaltbildControllerTest
{
/**
 * Das Testbjekt der Klasse {@link ErsatzschaltbildController}
 */
private ErsatzschaltbildController ersatzschaltbildController;

/**
 * Das in den Tests verwendete Modell der Ersatzschaltbildberechnung
 */
private ErsatzschaltbildModell testErsatzschaltbildModell;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode initialisiert die Tests. Dieser Test wird spezifiziert im Tabellenwerk ersatzschaltbild.ods.
 * 
 * @throws java.lang.Exception
 */
@Before
public void setUp() throws Exception
   {
   // Das Objekt der zu testenden Klasse wird erzeugt.
   this.ersatzschaltbildController = new ErsatzschaltbildController();
   
   // Das in den Tests verwendete Modell der Ersatzschaltbildberechnung wird erzeugt.
   this.testErsatzschaltbildModell = new ErsatzschaltbildModell();
   
   // Die im Test verwendete Ortskurve wird erstellt und zum Modell der Ersatzschaltbildberechnung hinzugefügt.
   Ortskurve testOrtskurve = new Ortskurve(new Vector2D(2.0, 0.5), 1.0);
   this.testErsatzschaltbildModell.setOrtskurve(testOrtskurve);
   
   // Die im Test verwendete Strangspannung (in V) wird im Modell der Ersatzschaltbildberechnung gespeichert.
   this.testErsatzschaltbildModell.setU_LL(400.0);
   
   // Die im Test verwendete Polpaarzahl wird im Modell der Ersatzschaltbildberechnung gespeichert.
   this.testErsatzschaltbildModell.setP(1);
   
   // Die in den Tests verwendete Netzfrequenz wird im Datenmodell der Ersatzschaltbildberechnung gespeichert.
   this.testErsatzschaltbildModell.setF1(50.0);
   
   // Das im Test verwendete Feld der Betriebspunkte wird erstellt und zum Modell hinzugefügt.
   ArrayList<Betriebspunkt> testBetriebspunkte = new ArrayList<>();
   this.testErsatzschaltbildModell.setBetriebspunkte(testBetriebspunkte);
   
   // Ein im Test verwendeter Betriebspunkt wird erstellt und zum Feld der Betriebspunkte hinzugefügt.
   Betriebspunkt betriebspunkt = new Betriebspunkt(new Complex(0.1724324324, -1.0445945946));
   betriebspunkt.setN(50.0);
   testBetriebspunkte.add(betriebspunkt);
   
   // Ein im Test verwendeter Betriebspunkt wird erstellt und zum Feld der Betriebspunkte hinzugefügt.
   betriebspunkt = new Betriebspunkt(new Complex(1.4081595206, -2.3941399308));
   betriebspunkt.setN(0.0);
   testBetriebspunkte.add(betriebspunkt);
   
   // Ein im Test verwendeter Betriebspunkt wird erstellt und zum Feld der Betriebspunkte hinzugefügt.
   betriebspunkt = new Betriebspunkt(new Complex(1.4708019328, -2.1940196055));
   betriebspunkt.setN(25.0);
   testBetriebspunkte.add(betriebspunkt);
   
   // Der im Test verwendete Schaltungstyp wird im Datenmodell der Ersatzschaltbildberechnung gespeichert.
   this.testErsatzschaltbildModell.setSchaltungstyp(Schaltungstyp.STERN);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildController#setErsatzschaltbildModell(ErsatzschaltbildModell)}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testSetErsatzschaltbildModell() throws NoSuchFieldException, SecurityException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Die zu testende Methode wird aufgerufen.
   this.ersatzschaltbildController.setErsatzschaltbildModell(this.testErsatzschaltbildModell);
   
   // Das Modell der Ersatzschaltbildberechnung wird gelesen.
   Field feld = ErsatzschaltbildController.class.getDeclaredField("ersatzschaltbildModell");
   feld.setAccessible(true);
   ErsatzschaltbildModell ersatzschaltbildModell = (ErsatzschaltbildModell) feld.get(this.ersatzschaltbildController);
   
   // Es wird überprüft, ob das Modell der Ersatzschaltbildberechnung korrekt im Controller gespeichert worden ist.
   assertEquals(this.testErsatzschaltbildModell, ersatzschaltbildModell);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link ErsatzschaltbildController#ersatzschaltbildBerechnenIntern()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testErsatzschaltbildBerechnenIntern() throws NoSuchMethodException, SecurityException, 
   IllegalAccessException, IllegalArgumentException, InvocationTargetException
   {
   // Die zu testende Methode wird aufgerufen.
   this.ersatzschaltbildController.setErsatzschaltbildModell(this.testErsatzschaltbildModell);
   
   // Die zu testende Methode wird aufgerufen.
   Method methode = 
      ErsatzschaltbildController.class.getDeclaredMethod("ersatzschaltbildBerechnenIntern", (Class<?>[]) null);
   methode.setAccessible(true);
   methode.invoke(this.ersatzschaltbildController, (Object[]) null);
   
   // Die Repräsentation des Ersatzschaltbildes wird gelesen.
   Ersatzschaltbild ersatzschaltbild = this.testErsatzschaltbildModell.getErsatzschaltbild();
   
   // Es wir überprüft, ob die Widerstände korrekt berechnet worden sind.
   assertEquals(35.53, ersatzschaltbild.getR1(), 35.53 / 1000.0);
   assertEquals(213.2, ersatzschaltbild.getX1(), 213.2 / 1000.0);
   assertEquals(106.6, ersatzschaltbild.getX_k(), 106.6 / 1000.0);
   assertEquals(15.0, ersatzschaltbild.getR2_strich(), 15.0 / 100.0);
   }
}
