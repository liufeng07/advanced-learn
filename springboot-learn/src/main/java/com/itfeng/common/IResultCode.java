package com.itfeng.common;

import java.io.Serializable;

public interface IResultCode extends Serializable {
    String getMessage();

    String getCode();
}