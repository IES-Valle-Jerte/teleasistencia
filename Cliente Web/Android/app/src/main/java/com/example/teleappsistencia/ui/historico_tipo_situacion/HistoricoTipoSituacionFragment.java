package com.example.teleappsistencia.ui.historico_tipo_situacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.teleappsistencia.databinding.FragmentHistoricoTipoSituacionBinding;

public class HistoricoTipoSituacionFragment extends Fragment {

    private HistoricoTipoSituacionViewModel historicoTipoSituacionViewModel;
    private FragmentHistoricoTipoSituacionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historicoTipoSituacionViewModel =
                new ViewModelProvider(this).get(HistoricoTipoSituacionViewModel.class);

        binding = FragmentHistoricoTipoSituacionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}