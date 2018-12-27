package org.tomaszkowalczyk94.z80emu.core.instruction.helper;

import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.BC;
import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.DE;
import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.HL;

public class LoadDataAndIncrementHelper {

    public void execute( Z80 z80, int incrementer) throws Z80Exception {

        z80.getMem().write(
                z80.getRegs().getDE(),
                z80.getMem().read(z80.getRegs().getHL())
        );

        //increment de, hl and decrement bc
        z80.getRegs().incrementReg16bit(DE, incrementer);
        z80.getRegs().incrementReg16bit(HL, incrementer);
        z80.getRegs().incrementReg16bit(BC, -1);

        //set flags
        z80.getRegs().setFlag(FlagRegManager.Flag.H, false);
        z80.getRegs().setFlag(FlagRegManager.Flag.N, false);
        z80.getRegs().setFlag(
                FlagRegManager.Flag.PV,
                (z80.getRegs().getBC().getUnsignedValue()!=0)
        );
    }
}
