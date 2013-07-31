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
package de.thkwalter.jsf;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Diese {@link ExceptionHandlerFactory} liefert den programmspezifischen {@link ExceptionHandler}.
 *
 * @author Th. K. Walter
 */
public class ApplicationExceptionHandlerFactory extends ExceptionHandlerFactory 
{
/**
 * Die umwickelte {@link ExceptionHandlerFactory}.
 */
private ExceptionHandlerFactory parent;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert das Attribut für die umwickelte {@link ExceptionHandlerFactory}.
 * 
 * @param wrapped Die {@link ExceptionHandlerFactory}, die umwickelt werden soll.
 */
public ApplicationExceptionHandlerFactory(ExceptionHandlerFactory parent) 
   {
   this.parent = parent;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den programmspezifischen ExceptionHandler zurück.
 * 
 * @return Der programmspezifische ExceptionHandler.
 */
@Override
public ExceptionHandler getExceptionHandler() 
   {
   // Der programmspezifische ExceptionHandler wird erzeugt, wobei der ExceptionHandler übergeben wird, welchen die 
   // umwickelte ExceptionHandlerFactory zurückgibt.
   return new ApplicationExceptionHandler(parent.getExceptionHandler());
   }
}
