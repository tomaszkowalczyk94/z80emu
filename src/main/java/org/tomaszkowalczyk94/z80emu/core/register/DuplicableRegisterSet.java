package org.tomaszkowalczyk94.z80emu.core.register;

import lombok.Data;
import lombok.NonNull;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.register.exception.UnsupportedGeneralPurposeRegisterIdException;

/**
 * The registers include two sets of six general-purpose (and W,Z,A,F register)
 * registers that may be used individually as 8-bit registers or in pairs as 16-bit
 * registers. There are also two sets of accumulator and flag registers and six
 * special-purpose registers. This class represents bank register which can be duplicated.
 * The rest of registers is in {@link RegisterBank}
 */
@Data
public class DuplicableRegisterSet {
    private XBit8 a = XBit8.valueOfSigned(0);
    private XBit8 f = XBit8.valueOfSigned(0);
    private XBit8 b = XBit8.valueOfSigned(0);
    private XBit8 c = XBit8.valueOfSigned(0);
    private XBit8 d = XBit8.valueOfSigned(0);
    private XBit8 e = XBit8.valueOfSigned(0);
    private XBit8 h = XBit8.valueOfSigned(0);
    private XBit8 l = XBit8.valueOfSigned(0);

    public XBit16 getBC() {
        return XBit16.valueOfHighAndLow(b, c);
    }

    public void setBC(@NonNull XBit16 value) {
        b = value.getHighByte();
        c = value.getLowByte();
    }

    public XBit16 getDE() {
        return XBit16.valueOfHighAndLow(d, e);
    }

    public void setDE(@NonNull XBit16 value) {
        d = value.getHighByte();
        e = value.getLowByte();
    }

    public XBit16 getHL() {
        return XBit16.valueOfHighAndLow(h, l);
    }

    public void setHL(XBit16 value) {
        h = value.getHighByte();
        l = value.getLowByte();
    }

    /**
     * @param id id of registers:<br>
     * 0b000 - B <br>
     * 0b001 - C <br>
     * 0b010 - D <br>
     * 0b011 - E <br>
     * 0b100 - H <br>
     * 0b101 - L <br>
     * 0b110 - unused <br>
     * 0b111 - A <br>
     * <br>
     * @param value new register value
     */
    public void set8BitRegisterById(byte id, XBit8 value) throws UnsupportedGeneralPurposeRegisterIdException {

        switch (id) {
            case 0b000:
                b = value;
                break;
            case 0b001:
                c = value;
                break;
            case 0b010:
                d = value;
                break;
            case 0b011:
                e = value;
                break;
            case 0b100:
                h = value;
                break;
            case 0b101:
                l = value;
                break;
            case 0b111:
                a = value;
                break;
            default:
                throw new UnsupportedGeneralPurposeRegisterIdException(id);
        }
    }

    /**
     * @param id id of registers:<br>
     * 0b000 - B <br>
     * 0b001 - C <br>
     * 0b010 - D <br>
     * 0b011 - E <br>
     * 0b100 - H <br>
     * 0b101 - L <br>
     * 0b110 - unused <br>
     * 0b111 - A <br>
     */
    public XBit8 get8BitRegisterById(byte id) throws UnsupportedGeneralPurposeRegisterIdException {
        switch (id) {
            case 0b000:
                return b;
            case 0b001:
                return c;
            case 0b010:
                return d;
            case 0b011:
                return e;
            case 0b100:
                return h;
            case 0b101:
                return l;
            case 0b111:
                return a;
            default:
                throw new UnsupportedGeneralPurposeRegisterIdException(id);
        }
    }

}
