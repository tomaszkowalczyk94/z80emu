package org.tomaszkowalczyk94.z80emu.core.io;

import lombok.Data;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;

/**
 * Device works like memory.
 * when read value to port, we can read the same value from port.
 */
@Data
public class SimpleIoDevice implements IoDevice {

    public final int bitsOfPortAddress;
    public final int countOfPorts;

    private final short[] memoryArray;

    /**
     * @param bitsOfPortAddress count of lower bits, which will contain port number in address bus.
     *                          For example if this value will be 8, and address bus will contain ABF0h value,
     *                          the port number will be F0h.
     */
    public SimpleIoDevice(int bitsOfPortAddress) {
        this.bitsOfPortAddress = bitsOfPortAddress;
        countOfPorts = (int)Math.pow(2, bitsOfPortAddress);
        memoryArray = new short[countOfPorts];
    }

    @Override
    public XBit8 read(XBit16 addressBus) {
        int arrIndex = addressBus.getValueOfBits(bitsOfPortAddress - 1, 0);
        return XBit8.valueOfUnsigned(memoryArray[arrIndex]);
    }

    @Override
    public void write(XBit16 addressBus, XBit8 dataBus) {
        int arrIndex = addressBus.getValueOfBits(bitsOfPortAddress - 1, 0);
        memoryArray[arrIndex] = dataBus.getUnsignedValue();
    }
}
