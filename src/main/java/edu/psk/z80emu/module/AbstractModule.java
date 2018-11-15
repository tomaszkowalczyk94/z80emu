package edu.psk.z80emu.module;

import edu.psk.z80emu.pin.Pin;
import edu.psk.z80emu.pin.PinMap;

public abstract class AbstractModule {

    AbstractModule parent;

    protected PinMap pins = new PinMap();

    public PinMap getPins() {
        return pins;
    }

    public abstract void flush();

    public Pin getPin(String name) {
        return getPins().get(name);
    }

    public AbstractModule getParent() {
        return parent;
    }

    public void setParent(AbstractModule parent) {
        this.parent = parent;
    }

    protected boolean getPinVal(String name) {
        return getPin(name).getValue(this);
    }
}
