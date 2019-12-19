package com.example.protector.SQl;


import org.litepal.crud.DataSupport;

public class XiuGai extends DataSupport {
    //设置参数表
    //---------------------------
    private int id;
    private int gonwei;
    private String qidong ="150";
    private String cecheng ="1";
    private String duanxiang ="250";
    private String m13 ="1";
    private String m30 ="1";
    private String chuanlian1 ="20.0";
    private String chuanlian2 ="27.5";
    private String binglian1 ="10.0";
    private String binglian2 ="14.0";
    private String duanxiangzhiliu ="0.2";
    private String jiaoliu ="3.0";
    private String xiangjian ="500";
    private String xiangduidi ="500";
    private String xiangduixianquan ="500";
    private String xianquan ="500";

    public String getCecheng() {
        return cecheng;
    }

    public void setCecheng(String cecheng) {
        this.cecheng = cecheng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGonwei() {
        return gonwei;
    }

    public void setGonwei(int gonwei) {
        this.gonwei = gonwei;
    }

    public String getQidong() {
        return qidong;
    }

    public void setQidong(String qidong) {
        this.qidong = qidong;
    }

    public String getDuanxiang() {
        return duanxiang;
    }

    public void setDuanxiang(String duanxiang) {
        this.duanxiang = duanxiang;
    }

    public String getM13() {
        return m13;
    }

    public void setM13(String m13) {
        this.m13 = m13;
    }

    public String getM30() {
        return m30;
    }

    public void setM30(String m30) {
        this.m30 = m30;
    }

    public String getChuanlian1() {
        return chuanlian1;
    }

    public void setChuanlian1(String chuanlian1) {
        this.chuanlian1 = chuanlian1;
    }

    public String getChuanlian2() {
        return chuanlian2;
    }

    public void setChuanlian2(String chuanlian2) {
        this.chuanlian2 = chuanlian2;
    }

    public String getBinglian1() {
        return binglian1;
    }

    public void setBinglian1(String binglian1) {
        this.binglian1 = binglian1;
    }

    public String getBinglian2() {
        return binglian2;
    }

    public void setBinglian2(String binglian2) {
        this.binglian2 = binglian2;
    }

    public String getDuanxiangzhiliu() {
        return duanxiangzhiliu;
    }

    public void setDuanxiangzhiliu(String duanxiangzhiliu) {
        this.duanxiangzhiliu = duanxiangzhiliu;
    }

    public String getJiaoliu() {
        return jiaoliu;
    }

    public void setJiaoliu(String jiaoliu) {
        this.jiaoliu = jiaoliu;
    }

    public String getXiangjian() {
        return xiangjian;
    }

    public void setXiangjian(String xiangjian) {
        this.xiangjian = xiangjian;
    }

    public String getXiangduidi() {
        return xiangduidi;
    }

    public void setXiangduidi(String xiangduidi) {
        this.xiangduidi = xiangduidi;
    }

    public String getXiangduixianquan() {
        return xiangduixianquan;
    }

    public void setXiangduixianquan(String xiangduixianquan) {
        this.xiangduixianquan = xiangduixianquan;
    }

    public String getXianquan() {
        return xianquan;
    }

    public void setXianquan(String xianquan) {
        this.xianquan = xianquan;
    }
}
