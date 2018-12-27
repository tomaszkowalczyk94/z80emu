package org.tomaszkowalczyk94.z80emu.core.register;

import lombok.Getter;
import lombok.Setter;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.register.exception.UnsupportedGeneralPurposeRegisterException;
import org.tomaszkowalczyk94.z80emu.core.register.exception.UnsupportedGeneralPurposeRegisterIdException;

/**
 * <img src="http://www.users.globalnet.co.uk/~jg27paw4/yr18/yr18_51b.gif" />
 */
public class RegisterBank {

    public enum Reg16bit {
        BC, DE, HL, SP, AF,
    }

    private final DuplicableRegisterSet regSetA = new DuplicableRegisterSet();
    private final DuplicableRegisterSet regSetB = new DuplicableRegisterSet();

    @Getter private DuplicableRegisterSet regSet;

    private final FlagRegManager flagRegManager = new FlagRegManager();

    @Getter @Setter private XBit8 i = XBit8.valueOfSigned(0);
    @Getter @Setter private XBit8 r = XBit8.valueOfSigned(0);
    @Getter @Setter private XBit16 ix = XBit16.valueOfSigned(0);
    @Getter @Setter private XBit16 iy = XBit16.valueOfSigned(0);
    @Getter @Setter private XBit16 sp = XBit16.valueOfUnsigned(0xFFFF);
    @Getter @Setter private XBit16 pc = XBit16.valueOfSigned(0);


    public RegisterBank() {
        regSet = regSetA;
    }

    public void switchRegisterSet() {
        regSet = (regSet == regSetA) ? regSetB : regSetA;
    }

    public void switchRegisterSetToA() {
        regSet = regSetA;
    }

    public void switchRegisterSetToB() {
        regSet = regSetB;
    }

    public DuplicableRegisterSet getAlternativeRegisterSet() {
        return  (regSet == regSetA) ? regSetB : regSetA;
    }

    public void incrementPc() {
        incrementPc(1);
    }

    public void incrementPc(int incrementer) {
        XBit16 incrementedPc = XBitUtils.incrementBy(getPc(), incrementer);
        setPc(incrementedPc);
    }

    /**
     * @param id id of registers:<br>
     * BC    00<br>
     * DE    01<br>
     * HL    10<br>
     * SP/AF 11 its depends of lastReg param<br>
     * @param lastReg the 0b11 id of register can be register SP or AF. Its depends of instruction context.
     */
    public void set16BitRegisterById(byte id, XBit16 value, Reg16bit lastReg) throws UnsupportedGeneralPurposeRegisterIdException {
        switch (id) {
            case 0b00:
                setBC(value);
                break;
            case 0b01:
                setDE(value);
                break;
            case 0b10:
                setHL(value);
                break;
            case 0b11:
                if(lastReg == Reg16bit.SP) {
                    setSp(value);
                } else if(lastReg == Reg16bit.AF) {
                    setAf(value);
                } else {
                    throw new UnsupportedGeneralPurposeRegisterIdException(id);
                }
                break;
            default:
                throw new UnsupportedGeneralPurposeRegisterIdException(id);
        }
    }

    /**
     * @param id id of registers:<br>
     * BC    00<br>
     * DE    01<br>
     * HL    10<br>
     * SP/AF 11 its depends of lastReg param<br>
     * @param lastReg the 0b11 id of register can be register SP or AF. Its depends of instruction context.
     */
    public XBit16 get16BitRegisterById(byte id, Reg16bit lastReg) throws UnsupportedGeneralPurposeRegisterIdException {
        switch (id) {
            case 0b00:
                return getBC();
            case 0b01:
                return getDE();
            case 0b10:
                return getHL();
            case 0b11:
                if(lastReg == Reg16bit.SP) {
                    return getSp();
                } else if(lastReg == Reg16bit.AF) {
                    return getAf();
                } else {
                    throw new UnsupportedGeneralPurposeRegisterIdException(id);
                }
            default:
                throw new UnsupportedGeneralPurposeRegisterIdException(id);
        }
    }

    public XBit16 get16bitRegister(Reg16bit regEnum) throws UnsupportedGeneralPurposeRegisterException {
        switch (regEnum) {
            case BC: return getBC();
            case DE: return getDE();
            case HL: return getHL();
            case SP: return getSp();
            case AF: return getAf();
            default:
                throw new UnsupportedGeneralPurposeRegisterException();
        }
    }

    public void set16bitRegister(Reg16bit regEnum, XBit16 value) throws UnsupportedGeneralPurposeRegisterException {
        switch (regEnum) {
            case BC:
                setBC(value);
                break;
            case DE:
                setDE(value);
                break;
            case HL:
                setHL(value);
                break;
            case SP:
                setSp(value);
                break;
            case AF:
                setAf(value);
                break;
            default:
                throw new UnsupportedGeneralPurposeRegisterException();
        }
    }

    /**
     * alias for {@link DuplicableRegisterSet#getA()}
     */
    public XBit8 getA() {
        return regSet.getA();
    }

    /**
     * alias for {@link DuplicableRegisterSet#setA(XBit8 a)}
     */
    public void setA(XBit8 a) {
        regSet.setA(a);
    }

    /**
     * alias for {@link DuplicableRegisterSet#getB()}
     */
    public XBit8 getB() {
        return regSet.getB();
    }

    /**
     * alias for {@link DuplicableRegisterSet#setB(XBit8 b)}
     */
    public void setB(XBit8 b) {
        regSet.setB(b);
    }

    /**
     * alias for {@link DuplicableRegisterSet#getF()}
     */
    public XBit8 getF() {
        return regSet.getF();
    }

    /**
     * alias for {@link DuplicableRegisterSet#setF(XBit8 f)}
     */
    public void setF(XBit8 f) {
        regSet.setF(f);
    }

    /**
     * alias for {@link DuplicableRegisterSet#getC()}
     */
    public XBit8 getC() {
        return regSet.getC();
    }

    /**
     * alias for {@link DuplicableRegisterSet#setC(XBit8 c)}
     */
    public void setC(XBit8 c) {
        regSet.setC(c);
    }

    /**
     * alias for {@link DuplicableRegisterSet#getD()}
     */
    public XBit8 getD() {
        return regSet.getD();
    }

    /**
     * alias for {@link DuplicableRegisterSet#setD(XBit8 d)}
     */
    public void setD(XBit8 d) {
        regSet.setD(d);
    }

    /**
     * alias for {@link DuplicableRegisterSet#getE()}
     */
    public XBit8 getE() {
        return regSet.getE();
    }

    /**
     * alias for {@link DuplicableRegisterSet#setE(XBit8 e)}
     */
    public void setE(XBit8 e) {
        regSet.setA(e);
    }

    /**
     * alias for {@link DuplicableRegisterSet#getH()}
     */
    public XBit8 getH() {
        return regSet.getH();
    }

    /**
     * alias for {@link DuplicableRegisterSet#setH(XBit8 h)}
     */
    public void setH(XBit8 h) {
        regSet.setH(h);
    }

    /**
     * alias for {@link DuplicableRegisterSet#getL()}
     */
    public XBit8 getL() {
        return regSet.getL();
    }

    /**
     * alias for {@link DuplicableRegisterSet#setL(XBit8 l)}
     */
    public void setL(XBit8 l) {
        regSet.setL(l);
    }

    /**
     * alias for {@link DuplicableRegisterSet#getBC()}
     */
    public XBit16 getBC() {
        return regSet.getBC();
    }

    /**
     * alias for {@link DuplicableRegisterSet#setBC(XBit16)}
     */
    public void setBC(XBit16 value) {
        regSet.setBC(value);
    }

    /**
     * alias for {@link DuplicableRegisterSet#getDE()}
     */
    public XBit16 getDE() {
        return regSet.getDE();
    }

    /**
     * alias for {@link DuplicableRegisterSet#setDE(XBit16)}
     */
    public void setDE(XBit16 value) {
        regSet.setDE(value);
    }

    /**
     * alias for {@link DuplicableRegisterSet#getHL()}
     */
    public XBit16 getHL() {
        return regSet.getHL();
    }

    /**
     * alias for {@link DuplicableRegisterSet#setAf(XBit16)}
     */
    public void setAf(XBit16 value) {
        regSet.setAf(value);
    }

    /**
     * alias for {@link DuplicableRegisterSet#getAf()}
     */
    public XBit16 getAf() {
        return regSet.getAf();
    }

    /**
     * alias for {@link DuplicableRegisterSet#setHL(XBit16)}
     */
    public void setHL(XBit16 value) {
        regSet.setHL(value);
    }

    /**
     * alias for {@link DuplicableRegisterSet#set8BitRegisterById(byte, XBit8)}
     */
    public void set8BitRegisterById(byte id, XBit8 value) throws UnsupportedGeneralPurposeRegisterIdException {
        regSet.set8BitRegisterById(id, value);
    }

    /**
     * alias for {@link DuplicableRegisterSet#get8BitRegisterById(byte)}}
     */
    public XBit8 get8BitRegisterById(byte id) throws UnsupportedGeneralPurposeRegisterIdException {
        return regSet.get8BitRegisterById(id);
    }

    public void setFlag(FlagRegManager.Flag flag, boolean value) {
        flagRegManager.setFlag(getRegSet(), flag, value);
    }

    public boolean getFlag(FlagRegManager.Flag flag) {
        return flagRegManager.getFlag(getRegSet(), flag);
    }

}
