package edu.psk.z80emu.module;

import edu.psk.z80emu.pin.InputPin;
import edu.psk.z80emu.pin.Pin;
import edu.psk.z80emu.waveJson.WaveJsonGenerator;

public abstract class AbstractModuleWithClock extends AbstractModule {

    public static final String CLOCK = "CLOCK";

    public AbstractModuleWithClock() {
        this.pins.addPin(new InputPin(this, CLOCK));
    }

    @Override
    public void onFlush() {
        if(isClockPosedge()) {
            onClockPosedge();
        }
        lastClockState = getPin(CLOCK).getValue(this);
    }

    protected abstract void onClockPosedge();

    boolean lastClockState = false;

    protected boolean isClockPosedge() {
        return ( getPin(CLOCK).getValue(this) && !lastClockState);
    }

    public void ticTocAndFlush(AbstractModule moduleChanging ) {
        getPin(CLOCK).setValue(moduleChanging, true);
        flush();
        getPin(CLOCK).setValue(moduleChanging, false);
        flush();
    }

    public void ticTocAndFlushByRoot() {
        getPin(CLOCK).setValueByRoot(false);
        flush();
        getPin(CLOCK).setValueByRoot(true);
        flush();
    }

}
