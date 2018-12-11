package org.tomaszkowalczyk94.z80emu.core.instruction.decoder;

import org.tomaszkowalczyk94.z80emu.core.instruction.load8bit.*;

/**
 * Contain all of instruction objects.
 */
public class InstructionsContainer {
    LoadRegisterFromRegister loadRegisterFromRegister = new LoadRegisterFromRegister();
    LoadRegisterFromImmediate8bit loadRegisterFromImmediate8bit = new LoadRegisterFromImmediate8bit();
    LoadRegisterFromMemoryAddressingByHl loadRegisterFromMemoryAddressingByHl = new LoadRegisterFromMemoryAddressingByHl();
    LoadRegisterFromMemoryAddressingByIxAndImmediate8bit loadRegisterFromMemoryAddressingByIxAndImmediate8Bit = new LoadRegisterFromMemoryAddressingByIxAndImmediate8bit();
    LoadRegisterFromIndexAddressingIy loadRegisterFromIndexAddressingIy = new LoadRegisterFromIndexAddressingIy();
    LoadMemoryAddressingByHlFromRegister loadMemoryAddressingByHlFromRegister = new LoadMemoryAddressingByHlFromRegister();
    LoadMemoryAddressingByIxAndImmediate8bitFromRegister loadMemoryAddressingByIxAndImmediate8bitFromRegister = new LoadMemoryAddressingByIxAndImmediate8bitFromRegister();
}
