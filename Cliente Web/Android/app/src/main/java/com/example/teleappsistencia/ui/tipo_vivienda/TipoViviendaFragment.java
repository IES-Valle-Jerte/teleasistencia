package com.example.teleappsistencia.ui.tipo_vivienda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.teleappsistencia.databinding.FragmentTipoViviendaBinding;

public class TipoViviendaFragment extends Fragment {

    private TipoViviendaViewModel tipoViviendaViewModel;
    private FragmentTipoViviendaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tipoViviendaViewModel =
                new ViewModelProvider(this).get(TipoViviendaViewModel.class);

        binding = FragmentTipoViviendaBinding.inflate(inflater, container, false);
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