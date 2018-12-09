package org.tomaszkowalczyk94.z80emu.core.instruction.decoder;

import org.tomaszkowalczyk94.z80emu.core.instruction.load8bit.*;

/**
 * Contain all of instruction objects.
 */
public class InstructionsContainer {
    LoadRegisterFromRegister loadRegisterFromRegister = new LoadRegisterFromRegister();
    LoadRegisterFromImmediate8bit loadRegisterFromImmediate8bit = new LoadRegisterFromImmediate8bit();
    LoadIndirectHlToRegister loadIndirectHlToRegister = new LoadIndirectHlToRegister();
    LoadRegisterFromIndexAddressingIx loadRegisterFromIndexAddressingIx = new LoadRegisterFromIndexAddressingIx();
    LoadRegisterFromIndexAddressingIy loadRegisterFromIndexAddressingIy = new LoadRegisterFromIndexAddressingIy();
}
