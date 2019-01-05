package org.tomaszkowalczyk94.z80emu.core.io;

import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;

import static org.junit.Assert.*;

public class SimpleIoDeviceTest {

    @Test
    public void testConstructor() {
        SimpleIoDevice simpleIoDevice = new SimpleIoDevice(8);

        assertEquals(8, simpleIoDevice.getBitsOfPortAddress());
        assertEquals(256, simpleIoDevice.getCountOfPorts());
        assertEquals(256, simpleIoDevice.getMemoryArray().length);

    }

    @Test
    public void read() {
        SimpleIoDevice simpleIoDevice = new SimpleIoDevice(8);
        simpleIoDevice.getMemoryArray()[2] = 1;

        assertEquals(
                0x01,
                simpleIoDevice.read(XBit16.valueOfUnsigned(0xFF02)).getUnsignedValue()
        );
    }

    @Test
    public void write() {
        SimpleIoDevice simpleIoDevice = new SimpleIoDevice(8);
        simpleIoDevice.write(XBit16.valueOfUnsigned(0xFF02), XBit8.valueOfUnsigned(0xFF));

        assertEquals(
                0xFF,
                simpleIoDevice.getMemoryArray()[2]
        );

    }
}