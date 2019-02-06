package org.tomaszkowalczyk94.z80emu.core.helper;

import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.helper.exception.UnsupportedConditionException;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

public class ConditionHelper {

    public enum Condition {

        NON_ZERO(0b000),
        ZERO(0b001),
        NON_CARRY(0b010),
        CARRY(0b011),
        PARITY_ODD(0b100),
        PARITY_EVEN(0b101),
        SIGN_POSITIVE(0b110),
        SIGN_NEGATIVE(0b111)
        ;

        private int id;

        public int getId() {
            return id;
        }

        private Condition(int id) {
            this.id = id;
        }

        public static Condition getById(int id) throws UnsupportedConditionException {
            switch (id) {
                case 0b000:
                    return NON_ZERO;
                case 0b001:
                    return ZERO;
                case 0b010:
                    return NON_CARRY;
                case 0b011:
                    return CARRY;
                case 0b100:
                    return PARITY_ODD;
                case 0b101:
                    return PARITY_EVEN;
                case 0b110:
                    return SIGN_POSITIVE;
                case 0b111:
                    return SIGN_NEGATIVE;
                default:
                    throw new UnsupportedConditionException(id);
            }
        }
    }

    public boolean checkCondition(int id, Z80 z80) throws UnsupportedConditionException {
        return checkCondition(Condition.getById(id), z80);
    }

    public boolean checkCondition(Condition condition, Z80 z80) throws UnsupportedConditionException{
        switch (condition) {
            case NON_ZERO:
                return !z80.getRegs().getFlag(FlagRegManager.Flag.Z);
            case ZERO:
                return z80.getRegs().getFlag(FlagRegManager.Flag.Z);
            case NON_CARRY:
                return !z80.getRegs().getFlag(FlagRegManager.Flag.C);
            case CARRY:
                return z80.getRegs().getFlag(FlagRegManager.Flag.C);
            case PARITY_ODD:
                return !z80.getRegs().getFlag(FlagRegManager.Flag.PV);
            case PARITY_EVEN:
                return z80.getRegs().getFlag(FlagRegManager.Flag.PV);
            case SIGN_POSITIVE:
                return !z80.getRegs().getFlag(FlagRegManager.Flag.S);
            case SIGN_NEGATIVE:
                return z80.getRegs().getFlag(FlagRegManager.Flag.S);
            default:
                throw new UnsupportedConditionException(condition.id);
        }

    }

}
