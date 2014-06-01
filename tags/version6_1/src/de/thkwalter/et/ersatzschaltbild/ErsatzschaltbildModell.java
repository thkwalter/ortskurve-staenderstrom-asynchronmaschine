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

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.thkwalter.et.ortskurve.Ortskurve;
import de.thkwalter.et.ortskurve.OrtskurveModell;

/**
 * Das Datenmodell der Ersatzschaltbildberechnung.
 * 
 * @author Th. K. Walter
 * @version 1.2
 */
@SessionScoped
@ManagedBean(name="ersatzschaltbildModell")
public class ErsatzschaltbildModell implements Serializable
{
/**
 * Die Kreisparameter der Ortskurve
 */
private Ortskurve ortskurve;

/**
 * Die Daten der verschiedenen Betriebspunkte
 */
private ArrayList<Betriebspunkt> betriebspunkte;

/**
 * Die Leiter-Leiter-Spannung (in V)
 */
private double u_LL;

/**
 * Die Frequenz des Ständerstroms (in Hz)
 */
private double f1;

/**
 * Die Polpaarzahl
 */
private int p;

/**
 * Der Schaltungstyp
 */
private Schaltungstyp schaltungstyp;

/**
 * Die Repräsentation des Ersatzschaltbilds
 */
private Ersatzschaltbild ersatzschaltbild;

/**
 * Die Eisenverluste (in W)
 */
private double p_Fe;

/**
 * Die Serialisierungsnummer
 */
private static final long serialVersionUID = -8101831504048593878L;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert die Attribute.
 */
public ErsatzschaltbildModell()
   {
   this.init();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode initialisiert das Objekt.
 */
private void init()
   {
   // Die numerischen Attribute werden mit NaN-Werten initialisiert.
   this.f1 = Double.NaN;
   this.p = Integer.MIN_VALUE;
   this.u_LL = Double.NaN;
   this.p_Fe = Double.NaN;
   
   // Die Liste für die Daten der Betriebspunkte wird erzeugt.
   this.betriebspunkte = new ArrayList<Betriebspunkt>();
   
   // Der Radiobutton wird mit STERN vorbelegt.
   this.schaltungstyp = Schaltungstyp.STERN;
   
   // Das Ersatzschaltbild wird auf null gesetzt, damit alte Berechnungsergebnisse nicht angezeigt werden.
   this.ersatzschaltbild = null;
   }
   
// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode übernimmt die benötigten Daten des Datenmodells der Ortskurvenberechnung in das Datenmodell der 
 * Ersatzschaltbildberechnung
 * 
 * @param ortskurveModell Das Frontend-Modell der Ortskurvenberechnung
 */
public void datenUebernehmen(OrtskurveModell ortskurveModell)
   {
   // Das Frontend-Modell wird zurückgesetzt.
   this.init();
   
   // Die Ortskurve wird in das Datenmodell des Ersatzschaltbildberechnung übertragen.
   this.setOrtskurve(ortskurveModell.getOrtskurve());
   
   // Die Messpunkte werden in das Datenmodell der Ersatzschaltbildberechnung übertragen.
   Vector2D[] messpunkte = ortskurveModell.getMesspunkte();
   
   // Für jeden Messpunkt wird ein Betriebspunkt hinzugefügt.
   this.betriebspunkte = new ArrayList<Betriebspunkt>();
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
public Double getU_LL()
   {
   // Die Leiter-Leiter-Spannung (in V) wird zurückgegeben.
   return Double.isNaN(this.u_LL) ? null : this.u_LL;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die übergebene Leiter-Leiter-Spannung (in V) im Datenmodell.
 * 
 * @param u_LL Die Leiter-Leiter-Spannung (in V)
 */
public void setU_LL(Double u_LL)
   {
   // Die übergebene Leiter-Leiter-Spannung wird im Datenmodell gespeichert.
   this.u_LL = u_LL;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Frequenz des Ständerstroms (in Hz) zurück.
 * 
 * @return Die Frequenz des Ständerstroms (in Hz)
 */
public Double getF1()
   {
   // Die Frequenz des Ständerstroms (in Hz) wird zurückgegeben.
   return Double.isNaN(this.f1) ? null : this.f1;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die übergebene Frequenz des Ständerstroms (in Hz).
 * 
 * @param f1 Die Frequenz des Ständerstroms (in Hz).
 */
public void setF1(Double f1)
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
public Integer getP()
   {
   // Die Polpaarzahl wird zurückgegeben.
   return this.p == Integer.MIN_VALUE ? null : this.p;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die übergebene Polpaarzahl im Datenmodell.
 * 
 * @param p Die Polpaarzahl
 */
public void setP(Integer p)
   {
   // Die übergebene Polpaarzahl wird im Datenmodell gespeichert.
   this.p = p;
   }



// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Schaltungstyp zurück.
 * 
 * @return Der Schaltungstyp
 */
public Schaltungstyp getSchaltungstyp()
   {
   // Der Schaltungstyp wird zurückgegeben.
   return this.schaltungstyp;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert den übergebenen Schaltungstyp im Modell.
 * 
 * @param schaltungstyp Der Schaltungstyp
 */
public void setSchaltungstyp(Schaltungstyp schaltungstyp)
   {
   // Der übergebene Schaltungstyp wird im Modell gespeichert.
   this.schaltungstyp = schaltungstyp;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Repräsentation des Ersatzschaltbilds zurück.
 * 
 * @return Die Repräsentation des Ersatzschaltbilds
 */
public Ersatzschaltbild getErsatzschaltbild()
   {
   // Die Repräsentations des Ersatzschaltbilds wird zurückgegeben.
   return this.ersatzschaltbild;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die Repräsentation des Ersatzschaltbilds im Datenmodell.
 * 
 * @param ersatzschaltbild Die Repräsentation des Ersatzschaltbilds
 */
public void setErsatzschaltbild(Ersatzschaltbild ersatzschaltbild)
   {
   // Die Repräsentation des Ersatzschaltbilds wird im Datenmodell gespeichert.
   this.ersatzschaltbild = ersatzschaltbild;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Eisenverluste (in W) zurück.
 * 
 * @return Die Eisenverluste (in W)
 */
public double getP_Fe()
   {
   // Die Eisenverluste (in W) wird zurückgegeben.
   return this.p_Fe;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die Eisenverluste (in W) im Frontend-Modell.
 * 
 * @param p_Fe Die Eisenverluste (in W)
 */
public void setP_Fe(double p_Fe)
   {
   // Die Eisenverluste (in W) werden im Frontend-Modell gespeichert.
   this.p_Fe = p_Fe;
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
   StringBuilder builder = new StringBuilder("ErsatzschaltbildModell [");
   
   // Die Zeichenkette, welche die Ortskurve repräsentiert, wird hinzugefügt.
   if (this.ortskurve != null)
      {
      builder.append("ortskurve=").append(this.ortskurve).append(", ");
      }
   
   // Die Zeichenkette, welche die Daten der Betriebspunkte repräsentiert, wird hinzugefügt.
   if (this.betriebspunkte != null)
      {
      builder.append("betriebspunkte=").append(this.betriebspunkte).append(", ");
      }
   
   // Die Zeichenkette, welche den Schaltungstyp repräsentiert, wird hinzugefügt.
   if (this.schaltungstyp != null)
      {
      builder.append("schaltungstyp=").append(this.schaltungstyp).append(", ");
      }
   
   // Die Zeichenkette, welche das Ersatzschaltbild repräsentiert, wird hinzugefügt.
   if (this.ersatzschaltbild != null)
      {
      builder.append("ersatzschaltbild=").append(this.ersatzschaltbild).append(", ");
      }
   
   // Die Zeichenkette, welche die Leiter-Leiter-Spannung (in V) repräsentiert, wird hinzugefügt.
   builder.append("u_LL=").append(this.u_LL);
   
   // Die Zeichenkette, welche die Frequenz des Ständerstroms (in Hz) repräsentiert, wird hinzugefügt.
   builder.append(", f1=").append(this.f1);
   
   // Die Zeichenkette, welche die Polpaarzahl repräsentiert, wird hinzugefügt.
   builder.append(", p=").append(this.p).append("]");
   
   // Die Zeichenkette, die die dieses Datenmodell repräsentiert, wird zurückgegeben.
   return builder.toString();
   }
}