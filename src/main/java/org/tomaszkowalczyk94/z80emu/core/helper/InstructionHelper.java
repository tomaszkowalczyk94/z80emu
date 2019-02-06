package org.tomaszkowalczyk94.z80emu.core.helper;

import lombok.Data;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

@Data
public class InstructionHelper {

    /**
     * instruction can have more than 1 byte instruction. This method return second byte of instruction (first byte operand)
     */
    public XBit8 getSecondByte(Z80 z80) throws MemoryException {
        return z80.getMem().read(
                XBitUtils.incrementBy(z80.getRegs().getPc(), 1)
        );
    }

    /**
     * instruction can have more than 1 byte instruction. This method return third byte of instruction (second byte operand)
     */
    public XBit8 getThirdByte(Z80 z80) throws MemoryException {
        return z80.getMem().read(
                XBitUtils.incrementBy(z80.getRegs().getPc(), 2)
        );
    }

    /**
     * instruction can have more than 1 byte instruction. This method return fourth byte of instruction (third byte operand)
     */
    public XBit8 getFourthByte(Z80 z80) throws MemoryException {
        return z80.getMem().read(
                XBitUtils.incrementBy(z80.getRegs().getPc(), 3)
        );
    }

    /**
     * Write 16bit value to memory in little endian mode
     */
    public void write16bitToMemory(Z80 z80, XBit16 address, XBit16 value) throws MemoryException {

        XBit16 addressOfHigh = XBitUtils.incrementBy(address, 1);

        z80.getMem().write(
                address,
                value.getSecondByte()
        );

        z80.getMem().write(
                addressOfHigh,
                value.getFirstByte()
        );

    }

    /**
     * read 16bit value to from memory in little endian mode
     */
    public XBit16 read16bitFromMemory(Z80 z80, XBit16 address) throws MemoryException {
        return XBit16.valueOfHighAndLow(
                z80.getMem().read(
                        XBitUtils.incrementBy(address,1)
                ) ,
                z80.getMem().read(address)
        );
    }




}
