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

/**
 * Diese Klasse enthält kurze Formeln, die bei der Berechnung des Ersatzschaltbildes mehrmals benötigt werden.
 * 
 * @author Th. K. Walter
 */
public class Ersatzschaltbildformeln
{
/**
 * Diese Formel berechnet den Skalierungsfaktor, mit dessen Hilfe Leiterströme in Leitwerte umgerechnet werden 
 * können. Der Leiterstrom muss durch den Skalierungsfaktor dividiert werden, um den Leitwert zu erhalten.
 * 
 * @param u_LL Die Netzspannung (Leiter-Leiter; in V)
 * @param schaltungstyp Der Schaltungstyp (Stern oder Dreieck)
 * 
 * @return Der Skalierungsfaktor, mit dessen Hilfe Leiterströme in Leitwerte umgerechnet werden können.
 */
public static double skalierungsfaktorBestimmen(double u_LL, Schaltungstyp schaltungstyp)
   {   
   // Der Skalierungsfaktor, mit dessen Hilfe Leiterströme in Leitwerte umgerechnet werden können, wird berechnet und
   // zurückgegeben.
   return Schaltungstyp.STERN == schaltungstyp ? u_LL / Math.sqrt(3.0) : u_LL * Math.sqrt(3.0);
   }
}
