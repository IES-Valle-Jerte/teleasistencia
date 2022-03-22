package com.example.teleappsistencia.ui.tipo_situacion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TipoSituacionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TipoSituacionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Tipo Situacion fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}