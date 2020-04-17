package com.example.codemail.ui.home;

public class MainModel2 {
    Integer schoolLogo2;
    String schoolName2;

    public MainModel2 (Integer langLogo2, String langName2)
    {
        this.schoolLogo2 = langLogo2;
        this.schoolName2 = langName2;
    }

    public Integer getLangLogo()
    {
        return schoolLogo2;
    }

    public String getLangName() {
        return schoolName2;
    }
}
