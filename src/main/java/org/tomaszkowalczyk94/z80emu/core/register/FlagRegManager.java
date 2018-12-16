package org.tomaszkowalczyk94.z80emu.core.register;

public class FlagRegManager {

    public enum Flag{
        S(7),Z(6),Y(5),H(4),X(3),PV(2),N(1),C(0);

        private int index;

        Flag(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    public void setFlag(DuplicableRegisterSet duplicableRegisterSet, Flag flag, boolean value) {
        duplicableRegisterSet.setF(
                duplicableRegisterSet.getF().setBit(flag.getIndex(), value)
        );
    }

    public boolean getFlag(DuplicableRegisterSet duplicableRegisterSet, Flag flag) {
        return duplicableRegisterSet.getF().getBit(flag.index);
    }
}
