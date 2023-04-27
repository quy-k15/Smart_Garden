package com.example.smart_garden.Model;

public class thong_so {
    private double NhietDo;
    private double soil_moisture;
    private double light_intensity;
    private boolean TuoiNuoc;
    private boolean BatDen;
    private double Muc_DoAm;
    private double Muc_AS;

    public thong_so(double nhietDo, double soil_moisture, double light_intensity,  double muc_DoAm, double muc_AS) {
        NhietDo = nhietDo;
        this.soil_moisture = soil_moisture;
        this.light_intensity = light_intensity;
        TuoiNuoc = false;
        BatDen = false;
        Muc_DoAm = muc_DoAm;
        Muc_AS = muc_AS;
    }

    public double getNhietDo() {
        return NhietDo;
    }

    public void setNhietDo(double nhietDo) {
        NhietDo = nhietDo;
    }

    public double getSoil_moisture() {
        return soil_moisture;
    }

    public void setSoil_moisture(double soil_moisture) {
        this.soil_moisture = soil_moisture;
    }

    public double getLight_intensity() {
        return light_intensity;
    }

    public void setLight_intensity(double light_intensity) {
        this.light_intensity = light_intensity;
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
