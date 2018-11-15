package edu.psk.z80emu.module.register.registerFile;

import edu.psk.z80emu.module.AbstractModuleWithClock;
import edu.psk.z80emu.module.register.ByteRegister;
import edu.psk.z80emu.pin.InOutPin;
import edu.psk.z80emu.pin.InputPin;
import edu.psk.z80emu.pin.PinGroup;

import static edu.psk.z80emu.pin.PinGroup.FALSE_8B;

public class RegisterFileModule extends AbstractModuleWithClock {

    public static final String  REG_SEL_SYS_LO = "REG_SEL_SYS_LO"; //select system register output on???
    public static final String  REG_SEL_GP_LO = "REG_SEL_GP_LO";
    public static final String  REG_SEL_SYS_HI = "REG_SEL_SYS_HI";
    public static final String  REG_SEL_GP_HI = "REG_SEL_GP_HI";
    public static final String  REG_SEL_IR = "REG_SEL_IR";
    public static final String  REG_SEL_PC = "REG_SEL_PC";
    public static final String  CTL_SW_4U = "CTL_SW_4U";
    public static final String  REG_SEL_WZ = "REG_SEL_WZ";
    public static final String  REG_SEL_SP = "REG_SEL_SP";
    public static final String  REG_SEL_IY = "REG_SEL_IY";
    public static final String  REG_SEL_IX = "REG_SEL_IX";
    public static final String  REG_SEL_HL2 = "REG_SEL_HL2";
    public static final String  REG_SEL_HL = "REG_SEL_HL";
    public static final String  REG_SEL_DE2 = "REG_SEL_DE2";
    public static final String  REG_SEL_DE = "REG_SEL_DE";
    public static final String  REG_SEL_BC2 = "REG_SEL_BC2";
    public static final String  REG_SEL_BC = "REG_SEL_BC";
    public static final String  REG_SEL_AF2 = "REG_SEL_AF2";
    public static final String  REG_SEL_AF = "REG_SEL_AF";
    public static final String  REG_GP_WE = "REG_GP_WE";
    public static final String  REG_SYS_WE_LO = "REG_SYS_WE_LO"; // save data to selected register
    public static final String  REG_SYS_WE_HI = "REG_SYS_WE_HI";
    public static final String  CTL_REG_IN_HI = "CTL_REG_IN_HI";
    public static final String  CTL_REG_IN_LO = "CTL_REG_IN_LO";
    public static final String  CTL_REG_OUT_LO = "CTL_REG_OUT_LO";
    public static final String  CTL_REG_OUT_HI = "CTL_REG_OUT_HI";
    public static final String  REG_SW_4D_LO = "REG_SW_4D_LO";
    public static final String  REG_SW_4D_HI = "REG_SW_4D_HI";

    public static final String  DB_HI_AS_7 = "DB_HI_AS_7";
    public static final String  DB_HI_AS_6 = "DB_HI_AS_6";
    public static final String  DB_HI_AS_5 = "DB_HI_AS_5";
    public static final String  DB_HI_AS_4 = "DB_HI_AS_4";
    public static final String  DB_HI_AS_3 = "DB_HI_AS_3";
    public static final String  DB_HI_AS_2 = "DB_HI_AS_2";
    public static final String  DB_HI_AS_1 = "DB_HI_AS_1";
    public static final String  DB_HI_AS_0 = "DB_HI_AS_0";

    public static final String  DB_HI_DS_7 = "DB_HI_DS_7";
    public static final String  DB_HI_DS_6 = "DB_HI_DS_6";
    public static final String  DB_HI_DS_5 = "DB_HI_DS_5";
    public static final String  DB_HI_DS_4 = "DB_HI_DS_4";
    public static final String  DB_HI_DS_3 = "DB_HI_DS_3";
    public static final String  DB_HI_DS_2 = "DB_HI_DS_2";
    public static final String  DB_HI_DS_1 = "DB_HI_DS_1";
    public static final String  DB_HI_DS_0 = "DB_HI_DS_0";

    public static final String  DB_LO_AS_7 = "DB_LO_AS_7";
    public static final String  DB_LO_AS_6 = "DB_LO_AS_6";
    public static final String  DB_LO_AS_5 = "DB_LO_AS_5";
    public static final String  DB_LO_AS_4 = "DB_LO_AS_4";
    public static final String  DB_LO_AS_3 = "DB_LO_AS_3";
    public static final String  DB_LO_AS_2 = "DB_LO_AS_2";
    public static final String  DB_LO_AS_1 = "DB_LO_AS_1";
    public static final String  DB_LO_AS_0 = "DB_LO_AS_0";

    public static final String  DB_LO_DS_7 = "DB_LO_DS_7";
    public static final String  DB_LO_DS_6 = "DB_LO_DS_6";
    public static final String  DB_LO_DS_5 = "DB_LO_DS_5";
    public static final String  DB_LO_DS_4 = "DB_LO_DS_4";
    public static final String  DB_LO_DS_3 = "DB_LO_DS_3";
    public static final String  DB_LO_DS_2 = "DB_LO_DS_2";
    public static final String  DB_LO_DS_1 = "DB_LO_DS_1";
    public static final String  DB_LO_DS_0 = "DB_LO_DS_0";

    protected PinGroup dbHiAs = new PinGroup();
    protected PinGroup dbHiDs = new PinGroup();
    protected PinGroup dbLoAs = new PinGroup();
    protected PinGroup dbLoDs = new PinGroup();

    public RegisterFileModule() {
        pins.addPin(new InputPin(this, REG_SEL_SYS_LO));
        pins.addPin(new InputPin(this, REG_SEL_GP_LO));
        pins.addPin(new InputPin(this, REG_SEL_SYS_HI));
        pins.addPin(new InputPin(this, REG_SEL_GP_HI));
        pins.addPin(new InputPin(this, REG_SEL_IR));
        pins.addPin(new InputPin(this, REG_SEL_PC));
        pins.addPin(new InputPin(this, CTL_SW_4U));
        pins.addPin(new InputPin(this, REG_SEL_WZ));
        pins.addPin(new InputPin(this, REG_SEL_SP));
        pins.addPin(new InputPin(this, REG_SEL_IY));
        pins.addPin(new InputPin(this, REG_SEL_IX));
        pins.addPin(new InputPin(this, REG_SEL_HL2));
        pins.addPin(new InputPin(this, REG_SEL_HL));
        pins.addPin(new InputPin(this, REG_SEL_DE2));
        pins.addPin(new InputPin(this, REG_SEL_DE));
        pins.addPin(new InputPin(this, REG_SEL_BC2));
        pins.addPin(new InputPin(this, REG_SEL_BC));
        pins.addPin(new InputPin(this, REG_SEL_AF2));
        pins.addPin(new InputPin(this, REG_SEL_AF));
        pins.addPin(new InputPin(this, REG_GP_WE));
        pins.addPin(new InputPin(this, REG_SYS_WE_LO));
        pins.addPin(new InputPin(this, REG_SYS_WE_HI));
        pins.addPin(new InputPin(this, CTL_REG_IN_HI));
        pins.addPin(new InputPin(this, CTL_REG_IN_LO));
        pins.addPin(new InputPin(this, CTL_REG_OUT_LO));
        pins.addPin(new InputPin(this, CTL_REG_OUT_HI));
        pins.addPin(new InputPin(this, REG_SW_4D_LO));
        pins.addPin(new InputPin(this, REG_SW_4D_HI));

        pins.addPin(new InOutPin(this, DB_HI_AS_7));
        pins.addPin(new InOutPin(this, DB_HI_AS_6));
        pins.addPin(new InOutPin(this, DB_HI_AS_5));
        pins.addPin(new InOutPin(this, DB_HI_AS_4));
        pins.addPin(new InOutPin(this, DB_HI_AS_3));
        pins.addPin(new InOutPin(this, DB_HI_AS_2));
        pins.addPin(new InOutPin(this, DB_HI_AS_1));
        pins.addPin(new InOutPin(this, DB_HI_AS_0));

        pins.addPin(new InOutPin(this, DB_HI_DS_7));
        pins.addPin(new InOutPin(this, DB_HI_DS_6));
        pins.addPin(new InOutPin(this, DB_HI_DS_5));
        pins.addPin(new InOutPin(this, DB_HI_DS_4));
        pins.addPin(new InOutPin(this, DB_HI_DS_3));
        pins.addPin(new InOutPin(this, DB_HI_DS_2));
        pins.addPin(new InOutPin(this, DB_HI_DS_1));
        pins.addPin(new InOutPin(this, DB_HI_DS_0));

        pins.addPin(new InOutPin(this, DB_LO_AS_7));
        pins.addPin(new InOutPin(this, DB_LO_AS_6));
        pins.addPin(new InOutPin(this, DB_LO_AS_5));
        pins.addPin(new InOutPin(this, DB_LO_AS_4));
        pins.addPin(new InOutPin(this, DB_LO_AS_3));
        pins.addPin(new InOutPin(this, DB_LO_AS_2));
        pins.addPin(new InOutPin(this, DB_LO_AS_1));
        pins.addPin(new InOutPin(this, DB_LO_AS_0));

        pins.addPin(new InOutPin(this, DB_LO_DS_7));
        pins.addPin(new InOutPin(this, DB_LO_DS_6));
        pins.addPin(new InOutPin(this, DB_LO_DS_5));
        pins.addPin(new InOutPin(this, DB_LO_DS_4));
        pins.addPin(new InOutPin(this, DB_LO_DS_3));
        pins.addPin(new InOutPin(this, DB_LO_DS_2));
        pins.addPin(new InOutPin(this, DB_LO_DS_1));
        pins.addPin(new InOutPin(this, DB_LO_DS_0));

        dbHiAs.setPins(
                pins.get(DB_HI_AS_7),
                pins.get(DB_HI_AS_6),
                pins.get(DB_HI_AS_5),
                pins.get(DB_HI_AS_4),
                pins.get(DB_HI_AS_3),
                pins.get(DB_HI_AS_2),
                pins.get(DB_HI_AS_1),
                pins.get(DB_HI_AS_0)
        );

        dbHiDs.setPins(
                pins.get(DB_HI_DS_7),
                pins.get(DB_HI_DS_6),
                pins.get(DB_HI_DS_5),
                pins.get(DB_HI_DS_4),
                pins.get(DB_HI_DS_3),
                pins.get(DB_HI_DS_2),
                pins.get(DB_HI_DS_1),
                pins.get(DB_HI_DS_0)
        );

        dbLoAs.setPins(
                pins.get(DB_LO_AS_7),
                pins.get(DB_LO_AS_6),
                pins.get(DB_LO_AS_5),
                pins.get(DB_LO_AS_4),
                pins.get(DB_LO_AS_3),
                pins.get(DB_LO_AS_2),
                pins.get(DB_LO_AS_1),
                pins.get(DB_LO_AS_0)
        );

        dbLoDs.setPins(
                pins.get(DB_LO_DS_7),
                pins.get(DB_LO_DS_6),
                pins.get(DB_LO_DS_5),
                pins.get(DB_LO_DS_4),
                pins.get(DB_LO_DS_3),
                pins.get(DB_LO_DS_2),
                pins.get(DB_LO_DS_1),
                pins.get(DB_LO_DS_0)
        );
    }


    ByteRegister b2v_latch_af2_hi = new ByteRegister();
    ByteRegister b2v_latch_af2_lo = new ByteRegister();
    ByteRegister b2v_latch_af_hi = new ByteRegister();
    ByteRegister b2v_latch_af_lo = new ByteRegister();
    ByteRegister b2v_latch_bc2_hi = new ByteRegister();
    ByteRegister b2v_latch_bc2_lo = new ByteRegister();
    ByteRegister b2v_latch_bc_hi = new ByteRegister();
    ByteRegister b2v_latch_bc_lo = new ByteRegister();
    ByteRegister b2v_latch_de2_hi = new ByteRegister();
    ByteRegister b2v_latch_de2_lo = new ByteRegister();
    ByteRegister b2v_latch_de_hi = new ByteRegister();
    ByteRegister b2v_latch_de_lo = new ByteRegister();
    ByteRegister b2v_latch_hl2_hi = new ByteRegister();
    ByteRegister b2v_latch_hl2_lo = new ByteRegister();
    ByteRegister b2v_latch_hl_hi = new ByteRegister();
    ByteRegister b2v_latch_hl_lo = new ByteRegister();
    ByteRegister b2v_latch_ir_hi = new ByteRegister();
    ByteRegister b2v_latch_ir_lo = new ByteRegister();
    ByteRegister b2v_latch_ix_hi = new ByteRegister();
    ByteRegister b2v_latch_ix_lo = new ByteRegister();
    ByteRegister b2v_latch_iy_hi = new ByteRegister();
    ByteRegister b2v_latch_iy_lo = new ByteRegister();
    ByteRegister b2v_latch_pc_hi = new ByteRegister();
    ByteRegister b2v_latch_pc_lo = new ByteRegister();
    ByteRegister b2v_latch_sp_hi = new ByteRegister();
    ByteRegister b2v_latch_sp_lo = new ByteRegister();
    ByteRegister b2v_latch_wz_hi = new ByteRegister();
    ByteRegister b2v_latch_wz_lo = new ByteRegister();




    @Override
    public void flush() {
        super.flush();

        boolean clk = getPinVal(CLOCK);

        boolean[] gdfx_temp0 = FALSE_8B;
        boolean[] gdfx_temp1 = FALSE_8B;

        boolean SYNTHESIZED_WIRE_84 = !getPinVal(REG_SYS_WE_LO);
        boolean SYNTHESIZED_WIRE_86 = !getPinVal(REG_GP_WE);
        boolean SYNTHESIZED_WIRE_85 = !getPinVal(REG_SYS_WE_HI);

        boolean SYNTHESIZED_WIRE_82 = SYNTHESIZED_WIRE_84 && getPinVal(REG_SEL_SYS_LO) && getPinVal(REG_SEL_WZ);
        boolean SYNTHESIZED_WIRE_80 = getPinVal(REG_SEL_WZ) && getPinVal(REG_SEL_SYS_HI) && SYNTHESIZED_WIRE_85;
        boolean SYNTHESIZED_WIRE_78 = SYNTHESIZED_WIRE_86 && getPinVal(REG_SEL_GP_LO) && getPinVal(REG_SEL_SP);
        boolean SYNTHESIZED_WIRE_76 = getPinVal(REG_SEL_SP) && getPinVal(REG_SEL_GP_HI) && SYNTHESIZED_WIRE_86;
        boolean SYNTHESIZED_WIRE_71 = getPinVal(REG_SEL_GP_LO) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_IY);
        boolean SYNTHESIZED_WIRE_74 = SYNTHESIZED_WIRE_84 && getPinVal(REG_SEL_SYS_LO) &&  getPinVal(REG_SEL_PC);
        boolean SYNTHESIZED_WIRE_67 = getPinVal(REG_SEL_GP_LO) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_IX);
        
        boolean SYNTHESIZED_WIRE_55 = getPinVal(REG_SEL_GP_LO) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_HL2);
        boolean SYNTHESIZED_WIRE_72 = getPinVal(REG_SEL_PC) && getPinVal(REG_SEL_SYS_HI) && SYNTHESIZED_WIRE_85;
        boolean SYNTHESIZED_WIRE_59 = getPinVal(REG_SEL_GP_LO) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_HL);
        boolean SYNTHESIZED_WIRE_47 = getPinVal(REG_SEL_GP_LO) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_DE2);
        boolean SYNTHESIZED_WIRE_51 = getPinVal(REG_SEL_GP_LO) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_DE);
        boolean SYNTHESIZED_WIRE_81 = getPinVal(REG_SEL_WZ) && getPinVal(REG_SYS_WE_HI) && getPinVal(REG_SEL_SYS_HI);
        
        boolean SYNTHESIZED_WIRE_70 = SYNTHESIZED_WIRE_86 && getPinVal(REG_SEL_GP_LO) && getPinVal(REG_SEL_IY);
        boolean SYNTHESIZED_WIRE_68 = getPinVal(REG_SEL_IY) && getPinVal(REG_SEL_GP_HI) && SYNTHESIZED_WIRE_86;
        boolean SYNTHESIZED_WIRE_39 = getPinVal(REG_SEL_GP_LO) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_BC2);
        boolean SYNTHESIZED_WIRE_43 = getPinVal(REG_SEL_GP_LO) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_BC);
        boolean SYNTHESIZED_WIRE_31 = getPinVal(REG_SEL_GP_LO) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_AF2);
        boolean SYNTHESIZED_WIRE_77 = getPinVal(REG_SEL_SP) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_GP_HI);

        boolean SYNTHESIZED_WIRE_66 = SYNTHESIZED_WIRE_86 && getPinVal(REG_SEL_GP_LO) && getPinVal(REG_SEL_IX);
        boolean SYNTHESIZED_WIRE_64 = getPinVal(REG_SEL_IX) && getPinVal(REG_SEL_GP_HI) && SYNTHESIZED_WIRE_86;
        boolean SYNTHESIZED_WIRE_35 = getPinVal(REG_SEL_GP_LO) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_AF);
        boolean SYNTHESIZED_WIRE_69 = getPinVal(REG_SEL_IY) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_GP_HI);
        boolean SYNTHESIZED_WIRE_63 = getPinVal(REG_SEL_SYS_LO) && getPinVal(REG_SYS_WE_LO) && getPinVal(REG_SEL_IR);
        boolean SYNTHESIZED_WIRE_65 = getPinVal(REG_SEL_IX) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_GP_HI);
        boolean SYNTHESIZED_WIRE_53 = getPinVal(REG_SEL_HL2) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_GP_HI);

        boolean SYNTHESIZED_WIRE_54 = SYNTHESIZED_WIRE_86 && getPinVal(REG_SEL_GP_LO) && getPinVal(REG_SEL_HL2);
        boolean SYNTHESIZED_WIRE_52 = getPinVal(REG_SEL_HL2) &&getPinVal( REG_SEL_GP_HI) && SYNTHESIZED_WIRE_86;
        boolean SYNTHESIZED_WIRE_57 = getPinVal(REG_SEL_HL) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_GP_HI);
        boolean SYNTHESIZED_WIRE_45 = getPinVal(REG_SEL_DE2) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_GP_HI);
        boolean SYNTHESIZED_WIRE_49 = getPinVal(REG_SEL_DE) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_GP_HI);
        boolean SYNTHESIZED_WIRE_37 = getPinVal(REG_SEL_BC2) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_GP_HI);

        boolean SYNTHESIZED_WIRE_58 = SYNTHESIZED_WIRE_86 && getPinVal(REG_SEL_GP_LO) && getPinVal(REG_SEL_HL);
        boolean SYNTHESIZED_WIRE_56 = getPinVal(REG_SEL_HL) && getPinVal(REG_SEL_GP_HI) && SYNTHESIZED_WIRE_86;
        boolean SYNTHESIZED_WIRE_75 = getPinVal(REG_SEL_SYS_LO) && getPinVal(REG_SYS_WE_LO) && getPinVal(REG_SEL_PC);
        boolean SYNTHESIZED_WIRE_41 = getPinVal(REG_SEL_BC) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_GP_HI);
        boolean SYNTHESIZED_WIRE_29 = getPinVal(REG_SEL_AF2) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_GP_HI);
        boolean SYNTHESIZED_WIRE_33 = getPinVal(REG_SEL_AF) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_GP_HI);
        boolean SYNTHESIZED_WIRE_61 = getPinVal(REG_SEL_IR) && getPinVal(REG_SYS_WE_HI) && getPinVal(REG_SEL_SYS_HI);

        boolean SYNTHESIZED_WIRE_46 = SYNTHESIZED_WIRE_86 && getPinVal(REG_SEL_GP_LO) && getPinVal(REG_SEL_DE2);
        boolean SYNTHESIZED_WIRE_44 = getPinVal(REG_SEL_DE2) && getPinVal(REG_SEL_GP_HI) && SYNTHESIZED_WIRE_86;
        boolean SYNTHESIZED_WIRE_73 = getPinVal(REG_SEL_PC) && getPinVal(REG_SYS_WE_HI) && getPinVal(REG_SEL_SYS_HI);
        boolean SYNTHESIZED_WIRE_83 = getPinVal(REG_SEL_SYS_LO) && getPinVal(REG_SYS_WE_LO) && getPinVal(REG_SEL_WZ);
        boolean SYNTHESIZED_WIRE_50 = SYNTHESIZED_WIRE_86 && getPinVal(REG_SEL_GP_LO) && getPinVal(REG_SEL_DE);
        boolean SYNTHESIZED_WIRE_48 = getPinVal(REG_SEL_DE) && getPinVal(REG_SEL_GP_HI) && SYNTHESIZED_WIRE_86;
        boolean SYNTHESIZED_WIRE_38 = SYNTHESIZED_WIRE_86 && getPinVal(REG_SEL_GP_LO) && getPinVal(REG_SEL_BC2);

        boolean SYNTHESIZED_WIRE_36 = getPinVal(REG_SEL_BC2) && getPinVal(REG_SEL_GP_HI) && SYNTHESIZED_WIRE_86;
        boolean SYNTHESIZED_WIRE_42 = SYNTHESIZED_WIRE_86 && getPinVal(REG_SEL_GP_LO) && getPinVal(REG_SEL_BC);
        boolean SYNTHESIZED_WIRE_40 = getPinVal(REG_SEL_BC) && getPinVal(REG_SEL_GP_HI) && SYNTHESIZED_WIRE_86;
        boolean SYNTHESIZED_WIRE_30 = SYNTHESIZED_WIRE_86 && getPinVal(REG_SEL_GP_LO) && getPinVal(REG_SEL_AF2);
        boolean SYNTHESIZED_WIRE_28 = getPinVal(REG_SEL_AF2) && getPinVal(REG_SEL_GP_HI) && SYNTHESIZED_WIRE_86;
        boolean SYNTHESIZED_WIRE_62 = SYNTHESIZED_WIRE_84 && getPinVal(REG_SEL_SYS_LO) && getPinVal(REG_SEL_IR);
        boolean SYNTHESIZED_WIRE_34 = SYNTHESIZED_WIRE_86 && getPinVal(REG_SEL_GP_LO) && getPinVal(REG_SEL_AF);
        boolean SYNTHESIZED_WIRE_32 = getPinVal(REG_SEL_AF) && getPinVal(REG_SEL_GP_HI) && SYNTHESIZED_WIRE_86;
        boolean SYNTHESIZED_WIRE_60 = getPinVal(REG_SEL_IR) && getPinVal(REG_SEL_SYS_HI) && SYNTHESIZED_WIRE_85;
        boolean SYNTHESIZED_WIRE_79 = getPinVal(REG_SEL_GP_LO) && getPinVal(REG_GP_WE) && getPinVal(REG_SEL_SP);




        if(getPinVal(CTL_SW_4U)) {
            gdfx_temp0 = dbLoAs.getBooleanArray(this);
        }

        if(getPinVal(REG_SW_4D_LO)) {
            dbLoAs.setValue(this, gdfx_temp0);
        }

        if(getPinVal(CTL_SW_4U)) {
            gdfx_temp1 = dbHiAs.getBooleanArray(this);
        }

        if(getPinVal(REG_SW_4D_HI)) {
            dbHiAs.setValue(this, gdfx_temp1);
        }

        if(getPinVal(CTL_REG_OUT_LO)) {
            dbLoDs.setValue(this, gdfx_temp0);
        }

        if(getPinVal(CTL_REG_IN_LO)) {
            gdfx_temp0 = dbLoDs.getBooleanArray(this);
        }

        if(getPinVal(CTL_REG_OUT_HI)) {
            dbHiDs.setValue(this, gdfx_temp1);
        }

        if(getPinVal(CTL_REG_IN_HI)) {
            gdfx_temp1 = dbHiDs.getBooleanArray(this);
        }

    }

    @Override
    protected void onClockPosedge() {
        //nothing
    }


}
