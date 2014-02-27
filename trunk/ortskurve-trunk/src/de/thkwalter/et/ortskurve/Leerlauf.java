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
package de.thkwalter.et.ortskurve;

import java.util.logging.Logger;

import org.apache.commons.math3.complex.Complex;

/**
 * Diese Klasse repräsentiert den Leerlauf.
 * 
 * @author Th. K. Walter
 */
public class Leerlauf
{
/**
 * Der komplexe Zeiger des Ständerstroms (in A) im Leerlaufpunkt
 */
private Complex i1;

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(Leerlauf.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor berechnet den Leerlaufpunkt.
 * 
 * @param ortskurve Die Ortskurve, für welche der Leerlaufpunkt berechnet wird.
 */
public Leerlauf(Ortskurve ortskurve)
   {
   // Die x-Komponente des Mittelpunkts der Ortskurve (in A) wird gelesen.
   double mx = ortskurve.getMittelpunktOrtskurve().getX();
   
   // Die y-Komponente des Mittelpunktes der Ortskurve (in A) wird gelesen.
   double my = ortskurve.getMittelpunktOrtskurve().getY();
   
   // Der Radius der Ortskurve (in A) wird gelesen.
   double r = ortskurve.getRadiusOrtskurve();
   
   // Die x-Komponte des Hilfskreises (in A) wird berechnet.
   double x0 = 0.5 * (mx*mx + my*my - r*r) / (r + mx);
   
   // Eine Hilfsgröße wird definiert.
   double skalierungsfaktor = x0 / (r + x0);
   
   // Die x-Komponente des Ständerstroms (in A) im Leerlaufpunkt.
   double p0x = x0 + skalierungsfaktor * (mx - x0);
   
   // Die y-Komponente des Ständerstroms (in A) im Leerlaufpunkt.
   double p0y = skalierungsfaktor * my;
   
   // Der komplexe Zeiger des Ständerstroms (in A) wird berechnet.
   this.i1 = new Complex(p0y, -p0x);
   
   // Das Ergebnis wird protokolliert.
   Leerlauf.logger.info(this.toString());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den komplexen Zeiger des Ständerstroms (in A) im Leerlaufpunkt zurück.
 * 
 * @return Der komplexe Zeiger des Ständerstroms (in A) im Leerlaufpunkt 
 */
public Complex getI1()
   {
   // Der komplexe Zeiger des Ständerstroms (in A) im Leerlaufpunkt wird zurückgegeben.
   return this.i1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt eine Zeichenkette zurück, die den Leerlauf repräsentiert.
 * 
 * @return Eine Zeichenkette, die den Leerlauf repräsentiert.
 * 
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   // Die Zeichenkette, die den Leerlauf repräsentiert, wird zurückgegeben.
   return "Leerlauf [" + (i1 != null ? "i1=" + i1 : "") + "]";
   }
}
