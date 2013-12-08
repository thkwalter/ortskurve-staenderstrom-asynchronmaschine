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
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.thkwalter.et.ortskurve.Ortskurve;

/**
 * Diese Klasse repräsentiert die Ortskurve der Impedanz des Ersatzschaltbildes.
 * 
 * @author Th. K. Walter
 */
public class OrtskurveImpedanz
{


/**
 * Diese Methode invertiert eine kreisförmige Ortskurve.
 * 
 * @param ortskurve Die gegebene {@link Ortskurve}
 * 
 * @return Die invertierte Ortskurve
 */
public static Ortskurve kreisInvertieren(Ortskurve ortskurve)
   {
   // Die Parameter a und b werden bestimmt. a wird reell gewählt.
   double r = ortskurve.getRadiusOrtskurve();
   Complex a = new Complex(r);
   Complex b = 
      new Complex(ortskurve.getMittelpunktOrtskurve().getY() - 0.5 * r, - ortskurve.getMittelpunktOrtskurve().getX());
   
   // Eine Hilfsgröße wird ermittelt.
   Complex hilf = (b.add(a)).multiply(b).multiply(2);
   
   // Radius und Mittelpunkt des invertierten Kreises werden berechnet.
   double r_inv = a.divide(hilf).abs();
   Complex m_inv = (a.add(b.multiply(2))).divide(hilf);
   
   // Die neue Ortskurve wird erzeugt und zurückgegeben.
   return new Ortskurve(new Vector2D(- m_inv.getImaginary(), m_inv.getReal()), r_inv);
   }
}
