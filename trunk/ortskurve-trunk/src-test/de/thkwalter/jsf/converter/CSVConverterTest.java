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

import static org.junit.Assert.assertEquals;

import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import de.thkwalter.jsf.converter.CSVConverter;

/**
 * Diese Klasse enthält Tests für die Klasse {@link CSVConverter}.
 *
 * @author Th. K. Walter
 */
public class CSVConverterTest
{
/**
 * Das Objekt, das für die Tests verwendet wird.
 */
private CSVConverter csvConverter;

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
   this.csvConverter = new CSVConverter();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link CSVConverter#getAsObject(javax.faces.context.FacesContext, 
 * javax.faces.component.UIComponent, java.lang.String)}.
 */
@Test
public void testGetAsObject1()
   {
   // Die zu erstellende Zeichemkette wird erstellt.
   String eingabe = "1.0,2.0\n3.0,4.0  \n5.0,6.0\n   ";
   
   // Die zu testende Methode wird aufgerufen.
   Vector2D[] vektoren = (Vector2D[]) this.csvConverter.getAsObject(null, null, eingabe);
   
   // Es wird überprüft, ob die Zeichenkette in das korrekte Feld von Vector2D-Objekten konvertiert worden ist.
   assertEquals(3, vektoren.length);
   assertEquals(new Vector2D(1.0, 2.0), vektoren[0]);
   assertEquals(new Vector2D(3.0, 4.0), vektoren[1]);
   assertEquals(new Vector2D(5.0, 6.0), vektoren[2]);   
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link CSVConverter#getAsObject(javax.faces.context.FacesContext, 
 * javax.faces.component.UIComponent, java.lang.String)}.
 */
@Test(expected=ConverterException.class)
public void testGetAsObject2()
   {
   // Die zu erstellende Zeichemkette wird erstellt.
   String eingabe = "1.0,2.0\n3.0a,4.0\n5.0,6.0\n";
   
   // Ein Mock des FacesContext wird erstellt.
   FacesContext facesContextMock = new FacesContextMock();
   
   // Die zu testende Methode wird aufgerufen.
   this.csvConverter.getAsObject(facesContextMock, null, eingabe);  
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link CSVConverter#getAsString(javax.faces.context.FacesContext, 
 * javax.faces.component.UIComponent, java.lang.Object)}.
 */
@Test
public void testGetAsString1()
   {
   // Das zu konvertierende Feld von Vector2D-Objekten wird erstellt.
   Vector2D[] vektoren = {new Vector2D(1.0, 2.0), new Vector2D(3.0, 4.0), new Vector2D(5.0, 6.0)};
   
   // Die zu testende Methode wird aufgerufen.
   String ausgabe = this.csvConverter.getAsString(null, null, vektoren);
   
   // Es wird überprüft, ob das Feld in die korrekte Zeichenkette konvertiert worden ist.
   assertEquals("1.0,2.0\n3.0,4.0\n5.0,6.0\n", ausgabe);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link CSVConverter#getAsString(javax.faces.context.FacesContext, 
 * javax.faces.component.UIComponent, java.lang.Object)}.
 */
@Test(expected=ConverterException.class)
public void testGetAsString2()
   {   
   // Die zu testende Methode wird aufgerufen.
   this.csvConverter.getAsString(null, null, new String());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link CSVConverter#getAsString(javax.faces.context.FacesContext, 
 * javax.faces.component.UIComponent, java.lang.Object)}.
 */
@Test
public void testGetAsString3()
   {   
   // Die zu testende Methode wird aufgerufen.
   String ausgabe = this.csvConverter.getAsString(null, null, null);
   
   // Es wird überprüft, ob ein leerer String zurückgegeben wird, wenn das zu konvertierende Feld null ist.
   assertEquals("", ausgabe);
   }
}
