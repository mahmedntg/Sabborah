package com.example.company.sabborah;

import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Mohamed Sayed on 1/31/2018.
 */

public abstract class Presenter<T extends BaseView> {

    private WeakReference<T> view;

    protected AtomicBoolean isViewAlive = new AtomicBoolean();

    public T getView() {
        return view.get();
    }

    public void setView(T view) {
        this.view = new WeakReference<>(view);
    }

    public void initialize(Bundle extras) {
    }

    public void start() {
        isViewAlive.set(true);
    }

    public void finalizeView() {
        isViewAlive.set(false);
    }

}