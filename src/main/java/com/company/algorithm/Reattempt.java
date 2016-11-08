package com.company.algorithm;

/**
 * Created by anjalhussan on 10/22/16.
 */

@FunctionalInterface
public interface Reattempt {
    void attempt() throws Exception;
}
