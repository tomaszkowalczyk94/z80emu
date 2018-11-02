package edu.psk.z80emu.pin;

import edu.psk.z80emu.exception.InternalLogicError;
import edu.psk.z80emu.module.AbstractModule;

public class PinGroup {
    private Pin[] pins;

    public Pin[] getPins() {
        return pins;
    }

    public void setPins(Pin... pins) {
        this.pins = pins;
    }

    public int getIntValue(AbstractModule getterModule) {
        boolean[] values = new boolean[pins.length];

        for(int i = 0; i<pins.length;i++) {
            values[i] = pins[i].getValue(getterModule);
        }

        return booleansToInt(values);
    }

    public int getIntValueByRoot() {
        boolean[] values = new boolean[pins.length];

        for(int i = 0; i<pins.length;i++) {
            values[i] = pins[i].getValueByRoot();
        }

        return booleansToInt(values);
    }

    public void setIntValue(AbstractModule moduleChanging, int value) {

        boolean[] values = intToBooleans(value, pins.length);

        for(int i = 0; i<values.length;i++) {
            pins[i].setValue(moduleChanging, values[i]);
        }
    }

    private int booleansToInt(boolean[] arr){
        int n = 0;
        for (boolean b : arr)
            n = (n << 1) | (b ? 1 : 0);
        return n;
    }

    private boolean[] intToBooleans(int number, int base) {
        final boolean[] ret = new boolean[base];
        for (int i = 0; i < base; i++) {
            ret[base - 1 - i] = (1 << i & number) != 0;
        }
        return ret;
    }
}
