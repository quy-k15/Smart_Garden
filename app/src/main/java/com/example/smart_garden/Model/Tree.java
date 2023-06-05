package com.example.smart_garden.Model;

public class Tree {
    private String name;
    private double nhietDo;
    private double doAm;
    private double doSang;
    private String id_Tree;
    private String id_quanLy;

    public Tree()
    {
        this.name = "";
        nhietDo = 0;
        doAm = 0;
        doSang = 0;

    }
    public Tree(String name,String id_quanLy)
    {

        this.name = name;
        nhietDo = 0;
        doAm = 0;
        doSang = 0;
        this.id_quanLy=id_quanLy;

    }

    public Tree(String name, double nhietDo, double doAm, double doSang, String id_Tree) {
        this.name = name;
        this.nhietDo = nhietDo;
        this.doAm = doAm;
        this.doSang = doSang;

        this.id_Tree = id_Tree;
    }

    public String getId_quanLy() {
        return id_quanLy;
    }

    public void setId_quanLy(String id_quanLy) {
        this.id_quanLy = id_quanLy;
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


    public String getId_Tree() {
        return id_Tree;
    }

    public void setId_Tree(String id_Tree) {
        this.id_Tree = id_Tree;
    }
}
