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

import org.apache.commons.math3.analysis.UnivariateFunction;

/**
 * Das Residuum des Schlupfs eines Betriebspunkts in Abhängigkeit vom Steigungswinkel der Schlupfgeraden.
 * 
 * @author Th. K. Walter
 */
public class Schlupfresiduum implements UnivariateFunction
{
/**
 * Die Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten
 */
private double[] steigungen;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode initialisiert die Funktion
 * 
 * @param steigungen Die Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten
 */
public Schlupfresiduum(double[] steigungen)
   {
   // Die Attribute werden initialisiert.
   this.steigungen = steigungen;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet das Residuum des Schlupfs eines Betriebspunkts in Abhängigkeit vom Steigungswinkel der 
 * Schlupfgeraden.
 * 
 * @param phi Der Steigungswinkel der Schlupfgeraden
 * 
 * @return Das Residuum des Schlupfs eines Betriebspunkts
 */
@Override
public double value(double phi)
   {
   // Die Variable für das Residuum des Schlupfs eines Betriebspunkts wird deklariert.
   double residuum = Double.NaN;
   
   // Das Residuum des Schlupfs eines Betriebspunkts wird zurückgegeben.
   return residuum;
   }
}
