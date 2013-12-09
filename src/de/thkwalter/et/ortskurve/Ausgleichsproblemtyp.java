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
package de.thkwalter.et.ortskurve;

/**
 * Dieser Aufzählungstyp characterisiert den Typ des Ausgleichsproblems.
 * 
 * @author Th. K. Walter
 * @version 1.0
 */
public enum Ausgleichsproblemtyp
{
/**
 * Das Ausgleichsproblem zur Lösung der Ortskurve in zwei Dimensionen (x-Koordinate des Mittelpunkts, Radius).
 */
ORTSKURVE_2d,

/**
 * Das Ausgleichsproblem zur Lösung der Ortskurve in drei Dimensionen (Mittelpunkt, Radius).
 */
ORTSKURVE_3d;
}
