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

import org.apache.commons.math3.complex.Complex;

/**
 * Diese Klasse speichert die Daten zu einem Betriebspunkt.
 * 
 * @author Th. K. Walter
 * @version 1.0
 */
public class Betriebspunkt
{
/**
 * Der Ständerstrom
 */
private Complex i1;

/**
 * Die Drehzahl des Motors.
 */
private double n;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Ständerstrom zurück.
 * 
 * @return Der Ständerstrom
 */
public Complex getI1()
   {
   // Der Ständerstrom wird zurückgegeben.
   return this.i1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert den übergebenen Ständerstrom in diesem Objekt.
 * 
 * @param Der Ständerstrom 
 */
public void setI1(Complex i1)
   {
   // Der Ständerstrom wird gespeichert.
   this.i1 = i1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Drehzahl des Motors zurück.
 * 
 * @return Die Drehzahl des Motors
 */
public double getN()
   {
   // Die Drehzahl des Motors wird zurückgegeben.
   return this.n;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die Drehzahl des Motors in diesem Objekt.
 * 
 * @param Die Drehzahl des Motors
 */
public void setN(double n)
   {
   // Die Drehzahl des Motors wird gespeichert.
   this.n = n;
   }
}
