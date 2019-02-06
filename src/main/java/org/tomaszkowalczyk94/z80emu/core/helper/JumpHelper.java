package org.tomaszkowalczyk94.z80emu.core.helper;

import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

public class JumpHelper {

    private InstructionHelper helper;
    private ConditionHelper conditionHelper;

    public JumpHelper(InstructionHelper instructionHelper, ConditionHelper conditionHelper) {
        this.conditionHelper = conditionHelper;
        this.helper = instructionHelper;
    }

    public void jumpRelative(Z80 z80, int value) {
        z80.getRegs().setPc(
                XBitUtils.incrementBy(z80.getRegs().getPc(), value)
        );
    }

    public InstructionResult executeRelativeJumpCondition(Z80 z80, ConditionHelper.Condition condition) throws Z80Exception {
        boolean conditionResult = conditionHelper.checkCondition(condition, z80);

        InstructionResult.InstructionResultBuilder builder = InstructionResult.builder();
        builder.size(2);

        if(conditionResult) {
            this.jumpRelative(z80, helper.getSecondByte(z80).getSignedValue());

            builder.machineCycles(3)
                    .clocks(12)
                    .executionTime(3.00f);
        } else {
            builder.machineCycles(2)
                    .clocks(7)
                    .executionTime(1.75f);
        }

        return builder.build();
    }
}
