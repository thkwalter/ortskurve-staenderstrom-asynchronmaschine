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
 * @version 1.0
 */
public class OrtskurveModell
{
/**
 * Die Grafikdarstellung der Messpunkte
 */
private MesspunkteGrafik messpunkteGrafik;

/**
 * Die Grafikdarstellung der Ortskurve
 */
private OrtskurveGrafik ortskurveGrafik;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Grafikdarstellung der Messpunkte zurück.
 * 
 * @return Die Grafikdarstellung der Messpunkte
 */
public MesspunkteGrafik getMesspunkteGrafik()
   {   
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
   // Die übergebene Grafikdarstellung der Messpunkte wird in diesem Objekt gespeichert.
   this.messpunkteGrafik = messpunkteGrafik;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Grafikdarstellung der Ortskurve zurück.
 * 
 * @return Die Grafikdarstellung der Ortskurve
 */
public OrtskurveGrafik getOrtskurveGrafik()
   {
   return this.ortskurveGrafik;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die übergebene Grafikdarstellung der Ortskurve in diesem Objekt.
 * 
 * @param messpunkteGrafik Die Grafikdarstellung der Ortskurve
 */
public void setOrtskurveGrafik(OrtskurveGrafik ortskurveGrafik)
   {
   this.ortskurveGrafik = ortskurveGrafik;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString() 
 */
@Override
public String toString()
   {
   // Die Zeichenkette, die das Datenmodell der Zeichenkette repräsentiert, wird erzeugt..
   StringBuilder stringBuilder = new StringBuilder();
   
   // Die Zeichenkette, welche die Grafikdarstellung der Messpunkte repräsentiert, wird hinzugefügt. 
   stringBuilder.append("messpunkteGrafik: ");
   if (this.messpunkteGrafik != null)
      {
      stringBuilder.append(this.messpunkteGrafik.toString());
      }
   
   // Die Zeichenkette, welche die Grafikdarstellung der Ortskurve repräsentiert, wird hinzugefügt. 
   stringBuilder.append("ortskurveGrafik: ");
   if (this.messpunkteGrafik != null)
      {
      stringBuilder.append(this.ortskurveGrafik.toString());
      }
      
   // Die Zeichenkette, die das Datenmodell der Zeichenkette repräsentiert, wird zurückgegeben.
   return stringBuilder.toString();
   }

}
