package edu.psk.z80emu.module.test;

import edu.psk.z80emu.module.AbstractModuleWithClock;
import edu.psk.z80emu.pin.InOutPin;
import edu.psk.z80emu.pin.InputPin;


public class ByteRegister extends AbstractModuleWithClock {


    public static final String  OUTPUT_ENABLE = "OUTPUT_ENABLE";

    public static final String ENA = "ENA"; // latch enable or clock enable input

    public static final String DB_0 = "DB_0";
    public static final String DB_1 = "DB_1";
    public static final String DB_2 = "DB_2";
    public static final String DB_3 = "DB_3";
    public static final String DB_4 = "DB_4";
    public static final String DB_5 = "DB_5";
    public static final String DB_6 = "DB_6";
    public static final String DB_7 = "DB_7";


    private boolean[] value = new boolean[8];

    public ByteRegister() {

        this.pins.addPin(new InputPin(this, OUTPUT_ENABLE));
        this.pins.addPin(new InputPin(this, CLOCK));
        this.pins.addPin(new InputPin(this, ENA));

        this.pins.addPin(new InOutPin(this, DB_0));
        this.pins.addPin(new InOutPin(this, DB_1));
        this.pins.addPin(new InOutPin(this, DB_2));
        this.pins.addPin(new InOutPin(this, DB_3));
        this.pins.addPin(new InOutPin(this, DB_4));
        this.pins.addPin(new InOutPin(this, DB_5));
        this.pins.addPin(new InOutPin(this, DB_6));
        this.pins.addPin(new InOutPin(this, DB_7));

    }


    @Override
    protected void onClockPosedge() {



    }
}
