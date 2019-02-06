package org.tomaszkowalczyk94.z80emu.core.helper;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

public class StackHelper {
    public void pushToStack(Z80 z80, XBit16 value) throws MemoryException {

        //write value to stack
        z80.getMem().write(
                XBitUtils.incrementBy(z80.getRegs().getSp(), -1),
                value.getFirstByte()
        );

        z80.getMem().write(
                XBitUtils.incrementBy(z80.getRegs().getSp(), -2),
                value.getSecondByte()
        );

        //set stack pointer
        z80.getRegs().setSp(
                XBitUtils.incrementBy(z80.getRegs().getSp(), -2)
        );
    }

    public XBit16 popFromStack(Z80 z80) throws MemoryException {

        XBit16 value = XBit16.valueOfHighAndLow(
                z80.getMem().read(
                        XBitUtils.incrementBy(z80.getRegs().getSp(), 1)
                ),
                z80.getMem().read(
                        z80.getRegs().getSp()
                )
        );

        //set stack pointer
        z80.getRegs().setSp(
                XBitUtils.incrementBy(z80.getRegs().getSp(), 2)
        );

        return value;
    }
}
