package edu.psk.z80emu;

import edu.psk.z80emu.module.register.ByteRegister;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static edu.psk.z80emu.module.register.ByteRegister.OUTPUT_ENABLE;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
//        InputPin inputPinFirst = new InputPin(null, "testInput1");
//        InputPin inputPinSecond = new InputPin(null, "testInput2");
//        OutputPin outputPin = new OutputPin(null, "testOutput");
//
//        assertFalse(inputPinFirst.getValue());
//        assertFalse(inputPinSecond.getValue());
//
//        outputPin.connectInput(inputPinFirst);
//        outputPin.connectInput(inputPinSecond);
//
//        outputPin.setValueByRoot(true);
//
//        assertTrue(inputPinFirst.getValue());
//        assertTrue(inputPinSecond.getValue());

        ByteRegister byteRegister = new ByteRegister();

        byteRegister.getPin(OUTPUT_ENABLE).setValueByRoot(false);

    }
}
