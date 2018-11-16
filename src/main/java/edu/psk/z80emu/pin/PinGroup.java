package edu.psk.z80emu.pin;

import edu.psk.z80emu.exception.InternalLogicError;
import edu.psk.z80emu.module.AbstractModule;
import edu.psk.z80emu.util.Helper;

import static edu.psk.z80emu.pin.Pin._0;
import static edu.psk.z80emu.pin.Pin._1;

public class PinGroup {

    public static boolean[] TRUE_8B = {_1,_1,_1,_1,_1,_1,_1,_1};
    public static boolean[] FALSE_8B = {_0,_0,_0,_0,_0,_0,_0,_0};
    private Pin[] pins;

    public Pin[] getPins() {
        return pins;
    }

    public void setPins(Pin... pins) {
        this.pins = pins;
    }

    public int getIntValue(AbstractModule getterModule) {
        boolean[] values = getBooleanArray(getterModule);
        return Helper.booleansToInt(values);
    }

    public int getIntValueByRoot() {
        boolean[] values = getBooleanArrayByRoot();
        return Helper.booleansToInt(values);
    }

    public boolean[] getBooleanArray(AbstractModule getterModule) {
        boolean[] values = new boolean[pins.length];

        for(int i = 0; i<pins.length;i++) {
            values[i] = pins[i].getValue(getterModule);
        }

        return values;
    }

    public boolean[] getBooleanArrayByRoot() {
        boolean[] values = new boolean[pins.length];

        for(int i = 0; i<pins.length;i++) {
            values[i] = pins[i].getValueByRoot();
        }

        return values;
    }

    public void setValue(AbstractModule moduleChanging, int value) {

        boolean[] values = Helper.intToBooleans(value, pins.length);

        setValue(moduleChanging, values);
    }

    public void setValue(AbstractModule moduleChanging, boolean[] values) {
        for(int i = 0; i<values.length;i++) {
            pins[i].setValue(moduleChanging, values[i]);
        }
    }


}
