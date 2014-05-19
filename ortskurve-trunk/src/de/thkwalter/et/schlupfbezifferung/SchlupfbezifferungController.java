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

import org.apache.commons.math3.analysis.solvers.BisectionSolver;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.thkwalter.jsf.ApplicationRuntimeException;

/**
 * Dieser Klasse steuert die Bestimmung der Schlupfbezifferung.
 * 
 * @author Th. K. Walter
 */
public class SchlupfbezifferungController
{
/**
 * Das Datenmodell der Schlupfbezifferungsbestimmung.
 */
private SchlupfbezifferungModell schlupfbezifferungModell;

/**
 * Der Lösungsalgorithmus zur Bestimmung des Steigungswinkels der Schlupfgeraden
 */
private BisectionSolver bisectionSolver;

// ---------------------------------------------------------------------------------------------------------------------

/**
 * Die absolute Genauigkeit der berechneten Schaltwinkel (im Bogenmaß), bei der die Iteration abgebrochen wird.
 */
private final static double ABBRUCHKRITERIUM_ABSOLUTE_GENAUIGKEIT_SCHALTWINKEL = 1e-6; 

/**
 * Die relative Genauigkeit der berechneten Schaltwinkel, bei der die Iteration abgebrochen wird.
 */
private final static double ABBRUCHKRITERIUM_RELATIVE_GENAUIGKEIT_SCHALTWINKEL = 0.0; 

/**
 * Die maximale Anzahl von Iterationen.
 */
private final static int MAX_ANZAHL_ITERATIONEN = 100;

// =====================================================================================================================
// =====================================================================================================================

/** 
 * Dieser Konstruktor initilisiert den Lösungsalgorithmus zur Bestimmung des Steigungswinkels der Schlupfgeraden
 */
public SchlupfbezifferungController()
   {
   // Der Lösungsalgorithmus zur Bestimmung des Steigungswinkels der Schlupfgeraden wird erzeugt.
   this.bisectionSolver = new BisectionSolver(
      SchlupfbezifferungController.ABBRUCHKRITERIUM_RELATIVE_GENAUIGKEIT_SCHALTWINKEL,
      SchlupfbezifferungController.ABBRUCHKRITERIUM_ABSOLUTE_GENAUIGKEIT_SCHALTWINKEL);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode bestimmt die Schlupfbezifferung.
 */
private void schlupfbezifferungBestimmenIntern()
   {
   // Das Inversionszentrum (in A) wird berechnet und im Datenmodell der Schlupfbezifferungsbestimmung gespeichert.
   Vector2D inversionszentrum = this.inversionszentrumBerechnen();
   this.schlupfbezifferungModell.setInversionszentrum(inversionszentrum);
   
   // Der Drehpunkt der Schlupfgerade (in A) wird berechnet und im Datenmodell der Schlupfbezifferungsbestimmung 
   // gespeichert.
   Vector2D drehpunktSchlupfgerade = this.drehpunktSchlupfgeradeBerechnen();
   this.schlupfbezifferungModell.setDrehpunktSchlupfgerade(drehpunktSchlupfgerade);
   
   // Die Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten werden berechnet.
   double[] steigungen = this.steigungenBerechnen();
   
   // Das Residuum des Schlupfs eines Betriebspunkts in Abhängigkeit vom Steigungswinkel der Schlupfgeraden wird
   // erzeugt.
   Schlupfresiduum schlupfresiduum = new Schlupfresiduum(steigungen, this.schlupfbezifferungModell.getBetriebspunkte(), 
      inversionszentrum, drehpunktSchlupfgerade);
   
   // Der Steigungswinkel der Schlupfgeraden wird berechnet und im Datenmodell der Schlupfbezifferungsbestimmung 
   // gespeichert.
   double phi = 
      bisectionSolver.solve(SchlupfbezifferungController.MAX_ANZAHL_ITERATIONEN, schlupfresiduum, 0.0, Math.PI);
   this.schlupfbezifferungModell.setPhi(phi);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet das Inversionszentrum (in A). Das Inversionszentrum liegt auf der gedrehten Ortskurve unter 
 * dem Polarwinkel von 315 Grad.
 * 
 * @return Das Inversionzentrum (in A)
 */
private Vector2D inversionszentrumBerechnen()
   {
   // Der Mittelpunkt der gedrehten Ortskurve (in A) wird gelesen.
   Vector2D mittelpunktOrtskurve = this.schlupfbezifferungModell.getOrtskurve().getMittelpunktOrtskurve();
   double m_x = mittelpunktOrtskurve.getX();
   double m_y = mittelpunktOrtskurve.getY();
   
   // Der Radius der Ortskurve (in A) wird gelesen.
   double r = this.schlupfbezifferungModell.getOrtskurve().getRadiusOrtskurve();
   
   // Das Inversionszentrum (in A) wird berechnet. 
   double x_0 = m_x + r * Math.cos(7 * Math.PI / 4);
   double y_0 = m_y + r * Math.sin(7 * Math.PI / 4);
   Vector2D inversionszentrum = new Vector2D(x_0, y_0);
   
   // Das Inversionszentrum (in A) wird zurückgegeben.
   return inversionszentrum;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet den Drehpunkt der Schlupfgerade (in A). Der Drehpunkt liegt auf der gedrehten Ortskurve 
 * senkrecht unter dem Mittelpunkt.
 * 
 * @return Der Drehpunkt der Schlupfgerade (in A)
 */
private Vector2D drehpunktSchlupfgeradeBerechnen()
   {
   // Der Mittelpunkt der gedrehten Ortskurve (in A) wird gelesen.
   Vector2D mittelpunktOrtskurve = this.schlupfbezifferungModell.getOrtskurve().getMittelpunktOrtskurve();
   
   // Der Radius der Ortskurve (in A) wird gelesen.
   double r = this.schlupfbezifferungModell.getOrtskurve().getRadiusOrtskurve();
   
   // Der Drehpunkt der Schupfgerade (in A) wird berechnet.
   Vector2D drehpunkt = mittelpunktOrtskurve.subtract(new Vector2D(0.0, r));
   
   // Der Drehpunkt der Schlupfgerade (in A) wird zurückgegeben.
   return drehpunkt;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet die Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten.
 * 
 * @return Die Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten
 */
private double[] steigungenBerechnen()
   {
   // Das Feld der Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten wird erzeugt.
   double[] steigungen = new double[3];
   
   // Die Betriebspunkte werden gelesen.
   Betriebspunkt[] betriebspunkte = this.schlupfbezifferungModell.getBetriebspunkte();
   
   // Das Inversionszentrum (in A) wird gelesen.
   Vector2D inversionszentrum = this.schlupfbezifferungModell.getInversionszentrum();
   
   // Eine Hilfsvariable wird deklariert.
   double nenner = Double.NaN;
   
   // In der folgenden Schleife werden die Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten 
   // berechnet.
   for (int i = 0; i < betriebspunkte.length; i++)
      {
      // Die komplexe Ständerstromstärke des aktuellen Betriebspunkts (in A) wird gelesen.
      Vector2D i_1 = betriebspunkte[i].getI_1();
      
      // Eine Hilfsgröße (in A) wird berechnet.
      nenner = (i_1.getX() - inversionszentrum.getX());
      
      // Falls die Hilfsfröße zu klein wird, wird eine Ausnahme geworfen.
      if (Math.abs(nenner / inversionszentrum.getX()) < 1E-10)
         {
         // Die Fehlermeldung wird erstellt.
         String message = "Der Punkt " + betriebspunkte[i].getI_1() + " A liegt über dem Inversionszentrum und ist " +
            "daher zur Bestimmung der Schlupfbezifferung ungeeignet! Wählen Sie bitte einen anderen Punkt aus.";
         
         // Die Ausnahme wird geworfen.
         throw new ApplicationRuntimeException(message);
         }
      
      // Die Steigung des Strahls vom Inversionszentrum zum aktuellen Betriebspunkt wird berechnet.
      steigungen[i] = (i_1.getY() - inversionszentrum.getY()) / nenner;
      }
   
   // Die Steigungen der Strahlen vom Inversionszentrum zu den Betriebspunkten werden zurückgegeben.
   return steigungen;
   }
}
