package org.tomaszkowalczyk94.z80emu.core.register;

import lombok.Getter;
import lombok.Setter;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;

/**
 * <img src="http://www.users.globalnet.co.uk/~jg27paw4/yr18/yr18_51b.gif" />
 */
public class RegisterBank {

    private DuplicableRegisterSet registerSetA = new DuplicableRegisterSet();
    private DuplicableRegisterSet registerSetB = new DuplicableRegisterSet();

    @Getter
    private DuplicableRegisterSet registerSet;

    @Getter @Setter private XBit8 registerI = XBit8.valueOfSigned(0);
    @Getter @Setter private XBit8 registerR = XBit8.valueOfSigned(0);
    @Getter @Setter private XBit16 registerIX = XBit16.valueOfSigned(0);
    @Getter @Setter private XBit16 registerIY = XBit16.valueOfSigned(0);
    @Getter @Setter private XBit16 registerSP = XBit16.valueOfSigned(0);
    @Getter @Setter private XBit16 registerPC = XBit16.valueOfSigned(0);


    public RegisterBank() {
        registerSet = registerSetA;
    }

    public void switchRegisterSet() {
        registerSet = (registerSet == registerSetA) ? registerSetB : registerSetA;
    }

    public void switchRegisterSetToA() {
        registerSet= registerSetA;
    }

    public void switchRegisterSetToB() {
        registerSet= registerSetB;
    }

    public void incrementPc() {
        incrementPc(1);
    }
    public void incrementPc(int incrementer) {
        XBit16 incrementedPc = XBitUtils.incrementBy(getRegisterPC(), incrementer);
        setRegisterPC(incrementedPc);
    }


}
