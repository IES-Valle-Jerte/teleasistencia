package com.example.teleappsistencia.ui.dispositivos_aux;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.teleappsistencia.databinding.FragmentDispositivosAuxiliaresBinding;

public class DispositivosAuxiliaresFragment extends Fragment {

    private DispositivosAuxiliaresViewModel dispositivosAuxiliaresViewModel;
    private FragmentDispositivosAuxiliaresBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dispositivosAuxiliaresViewModel =
                new ViewModelProvider(this).get(DispositivosAuxiliaresViewModel.class);

        binding = FragmentDispositivosAuxiliaresBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}