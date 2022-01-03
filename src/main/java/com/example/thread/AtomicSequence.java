package com.example.thread;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class AtomicSequence {
    private final AtomicInteger value;

    public AtomicSequence() {
        this(0);
    }

    public AtomicSequence(int initialValue) {
        value = new AtomicInteger(initialValue);
    }

    public int getNext() {
//        return value.incrementAndGet();
        return value.getAndIncrement();
    }
}
