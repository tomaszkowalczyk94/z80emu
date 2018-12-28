package org.tomaszkowalczyk94.z80emu.core.instruction.helper;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.N;
import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.PV;
import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.BC;
import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.HL;

public class CompareAndIncrementHelper {

    private FlagHelper flagHelper;

    public CompareAndIncrementHelper(FlagHelper flagHelper) {
        this.flagHelper = flagHelper;
    }

    /**
     * @return boolean. true if BC = 0 and A = (HL)
     */
    public boolean execute(Z80 z80, int incrementer) throws Z80Exception {

        XBit8 aVal = z80.getRegs().getA();
        XBit8 memVal = z80.getMem().read(z80.getRegs().getHL());

        XBitUtils.Arithmetic8bitResult result = XBitUtils.subTwo8bits(
                aVal,
                memVal
        );

        z80.getRegs().incrementReg16bit(HL, incrementer);
        z80.getRegs().incrementReg16bit(BC, -1);

        flagHelper.setSignFlagBy8bit(z80, result.result);
        flagHelper.setZeroFlabBy8bit(z80, result.result);
        flagHelper.setHalfCarryFlagSub(z80, aVal, memVal);
        z80.getRegs().setFlag(
                PV,
                (z80.getRegs().getBC().getSignedValue() != 0)
        );
        z80.getRegs().setFlag(N, true);


        return (
                z80.getRegs().getBC().getUnsignedValue() == 0 &&
                aVal.getSignedValue() == memVal.getSignedValue()
        );
    }
}
