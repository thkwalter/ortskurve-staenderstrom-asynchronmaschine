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
public class BehandelbareAusnahme extends Exception
{
/**
 * Die Serialisierungs-Id.
 */
private static final long serialVersionUID = 8982555710277296332L;

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see Exception#Exception() 
 */
public BehandelbareAusnahme()
   {
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see Exception#Exception(String)
 */
public BehandelbareAusnahme(String message)
   {
   super(message);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see Exception#Exception(Throwable)
 */
public BehandelbareAusnahme(Throwable cause)
   {
   super(cause);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * @see Exception#Exception(String, Throwable)
 */
public BehandelbareAusnahme(String message, Throwable cause)
   {
   super(message, cause);
   }

}
