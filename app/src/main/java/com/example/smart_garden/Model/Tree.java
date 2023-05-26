package com.example.smart_garden.Model;

public class Tree {
    private String name;
    private double nhietDo;
    private double doAm;
    private double doSang;

    private boolean tuoiNuoc;
    private boolean batDen;

    private boolean tuoiNuoc_TuDong;
    private boolean batDen_TuDong;

    private double muc_DoAm;
    private double muc_AS;
    private String id_Tree;

    public Tree()
    {
        this.name = "";
        nhietDo = 0;
        doAm = 0;
        doSang = 0;
        tuoiNuoc = false;
        batDen = false;
        tuoiNuoc_TuDong = false;
        batDen_TuDong = false;
        this.muc_DoAm = 0;
        this.muc_AS = 0;

    }
    public Tree(String name,double muc_DoAm,double muc_AS)
    {

        this.name = name;
        nhietDo = 0;
        doAm = 0;
        doSang = 0;
        tuoiNuoc = false;
        batDen = false;
        tuoiNuoc_TuDong = false;
        batDen_TuDong = false;
        this.muc_DoAm = muc_DoAm;
        this.muc_AS = muc_AS;

    }

    public Tree(String name, double nhietDo, double doAm, double doSang, boolean tuoiNuoc, boolean batDen, boolean tuoiNuoc_TuDong, boolean batDen_TuDong, double muc_DoAm, double muc_AS, String id_Tree) {
        this.name = name;
        this.nhietDo = nhietDo;
        this.doAm = doAm;
        this.doSang = doSang;
        this.tuoiNuoc = tuoiNuoc;
        this.batDen = batDen;
        this.tuoiNuoc_TuDong = tuoiNuoc_TuDong;
        this.batDen_TuDong = batDen_TuDong;
        this.muc_DoAm = muc_DoAm;
        this.muc_AS = muc_AS;
        this.id_Tree = id_Tree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNhietDo() {
        return nhietDo;
    }

    public void setNhietDo(double nhietDo) {
        this.nhietDo = nhietDo;
    }

    public double getDoAm() {
        return doAm;
    }

    public void setDoAm(double doAm) {
        this.doAm = doAm;
    }

    public double getDoSang() {
        return doSang;
    }

    public void setDoSang(double doSang) {
        this.doSang = doSang;
    }

    public boolean isTuoiNuoc() {
        return tuoiNuoc;
    }

    public void setTuoiNuoc(boolean tuoiNuoc) {
        this.tuoiNuoc = tuoiNuoc;
    }

    public boolean isBatDen() {
        return batDen;
    }

    public void setBatDen(boolean batDen) {
        this.batDen = batDen;
    }

    public boolean isTuoiNuoc_TuDong() {
        return tuoiNuoc_TuDong;
    }

    public void setTuoiNuoc_TuDong(boolean tuoiNuoc_TuDong) {
        this.tuoiNuoc_TuDong = tuoiNuoc_TuDong;
    }

    public boolean isBatDen_TuDong() {
        return batDen_TuDong;
    }

    public void setBatDen_TuDong(boolean batDen_TuDong) {
        this.batDen_TuDong = batDen_TuDong;
    }

    public double getMuc_DoAm() {
        return muc_DoAm;
    }

    public void setMuc_DoAm(double muc_DoAm) {
        this.muc_DoAm = muc_DoAm;
    }

    public double getMuc_AS() {
        return muc_AS;
    }

    public void setMuc_AS(double muc_AS) {
        this.muc_AS = muc_AS;
    }

    public String getId_Tree() {
        return id_Tree;
    }

    public void setId_Tree(String id_Tree) {
        this.id_Tree = id_Tree;
    }
}
