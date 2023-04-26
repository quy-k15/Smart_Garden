package com.example.smart_garden.Model;

public class ThongSo {

    private double NhietDo;
    private double DoAm;
    private double AnhSang;
    private boolean TuoiNuoc;
    private boolean BatDen;

    public ThongSo(double nhietDo, double doAm, double anhSang, boolean tuoiNuoc, boolean batDen) {
        NhietDo = nhietDo;
        DoAm = doAm;
        AnhSang = anhSang;
        TuoiNuoc = tuoiNuoc;
        BatDen = batDen;

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

    public double getAnhSang() {
        return AnhSang;
    }

    public void setAnhSang(double anhSang) {
        AnhSang = anhSang;
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

}
