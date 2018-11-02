package edu.psk.z80emu.pin;

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

    private int booleansToInt(boolean[] arr){
        int n = 0;
        for (boolean b : arr)
            n = (n << 1) | (b ? 1 : 0);
        return n;
    }
}
