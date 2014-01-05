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
import java.util.logging.Logger;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import de.thkwalter.et.ortskurve.Ortskurve;

/**
 * Diese Klasse dient zur Berechnung des auf den Ständer bezogenen Läuferwicklungswiderstands.
 * 
 * @author thomas
 */
public class Laeuferwicklungswiderstand
{
/**
 * Das Objekt, das für die Statistikberechnungen genutzt wird
 */
private DescriptiveStatistics descriptiveStatistics;

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(Laeuferwicklungswiderstand.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor berechnet den auf den Ständer bezogenen, ohmschen Läuferwiderstand.
 * 
 * @param betriebspunkte Die gemessenen Betriebspunkte
 * @param ortskurve Die Stromortskurve
 * @param u_LL Die Netzspannung (Leiter-Leiter; in V)
 * @param schaltungstyp Der Schaltungstyp (Stern oder Dreieck)
 * @param r_1 Der ohmsche Ständerwicklungswiderstand (in Ohm)
 * @param x_1 (in Ohm)
 * @param x_k Die Hauptreaktanz (in Ohm)
 * @param n_s Die synchrone Drehzahl (in Hz)
 */
public Laeuferwicklungswiderstand(ArrayList<Betriebspunkt> betriebspunkte, Ortskurve ortskurve, double u_LL, 
   Schaltungstyp schaltungstyp, double r_1, double x_1, double x_k, double n_s)
   {      
   // Das Objekt zur Berechnung der statistischen Werte wird initialisiert.
   this.descriptiveStatistics = new DescriptiveStatistics();
   
   // Der Mittelpunkt (in realen Koordinaten) und der Radius der Ortskurve werden gelesen (in A).
   Complex mittelpunktOrtskurve = 
      new Complex(ortskurve.getMittelpunktOrtskurve().getY(), - ortskurve.getMittelpunktOrtskurve().getX());
   double radiusOrtskurve = ortskurve.getRadiusOrtskurve();
   
   // Der Skalierungsfaktor, mit dessen Hilfe Leiterströme in Leitwerte umgerechnet werden können, wird berechnet.
   double skalierungsfaktor = Ersatzschaltbildformeln.skalierungsfaktorBestimmen(u_LL, schaltungstyp);
   
   // Einige in der folgenden Schleife verwendeten Variablen und Referenzen werden deklariert.
   Complex projezierterI1 = null;
   Complex z_1 = null;
   Complex r_2_komplex = null;
   double s = Double.NaN;
   
   // Das Objekt zur Berechnung des Mittelwerts wird erzeugt.
   this.descriptiveStatistics = new DescriptiveStatistics();
   
   // In einer Schleife wird für jeden Betriebspunkt der auf den Ständer bezogene, ohmsche Läuferwiderstand berechnet.
   for (Betriebspunkt betriebspunkt : betriebspunkte)
      {
      // Der Strommesspunkt wird auf die Ortskurve projeziert.
      projezierterI1 = this.aufOrtskurveProjezieren(betriebspunkt, mittelpunktOrtskurve, radiusOrtskurve);
      
      // Die Impedanz des Messpunkts wird berechnet.
      z_1 = projezierterI1.reciprocal().multiply(skalierungsfaktor);
      
      // Der Schlupf wird berechnet.
      s = 1 - betriebspunkt.getN() / n_s;

      // Der auf den Ständer bezogene Läuferwicklungswiderstand (in Ohm) kann für Schlupf gleich 0 nicht berechnet 
      // werden.
      if (s >= 0.01)
         {
         // Der auf den Ständer bezogene Läuferwicklungswiderstand dieses Messpunkts wird (in Ohm) berechnet.
         r_2_komplex = this.r_2_komplex(z_1, r_1, x_1, x_k, s);
      
         // Der auf der Ständer bezogene, ohmsche Läuferwicklungswiderstand (in Ohm) sollte real sein. Ergibt sich bei 
         // der Berechnung ein nicht zu vernachlässigender Imaginärteil, so wird eine Warnung protokolliert.
         if (r_2_komplex.getImaginary() > 0.01 * r_2_komplex.getReal())
            {
            Laeuferwicklungswiderstand.logger.warning("Der Widerstand R2 mit dem Wert " + r_2_komplex + "besitzt einen sehr großen "
               + "Imaginärteil!");
            }
         
         // Der berechnete, auf der Ständer bezogene, ohmsche Läuferwicklungswiderstand (in Ohm) wird zur Statistik
         // hinzugefügt.
         descriptiveStatistics.addValue(r_2_komplex.getReal());
         }
      }
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode projeziert einen Strommesspunkt auf die Ortskurve
 * 
 * @param originalBetriebspunkt Der originale Betriebspunkt
 * @param mittelpunktOrtskurve Der Mittelpunkt der Ortskurve (in realen Koordinaten; in A)
 * @param radiusOrtskurve Der Radius der Ortskurve (in A)
 * 
 * @return Der auf die Ortskurve projezierte Strommesspunkt
 */
private Complex aufOrtskurveProjezieren(Betriebspunkt originalBetriebspunkt, Complex mittelpunktOrtskurve, 
   double radiusOrtskurve)
   {
   // Der Polarwinkel phi des Betriebspunkts vom Mittelpunkt der Ortskurve aus wird bestimmt.
   double phi = originalBetriebspunkt.getI1().subtract(mittelpunktOrtskurve).getArgument();
   
   // Der projezierte Strommesspunkt wird berechnet und zurückgegeben.
   return ComplexUtils.polar2Complex(radiusOrtskurve, phi).add(mittelpunktOrtskurve);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet den auf den Ständer bezogenen, ohmschen Läuferwicklungswiderstand für einen Messpunkt.
 * 
 * @param z_1 Die Impedanz, die zum Messpunkt gehört (in Ohm)
 * @param r_1 Der ohmsche Ständerwicklungswiderstand (in Ohm)
 * @param x_1 (in Ohm)
 * @param x_k Die Hauptreaktanz (in Ohm)
 * @param s Der Schlupf
 * 
 * @return Der auf den Ständer bezogene, ohmschen Läuferwicklungswiderstand.
 */
private Complex r_2_komplex(Complex z_1, double r_1, double x_1, double x_k, double s)
   {
   // Eine Hilfsgröße wird berechnet.
   Complex hilf1 = z_1.subtract(r_1);
   
   // Zähler und Nenner werden berechnet.
   Complex zaehler = (hilf1.multiply(new Complex(0.0, x_1+x_k))).add(x_k * x_1);
   Complex nenner = (new Complex(0.0, x_1)).subtract(hilf1);
         
   // Der auf den Ständer bezogene, ohmesche Läuferwicklungswiderstand wird berechnet und zurückgegeben.
   return (zaehler.divide(nenner)).multiply(s);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den auf den Ständer bezogenen Läuferwicklungswiderstand (in Ohm) zurück. Der zurückgegebene Wert
 * ist der Mittelwert der für die verschiedenen Messpunkte berechneten Werte.
 * 
 * @return Der auf den Ständer bezogene Läuferwicklungswiderstand (in Ohm)
 */
public double getR2()
   {
   // Der auf den Ständer bezogene Läuferwicklungswiderstand (in Ohm) wird zurückgegeben. Der zurückgegebene Wert ist 
   // der Mittelwert der für die verschiedenen Messpunkte berechneten Werte.
   return this.descriptiveStatistics.getMean();
   }
}
