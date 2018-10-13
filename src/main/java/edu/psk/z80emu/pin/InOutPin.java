package edu.psk.z80emu.pin;

import edu.psk.z80emu.exception.InternalOperationNotPermitted;
import edu.psk.z80emu.module.AbstractModule;

public class InOutPin extends Pin {
    @Override
    public void setValue(AbstractModule moduleChanging, boolean value) {
        if(state == STATE.INPUT && moduleChanging == owner) {
            throw new InternalOperationNotPermitted("InOut pin is in input state, pin owner cannot change value");
        }

        if(state == STATE.OUTPUT && moduleChanging != owner) {
            throw new InternalOperationNotPermitted("InOut pin is in output state, only pin owner can change value");
        }

        this.value = value;
    }
}
