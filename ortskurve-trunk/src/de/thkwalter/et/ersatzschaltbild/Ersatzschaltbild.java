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

/**
 * Diese Klasse repräsentiert das Ersatzschaltbild.
 * 
 * @author Th. K. Walter
 */
public class Ersatzschaltbild
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
 * Die Ständerstreuung (in Ohm).
 */
private double x1_sigma;

/**
 * Die auf den Ständer bezogene Läuferstreuung (in Ohm).
 */
private double x2_sigma_strich;

/**
 * Die Hauptreaktanz (in Ohm)
 */
private double x_1h;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert die Felder.
 */
public Ersatzschaltbild()
   {
   this.x_1h = Double.NaN;
   this.r1 = Double.NaN;
   this.x1_sigma = Double.NaN;
   this.x2_sigma_strich = Double.NaN;
   this.r2_strich = Double.NaN; 
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Hauptreaktanz (in Ohm) zurück.
 * 
 * @return Die Hauptreaktanz (in Ohm)
 */
public double getX_1h()
   {
   // Die Hauptreaktanz (in Ohm) wird zurückgegeben.
   return this.x_1h;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die übergebene Hauptreaktanz (in Ohm) in diesem Modell.
 * 
 * @param x_1h Die Hauptreaktanz (in Ohm)
 */
public void setX_1h(double x_1h)
   {
   // Die übergebene Hauptreaktanz (in Ohm) wird in diesem Modell gespeichert.
   this.x_1h = x_1h;
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
 * Diese Methode gibt die Ständerstreuung (in Ohm) zurück.
 * 
 * @return Die Ständerstreuung (in Ohm)
 */
public double getX1_sigma()
   {
   // Die Ständerstreuung (in Ohm) wird zurückgegeben.
   return this.x1_sigma;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die übergebene Ständerstreuung im Datenmodell.
 * 
 * @param x1_sigma Die Ständerstreuung (in Ohm)
 */
public void setX1_sigma(double x1_sigma)
   {
   // Die übergebene Ständerstreuung (in Ohm) wird im Datenmodell gespeichert.
   this.x1_sigma = x1_sigma;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die auf den Ständer bezogene Läuferstreuung (in Ohm) zurück.
 * 
 * @return Die auf den Ständer bezogene Läuferstreuung (in Ohm)
 */
public double getX2_sigma_strich()
   {
   // Die auf den Ständer bezogene Läuferstreuung (in Ohm) wird zurückgegeben.
   return this.x2_sigma_strich;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die auf den Ständer bezogene Läuferstreuung (in Ohm) im Datenmodell.
 * 
 * @param Die auf den Ständer bezogene Läuferstreuung (in Ohm)
 */
public void setX2_sigma_strich(double x2_sigma_strich)
   {
   // Die auf den Ständer bezogene Läuferstreuung (in Ohm) wird im Datenmodell gespeichert.
   this.x2_sigma_strich = x2_sigma_strich;
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
   
   // Die Zeichenkette, welche die Ständerstreuung (in Ohm) repräsentiert, wird hinzugefügt.
   builder.append(", x1_sigma=").append(this.x1_sigma);
   
   // Die Zeichenkette, welche die auf den Ständer bezogene Läuferstreuung (in Ohm) repräsentiert, wird hinzugefügt.
   builder.append(", x2_sigma_strich=").append(this.x2_sigma_strich);
   
   // Die Zeichenkette, welche den auf den Ständer bezogenen ohmschen Läuferwiderstand (in Ohm) repräsentiert, wird 
   // hinzugefügt.
   builder.append(", r2_strich=").append(this.r2_strich);
   
   // Die Zeichenkette, welche die Hauptreaktanz repräsentiert, wird hinzugefügt.
   builder.append(", x_1h=").append(this.x_1h).append("]");
   
   // Die Zeichenkette, die die dieses Datenmodell repräsentiert, wird zurückgegeben.
   return builder.toString();
   }
}