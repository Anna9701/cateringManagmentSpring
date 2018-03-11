package com.zai.caterings.Auth.Interfaces;

public interface SecurityService {
    String findLoggedInUsername();
    void autologin(String username, String password);
}
