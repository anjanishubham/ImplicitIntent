package com.lovelycoding.intent.data;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class InputData extends BaseObservable
{
    private String input;

    @Bindable
    public String getInput() {
        return input;
    }

    public void setInput(String input)
    {
        this.input = input;
        notifyPropertyChanged(BR.input);
    }
}
