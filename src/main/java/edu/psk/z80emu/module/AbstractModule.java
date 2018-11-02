package edu.psk.z80emu.module;

import edu.psk.z80emu.pin.Pin;
import edu.psk.z80emu.pin.PinMap;

public abstract class AbstractModule {



    protected PinMap pins = new PinMap();

    public PinMap getPins() {
        return pins;
    }

    public abstract void afterInputPinChanged(Pin pin, boolean oldValue);

    public Pin getPin(String name) {
        return getPins().get(name);
    }


}
