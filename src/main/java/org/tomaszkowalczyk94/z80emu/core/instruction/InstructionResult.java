package org.tomaszkowalczyk94.z80emu.core.instruction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstructionResult {

    /**
     * Machine cycles
     */
    int machineCycles;

    /**
     * Count of clocks processor for instruction
     */
    int clocks;

    /**
     * Execution time (E.T.) for each instruction is provided in microseconds for an assumed
     * 4 MHz clock.
     */
    float executionTime;

    /**
     * Instruction size in bytes
     */
    int size;
}
