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
package de.thkwalter.et.ersatzschaltbild;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.thkwalter.et.ortskurve.Ausgleichsproblem;
import de.thkwalter.et.ortskurve.Ortskurve;
import de.thkwalter.et.ortskurve.OrtskurveModell;

/**
 * Das Datenmodell der Ersatzschaltbildberechnung.
 * 
 * @author Th. K. Walter
 * @version 1.2
 */
@ViewScoped
@ManagedBean(name="ersatzschaltbildModell")
public class ErsatzschaltbildModell
{
/**
 * Die Kreisparameter der Ortskurve
 */
private Ortskurve ortskurve;

/**
 * Die Daten der verschiedenen Betriebspunkte.
 */
private ArrayList<Betriebspunkt> betriebspunkte;

/**
 * Die Leiter-Leiter-Spannung (in V).
 */
private double u1;

/**
 * Die Frequenz des Ständerstroms (in Hz).
 */
private double f1;

/**
 * Die Polpaarzahl.
 */
private int p;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert die Attribute.
 */
public ErsatzschaltbildModell()
   {
   // Die numerischen Attribute werden mit NaN-Werten initialisiert.
   this.f1 = Double.NaN;
   this.p = Integer.MIN_VALUE;
   this.u1 = Double.NaN;
   
   // Die Liste für die Daten der Betriebspunkte wird erzeugt.
   this.betriebspunkte = new ArrayList<Betriebspunkt>();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode initialisiert das Datenmodell mit Hilfe des Datenmodells der Ortskurvenberechnung.
 */
@PostConstruct
public void init()
   {
   // Der FacesContext wird gelesen.
   FacesContext facesContext = FacesContext.getCurrentInstance();
   
   // Der Controller der Ortskurvenberechnung wird gelesen.
   Ausgleichsproblem ausgleichsproblem = (Ausgleichsproblem) facesContext.getApplication().evaluateExpressionGet(
      facesContext, "#{ausgleichsproblem}", Ausgleichsproblem.class); 
   
   // Die benötigten Daten des Datenmodells der Ortskurvenberechnung werden in das Datenmodell der 
   // Ersatzschaltbildberechnung übernommen.
   this.datenUebernehmen(ausgleichsproblem);
   }
   
// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode übernimmt die benötigten Daten des Datenmodells der Ortskurvenberechnung in das Datenmodell der 
 * Ersatzschaltbildberechnung
 * 
 * @param ausgleichsproblem Der Controller der Ortskurvenberechnung
 */
private void datenUebernehmen(Ausgleichsproblem ausgleichsproblem)
   {
   // Das Datenmodell der Ortskurvenberechnung wird gelesen.
   OrtskurveModell ortskurveModell = ausgleichsproblem.getOrtskurveModell();
   
   // Die Ortskurve wird in das Datenmodell des Ersatzschaltbildberechnung übertragen.
   this.setOrtskurve(ortskurveModell.getOrtskurve());
   
   // Die Messpunkte werden in das Datenmodell der Ersatzschaltbildberechnung übertragen.
   Vector2D[] messpunkte = ortskurveModell.getMesspunkte();
   for (Vector2D messpunkt : messpunkte)
      {
      this.betriebspunkte.add(new Betriebspunkt(new Complex(messpunkt.getY(), -messpunkt.getX())));
      }
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Kreisparameter der Ortskurve zurück.
 * 
 * @return Die Kreisparameter der Ortskurve
 */
public Ortskurve getOrtskurve()
   {
   // Die Kreisparameter der Ortskurve werden zurückgegeben.
   return this.ortskurve;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Die übergebenen Kreisparameter der Ortskurve werden im Datenmodell gespeichert.
 * 
 * @param ortskurve Die übergebenen Kreisparameter der Ortskurve
 */
public void setOrtskurve(Ortskurve ortskurve)
   {
   // Die übergebenen Kreisparameter der Ortskurve werden gespeichert.
   this.ortskurve = ortskurve;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Daten der Betriebspunkte zurück.
 * 
 * @return Die Daten der Betriebspunkte
 */
public ArrayList<Betriebspunkt> getBetriebspunkte()
   {
   // Die Daten der Betriebspunkte werden zurückgegeben.
   return this.betriebspunkte;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die Daten der Betriebspunkte im Datenmodell.
 * 
 * @param betriebspunkte Die Daten der Betriebspunkte
 */
public void setBetriebspunkte(ArrayList<Betriebspunkt> betriebspunkte)
   {
   // Die Daten der Betriebspunkte werden im Datenmodell gespeichert.
   this.betriebspunkte = betriebspunkte;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Leiter-Leiter-Spannung zurück (in V) zurück.
 * 
 * @return Die Leiter-Leiter-Spannung (in V)
 */
public double getU1()
   {
   // Die Leiter-Leiter-Spannung (in V) wird zurückgegeben.
   return this.u1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die übergebene Leiter-Leiter-Spannung (in V) im Datenmodell.
 * 
 * @param u1 Die Leiter-Leiter-Spannung (in V)
 */
public void setU1(double u1)
   {
   // Die übergebene Leiter-Leiter-Spannung wird im Datenmodell gespeichert.
   this.u1 = u1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Frequenz des Ständerstroms (in Hz) zurück.
 * 
 * @return Die Frequenz des Ständerstroms (in Hz)
 */
public double getF1()
   {
   // Die Frequenz des Ständerstroms (in Hz) wird zurückgegeben.
   return this.f1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die übergebene Frequenz des Ständerstroms (in Hz).
 * 
 * @param f1 Die Frequenz des Ständerstroms (in Hz).
 */
public void setF1(double f1)
   {
   // Die übergebene Frequenz des Ständerstroms wird im Datenmodell gespeichert.
   this.f1 = f1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Polpaarzahl zurück.
 * 
 * @return Die Polpaarzahl
 */
public int getP()
   {
   // Die Polpaarzahl wird zurückgegeben.
   return this.p;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die übergebene Polpaarzahl im Datenmodell.
 * 
 * @param p Die Polpaarzahl
 */
public void setP(int p)
   {
   // Die übergebene Polpaarzahl wird im Datenmodell gespeichert.
   this.p = p;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   // Die Zeichenkette, die dieses Datenmodell repräsentiert, wird erzeugt.
   StringBuilder builder = new StringBuilder();
   builder.append("ErsatzschaltbildModell [");
   
   // Die Zeichenkette, welche die Ortskurve repräsentiert, wird hinzugefügt.
   if (ortskurve != null)
      {
      builder.append("ortskurve=").append(ortskurve).append(", ");
      }
   
   // Die Zeichenkette, welche die Daten der Betriebspunkte repräsentiert, wird hinzugefügt.
   if (betriebspunkte != null)
      {
      builder.append("betriebspunkte=").append(betriebspunkte).append(", ");
      }
   
   // Die Zeichenkette, welche die Leiter-Leiter-Spannung (in V) repräsentiert, wird hinzugefügt.
   builder.append("u1=").append(u1);
   
   // Die Zeichenkette, welche die Frequenz des Ständerstroms (in Hz) repräsentiert, wird hinzugefügt.
   builder.append(", f1=").append(f1);
   
   // Die Zeichenkette, welche die Polpaarzahl repräsentiert, wird hinzugefügt.
   builder.append(", p=").append(p).append("]");
   
   // Die Zeichenkette, die die dieses Datenmodell repräsentiert, wird zurückgegeben.
   return builder.toString();
   }
}
