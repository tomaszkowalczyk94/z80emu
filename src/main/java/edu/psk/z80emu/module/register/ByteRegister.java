package edu.psk.z80emu.module.register;

import edu.psk.z80emu.module.AbstractModuleWithClock;
import edu.psk.z80emu.pin.InOutPin;
import edu.psk.z80emu.pin.InputPin;
import edu.psk.z80emu.pin.Pin;
import edu.psk.z80emu.pin.PinGroup;


public class ByteRegister extends AbstractModuleWithClock {


    public static final String  OUTPUT_ENABLE = "OUTPUT_ENABLE";

    public static final String ENA = "ENA"; // latch enable

    public static final String DB_0 = "DB_0";
    public static final String DB_1 = "DB_1";
    public static final String DB_2 = "DB_2";
    public static final String DB_3 = "DB_3";
    public static final String DB_4 = "DB_4";
    public static final String DB_5 = "DB_5";
    public static final String DB_6 = "DB_6";
    public static final String DB_7 = "DB_7";

    protected PinGroup dbPinGroup = new PinGroup();

    private boolean[] value = new boolean[8];

    public ByteRegister() {
        super();

        pins.addPin(new InputPin(this, OUTPUT_ENABLE));
        pins.addPin(new InputPin(this, ENA));

        pins.addPin(new InOutPin(this, DB_7));
        pins.addPin(new InOutPin(this, DB_6));
        pins.addPin(new InOutPin(this, DB_5));
        pins.addPin(new InOutPin(this, DB_4));
        pins.addPin(new InOutPin(this, DB_3));
        pins.addPin(new InOutPin(this, DB_2));
        pins.addPin(new InOutPin(this, DB_1));
        pins.addPin(new InOutPin(this, DB_0));

        dbPinGroup.setPins(
                pins.get(DB_7),
                pins.get(DB_6),
                pins.get(DB_5),
                pins.get(DB_4),
                pins.get(DB_3),
                pins.get(DB_2),
                pins.get(DB_1),
                pins.get(DB_0)
        );
    }


    @Override
    protected void onClockPosedge() {
        updateStateOfDbByOutputEnable();
        updateLatch();
        updateOutput();
    }

    protected void updateLatch() {
        if(!getPin(OUTPUT_ENABLE).getValue(this) && getPin(ENA).getValue(this)) {
            value[0] = getPin(DB_7).getValue(this);
            value[1] = getPin(DB_6).getValue(this);
            value[2] = getPin(DB_5).getValue(this);
            value[3] = getPin(DB_4).getValue(this);
            value[4] = getPin(DB_3).getValue(this);
            value[5] = getPin(DB_2).getValue(this);
            value[6] = getPin(DB_1).getValue(this);
            value[7] = getPin(DB_0).getValue(this);
        }
    }

    protected void updateOutput() {
        if(getPin(OUTPUT_ENABLE).getValue(this)) {
            getPin(DB_7).setValue(this, value[0]);
            getPin(DB_6).setValue(this, value[1]);
            getPin(DB_5).setValue(this, value[2]);
            getPin(DB_4).setValue(this, value[3]);
            getPin(DB_3).setValue(this, value[4]);
            getPin(DB_2).setValue(this, value[5]);
            getPin(DB_1).setValue(this, value[6]);
            getPin(DB_0).setValue(this, value[7]);
        }
    }

    protected void updateStateOfDbByOutputEnable() {
        boolean outputEnable = getPin(OUTPUT_ENABLE).getValue(this);
        Pin.STATE newState = (outputEnable) ? Pin.STATE.OUTPUT : Pin.STATE.INPUT;

        getPin(DB_7).setState(newState);
        getPin(DB_6).setState(newState);
        getPin(DB_5).setState(newState);
        getPin(DB_4).setState(newState);
        getPin(DB_3).setState(newState);
        getPin(DB_2).setState(newState);
        getPin(DB_1).setState(newState);
        getPin(DB_0).setState(newState);
    }

    public PinGroup getDbPinGroup() {
        return dbPinGroup;
    }
}
