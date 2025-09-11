package com.example.scoringapp.Adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scoringapp.Models.MonHoc;
import com.example.scoringapp.R;

import java.util.List;

public class MonHocAdapter extends RecyclerView.Adapter<MonHocAdapter.MonHocViewHolder> {

    private final List<MonHoc> monHocList;

    // Constructor nhận list
    public MonHocAdapter(List<MonHoc> monHocList) {
        this.monHocList = monHocList;
    }

    @NonNull
    @Override
    public MonHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_monhoc, parent, false);
        return new MonHocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonHocViewHolder holder, int position) {
        MonHoc monHoc = monHocList.get(position);

        // Set dữ liệu vào EditText
        holder.edtTenMon.setText(monHoc.getTenMon());
        holder.edtTinChi.setText(String.valueOf(monHoc.getTinChi()));
        holder.edtDiemThuongKy1.setText(String.valueOf(monHoc.getDiemThuongKy1()));
        holder.edtDiemThuongKy2.setText(String.valueOf(monHoc.getDiemThuongKy2()));
        holder.edtDiemThuongKy3.setText(String.valueOf(monHoc.getDiemThuongKy3()));
        holder.edtDiemThucHanh1.setText(String.valueOf(monHoc.getDiemThucHanh1()));
        holder.edtDiemThucHanh2.setText(String.valueOf(monHoc.getDiemThucHanh2()));
        holder.edtDiemThucHanh3.setText(String.valueOf(monHoc.getDiemThucHanh3()));
        holder.edtGiuaKy.setText(String.valueOf(monHoc.getGiuaKi()));
        holder.edtDiemCuoiKy.setText(String.valueOf(monHoc.getDiemCuoiKy()));

        // Gán listener để cập nhật model khi người dùng nhập
        addSafeTextWatcher(holder.edtTenMon, s -> monHoc.setTenMon(s));
        addSafeTextWatcher(holder.edtTinChi, s -> monHoc.setTinChi(parseIntSafe(s)));
        addSafeTextWatcher(holder.edtDiemThuongKy1, s -> monHoc.setDiemThuongKy1(parseDoubleSafe(s)));
        addSafeTextWatcher(holder.edtDiemThuongKy2, s -> monHoc.setDiemThuongKy2(parseDoubleSafe(s)));
        addSafeTextWatcher(holder.edtDiemThuongKy3, s -> monHoc.setDiemThuongKy3(parseDoubleSafe(s)));
        addSafeTextWatcher(holder.edtDiemThucHanh1, s -> monHoc.setDiemThucHanh1(parseDoubleSafe(s)));
        addSafeTextWatcher(holder.edtDiemThucHanh2, s -> monHoc.setDiemThucHanh2(parseDoubleSafe(s)));
        addSafeTextWatcher(holder.edtDiemThucHanh3, s -> monHoc.setDiemThucHanh3(parseDoubleSafe(s)));
        addSafeTextWatcher(holder.edtGiuaKy, s -> monHoc.setGiuaKi(parseDoubleSafe(s)));
        addSafeTextWatcher(holder.edtDiemCuoiKy, s -> monHoc.setDiemCuoiKy(parseDoubleSafe(s)));
    }

    @Override
    public int getItemCount() {
        return monHocList.size();
    }

    // Tính điểm trung bình có trọng số tín chỉ
    public double getAverageScore() {
        double totalScore = 0;
        int totalCredits = 0;
        for (MonHoc monHoc : monHocList) {
            totalScore += monHoc.tinhDiemTrungBinh() * monHoc.getTinChi();
            totalCredits += monHoc.getTinChi();
        }
        return totalCredits == 0 ? 0 : totalScore / totalCredits;
    }

    public static class MonHocViewHolder extends RecyclerView.ViewHolder {
        EditText edtTenMon, edtTinChi, edtDiemThuongKy1, edtDiemThuongKy2, edtDiemThuongKy3;
        EditText edtDiemThucHanh1, edtDiemThucHanh2, edtDiemThucHanh3;
        EditText edtGiuaKy, edtDiemCuoiKy;

        public MonHocViewHolder(@NonNull View itemView) {
            super(itemView);
            edtTenMon = itemView.findViewById(R.id.edtTenMon);
            edtTinChi = itemView.findViewById(R.id.edtTinChi);
            edtDiemThuongKy1 = itemView.findViewById(R.id.edtDiemThuongKy1);
            edtDiemThuongKy2 = itemView.findViewById(R.id.edtDiemThuongKy2);
            edtDiemThuongKy3 = itemView.findViewById(R.id.edtDiemThuongKy3);
            edtDiemThucHanh1 = itemView.findViewById(R.id.edtDiemThucHanh1);
            edtGiuaKy = itemView.findViewById(R.id.edtGiuaKy);
            edtDiemThucHanh2 = itemView.findViewById(R.id.edtDiemThucHanh2);
            edtDiemThucHanh3 = itemView.findViewById(R.id.edtDiemThucHanh3);
            edtDiemCuoiKy = itemView.findViewById(R.id.edtDiemCuoiKy);
        }
    }

    // Hàm tiện ích để parse số an toàn
    private int parseIntSafe(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double parseDoubleSafe(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Hàm thêm TextWatcher an toàn
    private void addSafeTextWatcher(EditText editText, OnTextChanged onTextChanged) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                onTextChanged.update(s.toString().trim());
            }
        });
    }

    interface OnTextChanged {
        void update(String s);
    }
}
