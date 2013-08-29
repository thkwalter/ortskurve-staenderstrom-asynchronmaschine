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

import java.util.logging.Logger;

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

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(Achsendimensionierung.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor dimensioniert für eine gegebene Punktemenge die Achsen des Koordinatensystems.
 * 
 * @param punkte Die Punktemenge.
 */
public Achsendimensionierung(Vector2D[] punkte)
   {
   // Der Einsprung in den Konstruktor wird protokolliert.
   Achsendimensionierung.logger.entering("Achsendimensionierung", "Achsendimensionierung");
   
   // Die Wertebereiche der Punktemenge wird bestimmt.
   Wertebereich wertebereich = this.wertebereichBestimmen(punkte);
   
   // Der Wertebereich wird gegebenenfalls bis zum Ursprung ausgedehnt.
   this.wertebereichKoordinatensystem = this.ursprungEinbeziehen(wertebereich);
   
   // Der Rücksprung aus dem Konstruktor wird protokolliert.
   Achsendimensionierung.logger.exiting("Achsendimensionierung", "Achsendimensionierung");
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
   // Der Einsprung in die Methode wird protokolliert.
   Achsendimensionierung.logger.entering("Achsendimensionierung", "wertebereichBestimmen");
   
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
   
   // Die Maxima und Minima werden protokolliert.
   Achsendimensionierung.logger.fine("maxX: " + maxX);
   Achsendimensionierung.logger.fine("maxY: " + maxY);
   Achsendimensionierung.logger.fine("minX: " + minX);
   Achsendimensionierung.logger.fine("minY: " + minY);
   
   // Der Rücksprung aus der Methode wird protokolliert.
   Achsendimensionierung.logger.exiting("Achsendimensionierung", "wertebereichBestimmen");

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
   // Der Einsprung in die Methode wird protokolliert.
   Achsendimensionierung.logger.entering("Achsendimensionierung", "ursprungEinbeziehen");
   
   // Der Wertebereich wird gegebenenfalls so ausgedehnt, dass er den Ursprung umfasst.
   double maxX = Math.max(0.0, wertebereichPunktemenge.getMaxX());
   double maxY = Math.max(0.0, wertebereichPunktemenge.getMaxY());
   double minX = Math.min(0.0, wertebereichPunktemenge.getMinX());
   double minY = Math.min(0.0, wertebereichPunktemenge.getMinY());
   
   // Die neuen Maxima und Minima werden protokolliert.
   Achsendimensionierung.logger.fine("maxX: " + maxX);
   Achsendimensionierung.logger.fine("maxY: " + maxY);
   Achsendimensionierung.logger.fine("minX: " + minX);
   Achsendimensionierung.logger.fine("minY: " + minY);
   
   // Der Rücksprung aus der Methode wird protokolliert.
   Achsendimensionierung.logger.exiting("Achsendimensionierung", "ursprungEinbeziehen");
   
   // Dererweiterte Wertebereich der Punktemenge wird zurückgegeben.
   return new Wertebereich(maxX, maxY, minX, minY);
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
}
