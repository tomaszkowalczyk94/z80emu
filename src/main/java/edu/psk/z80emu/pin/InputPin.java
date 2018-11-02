package edu.psk.z80emu.pin;

import edu.psk.z80emu.exception.InternalOperationNotPermitted;
import edu.psk.z80emu.module.AbstractModule;

public class InputPin extends Pin {

    public InputPin(AbstractModule owner, String name) {
        this.owner = owner;
        this.name = name;

        this.state = STATE.INPUT;
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
    public void setValueByModule(AbstractModule moduleChanging, boolean value) {
        throw new InternalOperationNotPermitted("module cannot change the pin value by hand");
    }
}
