package edu.psk.z80emu.pin;

import edu.psk.z80emu.module.AbstractModule;

public class InOutPin extends Pin {

    public InOutPin(AbstractModule owner, String name) {
        super(owner, name);

        this.state = STATE.INPUT;
    }
}
