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
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import de.thkwalter.koordinatensystem.Achsendimensionierung;
import de.thkwalter.koordinatensystem.Koordinatenachsen;
import de.thkwalter.koordinatensystem.PunktPixelKonverter;
import de.thkwalter.koordinatensystem.Wertebereich;

/**
 * Diese Klasse enthält Tests für die Klasse {@link Ausgleichsproblem}.
 *
 * @author Th. K. Walter
 */
public class AusgleichsproblemTest
{
/**
 * Ein Objekt der zu testenden Klasse.
 */
private Ausgleichsproblem ausgleichsproblem;

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
   // Das Objekt der zu testenden Klasse wird erzeugt.
   this.ausgleichsproblem = new Ausgleichsproblem();
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#problemLoesen()}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testProblemLoesen1() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Das Feld der Messpunkte wird initialisiert.
   this.ausgleichsproblem.setMesspunkte(new Vector2D[]{new Vector2D(0.1, 0.0), new Vector2D(2.0, 2.1), 
      new Vector2D(3.9, 0.0), new Vector2D(2.0, -2.1)});
   
   // Die zu testende Methode wird ausgeführt.
   this.ausgleichsproblem.problemLoesen();
   
   // Die berechnete x-Komponente des Mittelpunkts wird überprüft.
   Field mxAttribut = Ausgleichsproblem.class.getDeclaredField("mx");
   mxAttribut.setAccessible(true);
   assertEquals(2.0, mxAttribut.getDouble(this.ausgleichsproblem), 2.0/1000);
   
   // Die berechnete y-Komponente des Mittelpunkts wird überprüft.
   Field myAttribut = Ausgleichsproblem.class.getDeclaredField("my");
   myAttribut.setAccessible(true);
   assertEquals(0.0, myAttribut.getDouble(this.ausgleichsproblem), 2.0/1000);
   
   // Der berechnete Radius wird überprüft.
   Field rAttribut = Ausgleichsproblem.class.getDeclaredField("r");
   rAttribut.setAccessible(true);
   assertEquals(2.0, rAttribut.getDouble(this.ausgleichsproblem), 2.0/1000);
   
   // Es wird überprüft, ob das Flag korrekt gesetzt ist.
   assertTrue(this.ausgleichsproblem.isLoesungAnzeigen());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#problemLoesen()}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testProblemLoesen2() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Das Feld der Messpunkte wird initialisiert.
   this.ausgleichsproblem.setMesspunkte(new Vector2D[]{new Vector2D(-5.0, 0.0), new Vector2D(0.0, 5.0), 
      new Vector2D(5.0, 0.0)});
   
   // Die zu testende Methode wird ausgeführt.
   this.ausgleichsproblem.problemLoesen();
   
   // Die berechnete x-Komponente des Mittelpunkts wird überprüft.
   Field mxAttribut = Ausgleichsproblem.class.getDeclaredField("mx");
   mxAttribut.setAccessible(true);
   assertEquals(0.0, mxAttribut.getDouble(this.ausgleichsproblem), 1.0/1000);
   
   // Die berechnete y-Komponente des Mittelpunkts wird überprüft.
   Field myAttribut = Ausgleichsproblem.class.getDeclaredField("my");
   myAttribut.setAccessible(true);
   assertEquals(0.0, myAttribut.getDouble(this.ausgleichsproblem), 1.0/1000);
   
   // Der berechnete Radius wird überprüft.
   Field rAttribut = Ausgleichsproblem.class.getDeclaredField("r");
   rAttribut.setAccessible(true);
   assertEquals(5.0, rAttribut.getDouble(this.ausgleichsproblem), 5.0/1000);
   
   // Es wird überprüft, ob das Flag korrekt gesetzt ist.
   assertTrue(this.ausgleichsproblem.isLoesungAnzeigen());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#problemLoesen()}.
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testProblemLoesen3() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException
   {
   // Das Feld der Messpunkte wird initialisiert.
   this.ausgleichsproblem.setMesspunkte(new Vector2D[]{new Vector2D(1.0, 0.0), new Vector2D(5.0, 4.0), 
      new Vector2D(9.0, 0.0), new Vector2D(5.0, -4.0)});
   
   // Die zu testende Methode wird ausgeführt.
   this.ausgleichsproblem.problemLoesen();
   
   // Die berechnete x-Komponente des Mittelpunkts wird überprüft.
   Field mxAttribut = Ausgleichsproblem.class.getDeclaredField("mx");
   mxAttribut.setAccessible(true);
   assertEquals(5.0, mxAttribut.getDouble(this.ausgleichsproblem), 5.0/1000);
   
   // Die berechnete y-Komponente des Mittelpunkts wird überprüft.
   Field myAttribut = Ausgleichsproblem.class.getDeclaredField("my");
   myAttribut.setAccessible(true);
   assertEquals(0.0, myAttribut.getDouble(this.ausgleichsproblem), 4.0/1000);
   
   // Der berechnete Radius wird überprüft.
   Field rAttribut = Ausgleichsproblem.class.getDeclaredField("r");
   rAttribut.setAccessible(true);
   assertEquals(4.0, rAttribut.getDouble(this.ausgleichsproblem), 4.0/1000);
   
   // Es wird überprüft, ob das Flag korrekt gesetzt ist.
   assertTrue(this.ausgleichsproblem.isLoesungAnzeigen());
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#grafikdatenBerechnen()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 */
@Test
public void testGrafikdatenBerechnen() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException, NoSuchMethodException, InvocationTargetException
   {
   // Das Datenmodell der Ortskurve wird erzeugt und im zu testenden Objekt gespeichert.
   Field ortskurveModellFeld = Ausgleichsproblem.class.getDeclaredField("ortskurveModell");
   ortskurveModellFeld.setAccessible(true);
   ortskurveModellFeld.set(this.ausgleichsproblem, new OrtskurveModell());
   
   // Die Messpunkte werden deklariert und an das Objekt der zu testenden Klasse übergeben.
   Vector2D[] messpunkte = new Vector2D[]{new Vector2D(0.1, 0), new Vector2D(1.9, 0), new Vector2D(1, 1.1), 
      new Vector2D(1, -1.1)};
   this.ausgleichsproblem.setMesspunkte(messpunkte);
   
   // Die Kreisradius wird an das Objekt der zu testenden Klasse übergeben.
   Field rFeld = Ausgleichsproblem.class.getDeclaredField("r");
   rFeld.setAccessible(true);
   rFeld.setDouble(this.ausgleichsproblem, 1.0);
   
   // Die Kreismittelpunkt wird an das Objekt der zu testenden Klasse übergeben.
   Field mxFeld = Ausgleichsproblem.class.getDeclaredField("mx");
   mxFeld.setAccessible(true);
   mxFeld.setDouble(this.ausgleichsproblem, 1.0);
   
   // Die Kreismittelpunkt wird an das Objekt der zu testenden Klasse übergeben.
   Field myFeld = Ausgleichsproblem.class.getDeclaredField("my");
   myFeld.setAccessible(true);
   myFeld.setDouble(this.ausgleichsproblem, 0.0);
   
   // Die zu testende Methode wird aufgerufen.
   Method method = Ausgleichsproblem.class.getDeclaredMethod("grafikdatenBerechnen");
   method.setAccessible(true);
   method.invoke(this.ausgleichsproblem);
   
   // Die Koordinatenachsen werden gelesen.
   Field koordinatenachsenFeld = Ausgleichsproblem.class.getDeclaredField("koordinatenachsen");
   koordinatenachsenFeld.setAccessible(true);
   Koordinatenachsen koordinatenachsen = (Koordinatenachsen) koordinatenachsenFeld.get(this.ausgleichsproblem);

   // Es wird überprüft, ob die Koordinatenachsen korrekt berechnet worden sind.
   assertEquals(167.7, koordinatenachsen.getStartPunktYAchse().getX(), 167.7/1000);
   assertEquals(270, koordinatenachsen.getStartPunktYAchse().getY(), 270/1000);
   assertEquals(167.7, koordinatenachsen.getEndPunktYAchse().getX(), 167.7/1000);
   assertEquals(0, koordinatenachsen.getEndPunktYAchse().getY(), 0);
   assertEquals(147.3, koordinatenachsen.getStartPunktXAchse().getX(), 147.3/1000);
   assertEquals(135, koordinatenachsen.getStartPunktXAchse().getY(), 135/1000);
   assertEquals(392.7, koordinatenachsen.getEndPunktXAchse().getX(), 392.7/1000);
   assertEquals(135, koordinatenachsen.getEndPunktXAchse().getY(), 135/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#achsenDimensionieren()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 */
@Test
public void testAchsenDimensionieren() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException, NoSuchMethodException, InvocationTargetException
   {
   // Die Messpunkte werden deklariert und an das Objekt der zu testenden Klasse übergeben.
   Vector2D[] messpunkte = new Vector2D[]{new Vector2D(0.1, 0), new Vector2D(1.9, 0), new Vector2D(1, 1.1), 
      new Vector2D(1, -1.1)};
   this.ausgleichsproblem.setMesspunkte(messpunkte);
   
   // Die Kreisradius wird an das Objekt der zu testenden Klasse übergeben.
   Field rFeld = Ausgleichsproblem.class.getDeclaredField("r");
   rFeld.setAccessible(true);
   rFeld.setDouble(this.ausgleichsproblem, 1.0);
   
   // Die Kreismittelpunkt wird an das Objekt der zu testenden Klasse übergeben.
   Field mxFeld = Ausgleichsproblem.class.getDeclaredField("mx");
   mxFeld.setAccessible(true);
   mxFeld.setDouble(this.ausgleichsproblem, 1.0);
   
   // Die Kreismittelpunkt wird an das Objekt der zu testenden Klasse übergeben.
   Field myFeld = Ausgleichsproblem.class.getDeclaredField("my");
   myFeld.setAccessible(true);
   myFeld.setDouble(this.ausgleichsproblem, 0.0);
   
   // Die zu testende Methode wird aufgerufen.
   Method method = Ausgleichsproblem.class.getDeclaredMethod("achsenDimensionieren");
   method.setAccessible(true);
   Achsendimensionierung achsendimensionierung = (Achsendimensionierung) method.invoke(this.ausgleichsproblem);
   
   // Es wird überprüft, ob die Achsen korrekt dimensioniert worden sind.
   assertEquals(2.2, achsendimensionierung.getWertebereichKoordinatensystem().getMaxX(), 2.2/1000);
   assertEquals(-0.2, achsendimensionierung.getWertebereichKoordinatensystem().getMinX(), -0.2/1000);
   assertEquals(1.32, achsendimensionierung.getWertebereichKoordinatensystem().getMaxY(), 1.32/1000);
   assertEquals(-1.32, achsendimensionierung.getWertebereichKoordinatensystem().getMinY(), 1.32/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#konverterErstellen()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 */
@Test
public void testKonverterErstellen() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException, NoSuchMethodException, InvocationTargetException
   {
   // Der Wertebereich des Koordinatensystems, der im Test verwendet wird, wird erstellt.
   Wertebereich wertebereich = new Wertebereich(2.0, 1.1, 0.0, -1.1);

   // Die zu testende Methode wird aufgerufen.
   Method method = Ausgleichsproblem.class.getDeclaredMethod("konverterErstellen", Wertebereich.class);
   method.setAccessible(true);
   PunktPixelKonverter punktPixelKonverter = (PunktPixelKonverter) method.invoke(this.ausgleichsproblem, wertebereich);
   
   // Es wird überprüft, ob der korrekte Skalierungsfaktor berechnet worden ist.
   Field skalierungsfaktorFeld = PunktPixelKonverter.class.getDeclaredField("skalierungsfaktor");
   skalierungsfaktorFeld.setAccessible(true);
   double skalierungsfaktor = (Double) skalierungsfaktorFeld.get(punktPixelKonverter);
   assertEquals(122.7, skalierungsfaktor, 122.7/1000);
   
   // Es wird überprüft, ob der korrekte Ursprung in Pixeln berechnet worden ist.
   Field ursprungInPixelFeld = PunktPixelKonverter.class.getDeclaredField("ursprungInPixel");
   ursprungInPixelFeld.setAccessible(true);
   Vector2D ursprungInPixel = (Vector2D) ursprungInPixelFeld.get(punktPixelKonverter);
   assertEquals(147.3, ursprungInPixel.getX(), 147.3/1000);
   assertEquals(135, ursprungInPixel.getY(), 135/1000);
   }

// =====================================================================================================================
// =====================================================================================================================

/**
 * Test für die Methode {@link Ausgleichsproblem#getOrtskurveModell()}.
 * 
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
@Test
public void testGetOrtskurveModell() throws SecurityException, NoSuchFieldException, IllegalArgumentException, 
   IllegalAccessException 
   {
   // Das Datenmodell der Ortskurve wird erzeugt.
   OrtskurveModell ortskurveModell = new OrtskurveModell();
   
   // Das Datenmodell der Ortskurve wird an das Objekt der zu testenden Klasse übergeben.
   Field ortskurveFeld = Ausgleichsproblem.class.getDeclaredField("ortskurveModell");
   ortskurveFeld.setAccessible(true);
   ortskurveFeld.set(this.ausgleichsproblem, ortskurveModell);
   
   // Es wird überprüft, ob das Datenmodell der Ortskurve korrekt zurückgegeben wird.
   assertEquals(ortskurveModell, this.ausgleichsproblem.getOrtskurveModell());
   }
}
