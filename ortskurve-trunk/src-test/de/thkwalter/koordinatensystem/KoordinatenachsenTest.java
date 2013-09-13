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
package de.thkwalter.koordinatensystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Koordinatenachsen}.
 *
 * @author Th. K. Walter
 * @version 1.0
 */
public class KoordinatenachsenTest
{
/**
 * Ein Objekt der zu testenden Klasse {@link Koordinatenachsen}.
 */
private Koordinatenachsen koordinatenachsen;

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
   Wertebereich wertebereich = new Wertebereich(2.2, 1.2, -0.2, -1.2);
   
   // Der zu testende Konstruktor wird aufgerufen.
   PunktPixelKonverter punktPixelKonverter = new PunktPixelKonverter(wertebereich, 540, 270);
   
   // Das Objekt der zu testenden Klasse wird erzeugt.
   this.koordinatenachsen = new Koordinatenachsen(wertebereich, punktPixelKonverter);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Koordinatenachsen#Koordinatenachsen(Wertebereich, PunktPixelKonverter)}.
 */
@Test
public void testKoordinatenachsen()
   {
   // Es wird überprüft, ob ein Objekt der zu testenden Klasse erzeugt worden ist.
   assertNotNull(this.koordinatenachsen);
   
   // Es wird überprüft, ob die Start- und Endpunkte der Koordinatenachsen korrekt berechnet worden sind.
   assertEquals(135.0, this.koordinatenachsen.getStartPunktXAchse().getX(), 135.0/1000);
   assertEquals(135.0, this.koordinatenachsen.getStartPunktXAchse().getY(), 135.0/1000);
   assertEquals(405.0, this.koordinatenachsen.getEndPunktXAchse().getX(), 405.0/1000);
   assertEquals(135.0, this.koordinatenachsen.getEndPunktXAchse().getY(), 135.0/1000);
   assertEquals(157.5, this.koordinatenachsen.getStartPunktYAchse().getX(), 157.5/1000);
   assertEquals(270.0, this.koordinatenachsen.getStartPunktYAchse().getY(), 270.0/1000);
   assertEquals(157.5, this.koordinatenachsen.getEndPunktYAchse().getX(), 157.5/1000);
   assertEquals(0.0, this.koordinatenachsen.getEndPunktYAchse().getY(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Koordinatenachsen#getStartPunktXAchse()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetStartPunktXAchse() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {   
   // Die Testdaten werden in dem zu testenden Objekt gespeichert.
   Field feld = Koordinatenachsen.class.getDeclaredField("startPunktXAchse");
   feld.setAccessible(true);
   Vector2D startPunktXAchse = (Vector2D) feld.get(this.koordinatenachsen);
   
   // Es wird überprüft, ob die zu testende Methode den korrekten Wert zurückgibt.
   assertEquals(startPunktXAchse, this.koordinatenachsen.getStartPunktXAchse());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Koordinatenachsen#getEndPunktXAchse()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetEndPunktXAchse() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {  
   // Die Testdaten werden in dem zu testenden Objekt gespeichert.
   Field feld = Koordinatenachsen.class.getDeclaredField("endPunktXAchse");
   feld.setAccessible(true);
   Vector2D endPunktXAchse = (Vector2D) feld.get(this.koordinatenachsen);
   
   // Es wird überprüft, ob die zu testende Methode den korrekten Wert zurückgibt.
   assertEquals(endPunktXAchse, this.koordinatenachsen.getEndPunktXAchse());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Koordinatenachsen#getStartPunktYAchse()}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetStartPunktYAchse() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {   
   // Die Testdaten werden in dem zu testenden Objekt gespeichert.
   Field feld = Koordinatenachsen.class.getDeclaredField("startPunktYAchse");
   feld.setAccessible(true);
   Vector2D startPunktYAchse = (Vector2D) feld.get(this.koordinatenachsen);
   
   // Es wird überprüft, ob die zu testende Methode den korrekten Wert zurückgibt.
   assertEquals(startPunktYAchse, this.koordinatenachsen.getStartPunktYAchse());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Koordinatenachsen#getEndPunktYAchse()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetEndPunktYAchse() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   { 
   // Die Testdaten werden in dem zu testenden Objekt gespeichert.
   Field feld = Koordinatenachsen.class.getDeclaredField("endPunktYAchse");
   feld.setAccessible(true);
   Vector2D endPunktYAchse = (Vector2D) feld.get(this.koordinatenachsen);
   
   // Es wird überprüft, ob die zu testende Methode den korrekten Wert zurückgibt.
   assertEquals(endPunktYAchse, this.koordinatenachsen.getEndPunktYAchse());
   }
}
