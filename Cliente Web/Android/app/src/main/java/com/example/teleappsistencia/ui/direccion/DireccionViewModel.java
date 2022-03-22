package com.example.teleappsistencia.ui.direccion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DireccionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DireccionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is direccion fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}