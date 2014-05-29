/**
 *  Copyright 2014 Th. K. Walter, Nürnberg.
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
package de.thkwalter.et.schlupfbezifferung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.thkwalter.et.ortskurve.Ortskurve;
import de.thkwalter.et.ortskurve.OrtskurveModell;

/**
 * Diese Klasse ist das Datenmodell der Schlupfbezifferungsbestimmung.
 * 
 * @author Th. K. Walter
 */
@SessionScoped
@ManagedBean(name="schlupfbezifferungModell")
public class SchlupfbezifferungModell implements Serializable
{
/**
 * Die Serialisierungs-ID.
 */
private static final long serialVersionUID = -5355873972003968449L;

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger("SchlupfbezifferungModell.class");

//---------------------------------------------------------------------------------------------------------------------

/**
 * Die Ortskurve
 */
private Ortskurve ortskurve;

/**
 * Die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden.
 */
private Betriebspunkt[] betriebspunkte;

// ---------------------------------------------------------------------------------------------------------------------

/**
 * Der Inversionszentrum (in A)
 */
private Vector2D inversionszentrum;

/**
 * Der Drehpunkt der Schlupfgerade (in A)
 */
private Vector2D drehpunktSchlupfgerade;

/**
 * Der Steigungswinkel der Schlupfgeraden
 */
private double phi;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode übernimmt die benötigten Daten des Datenmodells der Ortskurvenberechnung in dieses Datenmodell.
 * 
 * @param ortskurveModell Das Datenmodell der Ortskurvenberechnung
 */
public void datenUebernehmen(OrtskurveModell ortskurveModell)
   {   
   // Die Ortskurve wird in das Datenmodell des Ersatzschaltbildberechnung übertragen.
   this.ortskurve = ortskurveModell.getOrtskurve();
   
//   // Die Messpunkte werden in das Datenmodell der Ersatzschaltbildberechnung übertragen.
//   Vector2D[] messpunkte = ortskurveModell.getMesspunkte();
//   
//   // Für jeden Messpunkt wird ein Betriebspunkt hinzugefügt.
//   this.betriebspunkte = new Betriebspunkt[messpunkte.length];
//   for (int i = 0; i < messpunkte.length; i++)
//      {
//      this.betriebspunkte[i] = new Betriebspunkt(new Vector2D(messpunkt.getY(), -messpunkt.getX()));
//      }
   
   // Der Zustand des Datenmodells nach der Datenübernahme wird protokolliert.
   SchlupfbezifferungModell.logger.info(this.toString());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt das Inversionszentrum (in A) zurück.
 * 
 * @return Das Inversionszentrum (in A)
 */
public Vector2D getInversionszentrum()
   {
   // Das Inversionszentrum (in A) wird zurückgegeben.
   return this.inversionszentrum;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert das Inversionszenrum (in A) in diesem Datenmodell.
 * 
 * @param inversionszentrum Das Inversionszentrum (in A)
 */
public void setInversionszentrum(Vector2D inversionszentrum)
   {
   // Das Inversionszentrum (in A) wird in diesem Datenmodell gespeichert.
   this.inversionszentrum = inversionszentrum;
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
 * Diese Methode speichert die Ortskurve in diesem Datenmodell.
 * 
 * @param ortskurve Die Ortskurve
 */
public void setOrtskurve(Ortskurve ortskurve)
   {
   // Die Ortskurve wird in diesem Datenmodell gespeichert.
   this.ortskurve = ortskurve;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Drehpunkt der Schlupfgeraden (in A) zurück.
 * 
 * @return Der Drehpunkt der Schlupfgeraden (in A).
 */
public Vector2D getDrehpunktSchlupfgerade()
   {
   // Der Drehpunkt der Schlupfgeraden (in A) wird zurückgegeben.
   return this.drehpunktSchlupfgerade;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert den Drehpunkt der Schlupfgeraden (in A) in diesem Datenmodell.
 * 
 * @param drehpunktSchlupfgerade Der Drehpunkt der Schlupfgeraden (in A).
 */
public void setDrehpunktSchlupfgerade(Vector2D drehpunktSchlupfgerade)
   {
   // Der Drehpunkt der Schlupfgeraden (in A) wird in diesem Datenmodell gespeichert.
   this.drehpunktSchlupfgerade = drehpunktSchlupfgerade;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt die Betriebspunkte zurück, die zur Bestimmung der Schlupfbezifferung verwendet werden.
 * 
 * @return Die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden.
 */
public Betriebspunkt[] getBetriebspunkte()
   {
   // Die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden, werden zurückgegeben.
   return this.betriebspunkte;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden, in diesem
 * Datenmodell.
 * 
 * @param betriebspunkte Die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden.
 */
public void setBetriebspunkte(Betriebspunkt[] betriebspunkte)
   {
   // Die Betriebspunkte, die zur Bestimmung der Schlupfbezifferung verwendet werden, werden in diesem Datenmodell
   // gespeichert.
   this.betriebspunkte = betriebspunkte;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Steigungswinkel der Schlupfgeraden zurück.
 * 
 * @return Der Steigungswinkel der Schlupfgeraden 
 */
public double getPhi()
   {
   // Der Steigungswinkel der Schlupfgeraden wird zurückgegeben.
   return this.phi;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert den Steigungswinkel der Schlupfgeraden in diesem Datenmodell.
 * 
 * @param phi Der Steigungswinkel der Schlupfgeraden 
 */
public void setPhi(double phi)
   {
   // Der Steigungswinkel der Schlupfgeraden wird in diesem Datenmodell gespeichert.
   this.phi = phi;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
   {
   // Der Zustand wird zurückgegeben.
   return "SchlupfbezifferungModell [" + 
      (ortskurve != null ? "ortskurve=" + ortskurve + ", " : "") + 
      (betriebspunkte != null ? "betriebspunkte=" + Arrays.toString(betriebspunkte) + ", " : "") + 
      (inversionszentrum != null ? "inversionszentrum=" + inversionszentrum + ", " : "") + 
      (drehpunktSchlupfgerade != null ? "drehpunktSchlupfgerade="+ drehpunktSchlupfgerade + ", " : "")  + 
      "phi=" + phi + "]";
   }
}
