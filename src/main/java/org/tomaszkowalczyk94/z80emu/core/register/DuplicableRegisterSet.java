package org.tomaszkowalczyk94.z80emu.core.register;

import lombok.Data;
import lombok.NonNull;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;

/**
 * The registers include two sets of six general-purpose (and W,Z,A,F register)
 * registers that may be used individually as 8-bit registers or in pairs as 16-bit
 * registers. There are also two sets of accumulator and flag registers and six
 * special-purpose registers. This class represents bank register which can be duplicated.
 * The rest of registers is in {@link RegisterBank}
 *
 * <h2>register WZ</h2>
 * The WZ temporary registers The Z80 (like the 8080 and 8085) has a WZ register pair that is used for temporary
 * storage but is invisible to the programmer. The primary use of WZ is to hold an operand from a two or three byte
 * instruction until it can be used.
 *
 */
@Data
public class DuplicableRegisterSet {
    XBit8 registerA = XBit8.valueOfSigned(0);
    XBit8 registerF = XBit8.valueOfSigned(0);
    XBit8 registerB = XBit8.valueOfSigned(0);
    XBit8 registerC = XBit8.valueOfSigned(0);
    XBit8 registerD = XBit8.valueOfSigned(0);
    XBit8 registerE = XBit8.valueOfSigned(0);
    XBit8 registerH = XBit8.valueOfSigned(0);
    XBit8 registerL = XBit8.valueOfSigned(0);
    XBit8 registerW = XBit8.valueOfSigned(0);
    XBit8 registerZ = XBit8.valueOfSigned(0);

    public XBit16 getBC() {
        return XBit16.valueOfHighAndLow(registerB, registerC);
    }

    public void setBC(@NonNull XBit16 value) {
        registerB = value.getHighByte();
        registerC = value.getLowByte();
    }

    public XBit16 getDE() {
        return XBit16.valueOfHighAndLow(registerD, registerE);
    }

    public void setDE(@NonNull XBit16 value) {
        registerD = value.getHighByte();
        registerE = value.getLowByte();
    }

    public XBit16 getHL() {
        return XBit16.valueOfHighAndLow(registerH, registerL);
    }

    public void setHL(XBit16 value) {
        registerH = value.getHighByte();
        registerL = value.getLowByte();
    }
}
