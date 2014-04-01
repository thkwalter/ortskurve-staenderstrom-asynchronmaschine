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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
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
 * Diese Klasse bestimmt, wie mit nicht gefangenen Ausnahmen umgegangen wird.
 * 
 * @author Th. K. Walter
 */
public class ApplicationExceptionHandler extends ExceptionHandlerWrapper
{
/**
 * Der umwickelte {@link ExceptionHandler}.
 */
private ExceptionHandler wrapped;

/**
 * Der Logger dieser Klasse.
 */
private static Logger logger = Logger.getLogger(ApplicationExceptionHandler.class.getName());

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor initialisiert das Attribut für den umwickelten {@link ExceptionHandler}.
 * 
 * @param wrapped Der {@link ExceptionHandler}, der umwickelt werden soll.
 */
public ApplicationExceptionHandler(ExceptionHandler wrapped)
   {
   this.wrapped = wrapped;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den umwickelten {@link ExceptionHandler} zurück.
 * 
 * @return Der umwickelte {@link ExceptionHandler}.
 */
@Override
public ExceptionHandler getWrapped()
   {
   return this.wrapped;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode behandelt die nicht-gefangenen Ausnahmen.
 */
@Override
public void handle() throws FacesException
   {
   // Ein Iterator auf die Queue der Ausnahmeereignisse wird gelesen.
   Iterator<ExceptionQueuedEvent> itr = this.getUnhandledExceptionQueuedEvents().iterator();
   
   // Der FacesContext und der NavigationHandler werden gelesen.
   FacesContext facesContext = FacesContext.getCurrentInstance();
   NavigationHandler nav = facesContext.getApplication().getNavigationHandler();  
   
   // Einige Referenzen werden deklariert.
   ExceptionQueuedEvent event = null;
   ExceptionQueuedEventContext context = null;
   Throwable throwable = null;
   StringWriter stringWriter = null;
   PrintWriter printWriter = null;
   String stackTrace = null;
   
   // Das aktuelle Datum wird gelesen.
   Date date = new Date();
   
   // Eine Schleife über die Ausnahmeereignisse.
   while (itr.hasNext())
      {
      // Das Ausnahmeereignis und sein Kontext werden gelesen.
      event = itr.next();
      context = (ExceptionQueuedEventContext) event.getSource();
      
      // Die Ausnahme wird gelesen
      throwable = context.getException();

      try
         {
         // Ein PrintWiter, der in einen StringWriter schreibt, wird erzeugt.
         stringWriter = new StringWriter();
         printWriter = new PrintWriter(stringWriter);
         
         // Der StackTrace wird in den PrintWriter geschrieben und dieser gibt sie an den StringWriter weiter.
         throwable.printStackTrace(printWriter);
         
         // Der StackTrace wird aus dem StringWriter gelesen.
         stackTrace = stringWriter.toString();
         
         // Der StackTrace wird protokolliert.
         ApplicationExceptionHandler.logger.severe(throwable.getMessage());
         
         // Eine FacesMessage wird erzeugt und in den FacesContext geschrieben.
         facesContext.addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, date.toString() + ": " + stackTrace, ""));
      
         // Die Kontrolle wird an die Fehlerseite weitergeleitet.
         nav.handleNavigation(facesContext, null, "/pages/fehlerseite.xhtml");
         facesContext.renderResponse();
         }
      
      // Das Ausnahmeereignis wird auf alle Fälle aus der Queue entfernt.
      finally
         {
         itr.remove();
         }   
      }
   
   // Der umwickelte ExceptionHandler behandelt ebenfalls noch die Ausnahmen.
   getWrapped().handle();
   }

}
