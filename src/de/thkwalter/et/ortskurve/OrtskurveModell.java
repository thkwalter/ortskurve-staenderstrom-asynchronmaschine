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

import java.util.logging.Logger;

/**
 * Das Datenmodell der Ortskurve.
 * 
 * @author Th. K. Walter
 */
public class OrtskurveModell
{
/**
 * Die Grafikdarstellung der Messpunkte.
 */
private MesspunkteGrafik messpunkteGrafik;

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(OrtskurveModell.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Grafikdarstellung der Messpunkte zurück.
 * 
 * @return Die Grafikdarstellung der Messpunkte
 */
public MesspunkteGrafik getMesspunkteGrafik()
   {
   // Die Grafikdarstellung der Messpunkte wird protokolliert.
   OrtskurveModell.logger.fine(this.messpunkteGrafik.toString());
   
   // Die Grafikdarstellung der Messpunkte wird zurückgegeben.
   return this.messpunkteGrafik;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die übergebene Grafikdarstellung der Messpunkte in diesem Objekt.
 * 
 * @param messpunkteGrafik Die Grafikdarstellung der Messpunkte
 */
public void setMesspunkteGrafik(MesspunkteGrafik messpunkteGrafik)
   {
   // Die übergebene Grafikdarstellung der Messpunkte wird protokolliert.
   OrtskurveModell.logger.fine(messpunkteGrafik.toString());
   
   // Die übergebene Grafikdarstellung der Messpunkte wird in diesem Objekt gespeichert.
   this.messpunkteGrafik = messpunkteGrafik;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString() 
 */
@Override
public String toString()
   {
   // Die Zeichenkette, die dieses Objekt repräsentiert, wird zusammengebaut.
   StringBuilder stringBuilder = new StringBuilder();
   stringBuilder.append("messpunkteGrafik: ").append(this.messpunkteGrafik.toString());

   // Die Zeichenkette, die dieses Objekt repräsentiert, wird zurückgegeben.
   return stringBuilder.toString();
   }

}
