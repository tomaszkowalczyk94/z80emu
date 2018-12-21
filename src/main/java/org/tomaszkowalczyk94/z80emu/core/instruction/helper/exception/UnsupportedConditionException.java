package org.tomaszkowalczyk94.z80emu.core.instruction.helper.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

@Data
@EqualsAndHashCode(callSuper = false)
public class UnsupportedConditionException extends Z80Exception {
    final int condition;

    public UnsupportedConditionException(int condition) {
        super("condition: "+Integer.toHexString(condition));
        this.condition = condition;
    }


}
