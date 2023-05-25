package com.ftn.sbnz.service.util;

import java.util.Iterator;

public abstract class NumberGenerator<T extends Number> implements Iterator<T> {
    protected T current;
    protected T step;

    public NumberGenerator(T start, T step) {
        this.current = start;
        this.step = step;
    }

    @Override
    public boolean hasNext() {
        if (isStepPositive()) {
            return !willOverflow();
        } else if (isStepNegative()) {
            return !willUnderflow();
        } else {
            // step is 0 so there is always more numbers to generate
            return true;
        }
    }

    public T current() {
        return current;
    }

    protected abstract boolean willOverflow();
    protected abstract boolean willUnderflow();

    public abstract boolean isStepPositive();
    public abstract boolean isStepNegative();

//
//    public abstract void restart();
//
//    public abstract T peakNext() throws NoSuchElementException;
}
