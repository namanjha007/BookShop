package com.example.codemail.ui.mywishlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MywishlistViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MywishlistViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is My wishlist fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}