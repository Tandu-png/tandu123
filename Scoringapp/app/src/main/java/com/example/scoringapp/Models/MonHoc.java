package com.example.scoringapp.Models;

public class MonHoc {
    private String tenMon;
    private int tinChi;

    // Thường kỳ (3 cột)
    private double diemThuongKy1;
    private double diemThuongKy2;
    private double diemThuongKy3;

    // Thực hành (3 cột)
    private double diemThucHanh1;
    private double diemThucHanh2;
    private double diemThucHanh3;

    // Giữa kỳ & Cuối kỳ
    private double giuaKi;
    private double diemCuoiKy;

    // Constructor rút gọn (dùng trung bình cho TX và TH)
    public MonHoc(String tenMon, int tinChi,
                  double diemTX, double diemTH,
                  double giuaKi, double cuoiKi) {
        this.tenMon = tenMon;
        this.tinChi = tinChi;

        // nếu chỉ nhập 1 giá trị TX/TH thì gán cho cả 3 cột
        this.diemThuongKy1 = diemTX;
        this.diemThuongKy2 = diemTX;
        this.diemThuongKy3 = diemTX;

        this.diemThucHanh1 = diemTH;
        this.diemThucHanh2 = diemTH;
        this.diemThucHanh3 = diemTH;

        this.giuaKi = giuaKi;
        this.diemCuoiKy = cuoiKi;
    }

    // Getter & Setter
    public String getTenMon() { return tenMon; }
    public void setTenMon(String tenMon) { this.tenMon = tenMon; }

    public int getTinChi() { return tinChi; }
    public void setTinChi(int tinChi) { this.tinChi = tinChi; }

    public double getDiemThuongKy1() { return diemThuongKy1; }
    public void setDiemThuongKy1(double diemThuongKy1) { this.diemThuongKy1 = diemThuongKy1; }

    public double getDiemThuongKy2() { return diemThuongKy2; }
    public void setDiemThuongKy2(double diemThuongKy2) { this.diemThuongKy2 = diemThuongKy2; }

    public double getDiemThuongKy3() { return diemThuongKy3; }
    public void setDiemThuongKy3(double diemThuongKy3) { this.diemThuongKy3 = diemThuongKy3; }

    public double getDiemThucHanh1() { return diemThucHanh1; }
    public void setDiemThucHanh1(double diemThucHanh1) { this.diemThucHanh1 = diemThucHanh1; }

    public double getDiemThucHanh2() { return diemThucHanh2; }
    public void setDiemThucHanh2(double diemThucHanh2) { this.diemThucHanh2 = diemThucHanh2; }

    public double getDiemThucHanh3() { return diemThucHanh3; }
    public void setDiemThucHanh3(double diemThucHanh3) { this.diemThucHanh3 = diemThucHanh3; }

    public double getGiuaKi() { return giuaKi; }
    public void setGiuaKi(double giuaKi) { this.giuaKi = giuaKi; }

    public double getDiemCuoiKy() { return diemCuoiKy; }
    public void setDiemCuoiKy(double diemCuoiKy) { this.diemCuoiKy = diemCuoiKy; }

    // Công thức tính điểm trung bình
    public double tinhDiemTrungBinh() {
        double diemThuongKy = (diemThuongKy1 + diemThuongKy2 + diemThuongKy3) / 3.0;
        double diemThucHanh = (diemThucHanh1 + diemThucHanh2 + diemThucHanh3) / 3.0;
        // ví dụ: 20% TX + 20% TH + 20% GK + 40% CK
        return 0.2 * diemThuongKy + 0.2 * diemThucHanh + 0.2 * giuaKi + 0.4 * diemCuoiKy;
    }
    public double getDiemTB() {

        double tbThuongKy = (diemThuongKy1 + diemThuongKy2 + diemThuongKy3) / 3.0;
        double tbThucHanh = (diemThucHanh1 + diemThucHanh2 + diemThucHanh3) / 3.0;
        return tbThuongKy + tbThucHanh; // hoặc return (tbThuongKy + tbThucHanh) / 2;

    }


}
