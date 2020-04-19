package com.example.codemail.ui.home;

import javax.xml.namespace.NamespaceContext;

public class LiveBooks
{
    public String name,url;

    public LiveBooks(String name, String url)
    {
        this.name = name;
        this.url = url;
    }

    public LiveBooks()
    {

    }

    public String getname()
    {
        return name;
    }

    public void setname(String name)
    {
        this.name = name;
    }

    public String geturl()
    {
        return url;
    }

    private void setURL(String url)
    {
        this.url = url;
    }
}
