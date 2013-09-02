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
 * Das Datenmodell der Ortskurve.
 * 
 * @author Th. K. Walter
 */
public class OrtskurveModell
{
/**
 * @see java.lang.Object#toString() 
 */
@Override
public String toString()
   {
   // Die Zeichenkette, die dieses Objekt repräsentiert, wird zusammengebaut.
   StringBuilder stringBuilder = new StringBuilder();
//   stringBuilder.append("mittelPunktInPixeln: ").append(this.mittelpunktInPixeln).append("; radiusInPixeln: ")
//            .append(this.radiusInPixeln);

   // Die Zeichenkette, die dieses Objekt repräsentiert, wird zurückgegeben.
   return stringBuilder.toString();
   }

}
