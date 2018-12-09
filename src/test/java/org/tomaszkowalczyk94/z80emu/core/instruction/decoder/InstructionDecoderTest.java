package org.tomaszkowalczyk94.z80emu.core.instruction.decoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.load8bit.LoadRegisterFromRegister;
import org.tomaszkowalczyk94.z80emu.core.instruction.decoder.exception.UnsupportedInstructionException;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class InstructionDecoderTest {

    private final InstructionDecoder instructionDecoder = new InstructionDecoder();

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { XBit8.valueOfUnsigned(0b01111000), LoadRegisterFromRegister.class},
        });
    }

    private final XBit8 opcode;
    private final Class expectedClass;

    public InstructionDecoderTest(XBit8 opcode, Class expectedClass) {
        this.opcode = opcode;
        this.expectedClass = expectedClass;
    }

    @Test
    public void decode() throws UnsupportedInstructionException {
        Instruction decodedInstruction = instructionDecoder.decode(opcode);

        assertEquals(expectedClass, decodedInstruction.getClass());
    }
}