package com.example.codemail.ui.home;

public class MainModel {
    Integer schoolLogo;
    String schoolName;

    public MainModel (Integer langLogo, String langName)
    {
        this.schoolLogo = langLogo;
        this.schoolName = langName;
    }

    public Integer getLangLogo()
    {
        return schoolLogo;
    }

    public String getLangName() {
        return schoolName;
    }
}
