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

/**
 * Diese Klasse repräsentiert das Ersatzschaltbild.
 * 
 * @author Th. K. Walter
 */
public class Ersatzschaltbild implements Serializable
{
/**
 * Der ohmsche Widerstand des Ständers (in Ohm).
 */
private double r1;

/**
 * Der auf den Ständer bezogene ohmsche Widerstand des Läufers (in Ohm).
 */
private double r2_strich;

/**
 * Der Streureaktanz (in Ohm).
 */
private double x_k;

/**
 * Die Reaktanz x_1 (in Ohm)
 */
private double x1;

/**
 * Die Serialisierungs-ID
 */
private static final long serialVersionUID = -6306287105391842968L;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert die Felder.
 */
public Ersatzschaltbild()
   {
   this.x1 = Double.NaN;
   this.r1 = Double.NaN;
   this.x_k = Double.NaN;
   this.r2_strich = Double.NaN; 
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Reaktanz x_1 (in Ohm) zurück.
 * 
 * @return Die Reaktanz x_1 (in Ohm)
 */
public double getX1()
   {
   // Die Reaktanz x_1 (in Ohm) wird zurückgegeben.
   return this.x1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die Reaktanz x_1 (in Ohm) in diesem Modell.
 * 
 * @param x_1h Die Reaktanz x_1 (in Ohm)
 */
public void setX1(double x1)
   {
   // Die Reaktanz x_1 (in Ohm) wird in diesem Modell gespeichert.
   this.x1 = x1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den ohmschen Widerstand des Ständers (in Ohm) zurück.
 * 
 * @return Der ohmsche Widerstand des Ständers (in Ohm)
 */
public double getR1()
   {
   // Der ohmsche Widerstand des Ständers (in Ohm) wird zurückgegeben.
   return this.r1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert den ohmschen Widerstand des Ständers (in Ohm) im Datenmodell.
 * 
 * @param r1 Der ohmsche Widerstand des Ständers (in Ohm)
 */
public void setR1(double r1)
   {
   // Der ohmsche Widerstand des Ständers (in Ohm) wird im Datenmodell gespeichert.
   this.r1 = r1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Streureaktanz (in Ohm) zurück.
 * 
 * @return Die Streureaktanz (in Ohm)
 */
public double getX_k()
   {
   // Die Streureaktanz (in Ohm) wird zurückgegeben.
   return this.x_k;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die Streureaktanz (in Ohm) im Datenmodell.
 * 
 * @param x_k Die Streureaktanz (in Ohm)
 */
public void setX_k(double x_k)
   {
   // Die Streureaktanz (in Ohm) wird im Datenmodell gespeichert.
   this.x_k = x_k;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den auf den Ständer bezogenen ohmschen Läuferwiderstand (in Ohm) zurück.
 * 
 * @return Der auf den Ständer bezogene ohmsche Läuferwiderstand (in Ohm)
 */
public double getR2_strich()
   {
   // Der auf den Ständer bezogene ohmsche Läuferwiderstand (in Ohm) wird zurückgegeben.
   return this.r2_strich;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert den auf den Ständer bezogenen ohmschen Läuferwiderstand (in Ohm) im Datenmodell.
 * 
 * @param r2_strich Der auf den Ständer bezogene ohmsche Läuferwiderstand (in Ohm)
 */
public void setR2_strich(double r2_strich)
   {
   // Der auf den Ständer bezogene ohmsche Läuferwiderstand (in Ohm) wird im Datenmodell gespeichert.
   this.r2_strich = r2_strich;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   // Die Zeichenkette, die dieses Datenmodell repräsentiert, wird erzeugt.
   StringBuilder builder = new StringBuilder("Ersatzschaltbild [");
   
   // Die Zeichenkette, welche den ohmschen Widerstand des Ständers (in Ohm) repräsentiert, wird hinzugefügt.
   builder.append("r1=").append(this.r1);
   
   // Die Zeichenkette, welche den  Ständerstreuung (in Ohm) repräsentiert, wird hinzugefügt.
   builder.append(", x_k=").append(this.x_k);
   
   // Die Zeichenkette, welche den auf den Ständer bezogenen ohmschen Läuferwiderstand (in Ohm) repräsentiert, wird 
   // hinzugefügt.
   builder.append(", r2_strich=").append(this.r2_strich);
   
   // Die Zeichenkette, welche die Reaktanz x_1repräsentiert, wird hinzugefügt.
   builder.append(", x1=").append(this.x1).append("]");
   
   // Die Zeichenkette, die die dieses Datenmodell repräsentiert, wird zurückgegeben.
   return builder.toString();
   }
}
