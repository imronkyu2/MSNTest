package com.example.msntest.modul.viewdata.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msntest.core.model.UserEntity;
import com.example.msntest.databinding.AdapterViewDataUserRegisteredBinding;

import java.util.List;

public class ViewDataUserRegisteredAdapter extends RecyclerView.Adapter<ViewDataUserRegisteredAdapter.ViewHolder> {
    private final List<UserEntity> userList;

    public ViewDataUserRegisteredAdapter(List<UserEntity> textList) {
        this.userList = textList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterViewDataUserRegisteredBinding binding = AdapterViewDataUserRegisteredBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserEntity user = userList.get(position);
        holder.binding.textView.setText(user.getUsername());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final AdapterViewDataUserRegisteredBinding binding;

        public ViewHolder(AdapterViewDataUserRegisteredBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}

