package edu.psk.z80emu.pin;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Map used in {@link Pin}
 */
public class PinMap extends LinkedHashMap<String, Pin> {

    public void addPin(Pin pin) {
        this.put(pin.getName(), pin);
    }


}
