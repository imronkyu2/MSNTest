package com.example.msntest.modul.home.subclass.modusemotion.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msntest.core.model.ModusEmotionResult;
import com.example.msntest.databinding.AdapterModusEmotionBinding;

import java.util.List;

public class ModusEmotionAdapter extends RecyclerView.Adapter<ModusEmotionAdapter.ViewHolder> {
    private final List<ModusEmotionResult> userList;

    public ModusEmotionAdapter(List<ModusEmotionResult> textList) {
        this.userList = textList;
    }

    @NonNull
    @Override
    public ModusEmotionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterModusEmotionBinding binding = AdapterModusEmotionBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ModusEmotionAdapter.ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ModusEmotionAdapter.ViewHolder holder, int position) {
        ModusEmotionResult user = userList.get(position);
        holder.binding.userName.setText(user.getName());
        holder.binding.emotion.setText(String.valueOf(user.getEmotion()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final AdapterModusEmotionBinding binding;

        public ViewHolder(AdapterModusEmotionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}