package com.example.scoringapp.Activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scoringapp.Adapters.MonHocAdapter;
import com.example.scoringapp.Models.MonHoc;
import com.example.scoringapp.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private Spinner spnLoaiDiem;
    private LinearLayout layoutMonHoc, layoutHocKy, layoutCaiThien, layoutTichLuy;
    private EditText edtSoTinChi, edtGiuaKi, edtCuoiKi, edtMucTieu, edtTongTinChiTruoc, edtDiemTichLuyTruoc;
    private RecyclerView rvThuongXuyen, rvThucHanh, rvHocKy;
    private Button btnTinhDiem, btnXoaTrang;
    private TextView tvThang10, tvThang4, tvDiemChu;

    private MonHocAdapter thuongXuyenAdapter, thucHanhAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Ánh xạ view
        spnLoaiDiem = findViewById(R.id.spnLoaiDiem);
        layoutMonHoc = findViewById(R.id.layoutMonHoc);
        layoutHocKy = findViewById(R.id.layoutHocKy);
        layoutCaiThien = findViewById(R.id.layoutCaiThien);
        layoutTichLuy = findViewById(R.id.layoutTichLuy);

        edtSoTinChi = findViewById(R.id.edtSoTinChi);
        edtGiuaKi = findViewById(R.id.edtGiuaKi);
        edtCuoiKi = findViewById(R.id.edtCuoiKi);
        edtMucTieu = findViewById(R.id.edtMucTieu);
        edtTongTinChiTruoc = findViewById(R.id.edtTongTinChiTruoc);
        edtDiemTichLuyTruoc = findViewById(R.id.edtDiemTichLuyTruoc);

        rvThuongXuyen = findViewById(R.id.rvThuongXuyen);
        rvThucHanh = findViewById(R.id.rvThucHanh);
        rvHocKy = findViewById(R.id.rvHocKy);

        btnTinhDiem = findViewById(R.id.btnTinhDiem);
        btnXoaTrang = findViewById(R.id.btnXoaTrang);

        tvThang10 = findViewById(R.id.tvThang10);
        tvThang4 = findViewById(R.id.tvthang4);
        tvDiemChu = findViewById(R.id.tvDiemChu);

        // Cài đặt Spinner
        String[] loaiDiem = {"Điểm từng môn", "Trung bình học kỳ", "Điểm cần cải thiện", "Điểm tích lũy"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, loaiDiem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLoaiDiem.setAdapter(adapter);

        spnLoaiDiem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                layoutMonHoc.setVisibility(View.GONE);
                layoutHocKy.setVisibility(View.GONE);
                layoutCaiThien.setVisibility(View.GONE);
                layoutTichLuy.setVisibility(View.GONE);

                switch (pos) {
                    case 0: // Điểm từng môn
                        layoutMonHoc.setVisibility(View.VISIBLE);
                        break;
                    case 1: // Trung bình học kỳ
                        layoutHocKy.setVisibility(View.VISIBLE);
                        break;
                    case 2: // Điểm cần cải thiện
                        layoutCaiThien.setVisibility(View.VISIBLE);
                        break;
                    case 3: // Điểm tích lũy
                        layoutTichLuy.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // RecyclerView Thường xuyên
        rvThuongXuyen.setLayoutManager(new LinearLayoutManager(this));
        List<MonHoc> listThuongXuyen = new ArrayList<>();
        listThuongXuyen.add(new MonHoc("TX1", 0, 0, 0, 0, 0));
        listThuongXuyen.add(new MonHoc("TX2", 0, 0, 0, 0, 0));
        listThuongXuyen.add(new MonHoc("TX3", 0, 0, 0, 0, 0));
        thuongXuyenAdapter = new MonHocAdapter(listThuongXuyen);
        rvThuongXuyen.setAdapter(thuongXuyenAdapter);

// RecyclerView Thực hành
        rvThucHanh.setLayoutManager(new LinearLayoutManager(this));
        List<MonHoc> listThucHanh = new ArrayList<>();
        listThucHanh.add(new MonHoc("TH1", 0, 0, 0, 0, 0));
        listThucHanh.add(new MonHoc("TH2", 0, 0, 0, 0, 0));
        listThucHanh.add(new MonHoc("TH3", 0, 0, 0, 0, 0));
        thucHanhAdapter = new MonHocAdapter(listThucHanh);
        rvThucHanh.setAdapter(thucHanhAdapter);;

        // RecyclerView Học kỳ
        rvHocKy.setLayoutManager(new LinearLayoutManager(this));

        // Nút tính điểm
        btnTinhDiem.setOnClickListener(v -> tinhDiem());

        // Nút xóa trắng
        btnXoaTrang.setOnClickListener(v -> xoaTrang());
    }

    private void tinhDiem() {
        double ketQua = 0.0;
        try {
            // Tính điểm môn học
            if (layoutMonHoc.getVisibility() == View.VISIBLE) {
                double giuaKi = getDouble(edtGiuaKi);
                double cuoiKi = getDouble(edtCuoiKi);

                // trung bình điểm thường xuyên
                double diemTX = thuongXuyenAdapter.getAverageScore();
                // trung bình điểm thực hành
                double diemTH = thucHanhAdapter.getAverageScore();

                ketQua = diemTX * 0.2 + diemTH * 0.2 + giuaKi * 0.2 + cuoiKi * 0.4;
            }
            // TODO: tính thêm cho học kỳ, tích lũy, cải thiện

            // Hiển thị kết quả
            tvThang10.setText("Kết quả thang 10: " + String.format("%.2f", ketQua));
            double diemHe4 = convertTo4(ketQua);
            tvThang4.setText("Kết quả thang 4: " + String.format("%.2f", diemHe4));
            tvDiemChu.setText("Điểm chữ: " + convertToLetter(diemHe4));

        } catch (Exception e) {
            Toast.makeText(this, "Lỗi khi tính điểm!", Toast.LENGTH_SHORT).show();
        }
    }

    private void xoaTrang() {
        edtSoTinChi.setText("");
        edtGiuaKi.setText("");
        edtCuoiKi.setText("");
        edtMucTieu.setText("");
        edtTongTinChiTruoc.setText("");
        edtDiemTichLuyTruoc.setText("");
        tvThang10.setText("Kết quả thang 10: ");
        tvThang4.setText("Kết quả thang 4: ");
        tvDiemChu.setText("Điểm chữ: ");
    }

    private double getDouble(EditText edt) {
        if (TextUtils.isEmpty(edt.getText().toString())) return 0.0;
        return Double.parseDouble(edt.getText().toString());
    }

    private double convertTo4(double diem10) {
        if (diem10 >= 8.5) return 4.0;
        if (diem10 >= 7.0) return 3.0;
        if (diem10 >= 5.5) return 2.0;
        if (diem10 >= 4.0) return 1.0;
        return 0.0;
    }

    private String convertToLetter(double diemHe4) {
        if (diemHe4 == 4.0) return "A";
        if (diemHe4 == 3.0) return "B";
        if (diemHe4 == 2.0) return "C";
        if (diemHe4 == 1.0) return "D";
        return "F";
    }
}
