package com.example.codemail.ui.yourorders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YourordersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public YourordersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is your orders fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}