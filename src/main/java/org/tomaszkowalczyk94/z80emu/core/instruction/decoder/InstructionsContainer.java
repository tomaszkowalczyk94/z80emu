package org.tomaszkowalczyk94.z80emu.core.instruction.decoder;

import org.tomaszkowalczyk94.z80emu.core.helper.*;
import org.tomaszkowalczyk94.z80emu.core.instruction.blocktransfer.LoadDataAndDecrement;
import org.tomaszkowalczyk94.z80emu.core.instruction.blocktransfer.LoadDataAndDecrementAndRepeat;
import org.tomaszkowalczyk94.z80emu.core.instruction.blocktransfer.LoadDataAndIncrement;
import org.tomaszkowalczyk94.z80emu.core.instruction.blocktransfer.LoadDataAndIncrementAndRepeat;
import org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn.Call16bitIfCondition;
import org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn.RetIfCondition;
import org.tomaszkowalczyk94.z80emu.core.instruction.compare.CompareAndDecrement;
import org.tomaszkowalczyk94.z80emu.core.instruction.compare.CompareAndDecrementAndRepeat;
import org.tomaszkowalczyk94.z80emu.core.instruction.compare.CompareAndIncrement;
import org.tomaszkowalczyk94.z80emu.core.instruction.compare.CompareAndIncrementAndRepeat;
import org.tomaszkowalczyk94.z80emu.core.instruction.exchange.*;
import org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn.Call16bit;
import org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn.Ret;
import org.tomaszkowalczyk94.z80emu.core.instruction.inout.InputDataToA;
import org.tomaszkowalczyk94.z80emu.core.instruction.inout.InputDataToR;
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
    private ExchangeRegistersHelper exchangeRegistersHelper = new ExchangeRegistersHelper(instructionHelper);
    private LoadDataAndIncrementHelper loadDataAndIncrementHelper = new LoadDataAndIncrementHelper();
    private FlagHelper flagHelper = new FlagHelper();
    private CompareAndIncrementHelper compareAndIncrementHelper = new CompareAndIncrementHelper(flagHelper);
    private StackHelper stackHelper = new StackHelper();

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
    LoadAFromI loadAFromI = new LoadAFromI(instructionHelper, flagHelper);
    LoadAFromR loadAFromR = new LoadAFromR(instructionHelper, flagHelper);

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
    PushReg pushReg = new PushReg(instructionHelper, stackHelper);
    PushIx pushIx = new PushIx(instructionHelper, stackHelper);
    PushIy pushIy = new PushIy(instructionHelper, stackHelper);
    PopReg popReg = new PopReg(instructionHelper, stackHelper);
    PopIx popIx = new PopIx(instructionHelper, stackHelper);
    PopIy popIy = new PopIy(instructionHelper, stackHelper);

    //call and return group
    Call16bit call16bit = new Call16bit(instructionHelper, stackHelper);
    Call16bitIfCondition call16bitIfCondition = new Call16bitIfCondition(instructionHelper, conditionHelper, stackHelper);
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
    JpByHl jpByHl = new JpByHl(instructionHelper);
    JpByIx jpByIx = new JpByIx(instructionHelper);
    JpByIy jpByIy = new JpByIy(instructionHelper);
    DecrementAndJumpOnNonZero decrementAndJumpOnNonZero = new DecrementAndJumpOnNonZero(instructionHelper, jumpHelper);

    //exchange, block transfer, search group
    ExchangeDeHl exchangeDeHl = new ExchangeDeHl(instructionHelper);
    ExchangeAfAf exchangeAfAf = new ExchangeAfAf(instructionHelper);
    ExchangeExtraRegisters exchangeExtraRegisters = new ExchangeExtraRegisters(instructionHelper);
    ExchangeHlWithStackTop exchangeHlWithStackTop = new ExchangeHlWithStackTop(instructionHelper, exchangeRegistersHelper);
    ExchangeIxWithStackTop exchangeIxWithStackTop = new ExchangeIxWithStackTop(instructionHelper, exchangeRegistersHelper);
    ExchangeIyWithStackTop exchangeIyWithStackTop = new ExchangeIyWithStackTop(instructionHelper, exchangeRegistersHelper);
    LoadDataAndIncrement loadDataAndIncrement = new LoadDataAndIncrement(instructionHelper, loadDataAndIncrementHelper);
    LoadDataAndIncrementAndRepeat loadDataAndIncrementAndRepeat = new LoadDataAndIncrementAndRepeat(instructionHelper, loadDataAndIncrementHelper);
    LoadDataAndDecrement loadDataAndDecrement = new LoadDataAndDecrement(instructionHelper, loadDataAndIncrementHelper);
    LoadDataAndDecrementAndRepeat loadDataAndDecrementAndRepeat = new LoadDataAndDecrementAndRepeat(instructionHelper, loadDataAndIncrementHelper);
    CompareAndIncrement compareAndIncrement = new CompareAndIncrement(instructionHelper, compareAndIncrementHelper);
    CompareAndIncrementAndRepeat compareAndIncrementAndRepeat = new CompareAndIncrementAndRepeat(instructionHelper, compareAndIncrementHelper);
    CompareAndDecrement compareAndDecrement = new CompareAndDecrement(instructionHelper, compareAndIncrementHelper);
    CompareAndDecrementAndRepeat compareAndDecrementAndRepeat = new CompareAndDecrementAndRepeat(instructionHelper, compareAndIncrementHelper);

    //input and output group
    InputDataToA inputDataToA = new InputDataToA(instructionHelper);
    InputDataToR inputDataToR = new InputDataToR(instructionHelper);


}
