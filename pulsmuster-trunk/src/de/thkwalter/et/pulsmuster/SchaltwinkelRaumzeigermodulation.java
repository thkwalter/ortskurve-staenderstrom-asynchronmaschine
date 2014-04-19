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

import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.RegulaFalsiSolver;

/**
 * Diese Klasse berechnet die Schaltwinkel bei Raumzeigermodulation.
 * 
 * @author Th. K. Walter
 */
public class SchaltwinkelRaumzeigermodulation
{
/**
 * Die absolute Genauigkeit der berechneten Schaltwinkel (im Bogenmaß), bei der die Iteration abgebrochen wird.
 */
private final static double ABBRUCHKRITERIUM_ABSOLUTE_GENAUIGKEIT_SCHALTWINKEL = 1e-6; 

/**
 * Die relative Genauigkeit der berechneten Schaltwinkel, bei der die Iteration abgebrochen wird.
 */
private final static double ABBRUCHKRITERIUM_RELATIVE_GENAUIGKEIT_SCHALTWINKEL = 0.0; 

/**
 * Die maximale Abweichung von Referenzsignal und Sägezahn, bei der die Iteration abgebrochen wird.
 */
private final static double ABBRUCHKRITERIUM_ABSOLUTE_GENAUIGKEIT_SIGNALDIFFERENZ = 0.0; 

/**
 * Die maximale Anzahl von Iterationen.
 */
private final static int MAX_ANZAHL_ITERATIONEN = 100;

// ---------------------------------------------------------------------------------------------------------------------

/**
 * Die berechneten Schaltwinkel (im Bogenmaß).
 */
private double[] schaltwinkel;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Der Konstruktor berechnet die Schaltwinkel (im Bogenmaß) bei Raumzeigermodulation.
 * 
 * @param pulszahl Die Pulszahl
 */
public SchaltwinkelRaumzeigermodulation(int pulszahl)
   {
   // Die Anzahl der Schnittpunkte (Schaltwinkel) wird berechnet. Pro Puls ergeben sich zwei Schnittpunkte 
   // (Schaltwinkel), jeweils ein Schnittpunkt für den Einschalt- und für den Ausschaltvorgang.
   int nSchnittpunkte = 2 * pulszahl;
   
   // Die Variablen für die obere und untere Grenze des Suchintervalls werden deklatiert.
   double min = Double.NaN;
   double max = Double.NaN;
   
   // Der Regula-Falsi-Algorithmus wird erzeugt.
   RegulaFalsiSolver regulaFalsiSolver = new RegulaFalsiSolver(
      SchaltwinkelRaumzeigermodulation.ABBRUCHKRITERIUM_RELATIVE_GENAUIGKEIT_SCHALTWINKEL,
      SchaltwinkelRaumzeigermodulation.ABBRUCHKRITERIUM_ABSOLUTE_GENAUIGKEIT_SCHALTWINKEL, 
      SchaltwinkelRaumzeigermodulation.ABBRUCHKRITERIUM_ABSOLUTE_GENAUIGKEIT_SIGNALDIFFERENZ);
   
   // Das Feld für die Schaltwinkel (im Bogenmaß) wird erzeugt.
   this.schaltwinkel = new double[nSchnittpunkte];
   
   // In der folgenden Schleife werden die Schnittpunkte (Schaltwinkel) berechnet.
   for (int i = 0; i < nSchnittpunkte; i++)
      {
      // Die obere und untere Grenze des Suchintervalls werden bestimmt. Das Suchintervall umfasst je eine halbe Periode
      // des Sägezahns.
      min = i * Math.PI / pulszahl;
      max = (i+1) * Math.PI / pulszahl;
      
      // Der Schaltwinkel wird berechnet und gespeichert.
//      this.schaltwinkel[i] = regulaFalsiSolver.solve(SchaltwinkelRaumzeigermodulation.MAX_ANZAHL_ITERATIONEN, 
//         f, min, max, AllowedSolution.RIGHT_SIDE);
      }
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die berechneten Schaltwinkel (im Bogenmaß) zurück.
 * 
 * @return Die berechneten Schaltwinkel (im Bogenmaß)
 */
public double[] getSchaltwinkel()
   {
   // Die berechneten Schaltwinkel (im Bogenmaß) werden zurückgegeben.
   return this.schaltwinkel;
   }
}
