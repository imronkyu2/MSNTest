package com.example.msntest.modul.home.subclass.averagescore.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msntest.core.model.AverageScoreResult;
import com.example.msntest.databinding.AdapterAverageScoreBinding;

import java.util.List;

public class AverageScoreAdapter extends RecyclerView.Adapter<AverageScoreAdapter.ViewHolder> {
    private final List<AverageScoreResult> userList;

    public AverageScoreAdapter(List<AverageScoreResult> textList) {
        this.userList = textList;
    }

    @NonNull
    @Override
    public AverageScoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterAverageScoreBinding binding = AdapterAverageScoreBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new AverageScoreAdapter.ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(AverageScoreAdapter.ViewHolder holder, int position) {
        AverageScoreResult user = userList.get(position);
        holder.binding.userName.setText(user.getName());
        holder.binding.score.setText(String.valueOf(user.getAverageScore()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final AdapterAverageScoreBinding binding;

        public ViewHolder(AdapterAverageScoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}