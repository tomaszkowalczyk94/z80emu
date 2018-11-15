package edu.psk.z80emu.module.test;

import org.junit.Assert;
import org.junit.Test;

import static edu.psk.z80emu.module.AbstractModuleWithClock.CLOCK;
import static edu.psk.z80emu.module.test.TestCounter.COUNT;
import static org.junit.Assert.*;

public class TestCounterTest {

    @Test
    public void test() {

        TestCounter testCounter = new TestCounter();
        testCounter.getPin(COUNT).setValueByRoot(true);
//        testCounter.getPin(CLOCK).ticTokByRoot();
//        testCounter.getPin(CLOCK).ticTokByRoot();
//        testCounter.getPin(CLOCK).ticTokByRoot();

        Assert.assertEquals(3, testCounter.getOutputDbPinGroup().getIntValueByRoot());
    }
}