package com.dranidis.message;

public class Response {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Response;
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
