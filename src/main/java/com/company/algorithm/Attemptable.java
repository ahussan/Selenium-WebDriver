package com.company.algorithm;

/**
 * Created by anjalhussan on 10/22/16.
 */
public interface Attemptable<T> {

    T attempt() throws Exception;
}

