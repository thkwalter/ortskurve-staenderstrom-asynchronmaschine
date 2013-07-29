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

import java.util.Iterator;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 * 
 * 
 * @author Th. K. Walter
 */
public class ETExceptionHandler extends ExceptionHandlerWrapper
{
/**
 * 
 */
private ExceptionHandler wrapped;

/**
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(ETExceptionHandler.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * 
 */
public ETExceptionHandler(ExceptionHandler wrapped)
   {
   this.wrapped = wrapped;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 *
 */
@Override
public ExceptionHandler getWrapped()
   {
   return this.wrapped;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 *
 */
@Override
public void handle() throws FacesException
   {
   Iterator<ExceptionQueuedEvent> itr = getUnhandledExceptionQueuedEvents().iterator();
   
   while (itr.hasNext())
      {
      ExceptionQueuedEvent event = itr.next();
      ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
      Throwable thr = context.getException();

      ETExceptionHandler.logger.severe("OK, der ExceptionHandler arbeitet");
      }
   getWrapped().handle();
   }

}
