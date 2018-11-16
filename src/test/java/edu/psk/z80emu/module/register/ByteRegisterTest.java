package edu.psk.z80emu.module.register;

import edu.psk.z80emu.pin.Pin;
import edu.psk.z80emu.waveJson.WaveJsonGenerator;
import org.junit.Assert;
import org.junit.Test;

import static edu.psk.z80emu.module.AbstractModuleWithClock.CLOCK;
import static edu.psk.z80emu.module.register.ByteRegister.DB_7;
import static edu.psk.z80emu.module.register.ByteRegister.ENA;
import static edu.psk.z80emu.module.register.ByteRegister.OUTPUT_ENABLE;
import static org.junit.Assert.*;

public class ByteRegisterTest {

    @Test
    public void test() {
        WaveJsonGenerator waveJsonGenerator = new WaveJsonGenerator();
        ByteRegister byteRegister = new ByteRegister(null);
        byteRegister.setWaveJsonGenerator(waveJsonGenerator);

        //step 1. set input value to 1
        byteRegister.getPin(OUTPUT_ENABLE).setValueByRoot(false);
        byteRegister.getPin(DB_7).setValueByRoot(true);
        byteRegister.getPin(ENA).setValueByRoot(true);
        byteRegister.ticTocAndFlushByRoot();

        Assert.assertEquals(Pin.STATE.INPUT, byteRegister.getPin(DB_7).getState());

        //step2. output enabled
        byteRegister.getPin(OUTPUT_ENABLE).setValueByRoot(true);
        byteRegister.ticTocAndFlushByRoot();

        Assert.assertEquals(Pin.STATE.OUTPUT, byteRegister.getPin(DB_7).getState());
        Assert.assertEquals(true, byteRegister.getPin(DB_7).getValueByRoot());

        System.out.println(waveJsonGenerator.generateJson());
    }
}