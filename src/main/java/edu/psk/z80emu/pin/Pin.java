package edu.psk.z80emu.pin;

import edu.psk.z80emu.exception.InternalOperationNotPermitted;
import edu.psk.z80emu.module.AbstractModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * can be observable and observe
 */
public abstract class Pin extends Observable {

    /**
     * alias for boolean false
     */
    public static boolean _0 = false;

    /**
     * alias for boolean true
     */
    public static boolean _1 = true;

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

    public Pin(AbstractModule owner, String name) {
        this.owner = owner;
        this.name = name;
    }

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

        this.value = value;

        setChanged();
        notifyObservers();
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

        this.value = value;

        setChanged();
        notifyObservers();
    }

    public boolean getValueByRoot() {
        return value;
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

}
