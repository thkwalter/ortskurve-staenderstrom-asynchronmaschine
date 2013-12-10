package de.thkwalter.jsf;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Diese Klasse bewirkt, dass das Session-Management in der GAE funktioniert. Diese Klasse wurde erstellt anhand des 
 * Artikels http://stackoverflow.com/questions/19259457/session-lost-in-google-app-engine-using-jsf
 * 
 * @author Th. K. Walter
 */
public class GaeSession implements PhaseListener 
{
/**
 * Die Serialisierungs-Id
 */
private static final long serialVersionUID = 1L;

// =====================================================================================================================
// =====================================================================================================================

@Override
public void afterPhase(PhaseEvent arg0) 
   {
   FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CURRENT_TIME", 
      System.currentTimeMillis());
   }

// =====================================================================================================================
// =====================================================================================================================

@Override
public void beforePhase(PhaseEvent arg0) 
   {
   }

// =====================================================================================================================
// =====================================================================================================================

@Override
public PhaseId getPhaseId() 
   {
   return PhaseId.ANY_PHASE;
   }
}
