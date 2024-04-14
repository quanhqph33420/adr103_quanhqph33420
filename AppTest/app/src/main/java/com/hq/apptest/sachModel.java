package com.hq.apptest;

public class sachModel {
    String _id, masach_ph33420, tieude_ph33420, tacgia_ph33420;
    int namxuatban_ph33420, sotrang_ph33420;
    String theloai_ph33420;

    public sachModel(String _id, String masach_ph33420, String tieude_ph33420, String tacgia_ph33420, int namxuatban_ph33420, int sotrang_ph33420, String theloai_ph33420) {
        this._id = _id;
        this.masach_ph33420 = masach_ph33420;
        this.tieude_ph33420 = tieude_ph33420;
        this.tacgia_ph33420 = tacgia_ph33420;
        this.namxuatban_ph33420 = namxuatban_ph33420;
        this.sotrang_ph33420 = sotrang_ph33420;
        this.theloai_ph33420 = theloai_ph33420;
    }

    public sachModel(String masach_ph33420, String tieude_ph33420, String tacgia_ph33420, int namxuatban_ph33420, int sotrang_ph33420, String theloai_ph33420) {
        this.masach_ph33420 = masach_ph33420;
        this.tieude_ph33420 = tieude_ph33420;
        this.tacgia_ph33420 = tacgia_ph33420;
        this.namxuatban_ph33420 = namxuatban_ph33420;
        this.sotrang_ph33420 = sotrang_ph33420;
        this.theloai_ph33420 = theloai_ph33420;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMasach_ph33420() {
        return masach_ph33420;
    }

    public void setMasach_ph33420(String masach_ph33420) {
        this.masach_ph33420 = masach_ph33420;
    }

    public String getTieude_ph33420() {
        return tieude_ph33420;
    }

    public void setTieude_ph33420(String tieude_ph33420) {
        this.tieude_ph33420 = tieude_ph33420;
    }

    public String getTacgia_ph33420() {
        return tacgia_ph33420;
    }

    public void setTacgia_ph33420(String tacgia_ph33420) {
        this.tacgia_ph33420 = tacgia_ph33420;
    }

    public int getNamxuatban_ph33420() {
        return namxuatban_ph33420;
    }

    public void setNamxuatban_ph33420(int namxuatban_ph33420) {
        this.namxuatban_ph33420 = namxuatban_ph33420;
    }

    public int getSotrang_ph33420() {
        return sotrang_ph33420;
    }

    public void setSotrang_ph33420(int sotrang_ph33420) {
        this.sotrang_ph33420 = sotrang_ph33420;
    }

    public String getTheloai_ph33420() {
        return theloai_ph33420;
    }

    public void setTheloai_ph33420(String theloai_ph33420) {
        this.theloai_ph33420 = theloai_ph33420;
    }
}
