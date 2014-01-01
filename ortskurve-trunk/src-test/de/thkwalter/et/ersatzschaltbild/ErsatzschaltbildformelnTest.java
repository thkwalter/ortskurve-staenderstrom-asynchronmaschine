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
package de.thkwalter.et.ersatzschaltbild;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link ErsatzschaltbildFormeln}.
 * 
 * @author Th. K. Walter
 */
public class ErsatzschaltbildformelnTest
{
/**
 * Test der Methode {@link Ersatzschaltbildformeln#skalierungsfaktorBestimmen(double, Schaltungstyp)}.
 */
@Test
public void testSkalierungsfaktorBestimmen()
   {
   // Die zu testende Methode wird aufgerufen.
   double skalierungsfaktor = Ersatzschaltbildformeln.skalierungsfaktorBestimmen(400, Schaltungstyp.STERN);
   
   // Es wird überprüft, ob der Skalierungsfaktor korrekt berechnet worden ist.
   assertEquals(230.9, skalierungsfaktor, 230.9/1000.0);
   
   // Die zu testende Methode wird aufgerufen.
   skalierungsfaktor = Ersatzschaltbildformeln.skalierungsfaktorBestimmen(400, Schaltungstyp.DREIECK);
   
   // Es wird überprüft, ob der Skalierungsfaktor korrekt berechnet worden ist.
   assertEquals(692.8, skalierungsfaktor, 692.8/1000.0);
   }
}
