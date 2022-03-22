package com.example.teleappsistencia.ui.tipo_vivienda;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TipoViviendaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TipoViviendaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Usuarios fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}