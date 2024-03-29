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

import org.apache.commons.math3.complex.Complex;

/**
 * Diese Klasse repräsentiert einen Betriebspunkt.
 * 
 * @author Th. K. Walter
 */
public class Betriebspunkt
{
/**
 * Der effektive Leiterstrom (in A).
 */
private double i_L;

/**
 * Die effektive Leiter-Leiterspannung (in V).
 */
private double u_LL;

/**
 * Die elektrische Leistung (in kW)
 */
private double p_el;

/**
 * Der {@link Schaltungstyp}
 */
private Schaltungstyp schaltungstyp;

// ---------------------------------------------------------------------------------------------------------------------

/**
 * Die Scheinleistung (in kVA).
 */
private double p_s;

/**
 * Die Phasenverschiebung zwischen Strangstrom und Strangspannung.
 */
private double cosPhi;

/**
 * Der komplexe Zeiger des Ständerstroms (in A)
 */
private Complex z_i_s;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor erzeugt einen Betriebspunkt und initialisiert alle reellwertigen Größen mit {@link Double#NaN}.
 */
public Betriebspunkt()
   {
   // Alle reellwertigen Größen werden mit Double.NaN initialisiert.
   this.u_LL = Double.NaN;
   this.i_L = Double.NaN;
   this.cosPhi = Double.NaN;
   this.p_el = Double.NaN;
   this.p_s = Double.NaN;
   
   // Der komplexe Zeiger des Ständerstroms (in A) wird initialisiert.
   this.z_i_s = Complex.NaN;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor erzeugt einen Betriebspunkt und berechnet auf Basis der Eingabewerte alle fehlenden Größen.
 * 
 * @param i_l Der effektive Leiterstrom (in A)
 * @param u_LL Die effektive Leiter-Leiterspannung (in V)
 * @param p_el Die elektrische Leistung (in kW)
 * @param schaltungstyp Der Schaltungstyp
 */
public Betriebspunkt(double i_L, double u_LL, double p_el, Schaltungstyp schaltungstyp)
   {
   // Die Attribute werden initialisiert.
   this.i_L = i_L;
   this.u_LL = u_LL;
   this.p_el = p_el;
   this.schaltungstyp = schaltungstyp;
   
   // Auf Basis der Eingabewerte werden alle fehlenden Größen berechnet.
   this.rechnen();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet auf Basis der Eingabewerte alle fehlenden Größen.
 */
private void rechnen()
   {
   // Die Scheinleistung (in kVA) wird berechnet.
   this.p_s = Math.sqrt(3.0) * this.getU_LL() * this.getI_L() / 1000.0;
   
   // Die Phasenverschiebung zwischen Strangstrom und Strangspannung wird berechnet.
   this.cosPhi = this.p_el / this.p_s;
   
   // Ein vom Schaltungstyp abhängiger Faktor wird berechnet.
   double faktor = this.schaltungstyp == Schaltungstyp.STERN ? 1.0 : 1.0 / Math.sqrt(3.0);
   
   // Der Realteil des Strangstroms (in A) wird berechnet.
   double i_s_re = faktor * this.i_L * this.cosPhi;
   
   // Der Imaginärteil des Strangstroms (in A) wird berechnet. Dabei ist zu beachten, dass für elektrische Maschinen der
   // Phasenwinkel phi negativ ist.
   double i_s_im = faktor * this.i_L * Math.sin(- Math.acos(this.cosPhi));
   
   // Der komplexe Zeiger des Ständerstroms (in A) wird erzeugt.
   this.z_i_s = new Complex(i_s_re, i_s_im);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den effektiven Leiterstrom (in A) zurück.
 * 
 * @return Der effektive Leiterstrom (in A)
 */
public double getI_L()
   {
   // Der effektive Leiterstrom (in A) wird zurückgegeben.
   return this.i_L;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert den effektiven Leiterstrom (in A) in diesem Objekt.
 * 
 * @param i_L Der effektive Leiterstrom (in A)
 */
public void setI_L(double i_L)
   {
   // Der effektive Leiterstrom (in A) wird in diesem Objekt gespeichert.
   this.i_L = i_L;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die effektive Leiter-Leiterspannung (in V) zurück.
 * 
 * @return Die effektive Leiter-Leiterspannung (in V)
 */
public double getU_LL()
   {
   // Die effektive Leiter-Leiterspannung (in V) wird zurückgegeben.
   return this.u_LL;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die effektive Leiter-Leiterspannung (in V) in diesem Objekt.
 * 
 * @param u_LL Die effektive Leiter-Leiterspannung (in V)
 */
public void setU_LL(double u_LL)
   {
   // Die effektive Leiter-Leiterspannung (in V) wird in diesem Objekt gespeichert.
   this.u_LL = u_LL;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die elektische Leistung (in kW) in diesem Objekt.
 * 
 * @return Die elektrische Leistung (in kW)
 */
public double getP_el()
   {
   // Die elektrische Leistung (in kW) wird zurückgegeben.
   return this.p_el;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die elektische Leistung (in kW) in diesem Objekt.
 * 
 * @param p_el Die elektische Leistung (in kW)
 */
public void setP_el(double p_el)
   {
   // die elektische Leistung (in kW) wird in diesem Objekt gespeichert.
   this.p_el = p_el;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Scheinleistung (in kVA) zurück.
 * 
 * @return Die Scheinleistung (in kVA)
 */
public double getP_s()
   {
   // Die Scheinleistung (in kVA) wird zurückgegeben.
   return this.p_s;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Phasenverschiebung zwischen Strangstrom und Strangspannung zurück.
 * 
 * @return Die Phasenverschiebung zwischen Strangstrom und Strangspannung wird zurückgegeben.
 */
public double getCosPhi()
   {
   // Die Phasenverschiebung zwischen Strangstrom und Strangspannung wird zurückgegeben.
   return this.cosPhi;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den komplexen Zeiger des Strangstroms (in A) zurück.
 * 
 * @return Der komplexe Zeiger des Strangstroms (in A)
 */
public Complex getZ_i_s()
   {
   // Der komplexe Zeiger des Strangstroms (in A) wird zurückgegeben
   return this.z_i_s;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den {@link Schaltungstyp} zurück.
 * 
 * @return Der {@link Schaltungstyp}
 */
public Schaltungstyp getSchaltungstyp()
   {
   // Der Schaltungstyp wird zurückgegeben.
   return this.schaltungstyp;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert den Schaltungstyp in diesem Objekt.
 * 
 * @param schaltungstyp Der {@link Schaltungstyp}
 */
public void setSchaltungstyp(Schaltungstyp schaltungstyp)
   {
   // Der Schaltungstyp wird in diesem Objekt gespeichert.
   this.schaltungstyp = schaltungstyp;
   }
}
