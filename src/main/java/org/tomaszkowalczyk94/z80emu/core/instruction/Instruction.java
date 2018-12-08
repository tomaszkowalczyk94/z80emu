package org.tomaszkowalczyk94.z80emu.core.instruction;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

/**
 * Representation of one cpu instruction
 */
public interface Instruction {
    void execute(XBit8 opcode, Z80 z80) throws Z80Exception;

    /**
     * Total machine cycles
     */
    int getMachineCycles();

    /**
     * Count of clocks processor for instruction
     */
    int getClocks();

    /**
     * Execution time (E.T.) for each instruction is provided in microseconds for an assumed
     * 4 MHz clock.
     */
    float getExecutionTime();

    /**
     * @return Instruction size in bytes
     */
    int getSize();
}
