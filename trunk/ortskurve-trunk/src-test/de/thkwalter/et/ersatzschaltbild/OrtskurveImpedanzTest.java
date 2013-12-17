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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Test;

import de.thkwalter.et.ortskurve.Ortskurve;

/**
 * Diese Klasse enthält Tests für die Klasse {@link OrtskurveImpedanz}.
 * 
 * @author Th. K. Walter
 */
public class OrtskurveImpedanzTest
{
/**
 * Test der Methode {@link OrtskurveImpedanz#ortskurveImpedanzBerechnen(Ortskurve, double, Schaltungstyp)}.
 */
@Test
public void testOrtskurveImpedanzBerechnen()
   {
   // Die im Test verwendete Ortskurve
   Ortskurve ortskurve = new Ortskurve(new Vector2D(-692.82, 230.94), 461.88);
   
   // Die zu testende Methode wird aufgerufen
   Ortskurve ortskurveImpedanz = OrtskurveImpedanz.ortskurveImpedanzBerechnen(ortskurve, 400.0, Schaltungstyp.STERN);
   
   // Es wird überprüft, ob die invertierte Ortskurve korrekt berechnet worden ist.
   assertEquals(0.3333, ortskurveImpedanz.getRadiusOrtskurve(), 0.3333/1000);
   assertEquals(0.5, ortskurveImpedanz.getMittelpunktOrtskurve().getX(), 0.5/1000);
   assertEquals(0.1667, ortskurveImpedanz.getMittelpunktOrtskurve().getY(), 0.1667/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link OrtskurveImpedanz#ortskurveInverseImpedanzBerechnen(Ortskurve, double, Schaltungstyp)}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testOrtskurveInverseImpedanzBerechnen1() throws NoSuchMethodException, SecurityException, 
   IllegalAccessException, IllegalArgumentException, InvocationTargetException
   {
   // Die im Test verwendete Ortskurve
   Ortskurve ortskurve = new Ortskurve(new Vector2D(4.0, 1.0), 2.0);
   
   // Die zu testende Methode wird aufgerufen.
   Method methode = OrtskurveImpedanz.class.getDeclaredMethod("ortskurveInverseImpedanzBerechnen", Ortskurve.class, 
      double.class, Schaltungstyp.class);
   methode.setAccessible(true);
   Ortskurve ortskurveInverseImpedanz = 
      (Ortskurve) methode.invoke(OrtskurveImpedanz.class, ortskurve, 400.0, Schaltungstyp.STERN);
   
   // Es wird überprüft, ob die berechnete Ortskurve der inversen Impedanz korrekt berechnet worden ist.
   assertEquals(0.00866, ortskurveInverseImpedanz.getRadiusOrtskurve(), 0.00866/1000);
   assertEquals(0.01732, ortskurveInverseImpedanz.getMittelpunktOrtskurve().getX(), 0.01732/1000);
   assertEquals(0.00433, ortskurveInverseImpedanz.getMittelpunktOrtskurve().getY(), 0.00433/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link OrtskurveImpedanz#ortskurveInverseImpedanzBerechnen(Ortskurve, double, Schaltungstyp)}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testOrtskurveInverseImpedanzBerechnen2() throws NoSuchMethodException, SecurityException, 
   IllegalAccessException, IllegalArgumentException, InvocationTargetException
   {
   // Die im Test verwendete Ortskurve
   Ortskurve ortskurve = new Ortskurve(new Vector2D(4.0, 1.0), 2.0);
   
   // Die zu testende Methode wird aufgerufen.
   Method methode = OrtskurveImpedanz.class.getDeclaredMethod("ortskurveInverseImpedanzBerechnen", Ortskurve.class, 
      double.class, Schaltungstyp.class);
   methode.setAccessible(true);
   Ortskurve ortskurveInverseImpedanz = 
      (Ortskurve) methode.invoke(OrtskurveImpedanz.class, ortskurve, 400.0, Schaltungstyp.DREIECK);
   
   // Es wird überprüft, ob die berechnete Ortskurve der inversen Impedanz korrekt berechnet worden ist.
   assertEquals(0.002887, ortskurveInverseImpedanz.getRadiusOrtskurve(), 0.002887/1000);
   assertEquals(0.00577, ortskurveInverseImpedanz.getMittelpunktOrtskurve().getX(), 0.00577/1000);
   assertEquals(0.001443, ortskurveInverseImpedanz.getMittelpunktOrtskurve().getY(), 0.001443/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link OrtskurveImpedanz#ortskurveInvertieren(Ortskurve)}.
 * 
 * @throws SecurityException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalArgumentException 
 * @throws IllegalAccessException 
 */
@Test
public void testOrtskurveInvertieren() throws NoSuchMethodException, SecurityException, 
   IllegalAccessException, IllegalArgumentException, InvocationTargetException
   {
   // Die im Test verwendete Ortskurve
   Ortskurve ortskurve = new Ortskurve(new Vector2D(-3.0, 1.0), 2.0);
   
   // Die zu testende Methode wird aufgerufen.
   Method methode = OrtskurveImpedanz.class.getDeclaredMethod("ortskurveInvertieren", Ortskurve.class);
   methode.setAccessible(true);
   Ortskurve ortskurveImpedanz = (Ortskurve) methode.invoke(OrtskurveImpedanz.class, ortskurve);
   
   // Es wird überprüft, ob die invertierte Ortskurve korrekt berechnet worden ist.
   assertEquals(0.3333, ortskurveImpedanz.getRadiusOrtskurve(), 0.3333/1000);
   assertEquals(0.5, ortskurveImpedanz.getMittelpunktOrtskurve().getX(), 0.5/1000);
   assertEquals(0.1667, ortskurveImpedanz.getMittelpunktOrtskurve().getY(), 0.1667/1000);
   }
}
