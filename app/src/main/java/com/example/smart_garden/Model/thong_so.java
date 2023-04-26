package com.example.smart_garden.Model;

public class thong_so {
    private double NhietDo;
    private double soil_moisture;
    private double light_intensity;
    private boolean TuoiNuoc;
    private boolean BatDen;

    public thong_so(double nhietDo, double soil_moisture, double light_intensity, boolean tuoiNuoc, boolean batDen) {
        NhietDo = nhietDo;
        this.soil_moisture = soil_moisture;
        this.light_intensity = light_intensity;
        TuoiNuoc = tuoiNuoc;
        BatDen = batDen;
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
}
