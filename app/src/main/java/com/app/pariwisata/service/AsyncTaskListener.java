package com.app.pariwisata.service;

/**
 * Created by Taufik on 04/12/17.
 */

public interface AsyncTaskListener<T> {
    public void result(T data);
}
