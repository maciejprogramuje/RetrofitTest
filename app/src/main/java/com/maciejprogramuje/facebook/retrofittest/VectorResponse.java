package com.maciejprogramuje.facebook.retrofittest;

class VectorResponse {
    public String login;
    public String password;
    public String sessionToken;

    @Override
    public String toString() {
        return login + ", " + password + ", " + sessionToken;
    }
}
