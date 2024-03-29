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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
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
public class OrtskurveModell implements Serializable
{
/**
 * Die Serialisierungsnummer
 */
private static final long serialVersionUID = -401924956499809486L;

/**
 * Dieses Feld enthält die Messpunkte.
 */
private Vector2D[] messpunkte;

/**
 * Die Ortskurve
 */
private Ortskurve ortskurve;

/**
 * Der optimale Ausgleichskreis.
 */
private Ortskurve optimalerAusgleichskreis;

/**
 * Die Grafikdarstellung der Messpunkte
 */
private MesspunkteGrafik messpunkteGrafik;

/**
 * Die Grafikdarstellung der Ortskurve
 */
private OrtskurveGrafik ortskurveGrafik;

/**
 * Die Grafikdarstellung des optimalen Ausgleichskreises
 */
private OrtskurveGrafik optimalerAusgleichskreisGrafik;

/**
 * Die Grafikdarstellung der Koordinatenachsen
 */
private Koordinatenachsen koordinatenachsen;

/**
 * Die Anzahl der Pixel der Grafik in x-Richtung.
 */
private final int xPixelGrafik = 550;

/**
 * Die Anzahl der Pixel der Grafik in y-Richtung.
 */
private final int yPixelGrafik = 275;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode initialisiert das Datenmodell mit Hilfe des Datenmodells der Ortskurvenberechnung.
 */
@PostConstruct
public void init()
   {
   // Die Ortskurve wird zurückgesetzt
   this.ortskurve = null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode berechnet die Daten für die Grafik der Ortskurve.
 */
public void grafikdatenBerechnen()
   {
   // Die Punkte, welche die Grafik begrenzen, werden zusammengestellt.
   Vector2D[] randpunkte = this.randpunkteZusammenstellen();
   
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
   
   // Falls eine 2d-Ausgleichsrechnung durchgeführt worden ist, wird auch die Grafikdarstellung des optimalen
   // Ausgleichskreises berechnet.
   if (this.optimalerAusgleichskreis != null)
      {
      this.optimalerAusgleichskreisGrafik = new OrtskurveGrafik(this.optimalerAusgleichskreis, punktPixelKonverter);
      }
   
   // Die Grafikdarstellung der Messpunkte wird berechnet.
   this.messpunkteGrafik = new MesspunkteGrafik(this.messpunkte, punktPixelKonverter);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode stellt die Punkte zusammen, welche die Grafik begrenzen.
 * 
 * @return Das Feld der Punkte, welche die Grafik begrenzen.
 */
private Vector2D[] randpunkteZusammenstellen()
   {
   // Das Feld der Randpunkte wird erzeugt.
   ArrayList<Vector2D> randpunkte = new ArrayList<Vector2D>();
   
   // Die Messpunkte werden in das Feld der Randpunkte kopiert.
   for (int i = 0; i < this.messpunkte.length; i++)
      {
      randpunkte.add(this.messpunkte[i]);
      }
   
   // Die Randpunkte der Ortskurve werden hinzugefügt.
   randpunkte.addAll(randpunkteOrtskurveZusammenstellen(this.ortskurve));
   
   // Falls eine 2d-Ausgleichsrechnung ausgeführt worden ist, werden die Randpunkte des optimalen Ausgleichskreises 
   // hinzugefügt.
   if (this.optimalerAusgleichskreis != null)
      {
      randpunkte.addAll(randpunkteOrtskurveZusammenstellen(this.optimalerAusgleichskreis));
      }
   
   // Die Liste der Randpunkte wird als Feld zurückgegeben.
   return randpunkte.toArray(new Vector2D[randpunkte.size()]);
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

//=====================================================================================================================
//=====================================================================================================================

/**
 * Diese Methode gibt die Grafikdarstellung des optimalen Ausgleichskreises zurück.
 * 
 * @return Die Grafikdarstellung des optimalen Ausgleichskreises
 */
public OrtskurveGrafik getOptimalerAusgleichskreisGrafik()
   {
   // Die Grafikdarstellung des optimalen Ausgleichskreises wird zurückgegeben.
   return this.optimalerAusgleichskreisGrafik; 
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

/**
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   // Die Zeichenkette, die das Datenmodell repräsentiert, wird erzeugt.
   StringBuilder builder = new StringBuilder("OrtskurveModell [");
   
   // Die Zeichenkette, welche die Messpunkte repräsentiert, wird hinzugefügt. 
   if (this.messpunkte != null)
      {
      builder.append("messpunkte=").append(Arrays.toString(this.messpunkte)).append(", ");
      }
      
   // Die Zeichenkette, welche die Ortskurve repräsentiert, wird hinzugefügt. 
   if (this.ortskurve != null)
      {
      builder.append("ortskurve=").append(this.ortskurve).append(", ");
      }
      
   // Die Zeichenkette, welche den optimalen Ausgleichskreis repräsentiert, wird hinzugefügt. 
   if (this.optimalerAusgleichskreis != null)
      {
      builder.append("optimaler Ausgleichskreis=").append(this.optimalerAusgleichskreis).append(", ");
      }
      
   // Die Zeichenkette, welche die Grafikdarstellung der Messpunkte repräsentiert, wird hinzugefügt. 
   if (this.messpunkteGrafik != null)
      {
      builder.append("messpunkteGrafik=").append(this.messpunkteGrafik).append(", ");
      }   
      
   // Die Zeichenkette, welche die Grafikdarstellung der Ortskurve repräsentiert, wird hinzugefügt. 
   if (this.ortskurveGrafik != null)
      {
      builder.append("ortskurveGrafik=").append(this.ortskurveGrafik).append(", ");
      }
   
   // Die Zeichenkette, welche die Grafikdarstellung des optimalen Ausgleichskreises repräsentiert, wird 
   // hinzugefügt. 
   if (this.optimalerAusgleichskreisGrafik != null)
      {
      builder.append("ortskurve2dGrafik=").append(this.optimalerAusgleichskreisGrafik).append(", ");
      }
      
   // Die Zeichenkette, welche die Koordinatenachsen repräsentiert, wird hinzugefügt. 
   if (this.koordinatenachsen != null)
      {
      builder.append("koordinatenachsen=").append(this.koordinatenachsen).append(", ");
      }
      
   // Die restlichen Parameter werden zur Zeichenkette hinzugefügt.
   builder.append("xPixelGrafik=").append(xPixelGrafik).append(", yPixelGrafik=").append(yPixelGrafik).append("]");
   
   // Die Zeichenkette, die das Datenmodell repräsentiert, wird zurückgegeben.
   return builder.toString();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die übergebenen Messpunkte in diesem Objekt.
 * 
 * @param messpunkte Die Messpunkte
 */
public void setMesspunkte(Vector2D[] messpunkte)
   {      
   // Die Messpunkte werden gespeichert.
   this.messpunkte = messpunkte;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Messpunkte zurück.
 * 
 * @return Die Messpunkte
 */
public Vector2D[] getMesspunkte()
   {
   // Die Messpunkte werden zurückgegeben.
   return this.messpunkte;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den optimalen Ausgeleichskreis zurück.
 * 
 * @return Der optimale Ausgleichskreis
 */
public Ortskurve getOptimalerAusgleichskreis()
   {
   // Der optimale Ausgleichskreis wird zurückgegeben.
   return this.optimalerAusgleichskreis;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert den übergebenen, optimalen Ausgleichskreis im Modell.
 * 
 * @param optimalerAusgleichskreis Der optimale Ausgleichskreis
 */
public void setOptimalerAusgleichskreis(Ortskurve optimalerAusgleichskreis)
   {
   // Der übergebene, optimale Ausgleichskreis wird in diesem Modell gespeichert.
   this.optimalerAusgleichskreis = optimalerAusgleichskreis;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode stellt die Randpunkte einer Ortskurve zusammen.
 * 
 * @param ortskurve Die Ortskurve
 * 
 * @return Eine Liste der Randpunkte der Ortskurve
 */
private ArrayList<Vector2D> randpunkteOrtskurveZusammenstellen(Ortskurve ortskurve)
   {
   // Die Parameter der Ortskurve werden gelesen.
   double mx = ortskurve.getMittelpunktOrtskurve().getX();
   double my = ortskurve.getMittelpunktOrtskurve().getY();
   double r = ortskurve.getRadiusOrtskurve();
   
   // Das Feld der Randpunkte wird erzeugt.
   ArrayList<Vector2D> randpunkte = new ArrayList<Vector2D>();
   
   // Der Punkt, der den Kreis links begrenzt, wird zum Feld der Randpunkte hinzugefügt.
   randpunkte.add(new Vector2D(mx - r, my));
   
   // Der Punkt, der den Kreis rechts begrenzt, wird zum Feld der Randpunkte hinzugefügt.
   randpunkte.add(new Vector2D(mx + r, my));
   
   // Der Punkt, der den Kreis oben begrenzt, wird zum Feld der Randpunkte hinzugefügt.
   randpunkte.add(new Vector2D(mx, my + r));
   
   // Der Punkt, der den Kreis unten begrenzt, wird zum Feld der Randpunkte hinzugefügt.
   randpunkte.add(new Vector2D(mx, my - r));

   // Die Randpunkte der Ortskurve werden zurückgegeben.
   return randpunkte;
   }
}
