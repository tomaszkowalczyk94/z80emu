package org.tomaszkowalczyk94.z80emu.core.instruction.decoder;

import org.tomaszkowalczyk94.z80emu.core.instruction.load8bit.LoadRegisterFromImmediate8bit;
import org.tomaszkowalczyk94.z80emu.core.instruction.load8bit.LoadRegisterFromRegister;

/**
 * Contain all of instruction objects.
 */
public class InstructionsContainer {
    LoadRegisterFromRegister loadRegisterFromRegister = new LoadRegisterFromRegister();
    LoadRegisterFromImmediate8bit loadRegisterFromImmediate8bit = new LoadRegisterFromImmediate8bit();


}
