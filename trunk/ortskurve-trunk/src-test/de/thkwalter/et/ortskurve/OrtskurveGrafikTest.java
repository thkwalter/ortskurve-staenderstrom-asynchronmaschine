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

import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link OrtskurveGrafik}.
 *
 * @author Th. K. Walter
 */
public class OrtskurveGrafikTest
{
/**
 * Test für die Methode {@link OrtskurveGrafik#getMittelpunkt()}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 */
@Test
public void testGetMittelpunkt() throws SecurityException, NoSuchFieldException
   {
//   OrtskurveGrafik
//   
//   Field feld = OrtskurveGrafik.class.getDeclaredField("mittelpunkt");
//   feld.setAccessible(true);
//   feld.set(obj, value)
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link OrtskurveGrafik#getRadius()}.
 */
@Test
public void testGetRadius()
   {
   fail("Not yet implemented");
   }

}
