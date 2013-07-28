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

/**
 * Diese Klasse repräsentiert eine Ausnahme, die im Programm behandelbar ist.
 *
 * @author Th. K. Walter
 */
public class JSFAusnahme extends Exception
{
/**
 * Der Text zur Erzeugung einer JSF-Meldung.
 */
private String jsfMeldungstext;

/**
 * Die Serialisierungs-Id.
 */
private static final long serialVersionUID = 8982555710277296332L;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor erzeugt eine neue Ausnahme.
 * 
 * @param jsfMeldungstext Der Text zur Erzeugung einer JSF-Meldung.
 * 
 * @see Exception#Exception() 
 */
public JSFAusnahme(String jsfMeldungstext)
   {
   this.jsfMeldungstext = jsfMeldungstext;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor erzeugt eine neue Ausnahme.
 * 
 * @param message Der Text der Ausnahme.
 * @param jsfMeldungstext Der Text zur Erzeugung einer JSF-Meldung.
 * 
 * @see Exception#Exception(String)
 */
public JSFAusnahme(String message, String jsfMeldungstext)
   {
   super(message);
   this.jsfMeldungstext = jsfMeldungstext;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor erzeugt eine neue Ausnahme.
 * 
 * @param cause Die Ausnahme, welche die Ursache dieser Ausnahme ist.
 * @param jsfMeldungstext Der Text zur Erzeugung einer JSF-Meldung.
 * 
 * @see Exception#Exception(Throwable)
 */
public JSFAusnahme(Throwable cause, String jsfMeldungstext)
   {
   super(cause);
   this.jsfMeldungstext = jsfMeldungstext;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor erzeugt eine neue Ausnahme.
 * 
 * @param message Der Text der Ausnahme.
 * @param cause Die Ausnahme, welche die Ursache dieser Ausnahme ist.
 * @param jsfMeldungstext Der Text zur Erzeugung einer JSF-Meldung.
 * 
 * @see Exception#Exception(String, Throwable)
 */
public JSFAusnahme(String message, Throwable cause, String jsfMeldungstext)
   {
   super(message, cause);
   this.jsfMeldungstext = jsfMeldungstext;
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode gibt den Text zur Erzeugung einer JSF-Meldung zurück.
 * 
 * @return Der Text zur Erzeugung einer JSF-Meldung.
 */
public String getJSFMeldungstext()
   {
   return this.jsfMeldungstext;
   }
}
