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


/**
 * Diese Klasse repräsentiert einen Wertebereich.
 *
 * @author Th. K. Walter
 */
public class Wertebereich
{
/**
 * Die maximale x-Koordinate.
 */
private double maxX;

/**
 * Die maximale y-Koordinate.
 */
private double maxY;

/**
 * Die minimale x-Koordinate.
 */
private double minX;

/**
 * Die maximale y-Koordinate.
 */
private double minY;

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(Wertebereich.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert den Wertebereich.
 * 
 * @param maxX Die maximale x-Koordinate.
 * @param maxY Die maximale y-Koordinate.
 * @param minX Die minimale x-Koordinate.
 * @param minY Die minimale y-Koordinate.
 */
public Wertebereich(double maxX, double maxY, double minX, double minY)
   {
   // Der Einsprung in den Konstruktor wird protokolliert.
   Wertebereich.logger.entering("Wertebereich", "Wertebereich");
   
   // Die Attribute werden initialisiert.
   this.maxX = maxX;
   this.maxY = maxY;
   this.minX = minX;
   this.minY = minY;
   
   // Der Wertebereich wird protokolliert.
   Wertebereich.logger.fine("maxX: " + this.maxX);
   Wertebereich.logger.fine("maxY: " + this.maxY);
   Wertebereich.logger.fine("minX: " + this.minX);
   Wertebereich.logger.fine("minY: " + this.minY);
   
   // Der Rücksprung aus dem Konstruktor wird protokolliert.
   Wertebereich.logger.exiting("Wertebereich", "Wertebereich");
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die maximale x-Koordinate zurück.
 * 
 * @return Die maximale x-Koordinate.
 */
public double getMaxX()
   {
   return this.maxX;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die maximale y-Koordinate zurück.
 * 
 * @return Die maximale y-Koordinate.
 */
public double getMaxY()
   {
   return this.maxY;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die minimale x-Koordinate zurück.
 * 
 * @return Die minimale x-Koordinate zurück.
 */
public double getMinX()
   {
   return this.minX;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die minimale y-Koordinate zurück.
 * 
 * @return Die minimale y-Koordinate.
 */
public double getMinY()
   {
   return this.minY;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   return this.maxX + "; " + this.maxY + "; " + this.minX + "; " + this.minY;
   }
}
