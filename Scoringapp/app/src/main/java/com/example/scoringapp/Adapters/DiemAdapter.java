package com.example.scoringapp.Adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scoringapp.R;

import java.util.ArrayList;

public class DiemAdapter extends RecyclerView.Adapter<DiemAdapter.DiemViewHolder> {

    private final ArrayList<Double> diemList = new ArrayList<>();
    private final String prefix; // "TX" hoặc "TH"

    // numItems = số lượng ô nhập, prefix = TX hoặc TH
    public DiemAdapter(int numItems, String prefix) {
        this.prefix = prefix;
        for (int i = 0; i < numItems; i++) {
            diemList.add(0.0); // mặc định = 0
        }
    }

    @NonNull
    @Override
    public DiemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_diem, parent, false);
        return new DiemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DiemViewHolder holder, int position) {
        holder.tvLoaiDiem.setText(prefix + (position + 1));

        // Hiển thị giá trị cũ nếu có
        Double value = diemList.get(position);
        if (value == 0.0) {
            holder.edtDiem.setText("");
        } else {
            holder.edtDiem.setText(String.valueOf(value));
        }

        // Gỡ bỏ TextWatcher cũ để tránh add chồng
        if (holder.currentWatcher != null) {
            holder.edtDiem.removeTextChangedListener(holder.currentWatcher);
        }

        // Tạo TextWatcher mới
        holder.currentWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    double d = Double.parseDouble(s.toString());
                    diemList.set(position, d);
                } catch (Exception e) {
                    diemList.set(position, 0.0);
                }
            }
        };

        holder.edtDiem.addTextChangedListener(holder.currentWatcher);
    }

    @Override
    public int getItemCount() {
        return diemList.size();
    }

    public ArrayList<Double> getDiemList() {
        return diemList;
    }

    public void clear() {
        for (int i = 0; i < diemList.size(); i++) {
            diemList.set(i, 0.0);
        }
        notifyDataSetChanged();
    }

    public void xoaTrang() {
        clear();
    }

    static class DiemViewHolder extends RecyclerView.ViewHolder {
        TextView tvLoaiDiem;
        EditText edtDiem;
        TextWatcher currentWatcher; // lưu để gỡ khi cần

        public DiemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLoaiDiem = itemView.findViewById(R.id.tvLoaiDiem);
            edtDiem = itemView.findViewById(R.id.edtDiem);
        }
    }
}
