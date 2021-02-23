package com.itfeng.antic.oom.stackError.entity;

public class count {
    public int cnumer = 0;

    public void setCnumer() {
        cnumer += 1;
        setCnumer();
    }
}
