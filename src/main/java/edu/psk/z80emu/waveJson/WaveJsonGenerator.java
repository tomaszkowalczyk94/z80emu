package edu.psk.z80emu.waveJson;

import edu.psk.z80emu.module.AbstractModule;
import edu.psk.z80emu.pin.Pin;

import java.util.*;

public class WaveJsonGenerator {

    Map<String, StringBuilder>  valuesMap = new LinkedHashMap<>();

    public WaveJsonGenerator() {

    }

    public void onFlush(AbstractModule module) {
        System.out.println("flushing :");

        module.getPins().forEach( (name, pin) -> {
            System.out.println("pin name: "+name+" value: "+pin.getValueByRoot());

            if(!valuesMap.containsKey(name)) {
                valuesMap.put(name, new StringBuilder());
            }

            char appenedChar = (pin.getValueByRoot()) ?  'x' : '0';

            valuesMap.get(name).append(appenedChar);
        });

    }

    public String generateJson() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{signal: [");

        valuesMap.forEach( (name, value) -> {

            stringBuilder.append("{name: '"+name+"', wave: '"+value+"'},");

        });

        stringBuilder.append("]}");

        return stringBuilder.toString();
    }


}
