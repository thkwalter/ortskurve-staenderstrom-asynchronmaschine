/**
 *  Copyright 2014 Th. K. Walter, Nürnberg.
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

import static org.junit.Assert.assertEquals;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthält Tests für die Klasse {@link ZeroNaNConverter}.
 * 
 * @author Th. K. Walter
 */
public class ZeroNaNConverterTest
{
/**
 * Der Prüfling der Klasse {@link ZeroNaNConverter}.
 */
private ZeroNaNConverter zeroNaNConverter;

// =====================================================================================================================
// =====================================================================================================================

/**
 * Diese Klasse initialisiert die Tests.
 * 
 * @throws java.lang.Exception
 */
@Before
public void setUp() throws Exception
   {
   // Der Prüfling wird erzeugt.
   this.zeroNaNConverter = new ZeroNaNConverter();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link ZeroNaNConverter#getAsObject(FacesContext, UIComponent, String)} für den Fall, dass die 
 * Zeichenkette <tt>null</tt> ist.
 */
@Test
public void testGetAsObjectNull()
   {
   // Die zu testende Methode wird ausgeführt.
   Object doubleValue = this.zeroNaNConverter.getAsObject(null, null, null);
   
   // Es wird überprüft, ob eine leere Zeichenkette zurückgegeben wird.
   assertEquals(Double.NaN, doubleValue);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link ZeroNaNConverter#getAsObject(FacesContext, UIComponent, String)} für den Fall, dass die 
 * Zeichenkette leer ist.
 */
@Test
public void testGetAsObjectLeer()
   {
   // Die zu testende Methode wird ausgeführt.
   Object doubleValue = this.zeroNaNConverter.getAsObject(null, null, "");
   
   // Es wird überprüft, ob eine leere Zeichenkette zurückgegeben wird.
   assertEquals(Double.NaN, doubleValue);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link ZeroNaNConverter#getAsObject(FacesContext, UIComponent, String)} für den Fall, dass die 
 * Zeichenkette eine reelle Zahl repräsentiert.
 */
@Test
public void testGetAsObjectDouble()
   {
   // Die zu testende Methode wird ausgeführt.
   Object doubleValue = this.zeroNaNConverter.getAsObject(null, null, "17.8");
   
   // Es wird überprüft, ob eine leere Zeichenkette zurückgegeben wird.
   assertEquals(17.8, (double) doubleValue, 0.0);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link ZeroNaNConverter#getAsObject(FacesContext, UIComponent, String)} für den Fall, dass die 
 * Zeichenkette keine reelle Zahl repräsentiert.
 */
@Test(expected = ConverterException.class)
public void testGetAsObjectKeinDouble()
   {
   // Ein Mock des FacesContext wird erstellt.
   FacesContext facesContextMock = new FacesContextMock();
   
   // Die zu testende Methode wird ausgeführt.
   this.zeroNaNConverter.getAsObject(facesContextMock, null, "abc");
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link ZeroNaNConverter#getAsString(FacesContext, UIComponent, Object)} für den Fall, dass der 
 * {@link Double}--Wert <tt>null</tt> ist.
 */
@Test
public void testGetAsStringNull()
   {
   // Die zu testende Methode wird ausgeführt.
   String doubleString = this.zeroNaNConverter.getAsString(null, null, Double.NaN);
   
   // Es wird überprüft, ob eine leere Zeichenkette zurückgegeben wird.
   assertEquals("", doubleString);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test der Methode {@link ZeroNaNConverter#getAsString(FacesContext, UIComponent, Object)} für den Fall, dass der 
 * {@link Double}--Wert nicht <tt>null</tt> ist.
 */
@Test
public void testGetAsStringNotNull()
   {
   // Die zu testende Methode wird ausgeführt.
   String doubleString = this.zeroNaNConverter.getAsString(null, null, new Double(17.8));
   
   // Es wird überprüft, ob eine leere Zeichenkette zurückgegeben wird.
   assertEquals("17.8", doubleString);
   }
}
