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
package de.thkwalter.et.schlupfbezifferung;

import java.io.Serializable;

import org.apache.commons.math3.complex.Complex;

/**
 * Diese Klasse repräsentiert einen Betriebspunkt.
 * 
 * @author Th. K. Walter
 */
public class Betriebspunkt implements Serializable
{
/**
 * Die komplexe Ständerstromstärke (in A)
 */
private Complex i_1;

/**
 * Der Schlupf
 */
private Double s;

/**
 * Die Serialisierungs-Id.
 */
private static final long serialVersionUID = -779788128611202575L;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert die komplexe Ständerstromstärke (in A)
 * 
 * @param i_1x Die x-Komponente der komplexen Ständerstromstärke (in A; das Negative des Imaginärteils)
 * @param i_1y Die y-Komponente der komplexen Ständerstromstärke (in A; der Realteil)
 */
public Betriebspunkt(double i_1x, double i_1y)
   {
   // Die komplexe Ständerstromstärke (in A) wird initialisiert.
   this.i_1 = new Complex(i_1y, -i_1x);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die x-Komponente der komplexen Ständerstromstärke (in A; das Negative des Imaginärteils) zurück.
 * 
 * @return Die x-Komponente der komplexen Ständerstromstärke (in A)
 */
public double getI_1x()
   {
   // Die x-Komponente der komplexen Ständerstromstärke (in A) wird zurückgegeben.
   return -this.i_1.getImaginary();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die y-Komponente der komplexen Ständerstromstärke (in A; der Realteil) zurück.
 * 
 * @return Die y-Komponente der komplexen Ständerstromstärke (in A)
 */
public double getI_1y()
   {
   // Die y-Komponente der komplexen Ständerstromstärke (in A) wird zurückgegeben.
   return this.i_1.getReal();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Schlupf zurück.
 * 
 * @return Der Schlupf
 */
public Double getS()
   {
   // Der Schlupf wird zurückgegeben.
   return this.s;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert den Schlupf in diesem Objekt.
 * 
 * @param s Der Schlupf
 */
public void setS(Double s)
   {
   // Der Schlupf wird in diesem Objekt gespeichert.
   this.s = s;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Zustand des Objekts als Zeichenkette zurück.
 * 
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   // Die Zeichenkette, welche den Zustand dieses Objekts repräsentiert, wird erzeugt.
   StringBuffer zustand = new StringBuffer("Betriebspunkt [");
   
   // Die Zeichenkette, welche die komplexe Ständerstromdichte (in A) repräsentiert, wird angehängt.
   zustand.append(i_1 != null ? "i_1=" + i_1 + ", " : "");
   
   // Die Zeichenkette, welche den Schlupf repräsentiert, wird angehängt.
   zustand.append(s != null ? "s=" + s : "");
   
   // Das Schlusszeichen wird angehängt.
   zustand.append("]");
   
   // Die Zeichenkette, welche den Zustand dieses Objekts repräsentiert, wird zurückgegeben.
   return zustand.toString();
   }
}
