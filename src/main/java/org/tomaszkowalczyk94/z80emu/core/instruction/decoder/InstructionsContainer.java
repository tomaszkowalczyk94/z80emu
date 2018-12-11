package org.tomaszkowalczyk94.z80emu.core.instruction.decoder;

import org.tomaszkowalczyk94.z80emu.core.instruction.load8bit.*;

/**
 * Contain all of instruction objects.
 */
public class InstructionsContainer {
    LoadRegFromReg loadRegFromReg = new LoadRegFromReg();
    LoadRegFrom8bit loadRegFrom8Bit = new LoadRegFrom8bit();
    LoadRegFromMemByHl loadRegFromMemByHl = new LoadRegFromMemByHl();
    LoadRegFromMemByIxAnd8bit loadRegFromMemByIxAnd8Bit = new LoadRegFromMemByIxAnd8bit();
    LoadRegFromMemByIyAnd8bit loadRegFromMemByIyAnd8bit = new LoadRegFromMemByIyAnd8bit();
    LoadMemByHlFromReg loadMemByHlFromReg = new LoadMemByHlFromReg();
    LoadMemByIxAnd8bitFromReg loadMemByIxAnd8BitFromReg = new LoadMemByIxAnd8bitFromReg();
    LoadMemByIyAnd8bitFromReg loadMemByIyAnd8BitFromReg = new LoadMemByIyAnd8bitFromReg();
    LoadMemByHlFrom8bit loadMemByHlFrom8Bit = new LoadMemByHlFrom8bit();
    LoadMemByIxAnd8bitFrom8bit loadMemByIxAnd8bitFrom8bit = new LoadMemByIxAnd8bitFrom8bit();
    LoadMemByIyAnd8bitFrom8bit loadMemByIyAnd8bitFrom8bit = new LoadMemByIyAnd8bitFrom8bit();
    LoadAFromMemByBc loadAFromMemByBc = new LoadAFromMemByBc();
    LoadAFromMemByDe loadAFromMemByDe = new LoadAFromMemByDe();
    LoadAFromMemBy16bit loadAFromMemBy16bit = new LoadAFromMemBy16bit();
    LoadMemoryByBcFromA loadMemoryByBcFromA = new LoadMemoryByBcFromA();
    LoadMemoryByDeFromA loadMemoryByDeFromA = new LoadMemoryByDeFromA();
    LoadMemoryBy16BitFromA loadMemoryBy16BitFromA = new LoadMemoryBy16BitFromA();
    LoadIFromA loadIFromA = new LoadIFromA();
}
