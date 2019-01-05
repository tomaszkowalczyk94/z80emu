package org.tomaszkowalczyk94.z80emu.core.io;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;

public interface IoDevice {

    /**
     * Read data from I/O devices
     * @param addressBus 16 bit address bus
     * @return data bus value
     */
    XBit8 read(XBit16 addressBus);

    /**
     * Write data to I/O device/devices
     * @param addressBus 16 bit address bus
     * @param dataBus data bus value
     */
    void write(XBit16 addressBus, XBit8 dataBus);

}
