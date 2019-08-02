package com.lovelycoding.intent.data;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import java.util.Observable;

public class SmsData extends BaseObservable {
    private String phoneNumber;
    private String smsText;

    public SmsData(String phoneNumber, String smsText) {
        this.phoneNumber = phoneNumber;
        this.smsText = smsText;
    }

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);

    }

    @Bindable
    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
        notifyPropertyChanged(BR.smsText);

    }
}
