package edu.psk.z80emu.pin;

import edu.psk.z80emu.exception.InternalOperationNotPermitted;
import edu.psk.z80emu.module.AbstractModule;

public class OutputPin extends Pin {

    public OutputPin(AbstractModule owner, String name) {
        this.owner = owner;
        this.name = name;

        this.state = STATE.OUTPUT;
    }

    /**
     * InputPin cannot change state. To learn more see {@link Pin.STATE}
     * @see Pin.STATE
     * @param state
     */
    @Override
    public void setState(STATE state) {
        throw new InternalOperationNotPermitted();
    }

    @Override
    public void setValueByRoot(boolean value) {
        throw new InternalOperationNotPermitted("cannot change output value by hand");
    }
}
