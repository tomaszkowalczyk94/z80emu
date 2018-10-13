package edu.psk.z80emu.pin;

import edu.psk.z80emu.module.AbstractModule;

import java.util.Observable;

public abstract class Pin {

    /**
     * Pin can by in <b>only one STATE</b> at a time.
     * <p>
     * {@link edu.psk.z80emu.pin.InputPin} and {@link edu.psk.z80emu.pin.OutputPin} has defined state, which cannot be changed.
     * {@link edu.psk.z80emu.pin.InOutPin} have state, which can be changed in runtime.
     */
    enum STATE {
        INPUT,
        OUTPUT
    };

    protected boolean value;

    protected STATE state;

    String name;

    /**
     * Module which is a owner of Pin. Each pin can has only one owner.
     */
    protected AbstractModule owner;

    public boolean getValue() {
        return value;
    }

    abstract public void setValue(AbstractModule moduleChanging, boolean value);

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public AbstractModule getOwner() {
        return owner;
    }
}
