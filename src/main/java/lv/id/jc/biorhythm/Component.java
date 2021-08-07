package lv.id.jc.biorhythm;

import lv.id.jc.biorhythm.ui.LocalTextInterface;

import java.time.LocalDate;

import static java.time.LocalDate.EPOCH;

public abstract class Component extends LocalTextInterface implements Runnable {
    protected final Context context;

    public Component() {
        context = new Context(EPOCH, EPOCH);
    }

    public Component(final Context context) {
        this.context = context;
    }

    protected LocalDate birthday() {
        return context.birthday();
    }

    protected LocalDate date() {
        return context.date();
    }

}