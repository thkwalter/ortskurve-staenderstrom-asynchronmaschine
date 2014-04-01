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
package de.thkwalter.et.ortskurve;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Leerlauf}.
 * 
 * @author Th. K. Walter
 */
public class LeerlaufTest 
{
/**
 * Der Prüfling der Klasse {@link Leerlauf}
 */
private Leerlauf leerlauf;

// =====================================================================================================================
// =====================================================================================================================

/* 
 * Diese Methode initialisiert die Tests.
 */
@Before
public void setUp() throws Exception
   {
   // Der Prüfling wird erzeugt.
   this.leerlauf = new Leerlauf(new Ortskurve(new Vector2D(17.0, 9.0), 10.0));
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für der Konstruktor {@link Leerlauf#Leerlauf(Ortskurve)}.
 */
@Test
public void testLeerlauf()
   {
   // Es wird überprüft, ob der Prüfling korrekt erzeugt worden ist.
   assertNotNull(this.leerlauf);
   
   // Der Leerlaufpunkt (in A) wird gelesen.
   Complex i1 = this.leerlauf.getI1();
   
   //
   assertEquals(new Complex(3.0, -9.0), i1);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link Leerlauf#getI1()}.
 * 
 * @throws SecurityException 
 * @throws NoSuchFieldException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetI1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
   {
   // Der in diesem Test verwendete Ständerstom im Leerlauf (in A) wird definiert.
   Complex test_i1 = new Complex(0.5, -1.5);
   
   // Der in diesem Test verwendete Ständerstrom im Leerlauf (in A) wird im Prüfling gespeichert.
   Field feld = Leerlauf.class.getDeclaredField("i1");
   feld.setAccessible(true);
   feld.set(this.leerlauf, test_i1);
   
   // Es wird überprüft, ob der Ständerstrom im Leerlauf (in A) korrekt zurückgegeben wird.
   assertEquals(test_i1, this.leerlauf.getI1());
   }
}
