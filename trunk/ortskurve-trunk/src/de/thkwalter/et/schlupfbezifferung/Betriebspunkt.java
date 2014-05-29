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

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Diese Klasse repräsentiert einen Betriebspunkt.
 * 
 * @author Th. K. Walter
 */
public class Betriebspunkt
{
/**
 * Die komplexe Ständerstromstärke (in A)
 */
private Vector2D i_1;

/**
 * Der Schlupf
 */
private double s;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert den Betriebspunkt
 * 
 * @param i_1 Die komplexe Ständerstromstärke (in A)
 * @param s Der Schlupf
 */
public Betriebspunkt(Vector2D i_1, double s)
   {
   // Die Attribute werden initialisiert.
   this.i_1 = i_1;
   this.s = s;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die komplexe Ständerstromstärke (in A) zurück.
 * 
 * @return Die komplexe Ständerstromstärke (in A)
 */
public Vector2D getI_1()
   {
   // Die komplexe Ständerstromstärke (in A) wird zurückgegeben.
   return this.i_1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Schlupf zurück.
 * 
 * @return Der Schlupf
 */
public double getS()
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
public void setS(double s)
   {
   // Der Schlupf wird in diesem Objekt gespeichert.
   this.s = s;
   }
}
