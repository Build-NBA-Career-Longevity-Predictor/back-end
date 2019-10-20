package com.lambdaschool.nbapredictor.models;

import com.lambdaschool.nbapredictor.logging.Loggable;

//For documentation for swagger manual api plugin. Doesn't show up as table because no entity annotation
@Loggable
public class UserLogin
{
    private String username;
    private String password;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
