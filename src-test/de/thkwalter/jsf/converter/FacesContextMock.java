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
package de.thkwalter.jsf.converter;

import java.util.Iterator;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;

/**
 * Diese Klasse ist ein Mock für die Klasse {@link FacesMock}
 *
 * @author Th. K. Walter
 */
public class FacesContextMock extends FacesContext
{
/**
 * @see javax.faces.context.FacesContext#addMessage(java.lang.String, javax.faces.application.FacesMessage)
 */
@Override
public void addMessage(String arg0, FacesMessage arg1)
   {
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#getApplication()
 */
@Override
public Application getApplication()
   {
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#getClientIdsWithMessages()
 */
@Override
public Iterator<String> getClientIdsWithMessages()
   {
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#getExternalContext()
 */
@Override
public ExternalContext getExternalContext()
   {
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#getMaximumSeverity()
 */
@Override
public Severity getMaximumSeverity()
   {
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#getMessages()
 */
@Override
public Iterator<FacesMessage> getMessages()
   {
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#getMessages(java.lang.String)
 */
@Override
public Iterator<FacesMessage> getMessages(String arg0)
   {
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#getRenderKit()
 */
@Override
public RenderKit getRenderKit()
   {
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#getRenderResponse()
 */
@Override
public boolean getRenderResponse()
   {
   return false;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#getResponseComplete()
 */
@Override
public boolean getResponseComplete()
   {
   return false;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#getResponseStream()
 */
@Override
public ResponseStream getResponseStream()
   {
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#getResponseWriter()
 */
@Override
public ResponseWriter getResponseWriter()
   {
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#getViewRoot()
 */
@Override
public UIViewRoot getViewRoot()
   {
   return null;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#release()
 */
@Override
public void release()
   {
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#renderResponse()
 */
@Override
public void renderResponse()
   {
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#responseComplete()
 */
@Override
public void responseComplete()
   {
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#setResponseStream(javax.faces.context.ResponseStream)
 */
@Override
public void setResponseStream(ResponseStream arg0)
   {
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#setResponseWriter(javax.faces.context.ResponseWriter)
 */
@Override
public void setResponseWriter(ResponseWriter arg0)
   {
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see javax.faces.context.FacesContext#setViewRoot(javax.faces.component.UIViewRoot)
 */
@Override
public void setViewRoot(UIViewRoot arg0)
   {
   }
}
