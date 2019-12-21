package com.example.protector.SQl;

import org.litepal.crud.DataSupport;

public class Gonwei extends DataSupport {
    public boolean one;
    public boolean two;

    public boolean isOne() {
        return one;
    }

    public void setOne(boolean one) {
        this.one = one;
    }

    public boolean isTwo() {
        return two;
    }

    public void setTwo(boolean two) {
        this.two = two;
    }
}
