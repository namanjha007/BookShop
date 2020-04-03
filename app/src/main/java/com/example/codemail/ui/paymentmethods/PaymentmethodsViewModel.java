package com.example.codemail.ui.paymentmethods;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PaymentmethodsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PaymentmethodsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is payment methods fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}