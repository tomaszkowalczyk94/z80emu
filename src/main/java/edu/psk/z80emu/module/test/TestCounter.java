package edu.psk.z80emu.module.test;

import edu.psk.z80emu.module.AbstractModuleWithClock;
import edu.psk.z80emu.module.register.ByteRegister;
import edu.psk.z80emu.pin.InputPin;
import edu.psk.z80emu.pin.OutputPin;
import edu.psk.z80emu.pin.PinGroup;

import static edu.psk.z80emu.pin.Pin._0;
import static edu.psk.z80emu.pin.Pin._1;

public class TestCounter extends AbstractModuleWithClock {


    public static final String OUTPUT_DB_0 = "OUTPUT_DB_0";
    public static final String OUTPUT_DB_1 = "OUTPUT_DB_1";
    public static final String OUTPUT_DB_2 = "OUTPUT_DB_2";
    public static final String OUTPUT_DB_3 = "OUTPUT_DB_3";
    public static final String OUTPUT_DB_4 = "OUTPUT_DB_4";
    public static final String OUTPUT_DB_5 = "OUTPUT_DB_5";
    public static final String OUTPUT_DB_6 = "OUTPUT_DB_6";
    public static final String OUTPUT_DB_7 = "OUTPUT_DB_7";

    public static final String COUNT = "COUNT";

    ByteRegister byteRegister = new ByteRegister();

    protected PinGroup outputDbPinGroup = new PinGroup();

    public TestCounter() {
        byteRegister.setParent(this);

        this.pins.addPin(new OutputPin(this, OUTPUT_DB_0));
        this.pins.addPin(new OutputPin(this, OUTPUT_DB_1));
        this.pins.addPin(new OutputPin(this, OUTPUT_DB_2));
        this.pins.addPin(new OutputPin(this, OUTPUT_DB_3));
        this.pins.addPin(new OutputPin(this, OUTPUT_DB_4));
        this.pins.addPin(new OutputPin(this, OUTPUT_DB_5));
        this.pins.addPin(new OutputPin(this, OUTPUT_DB_6));
        this.pins.addPin(new OutputPin(this, OUTPUT_DB_7));

        outputDbPinGroup.setPins(
                pins.get(OUTPUT_DB_0),
                pins.get(OUTPUT_DB_1),
                pins.get(OUTPUT_DB_2),
                pins.get(OUTPUT_DB_3),
                pins.get(OUTPUT_DB_4),
                pins.get(OUTPUT_DB_5),
                pins.get(OUTPUT_DB_6),
                pins.get(OUTPUT_DB_7)
        );

        this.pins.addPin(new InputPin(this, COUNT));

    }

    @Override
    protected void onClockPosedge() {
        if(getPin(COUNT).getValue(this)) {

            byteRegister.setInputs(this, _1, _0, _0, null  );
            byteRegister.ticTocAndFlush(this);


            int intValue = byteRegister.getDbPinGroup().getIntValue(this);

            intValue++;

//            //save new value to byte register
            byteRegister.setInputs(this, _0, _1, _0, null);
            byteRegister.ticTocAndFlush(this);
            byteRegister.setInputs(this, _0, _1, _0, intValue);
            byteRegister.ticTocAndFlush(this);

            outputDbPinGroup.setValue(
                    this,
                    intValue
            );

        }

        byteRegister.getPin(CLOCK).setValue(this, this.getPin(CLOCK).getValue(this));
    }

    public PinGroup getOutputDbPinGroup() {
        return outputDbPinGroup;
    }
}
