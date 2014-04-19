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
package de.thkwalter.et.pulsmuster;

import org.apache.commons.math3.analysis.UnivariateFunction;

/**
 * Diese {@link UnivariateFunction} repräsentiert das Differenzsignal aus Referenzsignal (Sinus) und Abtastsignal 
 * (Sägezahn) für eine Flanke des Sägezahns.
 * 
 * @author Th. K. Walter
 */
public class Differenzsignal implements UnivariateFunction
{
/**
 * Die Steigung der Flanke des Sägezahns.
 */
private double m;

/**
 * Der Achsenabschnitt der Flanke des Sägezahns.
 */
private double t;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Das Differenzsignal wird erzeugt.
 * 
 * @param x_1 Die x-Koordinate eines ersten Punkts auf der Flanke des Sägezahns
 * @param x_2 Die y-Koordinate eines ersten Punkts auf der Flanke des Sägezahns
 * @param y_1 Die x-Koordinate eines zweiten Punkts auf der Flanke des Sägezahns
 * @param y_2 Die y-Koordinate eines zweiten Punkts auf der Flanke des Sägezahns
 */
public Differenzsignal(double x_1, double y_1, double x_2, double y_2)
   {
   // Die Steigung der Flanke des Sägezahns wird berechnet.
   this.m = (y_2 - y_1) / (x_2 - x_1);
   
   // Der Achsenabschnitt der Flanke des Sägezahns wird berechnet.
   this.t = y_1 - m * x_1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Funktionswert zurück.
 * 
 * @param x Die unabhängige Variable
 * 
 * @return Der Funktionswert
 * 
 * @see UnivariateFunction#value(double)
 */
@Override
public double value(double x)
   {
   // Der Funktionswert wird zurückgegeben.
   return this.m * x + this.t - Math.sin(x);
   }
}
