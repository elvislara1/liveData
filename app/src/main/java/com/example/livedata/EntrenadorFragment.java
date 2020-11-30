package com.example.livedata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.livedata.databinding.FragmentEntrenadorBinding;


public class EntrenadorFragment extends Fragment {

    private FragmentEntrenadorBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentEntrenadorBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EntrenadorViewModel entrenadorViewModel = new ViewModelProvider(this).get(EntrenadorViewModel.class);

        entrenadorViewModel.obtenerImagen().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer ejercicio) {
                Glide.with(EntrenadorFragment.this).load(ejercicio).into(binding.ejercicio);
            }
        });
    }
}