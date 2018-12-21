package org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn;

/**
 * <h2>CALL cc, nn</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>IF cc true: (sp – 1) ← PCH, (sp – 2) ← PCL, pc ← nn</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>CALL</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>cc, nn</td>
 *     </tr>
 * </table>
 * <br>
 * If condition cc is true, this instruction pushes the current contents of the Program Counter
 * (PC) onto the top of the external memory stack, then loads the operands nn to PC to point
 * to the address in memory at which the first op code of a subroutine is to be fetched. At the
 * end of the subroutine, a RETurn instruction can be used to return to the original program
 * flow by popping the top of the stack back to PC. If condition cc is false, the Program
 * Counter is incremented as usual, and the program continues with the next sequential
 * instruction. The stack push is accomplished by first decrementing the current contents of
 * the Stack Pointer (SP), loading the high-order byte of the PC contents to the memory
 * address now pointed to by SP; then decrementing SP again, and loading the low-order
 * byte of the PC contents to the top of the stack.
 * Because this process is a 3-byte instruction, the Program Counter was incremented by
 * three before the push is executed.
 * Condition cc is programmed as one of eight statuses that corresponds to condition bits in
 * the Flag Register (Register F). These eight statuses are defined in the following table,
 * which also specifies the corresponding cc bit fields in the assembled object code.
 *
 */
public class Call16bitIfCondition {
}
