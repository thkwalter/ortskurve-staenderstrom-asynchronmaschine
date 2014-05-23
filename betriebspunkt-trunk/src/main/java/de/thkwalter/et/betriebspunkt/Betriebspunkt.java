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
package de.thkwalter.et.betriebspunkt;

/**
 * Diese Klasse repräsentiert einen Betriebspunkt.
 * 
 * @author Th. K. Walter
 */
public class Betriebspunkt
{
/**
 * Der Leiterstrom (in A).
 */
private double i_L;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Der Konstruktor initialisiert den Betriebspunkt
 * 
 * @param i_l Der Leiterstrom (in A)
 */
public Betriebspunkt(double i_L)
   {
   // Die Attribute werden initialisiert.
   this.i_L = i_L;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Leiterstrom (in A) zurück.
 * 
 * @return Der Leiterstrom (in A)
 */
public double i_L()
   {
   // Der Leiterstrom (in A) wird zurückgegeben.
   return this.i_L;
   }
}
