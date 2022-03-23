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

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}