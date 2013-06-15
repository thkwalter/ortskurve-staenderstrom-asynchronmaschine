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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Diese Klasse sucht mit Hilfe der Methoden der nichtlinearen Ausgleichsrechnung den Mittelpunkt und den Radius zu 
 * einer vorgegebenen Menge von Punkten.
 *
 * @author Th. Walter
 */
@RequestScoped
@ManagedBean
public class Ausgleichsproblem
{
private static Logger logger = Logger.getLogger(Ausgleichsproblem.class.getName());

// =====================================================================================================================
// =====================================================================================================================

public String problemLoesen()
   {
   Ausgleichsproblem.logger.entering("Ausgleichsproblem", "problemLoesen");
   
   System.out.println("Hallo!");
   
   return null;
   }
}
