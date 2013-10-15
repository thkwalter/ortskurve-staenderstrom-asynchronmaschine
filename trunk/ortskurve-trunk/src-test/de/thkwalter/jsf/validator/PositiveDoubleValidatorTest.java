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
 * Diese Klasse enthält Tests für die Klasse {@link PositiveDoubleValidator}.
 * 
 * @author Th. K. Walter
 */
public class PositiveDoubleValidatorTest
{
/** 
 * Das Objekt, das für die Tests verwendet wird.
 */
private PositiveDoubleValidator positiveDoubleValidator;

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
   this.positiveDoubleValidator = new PositiveDoubleValidator();
   
   // Die in den Tests verwendete UI-Komponente wird initialisiert.
   this.testUIInput = new UIInput();
   this.testUIInput.getAttributes().put("label", "Netzspannung");
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link PositiveDoubleValidator#validate(FacesContext, UIComponent, Object)}.
 */
@Test
public void testValidate1()
   {
   // Die zu testende Methode wird aufgerufen.
   this.positiveDoubleValidator.validate(null, null, new Double(5.0));
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link PositiveDoubleValidator#validate(FacesContext, UIComponent, Object)}.
 */
@Test(expected=ValidatorException.class)
public void testValidate2()
   {
   // Die zu testende Methode wird aufgerufen.
   this.positiveDoubleValidator.validate(null, this.testUIInput, new Double(-3.0));
   }
}
