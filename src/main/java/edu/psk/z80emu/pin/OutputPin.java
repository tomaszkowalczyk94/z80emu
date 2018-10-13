package edu.psk.z80emu.pin;

import edu.psk.z80emu.exception.InternalOperationNotPermitted;
import edu.psk.z80emu.module.AbstractModule;

public class OutputPin extends Pin {

    @Override
    public void setValue(AbstractModule moduleChanging, boolean value) {
        if(moduleChanging != owner) {
            throw new InternalOperationNotPermitted("only pin owner can change output value");
        }
        this.value = value;
    }

    /**
     * InputPin cannot change state. To learn more see {@link Pin.STATE}
     * @see Pin.STATE
     * @param state
     */
    public void setState(STATE state) {
        throw new InternalOperationNotPermitted();
    }
}
