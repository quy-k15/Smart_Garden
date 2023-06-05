package com.example.smart_garden.Model;

public class quan_ly {
    private boolean tuoiNuoc_ThuCong;
    private boolean batDen_ThuCong;
    private boolean tuoiNuoc;
    private boolean batDen;

    private boolean tuoiNuoc_TuDong;
    private boolean batDen_TuDong;
    private double muc_DoAm;
    private double muc_AS;

    private boolean tuoiNuoc_Gio;
    private boolean batDen_Gio;
    private String time_StartTuoi;
    private String time_EndTuoi;
    private String time_StartDen;
    private String time_EndDen;

    private String id_quanLy;

    public  quan_ly(double muc_DoAm,double muc_AS)
    {
        this.muc_DoAm = muc_DoAm;
        this.muc_AS = muc_AS;
        this.tuoiNuoc_TuDong=true;
        this.batDen_TuDong=true;
    }

    public quan_ly(boolean tuoiNuoc_ThuCong, boolean batDen_ThuCong, boolean tuoiNuoc, boolean batDen, boolean tuoiNuoc_TuDong, boolean batDen_TuDong, double muc_DoAm, double muc_AS, boolean tuoiNuoc_Gio, boolean batDen_Gio, String time_StartTuoi, String time_EndTuoi, String time_StartDen, String time_EndDen, String id_quanLy) {
        this.tuoiNuoc_ThuCong = tuoiNuoc_ThuCong;
        this.batDen_ThuCong = batDen_ThuCong;
        this.tuoiNuoc = tuoiNuoc;
        this.batDen = batDen;
        this.tuoiNuoc_TuDong = tuoiNuoc_TuDong;
        this.batDen_TuDong = batDen_TuDong;
        this.muc_DoAm = muc_DoAm;
        this.muc_AS = muc_AS;
        this.tuoiNuoc_Gio = tuoiNuoc_Gio;
        this.batDen_Gio = batDen_Gio;
        this.time_StartTuoi = time_StartTuoi;
        this.time_EndTuoi = time_EndTuoi;
        this.time_StartDen = time_StartDen;
        this.time_EndDen = time_EndDen;
        this.id_quanLy = id_quanLy;
    }

    public boolean isTuoiNuoc_ThuCong() {
        return tuoiNuoc_ThuCong;
    }

    public void setTuoiNuoc_ThuCong(boolean tuoiNuoc_ThuCong) {
        this.tuoiNuoc_ThuCong = tuoiNuoc_ThuCong;
    }

    public boolean isBatDen_ThuCong() {
        return batDen_ThuCong;
    }

    public void setBatDen_ThuCong(boolean batDen_ThuCong) {
        this.batDen_ThuCong = batDen_ThuCong;
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

    public String getId_quanLy() {
        return id_quanLy;
    }

    public void setId_quanLy(String id_quanLy) {
        this.id_quanLy = id_quanLy;
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

    public boolean isTuoiNuoc_Gio() {
        return tuoiNuoc_Gio;
    }

    public void setTuoiNuoc_Gio(boolean tuoiNuoc_Gio) {
        this.tuoiNuoc_Gio = tuoiNuoc_Gio;
    }

    public boolean isBatDen_Gio() {
        return batDen_Gio;
    }

    public void setBatDen_Gio(boolean batDen_Gio) {
        this.batDen_Gio = batDen_Gio;
    }

    public String getTime_StartTuoi() {
        return time_StartTuoi;
    }

    public void setTime_StartTuoi(String time_StartTuoi) {
        this.time_StartTuoi = time_StartTuoi;
    }

    public String getTime_EndTuoi() {
        return time_EndTuoi;
    }

    public void setTime_EndTuoi(String time_EndTuoi) {
        this.time_EndTuoi = time_EndTuoi;
    }

    public String getTime_StartDen() {
        return time_StartDen;
    }

    public void setTime_StartDen(String time_StartDen) {
        this.time_StartDen = time_StartDen;
    }

    public String getTime_EndDen() {
        return time_EndDen;
    }

    public void setTime_EndDen(String time_EndDen) {
        this.time_EndDen = time_EndDen;
    }


}
