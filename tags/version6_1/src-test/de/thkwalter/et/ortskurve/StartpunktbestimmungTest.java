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
package de.thkwalter.et.ortskurve;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Test;

import de.thkwalter.jsf.ApplicationRuntimeException;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Startpunktbestimmung}.
 *
 * @author Th. K. Walter
 */
public class StartpunktbestimmungTest
{
/**
 * Test für den Konstruktor {@link Startpunktbestimmung#Startpunktbestimmung(Vector2D[])}.
 * 
 * @throws ApplicationRuntimeException 
 */
@Test(expected=ApplicationRuntimeException.class)
public void testStartpunktbestimmung1() throws ApplicationRuntimeException
   {   
   // Es wird überprüft, ob der Konstruktor eine Ausnahme wirft, wenn null übergeben wird.
   Startpunktbestimmung.startpunktBerechnen(null);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link Startpunktbestimmung#Startpunktbestimmung(Vector2D[])}.
 * 
 * @throws ApplicationRuntimeException 
 */
@Test(expected=ApplicationRuntimeException.class)
public void testStartpunktbestimmung2() throws ApplicationRuntimeException
   {   
   // Es wird überprüft, ob der Konstruktor eine Ausnahme wirft, wenn weniger als drei Messpunkte übergeben werden.
   Startpunktbestimmung.startpunktBerechnen(new Vector2D[]{new Vector2D(0.0, 0.0), new Vector2D(1.0, 1.0)});
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link Startpunktbestimmung#Startpunktbestimmung(Vector2D[])}.
 */
@Test
public void testStartpunktbestimmung3() 
   {
   // Die Startpunktbestimmung wird mit genau drei Messpunkten durchgeführt.
   double[] startparameter = Startpunktbestimmung.startpunktBerechnen(new Vector2D[]{new Vector2D(0.0, 0.0), 
      new Vector2D(2.0, 2.0), new Vector2D(4.0, 0.0)});
   
   // Es wird überprüft, ob der Startpunkt korrekt bestimmt worden ist.
   assertEquals(2.0, startparameter[0], 2.0/1000);
   assertEquals(0.0, startparameter[1], 2.0/1000);
   assertEquals(2.0, startparameter[2], 2.0/1000); 
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für den Konstruktor {@link Startpunktbestimmung#Startpunktbestimmung(Vector2D[])}.
 */
@Test
public void testStartpunktbestimmung4() 
   {
   // Die Startpunktbestimmung wird mit genau drei Messpunkten durchgeführt.
   double[] startparameter = Startpunktbestimmung.startpunktBerechnen(new Vector2D[]{new Vector2D(0.0, 0.0), 
      new Vector2D(2.0, 2.0), new Vector2D(4.0, 0.0), new Vector2D(1.0, -1.0)});
   
   // Es wird überprüft, ob der Startpunkt korrekt bestimmt worden ist.
   assertEquals(2.0, startparameter[0], 2.0/1000);
   assertEquals(0.0, startparameter[1], 2.0/1000);
   assertEquals(2.0, startparameter[2], 2.0/1000); 
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#startpunktBestimmen(Vector2D[])}.
 * 
 * @throws ApplicationRuntimeException 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testStartpunktBestimmen1() throws ApplicationRuntimeException, SecurityException, NoSuchMethodException, 
   IllegalArgumentException, IllegalAccessException, InvocationTargetException
   {
   Vector2D[] messpunkteZurStartpunktbestimmung = 
      new Vector2D[]{new Vector2D(0.0, 0.0), new Vector2D(2.0, 2.0), new Vector2D(4.0, 0.0)};
   
   // Die zu testende Methode wird aufgerufen
   Method methode = 
      Startpunktbestimmung.class.getDeclaredMethod("startpunktBestimmen", Vector2D[].class);
   methode.setAccessible(true);
   double[] startpunkt = 
      (double[]) methode.invoke(Startpunktbestimmung.class, (Object) messpunkteZurStartpunktbestimmung);
   
   assertEquals(2.0, startpunkt[0], 2.0/1000);
   assertEquals(0.0, startpunkt[1], 2.0/1000);
   assertEquals(2.0, startpunkt[2], 2.0/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#startpunktBestimmen()}.
 * 
 * @throws ApplicationRuntimeException 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test(expected=ApplicationRuntimeException.class)
public void testStartpunktBestimmen2() throws Throwable
   {   
   Vector2D[] messpunkteZurStartpunktbestimmung = 
      new Vector2D[]{new Vector2D(1.0, 0.0), new Vector2D(2.0, 0.0), new Vector2D(3.0, 0.0)};
         
   // Die zu testende Methode wird aufgerufen
   Method methode = 
      Startpunktbestimmung.class.getDeclaredMethod("startpunktBestimmen", Vector2D[].class);
   methode.setAccessible(true);
   try
      {
      methode.invoke(Startpunktbestimmung.class, (Object) messpunkteZurStartpunktbestimmung);
      }
   catch (InvocationTargetException invocationTargetException)
      {
      throw invocationTargetException.getCause();
      }
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#messpunkteAuswaehlen(Vector2D[])}.
 * 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 * @throws NoSuchFieldException 
 */
@Test
public void testMesspunkteAuswaehlen1() throws SecurityException, NoSuchMethodException, 
   IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException
   {
   // Die Messpunkte, die für den Test verwendet werden.
   Vector2D[] messpunkte = 
      new Vector2D[]{new Vector2D(1.0, 1.0), new Vector2D(1.0, -1.0), new Vector2D(0.0, -0.1), new Vector2D(0.0, -0.2)};
   
   // Die zu testende Methode wird aufgerufen
   Method methode = 
      Startpunktbestimmung.class.getDeclaredMethod("messpunkteAuswaehlen", Vector2D[].class);
   methode.setAccessible(true);
   Vector2D[] messpunkteZurStartpunktbestimmung = 
      (Vector2D[]) methode.invoke(Startpunktbestimmung.class, (Object) messpunkte);
   
   // Es wird geprüft, ob die korrekten Messpunkt gefunden worden sind.
   assertEquals(messpunkte[0], messpunkteZurStartpunktbestimmung[0]);
   assertEquals(messpunkte[1], messpunkteZurStartpunktbestimmung[1]);
   assertEquals(messpunkte[2], messpunkteZurStartpunktbestimmung[2]);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#messpunkteAuswaehlen(Vector2D[])}.
 * 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 * @throws NoSuchFieldException 
 */
@Test
public void testMesspunkteAuswaehlen2() throws SecurityException, NoSuchMethodException, 
   IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException
   {
   // Die Messpunkte, die für den Test verwendet werden.
   Vector2D[] messpunkte = 
      new Vector2D[]{new Vector2D(1.1, 1.0), new Vector2D(0.0, 0.0), new Vector2D(2.0, 0.0), new Vector2D(1.2, 1.0)};
   
   // Die zu testende Methode wird aufgerufen
   Method methode = 
      Startpunktbestimmung.class.getDeclaredMethod("messpunkteAuswaehlen", Vector2D[].class);
   methode.setAccessible(true);
   Vector2D[] messpunkteZurStartpunktbestimmung = 
      (Vector2D[]) methode.invoke(Startpunktbestimmung.class, (Object) messpunkte);
   
   // Es wird geprüft, ob die korrekten Messpunkt gefunden worden sind.
   assertEquals(messpunkte[2], messpunkteZurStartpunktbestimmung[0]);
   assertEquals(messpunkte[1], messpunkteZurStartpunktbestimmung[1]);
   assertEquals(messpunkte[0], messpunkteZurStartpunktbestimmung[2]);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#mittlerenMesspunktBestimmen(ArrayList, double)}.
 * 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 * @throws NoSuchFieldException 
 */
@Test
public void testMittlerenMesspunktBestimmen1() throws SecurityException, NoSuchMethodException, 
   IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException
   {
   // Die Testdaten werden erzeugt.
   Vector2D messpunkt = new Vector2D(0.0, 0.1);
   ArrayList<YKomponenteMesspunkt> yListe = new ArrayList<YKomponenteMesspunkt>();
   yListe.add(new YKomponenteMesspunkt(new Vector2D(1.0, 1.0)));
   yListe.add(new YKomponenteMesspunkt(new Vector2D(1.0, -1.0)));
   yListe.add(new YKomponenteMesspunkt(messpunkt));
   
   // Die zu testende Methode wird aufgerufen
   Method methode = 
      Startpunktbestimmung.class.getDeclaredMethod("mittlerenMesspunktBestimmen", ArrayList.class, double.class);
   methode.setAccessible(true);
   Vector2D mittlererMesspunkt = (Vector2D) methode.invoke(Startpunktbestimmung.class, yListe, 0.0);
   
   // Es wird geprüft, ob der korrekte Messpunkt gefunden worden ist.
   assertEquals(messpunkt, mittlererMesspunkt);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#mittlerenMesspunktBestimmen(ArrayList, double)}.
 * 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 * @throws NoSuchFieldException 
 */
@Test
public void testMittlerenMesspunktBestimmen2() throws SecurityException, NoSuchMethodException, 
   IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException
   {
   // Die Testdaten werden erzeugt.
   Vector2D messpunkt = new Vector2D(1.9, 0.0);
   ArrayList<XKomponenteMesspunkt> xListe = new ArrayList<XKomponenteMesspunkt>();
   xListe.add(new XKomponenteMesspunkt(new Vector2D(1.0, 0.0)));
   xListe.add(new XKomponenteMesspunkt(new Vector2D(3.0, 0.0)));
   xListe.add(new XKomponenteMesspunkt(messpunkt));
   
   // Die zu testende Methode wird aufgerufen
   Method methode = 
      Startpunktbestimmung.class.getDeclaredMethod("mittlerenMesspunktBestimmen", ArrayList.class, double.class);
   methode.setAccessible(true);
   Vector2D mittlererMesspunkt = (Vector2D) methode.invoke(Startpunktbestimmung.class, xListe, 2.0);
   
   // Es wird geprüft, ob der korrekte Messpunkt gefunden worden ist.
   assertEquals(messpunkt, mittlererMesspunkt);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#maxMesspunktBestimmen(ArrayList)}.
 * 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testMaxMesspunktBestimmen1() throws SecurityException, NoSuchMethodException, IllegalArgumentException, 
   IllegalAccessException, InvocationTargetException 
   {
   // Die Testdaten werden erzeugt.
   Vector2D messpunkt1 = new Vector2D(1.0, 0.0);
   Vector2D messpunkt2 = new Vector2D(2.0, 0.0);
   ArrayList<XKomponenteMesspunkt> xListe = new ArrayList<XKomponenteMesspunkt>();
   xListe.add(new XKomponenteMesspunkt(messpunkt1));
   xListe.add(new XKomponenteMesspunkt(messpunkt2));
   
   // Die zu testende Methode wird aufgerufen
   Method methode = Startpunktbestimmung.class.getDeclaredMethod("maxMesspunktBestimmen", ArrayList.class);
   methode.setAccessible(true);
   Vector2D maxXMesspunkt = (Vector2D) methode.invoke(Startpunktbestimmung.class, xListe);
   
   // Es wird überprüft, ob der korrekte Messpunkt zurückgegeben wird.
   assertEquals(messpunkt2, maxXMesspunkt);
   
   // Es wird überprüft, ob der korrekte Messpunkt aus der Liste entfernt worden ist.
   assertEquals(1, xListe.size());
   assertEquals(messpunkt1, xListe.get(0).getMesspunkt());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#maxMesspunktBestimmen(ArrayList)}.
 * 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testMaxMesspunktBestimmen2() throws SecurityException, NoSuchMethodException, IllegalArgumentException, 
   IllegalAccessException, InvocationTargetException 
   {
   // Die Testdaten werden erzeugt.
   Vector2D messpunkt1 = new Vector2D(0.0, 2.0);
   Vector2D messpunkt2 = new Vector2D(0.0, 1.0);
   ArrayList<YKomponenteMesspunkt> yListe = new ArrayList<YKomponenteMesspunkt>();
   yListe.add(new YKomponenteMesspunkt(messpunkt1));
   yListe.add(new YKomponenteMesspunkt(messpunkt2));
   
   // Die zu testende Methode wird aufgerufen
   Method methode = Startpunktbestimmung.class.getDeclaredMethod("maxMesspunktBestimmen", ArrayList.class);
   methode.setAccessible(true);
   Vector2D maxYMesspunkt = (Vector2D) methode.invoke(Startpunktbestimmung.class, yListe);
   
   // Es wird überprüft, ob der korrekte Messpunkt zurückgegeben wird.
   assertEquals(messpunkt1, maxYMesspunkt);
   
   // Es wird überprüft, ob der korrekte Messpunkt aus der Liste entfernt worden ist.
   assertEquals(1, yListe.size());
   assertEquals(messpunkt2, yListe.get(0).getMesspunkt());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#minMesspunktBestimmen(ArrayList)}.
 * 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testMinMesspunktBestimmen1() throws SecurityException, NoSuchMethodException, IllegalArgumentException, 
   IllegalAccessException, InvocationTargetException 
   {
   // Die Testdaten werden erzeugt.
   Vector2D messpunkt1 = new Vector2D(1.0, 0.0);
   Vector2D messpunkt2 = new Vector2D(2.0, 0.0);
   ArrayList<XKomponenteMesspunkt> xListe = new ArrayList<XKomponenteMesspunkt>();
   xListe.add(new XKomponenteMesspunkt(messpunkt1));
   xListe.add(new XKomponenteMesspunkt(messpunkt2));
   
   // Die zu testende Methode wird aufgerufen
   Method methode = Startpunktbestimmung.class.getDeclaredMethod("minMesspunktBestimmen", ArrayList.class);
   methode.setAccessible(true);
   Vector2D minXMesspunkt = (Vector2D) methode.invoke(Startpunktbestimmung.class, xListe);
   
   // Es wird überprüft, ob der korrekte Messpunkt zurückgegeben wird.
   assertEquals(messpunkt1, minXMesspunkt);
   
   // Es wird überprüft, ob der korrekte Messpunkt aus der Liste entfernt worden ist.
   assertEquals(1, xListe.size());
   assertEquals(messpunkt2, xListe.get(0).getMesspunkt());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Startpunktbestimmung#minMesspunktBestimmen(ArrayList)}.
 * 
 * @throws NoSuchMethodException 
 * @throws SecurityException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testMinMesspunktBestimmen2() throws SecurityException, NoSuchMethodException, IllegalArgumentException, 
   IllegalAccessException, InvocationTargetException 
   {
   // Die Testdaten werden erzeugt.
   Vector2D messpunkt1 = new Vector2D(0.0, 2.0);
   Vector2D messpunkt2 = new Vector2D(0.0, 1.0);
   ArrayList<YKomponenteMesspunkt> yListe = new ArrayList<YKomponenteMesspunkt>();
   yListe.add(new YKomponenteMesspunkt(messpunkt1));
   yListe.add(new YKomponenteMesspunkt(messpunkt2));
   
   // Die zu testende Methode wird aufgerufen
   Method methode = Startpunktbestimmung.class.getDeclaredMethod("minMesspunktBestimmen", ArrayList.class);
   methode.setAccessible(true);
   Vector2D minYMesspunkt = (Vector2D) methode.invoke(Startpunktbestimmung.class, yListe);
   
   // Es wird überprüft, ob der korrekte Messpunkt zurückgegeben wird.
   assertEquals(messpunkt2, minYMesspunkt);
   
   // Es wird überprüft, ob der korrekte Messpunkt aus der Liste entfernt worden ist.
   assertEquals(1, yListe.size());
   assertEquals(messpunkt1, yListe.get(0).getMesspunkt());
   }

}