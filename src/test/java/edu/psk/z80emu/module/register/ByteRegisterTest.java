package edu.psk.z80emu.module.register;

import edu.psk.z80emu.pin.Pin;
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

        ByteRegister byteRegister = new ByteRegister();


        //step 1. set input value to 1
        byteRegister.getPin(OUTPUT_ENABLE).setValueByRoot(false);
        byteRegister.getPin(DB_7).setValueByRoot(true);
        byteRegister.getPin(ENA).setValueByRoot(true);

        Assert.assertEquals(Pin.STATE.INPUT, byteRegister.getPin(DB_7).getState());

        //step2. output enabled
        byteRegister.getPin(CLOCK).ticTokByRoot();
        byteRegister.getPin(OUTPUT_ENABLE).setValueByRoot(true);
        byteRegister.getPin(CLOCK).ticTokByRoot();

        Assert.assertEquals(Pin.STATE.OUTPUT, byteRegister.getPin(DB_7).getState());
        Assert.assertEquals(true, byteRegister.getPin(DB_7).getValueByRoot());


    }
}