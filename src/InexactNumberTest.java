import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class with some methods for the InexactNunber class.
 */
class InexactNumberTest {

    /**
     * Tests the default constructor und getX(), getDx().
     */
    @Test
    void inexactNumber0() {
        String msg = "";
        InexactNumber i0 = new InexactNumber();
        msg = "i0.getX()";
        assertEquals(0, i0.getX(), msg);
        msg = "i0.getDx()";
        assertEquals(0, i0.getDx(), msg);
    }

    /**
     * Tests the constructor with one parameter.
     */
    @Test
    void inexactNumber1() {
        String msg = "";
        InexactNumber i1 = new InexactNumber(-1.0);
        msg = "i1.getX()";
        assertEquals(-1, i1.getX(), msg);
        msg = "i1.getDx()";
        assertEquals(0, i1.getDx(), msg);
    }

    /**
     * Tests the constructor with two parameters and the methods
     * getMax() and getMin().
     */
    @Test
    void inexactNumber2() {
        String msg = "";
        InexactNumber i2 = new InexactNumber(-10.0, -1.0);
        msg = "i2.getX()";
        assertEquals(-10, i2.getX(), msg);
        msg = "i2.getDx()";
        assertEquals(1, i2.getDx(), msg);
        msg = "i2.getMax()";
        assertEquals(-9, i2.getMax(), msg);
        msg = "i2.getMin()";
        assertEquals(-11, i2.getMin(), msg);
        msg = "i2.toString()";
        assertEquals("(-10.0±1.0)", i2.toString(), msg);
    }

    /**
     * Tests the operators add, sub, mult and div.
     */
    @Test
    void inexactNumber3() {
        String msg = "";
        InexactNumber i3 = new InexactNumber(10.0, 1.0);
        InexactNumber i4 = new InexactNumber(20.0, 2.0);
        InexactNumber i5 = i3.add(i4);
        InexactNumber i6 = i3.sub(i4);
        InexactNumber i7 = i3.mult(i4);
        InexactNumber i8 = i3.div(i4);
        msg = "i5.getX()";
        assertEquals(30, i5.getX(), msg);
        msg = "i5.getDx()";
        assertEquals(3, i5.getDx(), msg);
        msg = "i6.getX()";
        assertEquals(-10, i6.getX(), msg);
        msg = "i6.getDx()";
        assertEquals(3, i6.getDx(), msg);
        msg = "i7.getX()";
        assertEquals(200, i7.getX(), msg);
        msg = "i7.getDx()";
        assertEquals(40, i7.getDx(), msg);
        msg = "i8.getX()";
        assertEquals(0.5, i8.getX(), msg);
        msg = "i8.getDx()";
        assertEquals(0.1, i8.getDx(), msg);
    }

    /**
     * Tests the example B.
     */
    @Test
    void testMainB() {
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor =
                new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String msg = "";
        msg = "Dieser Test testet die Anzahl der Gläser und den Glasinhalt";
        InexactNumber.main(null);
        String out = outputStreamCaptor.toString();
        assertEquals(true, out.contains("(35.0±2.45)")
                && out.contains("0.02±0.0014"), msg);
        msg = "Dieser Test testet die Mindestanzahl an Gläsern und den Rest";
        assertEquals(true, out.contains("mindestens 32 Gläser")
                && out.contains("0.0152 Liter"), msg);
        msg = "Dieser Test testet die Maximalanzahl an Gläsern und den Rest";
        assertEquals(true, out.contains("maximal 37 Gläser")
                && out.contains("0.0118 Liter"), msg);
        outputStreamCaptor.toString();
    }


    /**
     * Tests the example C.
     */
    @Test
    void testMainC() {
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor =
                new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String msg = "";
        msg = "Dieser Test testet die Wohnungsgröße";
        InexactNumber.main(null);
        String out = outputStreamCaptor.toString();
        assertEquals(true, out.contains("48.9944")
                && out.contains("±0.06102"), msg);
        msg = "Dieser Test testet die minimale und die maximale Wohnungsgröße";
        assertEquals(true,
                out.contains("48.933") && out.contains("49.055"), msg);
        msg = "Dieser Test testet, wie groß die Wohnungsgröße"
                + "laut Quadratmeter-Preis mindestens sein müsste";
        assertEquals(true, out.contains("57.548"), msg);
        msg = "Dieser Test testet den minimalen Quadratmeterpreis";
        assertEquals(true, out.contains("9.456"), msg);
        msg = "Dieser Test testet die maximale Gesamtmiete";
        assertEquals(true, out.contains("395.435"), msg);
        msg = "Dieser Test testet die Messungenauigkeit, damit die"
                + " angegebene Miete gerechtfertigt ist";
        assertEquals(true, out.contains("280.3578"), msg);
        outputStreamCaptor.toString();
    }
}
