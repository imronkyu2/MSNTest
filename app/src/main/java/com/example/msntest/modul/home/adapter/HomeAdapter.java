package com.example.msntest.modul.home.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msntest.core.model.UserEmotion;
import com.example.msntest.databinding.AdapterHomeBinding;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final List<UserEmotion> userList;

    public HomeAdapter(List<UserEmotion> textList) {
        this.userList = textList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterHomeBinding binding = AdapterHomeBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        UserEmotion user = userList.get(position);
        holder.binding.userName.setText(user.getName());
        holder.binding.date.setText(user.getCreated());
        holder.binding.emotion.setText(user.getEmotion());
        holder.binding.score.setText(String.valueOf(user.getScore()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final AdapterHomeBinding binding;

        public ViewHolder(AdapterHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
