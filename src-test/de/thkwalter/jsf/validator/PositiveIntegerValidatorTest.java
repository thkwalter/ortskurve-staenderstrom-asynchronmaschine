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
package de.thkwalter.jsf.validator;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link PositiveIntegerValidator}.
 * 
 * @author Th. K. Walter
 */
public class PositiveIntegerValidatorTest
{
/** 
 * Das Objekt, das für die Tests verwendet wird.
 */
private PositiveIntegerValidator positiveIntegerValidator;

/**
 * Die in den Tests verwendete UI-Komponente.
 */
private UIInput testUIInput;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Methode initialisiert die Tests.
 * 
 * @throws java.lang.Exception
 */
@Before
public void setUp() throws Exception
   {
   // Das Objekt, das für die Tests verwendet wird, wird erzeugt.
   this.positiveIntegerValidator = new PositiveIntegerValidator();
   
   // Die in den Tests verwendete UI-Komponente wird initialisiert.
   this.testUIInput = new UIInput();
   this.testUIInput.getAttributes().put("label", "Polpaarzahl");
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link PositiveIntegerValidator#validate(FacesContext, UIComponent, Object)}.
 */
@Test
public void testValidate1()
   {
   // Die zu testende Methode wird aufgerufen.
   this.positiveIntegerValidator.validate(null, null, new Integer(3));
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link PositiveIntegerValidator#validate(FacesContext, UIComponent, Object)}.
 */
@Test(expected=ValidatorException.class)
public void testValidate2()
   {
   // Die zu testende Methode wird aufgerufen.
   this.positiveIntegerValidator.validate(null, this.testUIInput, new Integer(0));
   }
}
