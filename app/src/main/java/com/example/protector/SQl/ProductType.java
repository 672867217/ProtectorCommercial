package com.example.protector.SQl;

import org.litepal.crud.DataSupport;

public class ProductType extends DataSupport {
    //产品类型表
    //---------------------------
    private int id;
    private String name;
    private String xinghao;
    private String changjia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXinghao() {
        return xinghao;
    }

    public void setXinghao(String xinghao) {
        this.xinghao = xinghao;
    }

    public String getChangjia() {
        return changjia;
    }

    public void setChangjia(String changjia) {
        this.changjia = changjia;
    }
}
