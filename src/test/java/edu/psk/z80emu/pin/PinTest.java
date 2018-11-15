package edu.psk.z80emu.pin;

import edu.psk.z80emu.exception.InternalOperationNotPermitted;
import edu.psk.z80emu.module.AbstractModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PinTest {


    class TestModule extends AbstractModule {

        public static final String TEST_INPUT = "TEST_INPUT";
        public static final String TEST_OUTPUT = "TEST_OUTPUT";
        public static final String TEST_INOUT = "TEST_INOUT";

        TestModule() {
            this.pins.addPin(new InputPin(this, TEST_INPUT));
            this.pins.addPin(new OutputPin(this, TEST_OUTPUT));
            this.pins.addPin(new InOutPin(this, TEST_INOUT));
        }

        @Override
        public void flush() {
            //nothing
        }
    };

    // ============= set value tests =============

    @Test
    public void setInputValueByOwner() {
        //given
        TestModule parentTestModule = new TestModule();
        TestModule testModule = new TestModule();
        testModule.setParent(parentTestModule);

        //when
        try {
            testModule.getPin(TestModule.TEST_INPUT).setValue(testModule, true);
            fail();
        } catch (InternalOperationNotPermitted e) {
            //then
            Assert.assertEquals("only parent module can set value of input", e.getMessage());
        }
    }

    @Test
    public void setInputValueByParent() {
        //given
        TestModule parentTestModule = new TestModule();
        TestModule testModule = new TestModule();
        testModule.setParent(parentTestModule);

        //when
        testModule.getPin(TestModule.TEST_INPUT).setValue(parentTestModule, true);

        //then
        Assert.assertEquals(true, testModule.getPin(TestModule.TEST_INPUT).getValueByRoot());
    }

    @Test
    public void setOutputValueByOwner() {
        //given
        TestModule parentTestModule = new TestModule();
        TestModule testModule = new TestModule();
        testModule.setParent(parentTestModule);

        //when
        testModule.getPin(TestModule.TEST_OUTPUT).setValue(testModule, true);

        //then
        Assert.assertEquals(true, testModule.getPin(TestModule.TEST_OUTPUT).getValueByRoot());
    }

    @Test
    public void setOutputValueByParent() {
        //given
        TestModule parentTestModule = new TestModule();
        TestModule testModule = new TestModule();
        testModule.setParent(parentTestModule);

        //when
        try {
            testModule.getPin(TestModule.TEST_OUTPUT).setValue(parentTestModule, true);
            fail();
        } catch (InternalOperationNotPermitted e) {
            //then
            Assert.assertEquals("only owner module can set value of output", e.getMessage());
        }
    }

    // ============= end of - set value tests =============


    // ============= get input tests =============

    @Test
    public void getInputValueByOwner() {
        //given
        TestModule parentTestModule = new TestModule();
        TestModule testModule = new TestModule();
        testModule.setParent(parentTestModule);

        //when
        boolean value = testModule.getPin(TestModule.TEST_INPUT).getValue(testModule);

        //then
        Assert.assertEquals(false, value);
    }

    @Test
    public void getInputValueByParent() {
        //given
        TestModule parentTestModule = new TestModule();
        TestModule testModule = new TestModule();
        testModule.setParent(parentTestModule);

        //when
        try {
           testModule.getPin(TestModule.TEST_INPUT).getValue(parentTestModule);
           fail();
        } catch (InternalOperationNotPermitted e) {
            //then
            Assert.assertEquals("only owner module can get value of input", e.getMessage());
        }
    }

    @Test
    public void getOutputValueByOwner() {
        //given
        TestModule parentTestModule = new TestModule();
        TestModule testModule = new TestModule();
        testModule.setParent(parentTestModule);

        //when
        try {
            testModule.getPin(TestModule.TEST_OUTPUT).getValue(testModule);
            fail();
        } catch (InternalOperationNotPermitted e) {
            //then
            Assert.assertEquals("only parent module can get value of output", e.getMessage());
        }
    }

    @Test
    public void getOutputValueByParent() {
        //given
        TestModule parentTestModule = new TestModule();
        TestModule testModule = new TestModule();
        testModule.setParent(parentTestModule);

        //when
        boolean value = testModule.getPin(TestModule.TEST_OUTPUT).getValue(parentTestModule);

        //then
        Assert.assertEquals(false, value);
    }


    // ============= end of - get input tests =============

}