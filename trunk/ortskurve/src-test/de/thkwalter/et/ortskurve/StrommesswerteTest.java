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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Strommesswerte}.
 *
 * @author Th. K. Walter
 */
public class StrommesswerteTest
{
/**
 * Ein Objekt der zu testenden Klasse.
 */
private Strommesswerte strommesswerte;

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
   // Das Feld, das die Stromvektoren enthält, wird erzeugt.
   Vector2D[] stromvektoren = {new Vector2D(2,0), new Vector2D(1, -1)};
   
   // Ein Objekt der zu testenden Klasse wird erzeugt.
   this.strommesswerte = new Strommesswerte(stromvektoren);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Test überprüft die Methode {@link de.thkwalter.et.ortskurve.Strommesswerte#value(double[])}.
 */
@Test
public void testValue()
   {
   // Das Feld, das die Parameter der Kreisgleichung enthält, wird erzeugt.
   double[] point = {1.0, 0.0, 0.5};
   
   // Die zu testende Methode wird ausgeführt.
   double[] abstaende = this.strommesswerte.value(point);
   
   // Es wird überprüft, ob das Feld, das die Methode zurückgibt, die korrekte Dimension hat.
   assertEquals(2, abstaende.length);
   
   // Es wird überprüft, ob die Methode die korrekten Werte zurückgibt.
   assertEquals(0.5, abstaende[0], 0.5/10000);
   assertEquals(0.5, abstaende[1], 0.5/10000);
   }

}
