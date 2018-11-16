package edu.psk.z80emu.module.register.registerFile;

import edu.psk.z80emu.waveJson.WaveJsonGenerator;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterFileModuleTest {

    @Test
    public void test() {
        WaveJsonGenerator waveJsonGenerator = new WaveJsonGenerator();
        RegisterFileModule registerFileModule = new RegisterFileModule(null);
        registerFileModule.setWaveJsonGenerator(waveJsonGenerator);


        registerFileModule.flush();


        System.out.println(waveJsonGenerator.generateJson());
    }

}