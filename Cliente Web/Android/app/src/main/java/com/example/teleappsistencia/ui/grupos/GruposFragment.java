package com.example.teleappsistencia.ui.grupos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.teleappsistencia.databinding.FragmentGruposBinding;

public class GruposFragment extends Fragment {

    private GruposViewModel gruposViewModel;
    private FragmentGruposBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gruposViewModel =
                new ViewModelProvider(this).get(GruposViewModel.class);

        binding = FragmentGruposBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}