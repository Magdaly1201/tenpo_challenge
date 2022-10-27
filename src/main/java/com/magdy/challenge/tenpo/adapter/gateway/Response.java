package com.magdy.challenge.tenpo.adapter.gateway;

public class Response {
    //TODO: MEJORAR
    //public int code;
    //public String message;
    public String body;

    public Response(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
