package org.tomaszkowalczyk94.z80emu.core.instruction;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

/**
 * Representation of one cpu instruction
 */
public interface Instruction {
    void execute(XBit8 instruction, Z80 cpu);

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
}
