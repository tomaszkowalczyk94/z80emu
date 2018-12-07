package org.tomaszkowalczyk94.z80emu.core;

import lombok.Data;
import org.tomaszkowalczyk94.z80emu.core.memory.Memory;
import org.tomaszkowalczyk94.z80emu.core.register.RegisterBank;

@Data
public class Z80 {

    Memory memory = new Memory();
    RegisterBank registerBank = new RegisterBank();

    public void run() {
        while (true) {

        }
    }

}
