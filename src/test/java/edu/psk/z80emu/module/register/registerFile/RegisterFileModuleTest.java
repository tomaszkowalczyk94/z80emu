package edu.psk.z80emu.module.register.registerFile;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterFileModuleTest {

    @Test
    public void test() {
        RegisterFileModule registerFileModule = new RegisterFileModule();

        registerFileModule.flush();

    }

}