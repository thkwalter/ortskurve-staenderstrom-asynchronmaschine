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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import de.thkwalter.et.ortskurve.Ausgleichsproblem;
import de.thkwalter.et.ortskurve.OrtskurveModell;

/**
 * Diese Klasse repräsentiert den Controller der Ersatzschaltbildberechnung.
 *
 * @author Th. K. Walter
 */
@RequestScoped
@ManagedBean
public class ErsatzschaltbildController
{
/*
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(ErsatzschaltbildController.class.getName());

public String datenUebernehmen()
   {
   ErsatzschaltbildController.logger.info("datenUebernehmen()");
   
   FacesContext facesContext = FacesContext.getCurrentInstance();
   Ausgleichsproblem ortskurveController = 
      (Ausgleichsproblem) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{ausgleichsproblem}", Ausgleichsproblem.class);
   
   OrtskurveModell ortskurveModell = 
            (OrtskurveModell) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{ortskurveModell}", OrtskurveModell.class);
        
   
   ErsatzschaltbildController.logger.info("ortskurve x: " + ortskurveModell.getOrtskurve().getRadiusOrtskurve());
   
   return "ersatzschaltbild";
   }
}
