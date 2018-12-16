package org.tomaszkowalczyk94.z80emu.core.instruction;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

/**
 * Representation of one cpu instruction
 */
public interface Instruction {
    InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception;

    /**
     * instruction can have more than 1 byte instruction. This method return second byte of instruction (first byte operand)
     */
    default XBit8 getSecondByte(Z80 z80) throws MemoryException {
        return z80.getMem().read(
                XBitUtils.incrementBy(z80.getRegs().getPc(), 1)
        );
    }

    /**
     * instruction can have more than 1 byte instruction. This method return third byte of instruction (second byte operand)
     */
    default XBit8 getThirdByte(Z80 z80) throws MemoryException {
        return z80.getMem().read(
                XBitUtils.incrementBy(z80.getRegs().getPc(), 2)
        );
    }

    /**
     * instruction can have more than 1 byte instruction. This method return fourth byte of instruction (third byte operand)
     */
    default XBit8 getFourthByte(Z80 z80) throws MemoryException {
        return z80.getMem().read(
                XBitUtils.incrementBy(z80.getRegs().getPc(), 3)
        );
    }
}
