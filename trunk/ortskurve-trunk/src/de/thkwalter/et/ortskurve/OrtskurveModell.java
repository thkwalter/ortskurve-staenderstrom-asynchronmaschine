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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.thkwalter.koordinatensystem.Achsendimensionierung;
import de.thkwalter.koordinatensystem.Koordinatenachsen;
import de.thkwalter.koordinatensystem.PunktPixelKonverter;


/**
 * Das Datenmodell der Ortskurve.
 * 
 * @author Th. K. Walter
 * @version 1.0
 */
@ViewScoped
@ManagedBean(name="ortskurveModell")
public class OrtskurveModell
{
/**
 * Die Ortskurve
 */
private Ortskurve ortskurve;

/**
 * Die Grafikdarstellung der Messpunkte
 */
private MesspunkteGrafik messpunkteGrafik;

/**
 * Die Grafikdarstellung der Ortskurve
 */
private OrtskurveGrafik ortskurveGrafik;

/**
 * Die Grafikdarstellung der Koordinatenachsen
 */
private Koordinatenachsen koordinatenachsen;

/**
 * Die Anzahl der Pixel der Grafik in x-Richtung.
 */
private final int xPixelGrafik = 540;

/**
 * Die Anzahl der Pixel der Grafik in y-Richtung.
 */
private final int yPixelGrafik = 270;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet die Daten für die Grafik der Ortskurve.
 * 
 * @param Die Messpunkte
 */
public void grafikdatenBerechnen(Vector2D[] messpunkte)
   {
   // Die Punkte, welche die Grafik begrenzen, werden zusammengestellt.
   Vector2D[] randpunkte = this.randpunkteZusammenstellen(messpunkte);
   
   // Ein Objekt der Klasse Achsendimensionierung berechnet den Wertebereich des Koordinatensystems.
   Achsendimensionierung achsendimensionierung = new Achsendimensionierung(randpunkte);
   
   // Ein Konverter, der Punkte in Pixel umrechnet, wird erzeugt.
   PunktPixelKonverter punktPixelKonverter = 
      new PunktPixelKonverter(achsendimensionierung.getWertebereichKoordinatensystem(), this.xPixelGrafik, 
         this.yPixelGrafik);
   
   // Die Koordinatenachsen werden berechnet.
   this.koordinatenachsen = 
      new Koordinatenachsen(achsendimensionierung.getWertebereichKoordinatensystem(), punktPixelKonverter);
   
   // Die Grafikdarstellung der Ortskurve wird berechnet.
   this.ortskurveGrafik = new OrtskurveGrafik(this.ortskurve, punktPixelKonverter);
   
   // Die Grafikdatstellung der Messpunkte wird berechnet.
   this.messpunkteGrafik = new MesspunkteGrafik(messpunkte, punktPixelKonverter);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode stellt die Punkte zusammen, welche die Grafik begrenzen.
 * 
 * @param Die Messpunkte
 * 
 * @return Das Feld der Punkte, welche die Grafik begrenzen.
 */
private Vector2D[] randpunkteZusammenstellen(Vector2D[] messpunkte)
   {
   // Die Parameter der Ortskurve werden gelesen.
   double mx = this.ortskurve.getMittelpunktOrtskurve().getX();
   double my = this.ortskurve.getMittelpunktOrtskurve().getY();
   double r = this.ortskurve.getRadiusOrtskurve();
   
   // Das Feld der Randpunkte wird erzeugt.
   int feldlaenge = messpunkte.length;
   Vector2D[] punkte = new Vector2D[feldlaenge + 4];
   
   // Die Messpunkte werden in das Feld der Randpunkte kopiert.
   for (int i = 0; i < feldlaenge; i++)
      {
      punkte[i] = messpunkte[i];
      }
   
   // Der Punkt, der den Kreis links begrenzt, wird zum Feld der Randpunkte hinzugefügt.
   punkte[feldlaenge] = new Vector2D(mx - r, my);
   
   // Der Punkt, der den Kreis rechts begrenzt, wird zum Feld der Randpunkte hinzugefügt.
   punkte[feldlaenge + 1] = new Vector2D(mx + r, my);
   
   // Der Punkt, der den Kreis oben begrenzt, wird zum Feld der Randpunkte hinzugefügt.
   punkte[feldlaenge + 2] = new Vector2D(mx, my + r);
   
   // Der Punkt, der den Kreis unten begrenzt, wird zum Feld der Randpunkte hinzugefügt.
   punkte[feldlaenge + 3] = new Vector2D(mx, my - r);
   
   // Das Feld der Randpunkte, welche die Grafik begrenzen, wird zurückgegeben.
   return punkte;
   }

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
 * Diese Methode gibt die Grafikdarstellung der Ortskurve zurück.
 * 
 * @return Die Grafikdarstellung der Ortskurve
 */
public OrtskurveGrafik getOrtskurveGrafik()
   {
   // Die Grafikdarstellung der Ortskurve wird zurückgegeben.
   return this.ortskurveGrafik;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Grafikdarstellung der Koordinatenachsen zurück.
 * 
 * @return Die Grafikdarstellung der Koordinatenachsen
 */
public Koordinatenachsen getKoordinatenachsen()
   {
   // Die Grafikdarstellung der Koordinatenachsen wird zurückgegeben.
   return this.koordinatenachsen;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Ortskurve zurück.
 * 
 * @return Die Ortskurve
 */
public Ortskurve getOrtskurve()
   {
   // Die Ortskurve wird zurückgegeben.
   return this.ortskurve;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die Ortskurve in diesem Objekt.
 * 
 * @param ortskurve Die Ortskurve
 */
public void setOrtskurve(Ortskurve ortskurve)
   {
   // Die übergebene Ortskurve wird in diesem Objekt gespeichert.
   this.ortskurve = ortskurve;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Anzahl der Pixel der Grafik in x-Richtung zurück.
 * 
 * @return Die Anzahl der Pixel der Grafik in x-Richtung.
 */
public int getxPixelGrafik()
   {
   return this.xPixelGrafik;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Anzahl der Pixel der Grafik in y-Richtung zurück.
 * 
 * @return Die Anzahl der Pixel der Grafik in y-Richtung.
 */
public int getyPixelGrafik()
   {
   return this.yPixelGrafik;
   }

// =====================================================================================================================
// =====================================================================================================================

///**
// * @see java.lang.Object#toString() 
// */
//@Override
//public String toString()
//   {
//   // Die Zeichenkette, die das Datenmodell der Zeichenkette repräsentiert, wird erzeugt.
//   StringBuilder stringBuilder = new StringBuilder();
//   
//   // Die Anzahl der Pixel der Grafik in x- und y-Richtung wird hinzugefügt.
//   stringBuilder.append("xPixelGrafik: ").append(this.xPixelGrafik).append("; yPixelGrafik: ").
//      append(this.yPixelGrafik).append("; ");
//   
//   // Die Zeichenkette, welche den Mittelpunkt der Ortskurve repräsentiert, wird hinzugefügt. 
//   stringBuilder.append("ortskurve: ");
//   if (this.ortskurve != null)
//      {
//      stringBuilder.append(this.ortskurve.toString());
//      }
//   
//   // Die Zeichenkette, welche die Grafikdarstellung der Messpunkte repräsentiert, wird hinzugefügt. 
//   stringBuilder.append("messpunkteGrafik: ");
//   if (this.messpunkteGrafik != null)
//      {
//      stringBuilder.append(this.messpunkteGrafik.toString());
//      }
//   
//   // Die Zeichenkette, welche die Grafikdarstellung der Ortskurve repräsentiert, wird hinzugefügt. 
//   stringBuilder.append("ortskurveGrafik: ");
//   if (this.ortskurveGrafik != null)
//      {
//      stringBuilder.append(this.ortskurveGrafik.toString());
//      }
//      
//   // Die Zeichenkette, die das Datenmodell der Zeichenkette repräsentiert, wird zurückgegeben.
//   return stringBuilder.toString();
//   }

}
