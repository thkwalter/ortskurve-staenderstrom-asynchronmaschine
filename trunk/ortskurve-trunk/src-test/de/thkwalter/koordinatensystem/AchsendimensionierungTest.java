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
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Achsendimensionierung}.
 *
 * @author Th. K. Walter
 */
public class AchsendimensionierungTest
{
/**
 * Ein Objekt der zu testenden Klasse {@link Achsendimensionierung.}
 */
private Achsendimensionierung achsendimensionierung;

/**
 * Die Punktemenge, die für den Test verwendet wird.
 */
private Vector2D[] punkte;

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
   // Die Punktemenge, die für den Test verwendet wird, wird erzeugt.
  this.punkte = new Vector2D[]{new Vector2D(-1, 0), new Vector2D(0, 2), new Vector2D(3,0), new Vector2D(0, -4)};
   
   // Das Objekt der zu testenden Klasse wird initialisiert.
   this.achsendimensionierung = new Achsendimensionierung(this.punkte);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test method for {@link Achsendimensionierung#Achsendimensionierung(Vector2D[])}.
 */
@Test
public void testAchsendimensionierung()
   {
   fail("Not yet implemented");
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Achsendimensionierung#wertebereichBestimmen(Vector2D[])}.
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testWertebereichBestimmen() throws SecurityException, NoSuchMethodException, IllegalArgumentException, 
   IllegalAccessException, InvocationTargetException
   {
   // Die zu testende Methode wird ausgeführt.
   Method method = Achsendimensionierung.class.getDeclaredMethod("wertebereichBestimmen", Vector2D[].class);
   method.setAccessible(true);
   Wertebereich wertebereich = (Wertebereich) method.invoke(this.achsendimensionierung, (Object) this.punkte);
   
   // Es wird überprüft, ob der korrekte Wertebereich bestimmt worden ist.
   assertEquals(this.punkte[2].getX(), wertebereich.getMaxX(), 0.0);
   assertEquals(this.punkte[0].getX(), wertebereich.getMinX(), 0.0);
   assertEquals(this.punkte[1].getY(), wertebereich.getMaxY(), 0.0);
   assertEquals(this.punkte[3].getY(), wertebereich.getMinY(), 0.0);
   }
}
