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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Modellgleichungen}.
 *
 * @author Th. K. Walter
 */
public class ModellgleichungenTest
{
/**
 * Die Messpunkte, die für den Test verwendet werden.
 */
private Vector2D[] testMesspunkte;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Das Objekt, das für die Tests verwendet wird.
 */
private Modellgleichungen modellgleichungen;

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
   // Die Messpunkte werden erzeugt.
   this.testMesspunkte = new Vector2D[]{new Vector2D(0.0, 0.0), new Vector2D(2.0, 1.0)};
   
   // Ein Objekt der zu testenden Klasse wird erzeugt.
   this.modellgleichungen = new Modellgleichungen(this.testMesspunkte);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link Modellgleichungen(Vector2D[])}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testModellgleichungen() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Der Wert des Attributs messwerte wird gelesen.   
   Field messpunkteFeld = Modellgleichungen.class.getDeclaredField("messpunkte");
   messpunkteFeld.setAccessible(true);
   Vector2D[] messpunkte = (Vector2D[]) messpunkteFeld.get(this.modellgleichungen);
   
   // Die Messpunkte werden überprüft.
   assertArrayEquals(messpunkte, this.testMesspunkte);  
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Test überprüft die Methode {@link Modellgleichungen#value(double[])}.
 */
@Test
public void testValue()
   {
   // Die zu testende Methode wird ausgeführt.
   double[] abstaende = this.modellgleichungen.value(new double[]{1.0, 0.0, 1.0});
   
   // Es wird überprüft, ob das Feld, das die Methode zurückgibt, die korrekte Dimension hat.
   assertEquals(2, abstaende.length);
   
   // Es wird überprüft, ob die Methode die korrekten Werte zurückgibt.
   assertEquals(0.0, abstaende[0], 0.0);
   assertEquals(0.4142, abstaende[1], 0.4142/10000);
   }

}
