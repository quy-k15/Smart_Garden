package com.example.smart_garden.Model;

public class Tree {
    private String Name;
    private double NhietDo;
    private double DoAm;
    private double DoSang;

    private boolean TuoiNuoc;
    private boolean BatDen;

    private boolean TuoiNuoc_TuDong;
    private boolean BatDen_TuDong;

    private double Muc_DoAm;
    private double Muc_AS;
    private String ID_Tree;

    public Tree()
    {
        ID_Tree="";
        Name = "";
        NhietDo = 0;
        DoAm = 0;
        DoSang = 0;
        TuoiNuoc = false;
        BatDen = false;
        TuoiNuoc_TuDong = false;
        BatDen_TuDong = false;
        Muc_DoAm = 0;
        Muc_AS = 0;

    }
    public Tree(String name,double muc_DoAm,double muc_AS)
    {

        Name = name;
        NhietDo = 0;
        DoAm = 0;
        DoSang = 0;
        TuoiNuoc = false;
        BatDen = false;
        TuoiNuoc_TuDong = false;
        BatDen_TuDong = false;
        Muc_DoAm = muc_DoAm;
        Muc_AS = muc_AS;

    }



    public Tree(String name, double nhietDo, double doAm, double doSang, boolean tuoiNuoc, boolean batDen, boolean tuoiNuoc_TuDong, boolean batDen_TuDong, double muc_DoAm, double muc_AS, String ID_Tree) {
        Name = name;
        NhietDo = nhietDo;
        DoAm = doAm;
        DoSang = doSang;
        TuoiNuoc = tuoiNuoc;
        BatDen = batDen;
        TuoiNuoc_TuDong = tuoiNuoc_TuDong;
        BatDen_TuDong = batDen_TuDong;
        Muc_DoAm = muc_DoAm;
        Muc_AS = muc_AS;
        this.ID_Tree = ID_Tree;
    }

    public String getID_Tree() {
        return ID_Tree;
    }

    public void setID_Tree(String ID_Tree) {
        this.ID_Tree = ID_Tree;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getNhietDo() {
        return NhietDo;
    }

    public void setNhietDo(double nhietDo) {
        NhietDo = nhietDo;
    }

    public double getDoAm() {
        return DoAm;
    }

    public void setDoAm(double doAm) {
        DoAm = doAm;
    }

    public double getDoSang() {
        return DoSang;
    }

    public void setDoSang(double doSang) {
        DoSang = doSang;
    }

    public boolean isTuoiNuoc() {
        return TuoiNuoc;
    }

    public void setTuoiNuoc(boolean tuoiNuoc) {
        TuoiNuoc = tuoiNuoc;
    }

    public boolean isBatDen() {
        return BatDen;
    }

    public void setBatDen(boolean batDen) {
        BatDen = batDen;
    }

    public boolean isTuoiNuoc_TuDong() {
        return TuoiNuoc_TuDong;
    }

    public void setTuoiNuoc_TuDong(boolean tuoiNuoc_TuDong) {
        TuoiNuoc_TuDong = tuoiNuoc_TuDong;
    }

    public boolean isBatDen_TuDong() {
        return BatDen_TuDong;
    }

    public void setBatDen_TuDong(boolean batDen_TuDong) {
        BatDen_TuDong = batDen_TuDong;
    }

    public double getMuc_DoAm() {
        return Muc_DoAm;
    }

    public void setMuc_DoAm(double muc_DoAm) {
        Muc_DoAm = muc_DoAm;
    }

    public double getMuc_AS() {
        return Muc_AS;
    }

    public void setMuc_AS(double muc_AS) {
        Muc_AS = muc_AS;
    }
}
