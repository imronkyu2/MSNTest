package com.example.msntest.modul.home.subclass.averagescoreandmodusemotion.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msntest.core.model.AvgScoreAndModusEmotionResult;
import com.example.msntest.databinding.AdapterAverageScoreAndModusEmotionBinding;

import java.util.List;

public class AverageScoreAndModusEmotionAdapter extends RecyclerView.Adapter<AverageScoreAndModusEmotionAdapter.ViewHolder> {
    private final List<AvgScoreAndModusEmotionResult> userList;

    public AverageScoreAndModusEmotionAdapter(List<AvgScoreAndModusEmotionResult> textList) {
        this.userList = textList;
    }

    @NonNull
    @Override
    public AverageScoreAndModusEmotionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterAverageScoreAndModusEmotionBinding binding = AdapterAverageScoreAndModusEmotionBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new AverageScoreAndModusEmotionAdapter.ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AverageScoreAndModusEmotionAdapter.ViewHolder holder, int position) {
        AvgScoreAndModusEmotionResult user = userList.get(position);
          String nameDate = user.getName() + " : " + user.getCreated();
          String score = "Rata-rata skor : " + user.getAverageScore();
          String emotion = "Modus emosi : " + user.modusEmotion;
        holder.binding.userName.setText(nameDate);
        holder.binding.score.setText(score);
        holder.binding.emotion.setText(emotion);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final AdapterAverageScoreAndModusEmotionBinding binding;

        public ViewHolder(AdapterAverageScoreAndModusEmotionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}