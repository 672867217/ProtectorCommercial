package com.example.protector.util;

import com.example.protector.SQl.TestData;

import org.litepal.LitePalApplication;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends LitePalApplication {
    public Map<String,TestData> map = new HashMap();
    public int scname = 0;
    public int cecheng = 0;
    public int name = 0;
    public int type = 0;
}
