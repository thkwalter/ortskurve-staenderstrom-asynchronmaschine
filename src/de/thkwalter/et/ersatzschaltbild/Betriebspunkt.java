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

import java.io.Serializable;

import org.apache.commons.math3.complex.Complex;

/**
 * Diese Klasse speichert die Daten zu einem Betriebspunkt.
 * 
 * @author Th. K. Walter
 * @version 1.2
 */
public class Betriebspunkt implements Serializable
{
/**
 * Der Ständerstrom (in A).
 */
private Complex i1;

/**
 * Die Drehzahl des Motors (in Hz).
 */
private double n;

/**
 * Die Serialisierungsnummer
 */
private static final long serialVersionUID = 1L;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert den Betriebspunkt.
 */
public Betriebspunkt(Complex i1)
   {
   // Die Attribute werden mit NaN-Werten initialisiert.
   this.n = Double.NaN;
   this.i1 = i1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Ständerstrom zurück (in A).
 * 
 * @return Der Ständerstrom (in A)
 */
public Complex getI1()
   {
   // Der Ständerstrom (in A) wird zurückgegeben.
   return this.i1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Drehzahl des Motors (in Hz) zurück.
 * 
 * @return Die Drehzahl des Motors (in Hz)
 */
public Double getN()
   {
   // Die Drehzahl des Motors (in Hz) wird zurückgegeben.
   return Double.isNaN(this.n) ? null : this.n;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die Drehzahl des Motors (in Hz) in diesem Objekt.
 * 
 * @param Die Drehzahl des Motors (in Hz)
 */
public void setN(Double n)
   {
   // Die Drehzahl des Motors (in Hz) wird gespeichert.
   this.n = n;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString() 
 */
@Override
public String toString()
   {
   // Die Zeichenkette, die den Betriebspunkt repräsentiert, wird erzeugt.
   StringBuilder builder = new StringBuilder();
   builder.append("Betriebspunkt [");
   
   // Die Zeichenkette, die den Ständerstrom repräsentiert, wird hinzugefügt.
   if (i1 != null)
      {
      builder.append("i1=").append(i1).append(", ");
      }
      
   // Die Zeichenkette, welche die Drehzahl repräsentiert, wird hinzugefügt.
   builder.append("n=").append(n).append("]");
   
   // Die Zeichenkette, die den Betriebspunkt repräsentiert, wird zurückgegeben.
   return builder.toString();
   }
}
