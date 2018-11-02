package edu.psk.z80emu.module;

import edu.psk.z80emu.pin.InputPin;
import edu.psk.z80emu.pin.Pin;

public abstract class AbstractModuleWithClock extends AbstractModule {

    public static final String CLOCK = "CLOCK";

    public AbstractModuleWithClock() {
        this.pins.addPin(new InputPin(this, CLOCK));
    }

    @Override
    public void afterInputPinChanged(Pin pin, boolean oldValue) {
        if(isClockPosedge(pin, oldValue)) {
            onClockPosedge();
        }
    }

    protected abstract void onClockPosedge();

    protected boolean isClockPosedge(Pin pin, boolean oldValue) {
        return (pin.getName().equals(CLOCK) && !oldValue && pin.getValue(this));
    }

}
