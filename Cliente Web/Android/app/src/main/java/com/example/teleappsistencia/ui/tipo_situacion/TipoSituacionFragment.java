package com.example.teleappsistencia.ui.tipo_situacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.teleappsistencia.databinding.FragmentTipoSituacionBinding;

public class TipoSituacionFragment extends Fragment {

    private TipoSituacionViewModel tipoSituacionViewModel;
    private FragmentTipoSituacionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tipoSituacionViewModel =
                new ViewModelProvider(this).get(TipoSituacionViewModel.class);

        binding = FragmentTipoSituacionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textGallery;
        usuariosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}