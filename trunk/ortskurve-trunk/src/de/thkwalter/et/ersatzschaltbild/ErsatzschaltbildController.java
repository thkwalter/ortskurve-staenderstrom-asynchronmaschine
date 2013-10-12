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

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Diese Klasse repräsentiert den Controller der Ersatzschaltbildberechnung.
 *
 * @author Th. K. Walter
 */
@RequestScoped
@ManagedBean
public class ErsatzschaltbildController
{
/**
 * Das Datenmodell der Ersatzschaltbildberechnung
 */
@ManagedProperty(value="#{ersatzschaltbildModell}")
private ErsatzschaltbildModell ersatzschaltbildModell;

/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(ErsatzschaltbildController.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode speichert das übergebene Datenmodell des Ersatzschaltbildes in diesem Controller.
 * 
 * @param ersatzschaltbildModell Das Datenmodell des Ersatzschaltbildes
 */
public void setErsatzschaltbildModell(ErsatzschaltbildModell ersatzschaltbildModell)
   {
   // Das Datenmodell der Ortskurve wird gespeichert.
   this.ersatzschaltbildModell = ersatzschaltbildModell;
   }
}