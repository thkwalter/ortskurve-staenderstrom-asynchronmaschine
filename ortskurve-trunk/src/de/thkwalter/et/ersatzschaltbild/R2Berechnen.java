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
package de.thkwalter.et.ersatzschaltbild;

import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;

import de.thkwalter.et.ortskurve.Ortskurve;

/**
 * Diese Klasse dient zum Berechnen des auf den Ständer bezogenen, ohmschen Läuferwiderstands.
 * 
 * @author thomas
 */
public class R2Berechnen
{
/**
 * Dieser Konstruktor berechnet den auf den Ständer bezogenen, ohmschen Läuferwiderstand.
 * 
 * @param betriebspunkte Die gemessenen Betriebspunkte
 * @param ortskurve Die Stromortskurve
 */
public R2Berechnen(ArrayList<Betriebspunkt> betriebspunkte, Ortskurve ortskurve)
   {   
   // Der Mittelpunkt (in realen Koordinaten) und der Radius der Ortskurve werden gelesen (in A).
   Complex mittelpunktOrtskurve = 
      new Complex(ortskurve.getMittelpunktOrtskurve().getY(), - ortskurve.getMittelpunktOrtskurve().getX());
   double radiusOrtskurve = ortskurve.getRadiusOrtskurve();
   
   // In einer Schleife wird für jeden Betriebspunkt der auf den Ständer bezogene, ohmsche Läuferwiderstand berechnet.
   Betriebspunkt projezierterBetriebspunkt = null;
   for (Betriebspunkt betriebspunkt : betriebspunkte)
      {
      // Der Betriebspunkt wird auf die Ortskurve projeziert.
      projezierterBetriebspunkt = this.aufOrtskurveProjezieren(betriebspunkt, mittelpunktOrtskurve, radiusOrtskurve);
      }
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode projeziert einen Betriebspunkt auf die Ortskurve
 * 
 * @param originalBetriebspunkt Der originale Betriebspunkt (in A)
 * @param mittelpunktOrtskurve Der Mittelpunkt der Ortskurve (in realen Koordinaten; in A)
 * @param radiusOrtskurve Der Radius der Ortskurve (in A)
 * 
 * @return Der auf die Ortskurve projezierte Betriebspunkt
 */
private Betriebspunkt aufOrtskurveProjezieren(Betriebspunkt originalBetriebspunkt, Complex mittelpunktOrtskurve, 
   double radiusOrtskurve)
   {
   // Der Polarwinkel phi des Betriebspunkts vom Mittelpunkt der Ortskurve aus wird bestimmt.
   double phi = originalBetriebspunkt.getI1().subtract(mittelpunktOrtskurve).getArgument();
   
   // Der projezierte Betriebspunkt wird berechnet.
   Complex projezierterI1 = ComplexUtils.polar2Complex(radiusOrtskurve, phi).add(mittelpunktOrtskurve);
   Betriebspunkt projezierterBetriebspunkt = new Betriebspunkt(projezierterI1);
   projezierterBetriebspunkt.setN(originalBetriebspunkt.getN());
   
   // Der projezierte Betriebspunkt wird zurückgegeben.
   return projezierterBetriebspunkt;
   }
}
