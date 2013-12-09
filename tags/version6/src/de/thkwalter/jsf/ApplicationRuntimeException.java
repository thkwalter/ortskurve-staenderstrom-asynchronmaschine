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
 * Diese Klasse repräsentiert eine Ausnahme, die vom Programm und nicht von einer eingebundenen Bibliothek geworfen wird
 * und behandelt werden kann.
 *
 * @author Th. K. Walter
 */
public class ApplicationRuntimeException extends RuntimeException
{
/**
 * Die Serialisierungs-Id.
 */
private static final long serialVersionUID = 8982555710277296332L;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor erzeugt eine neue Ausnahme.
 * 
 * @param message Der Text der Ausnahme.
 * 
 * @see Exception#Exception(String)
 */
public ApplicationRuntimeException(String message)
   {
   super(message);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor erzeugt eine neue Ausnahme.
 * 
 * @param cause Die Ausnahme, welche die Ursache dieser Ausnahme ist.
 * 
 * @see Exception#Exception(Throwable)
 */
public ApplicationRuntimeException(Throwable cause)
   {
   super(cause);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Dieser Konstruktor erzeugt eine neue Ausnahme.
 * 
 * @param message Der Text der Ausnahme.
 * @param cause Die Ausnahme, welche die Ursache dieser Ausnahme ist.
 * 
 * @see Exception#Exception(String, Throwable)
 */
public ApplicationRuntimeException(String message, Throwable cause)
   {
   super(message, cause);
   }
}
