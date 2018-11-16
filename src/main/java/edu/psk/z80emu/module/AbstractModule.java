package edu.psk.z80emu.module;

import edu.psk.z80emu.pin.Pin;
import edu.psk.z80emu.pin.PinMap;
import edu.psk.z80emu.waveJson.WaveJsonGenerator;

public abstract class AbstractModule {

    public AbstractModule(AbstractModule parent) {
        this.parent = parent;
    }

    WaveJsonGenerator waveJsonGenerator = null;

    AbstractModule parent;

    protected PinMap pins = new PinMap();

    public PinMap getPins() {
        return pins;
    }

    public void flush() {
        onFlush();
        if(waveJsonGenerator != null) {
            waveJsonGenerator.onFlush(this);
        }
    }

    public abstract void onFlush();

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

    public WaveJsonGenerator getWaveJsonGenerator() {
        return waveJsonGenerator;
    }

    public void setWaveJsonGenerator(WaveJsonGenerator waveJsonGenerator) {
        this.waveJsonGenerator = waveJsonGenerator;
    }
}
