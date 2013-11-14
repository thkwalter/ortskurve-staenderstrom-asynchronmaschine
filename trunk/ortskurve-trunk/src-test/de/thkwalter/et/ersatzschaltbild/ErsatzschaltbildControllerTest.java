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

import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link ErsatzschaltbildController}.
 * 
 * @author Th. K. Walter
 * @version 1.2
 */
public class ErsatzschaltbildControllerTest
{
/**
 * Das Objekt der zu testenden Klasse {@link ErsatzschaltbildController}
 */
private ErsatzschaltbildController ersatzschaltbildController;

/**
 * Das in den Tests verwendete Modell der Ersatzschaltbildberechnung
 */
private ErsatzschaltbildModell testErsatzschaltbildModell;

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
   this.ersatzschaltbildController = new ErsatzschaltbildController();
   
   // Das in den Tests verwendete Modell der Ersatzschaltbildberechnung wird erzeugt.
   this.testErsatzschaltbildModell = new ErsatzschaltbildModell();
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


}
