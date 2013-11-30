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
package de.thkwalter.koordinatensystem;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * Diese Klasse dimensioniert für eine gegebene Punktemenge die Achsen des Koordinatensystems.
 *
 * @author Th. K. Walter
 */
public class Achsendimensionierung
{
/**
 * Der Wertebereich des Koordinatensystems.
 */
private Wertebereich wertebereichKoordinatensystem;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor dimensioniert für eine gegebene Punktemenge die Achsen des Koordinatensystems.
 * 
 * @param punkte Die Punktemenge.
 */
public Achsendimensionierung(Vector2D[] punkte)
   {
   // Die Wertebereiche der Punktemenge wird bestimmt.
   Wertebereich wertebereich = this.wertebereichBestimmen(punkte);
   
   // Der Wertebereich wird gegebenenfalls bis zum Ursprung ausgedehnt.
   wertebereich = this.ursprungEinbeziehen(wertebereich);
   
   // Der Wertebereich wird in jede Richtung um zehn Prozent Sicherheitsabstand vergrößert.
   this.wertebereichKoordinatensystem = this.sicherheitsabstandHinzufuegen(wertebereich);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode bestimmt die Wertebereiche in x- und y-Richtung.
 * 
 * @param punkte Die Punktemenge.
 * 
 * @return Der Wertebereich der Punktemenge.
 */
private Wertebereich wertebereichBestimmen(Vector2D[] punkte)
   {   
   // Die Objekte, die zur Bestimmung der minimalen und maximalen Werte verwendet werden, werden erzeugt.
   SummaryStatistics summaryStatisticsX = new SummaryStatistics();
   SummaryStatistics summaryStatisticsY = new SummaryStatistics();
   
   // Die Objekte, die zur Bestimmung der minimalen und maximalen Werte verwendet werden, werden initialisiert.
   for (Vector2D punkt : punkte)
      {
      summaryStatisticsX.addValue(punkt.getX());
      summaryStatisticsY.addValue(punkt.getY());
      }
   
   // Die Maxima und Minima werden bestimmt.
   double maxX = summaryStatisticsX.getMax();
   double maxY = summaryStatisticsY.getMax();
   double minX = summaryStatisticsX.getMin();
   double minY = summaryStatisticsY.getMin();

   // Der maximale Wertebereich der Punktemenge wird zurückgegeben.
   return new Wertebereich(maxX, maxY, minX, minY);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode dehnt den Wertebereich gegebenenfalls so aus, dass der Ursprung enthalten ist.
 * 
 * @param wertebereichPunktemenge
 * 
 * @return Der gegebenenfalls um den Ursprung erweiterte Wertebereich.
 */
private Wertebereich ursprungEinbeziehen(Wertebereich wertebereichPunktemenge)
   {
   // Der Wertebereich wird gegebenenfalls so ausgedehnt, dass er den Ursprung umfasst.
   double maxX = Math.max(0.0, wertebereichPunktemenge.getMaxX());
   double maxY = Math.max(0.0, wertebereichPunktemenge.getMaxY());
   double minX = Math.min(0.0, wertebereichPunktemenge.getMinX());
   double minY = Math.min(0.0, wertebereichPunktemenge.getMinY());
   
   // Dererweiterte Wertebereich der Punktemenge wird zurückgegeben.
   return new Wertebereich(maxX, maxY, minX, minY);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode vergrößert den Wertebereich in jede Richtung um zehn Prozent Sicherheitsabstand.
 * 
 * @param wertebereich Der Wertebereich ohne Sicherheitsabstand
 * 
 * @return Der Wertebereich mit Sicherheitsabstand
 */
public Wertebereich sicherheitsabstandHinzufuegen(Wertebereich wertebereich)
   {
   // Die Ausdehnungen des Wertebereichs in x- und y-Richtung werden berechnet.
   double xAusdehnung = wertebereich.getMaxX() - wertebereich.getMinX();
   double yAusdehnung = wertebereich.getMaxY() - wertebereich.getMinY();
   
   // Der Wertebereich mit Sicherheitsabstand wird berechnet.
   double maxX = wertebereich.getMaxX() + 0.1 * xAusdehnung;
   double maxY = wertebereich.getMaxY() + 0.1 * yAusdehnung;
   double minX = wertebereich.getMinX() - 0.1 * xAusdehnung;
   double minY = wertebereich.getMinY() - 0.1 * yAusdehnung;
   Wertebereich werteBereichMitSicherheitsabstand = new Wertebereich(maxX, maxY, minX, minY);
   
   // Der Wertebereich mit Sicherheitsabstand wird zurückgegeben.
   return werteBereichMitSicherheitsabstand;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Der Wertebereich des Koordinatensystems wird zurückgegeben.
 * 
 * @return Der Wertebereich des Koordinatensystems.
 */
public Wertebereich getWertebereichKoordinatensystem()
   {
   return this.wertebereichKoordinatensystem;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   return this.wertebereichKoordinatensystem.toString();
   }
}
