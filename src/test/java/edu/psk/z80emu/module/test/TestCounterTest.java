package edu.psk.z80emu.module.test;

import org.junit.Test;

import static edu.psk.z80emu.module.AbstractModuleWithClock.CLOCK;
import static org.junit.Assert.*;

public class TestCounterTest {

    @Test
    public void test() {

        TestCounter testCounter = new TestCounter();

        testCounter.getPin(CLOCK).ticTokByRoot();

    }
}