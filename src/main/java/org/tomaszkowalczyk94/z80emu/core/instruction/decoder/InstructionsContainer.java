package org.tomaszkowalczyk94.z80emu.core.instruction.decoder;

import org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn.Call16bitIfCondition;
import org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn.RetIfCondition;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.ConditionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn.Call16bit;
import org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn.Ret;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.JumpHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.jump.*;
import org.tomaszkowalczyk94.z80emu.core.instruction.load16bit.*;
import org.tomaszkowalczyk94.z80emu.core.instruction.load8bit.*;

/**
 * Contain all of instruction objects.
 */
public class InstructionsContainer {
    private InstructionHelper instructionHelper = new InstructionHelper();
    private ConditionHelper conditionHelper = new ConditionHelper();
    private JumpHelper jumpHelper = new JumpHelper(instructionHelper, conditionHelper);

    //8bit load group
    LoadRegFromReg loadRegFromReg = new LoadRegFromReg(instructionHelper);
    LoadRegFrom8bit loadRegFrom8Bit = new LoadRegFrom8bit(instructionHelper);
    LoadRegFromMemByHl loadRegFromMemByHl = new LoadRegFromMemByHl(instructionHelper);
    LoadRegFromMemByIxAnd8bit loadRegFromMemByIxAnd8Bit = new LoadRegFromMemByIxAnd8bit(instructionHelper);
    LoadRegFromMemByIyAnd8bit loadRegFromMemByIyAnd8bit = new LoadRegFromMemByIyAnd8bit(instructionHelper);
    LoadMemByHlFromReg loadMemByHlFromReg = new LoadMemByHlFromReg(instructionHelper);
    LoadMemByIxAnd8bitFromReg loadMemByIxAnd8BitFromReg = new LoadMemByIxAnd8bitFromReg(instructionHelper);
    LoadMemByIyAnd8bitFromReg loadMemByIyAnd8BitFromReg = new LoadMemByIyAnd8bitFromReg(instructionHelper);
    LoadMemByHlFrom8bit loadMemByHlFrom8Bit = new LoadMemByHlFrom8bit(instructionHelper);
    LoadMemByIxAnd8bitFrom8bit loadMemByIxAnd8bitFrom8bit = new LoadMemByIxAnd8bitFrom8bit(instructionHelper);
    LoadMemByIyAnd8bitFrom8bit loadMemByIyAnd8bitFrom8bit = new LoadMemByIyAnd8bitFrom8bit(instructionHelper);
    LoadAFromMemByBc loadAFromMemByBc = new LoadAFromMemByBc(instructionHelper);
    LoadAFromMemByDe loadAFromMemByDe = new LoadAFromMemByDe(instructionHelper);
    LoadAFromMemBy16bit loadAFromMemBy16bit = new LoadAFromMemBy16bit(instructionHelper);
    LoadMemoryByBcFromA loadMemoryByBcFromA = new LoadMemoryByBcFromA(instructionHelper);
    LoadMemoryByDeFromA loadMemoryByDeFromA = new LoadMemoryByDeFromA(instructionHelper);
    LoadMemoryBy16BitFromA loadMemoryBy16BitFromA = new LoadMemoryBy16BitFromA(instructionHelper);
    LoadIFromA loadIFromA = new LoadIFromA(instructionHelper);
    LoadRFromA loadRFromA = new LoadRFromA(instructionHelper);
    LoadAFromI loadAFromI = new LoadAFromI(instructionHelper);
    LoadAFromR loadAFromR = new LoadAFromR(instructionHelper);

    //16bit load group
    LoadRegFrom16bit loadRegFrom16bit = new LoadRegFrom16bit(instructionHelper);
    LoadIxFrom16bit loadIxFrom16bit = new LoadIxFrom16bit(instructionHelper);
    LoadIyFrom16bit loadIyFrom16bit = new LoadIyFrom16bit(instructionHelper);
    LoadHlFromMemBy16bit loadHlFromMemBy16bit = new LoadHlFromMemBy16bit(instructionHelper);
    LoadRegFromMemBy16bit loadRegFromMemBy16Bit = new LoadRegFromMemBy16bit(instructionHelper);
    LoadIxFromMemBy16bit loadIxFromMemBy16bit = new LoadIxFromMemBy16bit(instructionHelper);
    LoadIyFromMemBy16bit loadIyFromMemBy16bit = new LoadIyFromMemBy16bit(instructionHelper);
    LoadMemBy16bitFromHl loadMemBy16bitFromHl = new LoadMemBy16bitFromHl(instructionHelper);
    LoadMemBy16bitFromIx loadMemBy16bitFromIx = new LoadMemBy16bitFromIx(instructionHelper);
    LoadMemBy16bitFromIy loadMemBy16bitFromIy = new LoadMemBy16bitFromIy(instructionHelper);
    LoadMemBy16bitFromReg loadMemBy16bitFromReg = new LoadMemBy16bitFromReg(instructionHelper);
    LoadSpFromHl loadSpFromHl = new LoadSpFromHl(instructionHelper);
    LoadSpFromIx loadSpFromIx = new LoadSpFromIx(instructionHelper);
    LoadSpFromIy loadSpFromIy = new LoadSpFromIy(instructionHelper);
    PushReg pushReg = new PushReg(instructionHelper);
    PushIx pushIx = new PushIx(instructionHelper);
    PushIy pushIy = new PushIy(instructionHelper);
    PopReg popReg = new PopReg(instructionHelper);
    PopIx popIx = new PopIx(instructionHelper);
    PopIy popIy = new PopIy(instructionHelper);

    //call and return group
    Call16bit call16bit = new Call16bit(instructionHelper);
    Call16bitIfCondition call16bitIfCondition = new Call16bitIfCondition(instructionHelper, conditionHelper);
    Ret ret = new Ret(instructionHelper);
    RetIfCondition retIfCondition = new RetIfCondition(instructionHelper, conditionHelper);

    //jump group
    Jp16bit jp16bit = new Jp16bit(instructionHelper);
    Jp16bitIfCondition jp16bitIfCondition = new Jp16bitIfCondition(instructionHelper, conditionHelper);
    Jre jre = new Jre(instructionHelper, jumpHelper);
    JreIfCarryFlag jreIfCarryFlag = new JreIfCarryFlag(instructionHelper, jumpHelper);
    JreIfNonCarryFlag jreIfNonCarryFlag = new JreIfNonCarryFlag(instructionHelper, jumpHelper);
    JreIfZeroFlag jreIfZeroFlag = new JreIfZeroFlag(instructionHelper, jumpHelper);
    JreIfNonZeroFlag jreIfNonZeroFlag = new JreIfNonZeroFlag(instructionHelper, jumpHelper);


}
