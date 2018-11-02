package edu.psk.z80emu.pin;

import java.util.HashMap;

/**
 * Map used in {@link Pin}
 */
public class PinMap extends HashMap<String, Pin> {

    public void addPin(Pin pin) {
        this.put(pin.getName(), pin);
    }

}
