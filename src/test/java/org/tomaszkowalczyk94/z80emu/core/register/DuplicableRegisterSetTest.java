package org.tomaszkowalczyk94.z80emu.core.register;

import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.register.exception.UnsupportedGeneralPurposeRegisterIdException;

import static org.junit.Assert.*;

public class DuplicableRegisterSetTest {
    private DuplicableRegisterSet duplicableRegisterSet;

    @Before
    public void setUp() {
        duplicableRegisterSet = new DuplicableRegisterSet();
    }

    @Test
    public void set8BitRegisterById() throws UnsupportedGeneralPurposeRegisterIdException {
        duplicableRegisterSet.set8BitRegisterById((byte)0b000, XBit8.valueOfUnsigned(1));
        duplicableRegisterSet.set8BitRegisterById((byte)0b001, XBit8.valueOfUnsigned(2));
        duplicableRegisterSet.set8BitRegisterById((byte)0b010, XBit8.valueOfUnsigned(3));
        duplicableRegisterSet.set8BitRegisterById((byte)0b011, XBit8.valueOfUnsigned(4));
        duplicableRegisterSet.set8BitRegisterById((byte)0b100, XBit8.valueOfUnsigned(5));
        duplicableRegisterSet.set8BitRegisterById((byte)0b101, XBit8.valueOfUnsigned(6));
        duplicableRegisterSet.set8BitRegisterById((byte)0b111, XBit8.valueOfUnsigned(7));

        assertEquals(1, duplicableRegisterSet.getB().getUnsignedValue());
        assertEquals(2, duplicableRegisterSet.getC().getUnsignedValue());
        assertEquals(3, duplicableRegisterSet.getD().getUnsignedValue());
        assertEquals(4, duplicableRegisterSet.getE().getUnsignedValue());
        assertEquals(5, duplicableRegisterSet.getH().getUnsignedValue());
        assertEquals(6, duplicableRegisterSet.getL().getUnsignedValue());
        assertEquals(7, duplicableRegisterSet.getA().getUnsignedValue());
    }

    @Test
    public void set8BitRegisterByIdHlRegisterId() {

        try {
            duplicableRegisterSet.set8BitRegisterById((byte)0b110, XBit8.valueOfUnsigned(255));
            fail();
        } catch (UnsupportedGeneralPurposeRegisterIdException e) {
            assertEquals("registerId: 6", e.getMessage());
        }
    }

    @Test
    public void set8BitRegisterByInvalidId() {

        try {
            duplicableRegisterSet.set8BitRegisterById((byte)0b1000, XBit8.valueOfUnsigned(255));
            fail();
        } catch (UnsupportedGeneralPurposeRegisterIdException e) {
            assertEquals("registerId: 8", e.getMessage());
        }
    }
}