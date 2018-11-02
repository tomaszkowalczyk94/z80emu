package edu.psk.z80emu.pin;

import edu.psk.z80emu.exception.InternalOperationNotPermitted;
import edu.psk.z80emu.module.AbstractModule;

import java.util.ArrayList;
import java.util.List;

/**
 * can be observable and observe
 */
public abstract class Pin {

    /**
     * Pin can by in <b>only one STATE</b> at a time.
     * <p>
     * {@link edu.psk.z80emu.pin.InputPin} and {@link edu.psk.z80emu.pin.OutputPin} has defined state, which cannot be changed.
     * {@link edu.psk.z80emu.pin.InOutPin} have state, which can be changed in runtime.
     */
    public enum STATE {
        INPUT,
        OUTPUT
    }

    protected boolean value;

    protected STATE state;

    String name;

    /**
     * Module which is a owner of Pin. Each pin can has only one owner.
     */
    protected AbstractModule owner;

    public boolean getValue(AbstractModule getterModule) {

        switch (state) {
            case INPUT:
                checkPermissionGetInputValue(getterModule);
                break;
            case OUTPUT:
                checkPermissionGetOutputValue(getterModule);
                break;
            default:
                throw new InternalOperationNotPermitted("unsupporded state");
        }

        return value;
    }

    private void checkPermissionGetInputValue(AbstractModule getterModule) {
        if(getterModule != owner) {
            throw new InternalOperationNotPermitted("only owner module can get value of input");
        }
    }

    private void checkPermissionGetOutputValue(AbstractModule getterModule) {
        if(getterModule != owner.getParent()) {
            throw new InternalOperationNotPermitted("only parent module can get value of output");
        }
    }

    /**
     * @param moduleChanging
     * @param value
     */
    public void setValue(AbstractModule moduleChanging, boolean value) {

        switch (state) {
            case INPUT:
                checkPermissionSetInputValue(moduleChanging, value);
                break;
            case OUTPUT:
                checkPermissionSetOutputValue(moduleChanging, value);
                break;
            default:
                throw new InternalOperationNotPermitted("unsupporded state");
        }

        notifyConnectedInputPins();
        notifyOwner(value);
        this.value = value;
    }

    protected void checkPermissionSetInputValue(AbstractModule moduleChanging, boolean value) {
        if(moduleChanging != owner.getParent()) {
            throw new InternalOperationNotPermitted("only parent module can set value of input");
        }
    }

    protected void checkPermissionSetOutputValue(AbstractModule moduleChanging, boolean value) {
        if(moduleChanging != owner) {
            throw new InternalOperationNotPermitted("only owner module can set value of output");
        }
    }

    public void setValueByRoot(boolean value) {
        if(state == STATE.OUTPUT) {
            throw new InternalOperationNotPermitted("cannot change output value by root. Only pin owner (some module) can change output value");
        }

        boolean oldValue = this.value;

        this.value = value;

        notifyConnectedInputPins();
        notifyOwner(oldValue);
    }

    public boolean getValueByRoot() {
        return value;
    }

    public void ticTokByRoot() {
        setValueByRoot(true);
        setValueByRoot(false);
    }

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public AbstractModule getOwner() {
        return owner;
    }

    private List<Pin> connectedInputPins = new ArrayList<>();

    /**
     * Connect output to another input.
     *
     * <b>IMPORTANT!! only output can connect pin to yourself, because output value will be
     * changed by input value. Another way, when we change value of output, we update all connected inputs</b>
     *
     * <b>INPUT ---------> OUTPUT</b>
     *
     * When value of output will be changed, value of connected input will be updated by
     * value of output automatically.
     * @see "observable pattern"
     * @param pin input to connect. Can be instance of {@link InputPin} or {@link InOutPin}
     */
    public void connectInput(Pin pin) {
        connectedInputPins.add(pin);
    }

    /**
     * Disconnect output pin.
     * @see "observable pattern"
     * @param pin input to disconnect. Can be instance of {@link InputPin} or {@link InOutPin}
     */
    public void disconnectInput(Pin pin) {
        connectedInputPins.remove(pin);
    }

    /**
     * Notify connected inputs pins about changed value of this pin.
     * @see "observable pattern"
     */
    protected void notifyConnectedInputPins() {

        if(this.state == STATE.OUTPUT) {
            System.out.println("powiadamiam podłączone inputy o zmianie wartości");
            connectedInputPins.forEach( connectedInputPin -> connectedInputPin.updateByOutput(this));
        }
    }

    /**
     *
     * @param outputPin
     */
    public void updateByOutput(Pin outputPin) {
        if(outputPin instanceof InputPin ) {
            throw new InternalOperationNotPermitted();
        }

        if(this instanceof OutputPin) {
            throw new InternalOperationNotPermitted();
        }

        if(outputPin.state == STATE.OUTPUT && this.state == STATE.INPUT) {
            this.value = outputPin.value;
            System.out.println("zmieniam wartość na: "+this.value);
            notifyConnectedInputPins();
        }
    }

    public void notifyOwner(boolean oldValue) {
        if(state == STATE.INPUT) {
            owner.afterInputPinChanged(this, oldValue);
        }
    }
}
