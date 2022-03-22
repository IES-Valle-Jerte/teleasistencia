package com.example.teleappsistencia.ui.historico_tipo_situacion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoricoTipoSituacionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HistoricoTipoSituacionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Historico Tipo Situacion fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}