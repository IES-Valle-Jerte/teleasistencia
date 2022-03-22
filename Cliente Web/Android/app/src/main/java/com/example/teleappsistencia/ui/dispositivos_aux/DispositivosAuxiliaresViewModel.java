package com.example.teleappsistencia.ui.dispositivos_aux;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DispositivosAuxiliaresViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DispositivosAuxiliaresViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is DispositivosAuxiliares fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}