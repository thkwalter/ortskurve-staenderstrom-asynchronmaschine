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

import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Wertebereich}.
 *
 * @author Th. K. Walter
 */
public class WertebereichTest
{
/**
 * Ein Objekt der zu testenden Klasse {@link Wertebereich.}
 */
private Wertebereich wertebereich;

/**
 * Die im Test verwendete, maximale x-Koordinate.
 */
private double maxX;

/**
 * Die im Test verwendete, minimale x-Koordinate.
 */
private double minX;

/**
 * Die im Test verwendete, maximale y-Koordinate.
 */
private double maxY;

/**
 * Die im Test verwendete, minimale y-Koordinate.
 */
private double minY;

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
   // Die Testdaten werden initialisiert.
   this.maxX = 10.0;
   this.minX = -9.0;
   this.maxY = 8.0;
   this.minY = -7.0;
   
   // Das Objekt der zu testenden Klasse wird initialisiert.
   this.wertebereich = new Wertebereich(this.maxX, this.maxY, this.minX, this.minY);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link Wertebereich#Wertebereich(double, double, double, double)}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testWertebereich() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Es wird überprüft, ob der Konstruktor erfolgreich ausgeführt worden ist.
   assertNotNull(this.wertebereich);
   
   // Es wird überprüft, ob das Attribut maxX korrekt initialisiert worden ist.
   Field maxXFeld = Wertebereich.class.getDeclaredField("maxX");
   maxXFeld.setAccessible(true);
   assertEquals(this.maxX, maxXFeld.get(this.wertebereich));
   
   // Es wird überprüft, ob das Attribut maxY korrekt initialisiert worden ist.
   Field maxYFeld = Wertebereich.class.getDeclaredField("maxY");
   maxYFeld.setAccessible(true);
   assertEquals(this.maxY, maxYFeld.get(this.wertebereich));
   
   // Es wird überprüft, ob das Attribut minX korrekt initialisiert worden ist.
   Field minXFeld = Wertebereich.class.getDeclaredField("minX");
   minXFeld.setAccessible(true);
   assertEquals(this.minX, minXFeld.get(this.wertebereich));
   
   // Es wird überprüft, ob das Attribut minY korrekt initialisiert worden ist.
   Field minYFeld = Wertebereich.class.getDeclaredField("minY");
   minYFeld.setAccessible(true);
   assertEquals(this.minY, minYFeld.get(this.wertebereich));
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Wertebereich#getMaxX()}.
 */
@Test
public void testGetMaxX()
   {
   assertEquals(this.maxX, this.wertebereich.getMaxX(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Wertebereich#getMaxY()}.
 */
@Test
public void testGetMaxY()
   {
   assertEquals(this.maxY, this.wertebereich.getMaxY(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Wertebereich#getMinX()}.
 */
@Test
public void testGetMinX()
   {
   assertEquals(this.minX, this.wertebereich.getMinX(), 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Wertebereich#getMinY()}.
 */
@Test
public void testGetMinY()
   {
   assertEquals(this.minY, this.wertebereich.getMinY(), 0.0);
   }

}
