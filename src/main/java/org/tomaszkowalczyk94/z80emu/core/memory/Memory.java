package org.tomaszkowalczyk94.z80emu.core.memory;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

@SuppressWarnings("WeakerAccess")
public class Memory {

    public static final int MEMORY_SIZE = 65536;

    private final byte[] memoryArray;

    public Memory() {
        memoryArray = new byte[MEMORY_SIZE];
    }

    public XBit8 read(int address) throws MemoryException {
        return read(XBit16.valueOfUnsigned(address));
    }

    public XBit8 read(XBit16 address) throws MemoryException {
        int addressInt = address.getUnsignedValue();

        if(addressInt>MEMORY_SIZE) {
            throw new MemoryException("address is too big");
        }

        return XBit8.valueOfSigned(memoryArray[addressInt]);
    }

    public void write(int address, XBit8 value) throws MemoryException {
        write(XBit16.valueOfUnsigned(address), value);
    }

    public void write(XBit16 address, XBit8 value) throws MemoryException {

        int addressInt = address.getUnsignedValue();

        if(addressInt>MEMORY_SIZE) {
            throw new MemoryException("address is too big");
        }

        memoryArray[addressInt] = value.getSignedValue();
    }
}
