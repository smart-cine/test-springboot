package org.example.cinemamanagement.service;

public interface SocketIOService {
    public void emit(String event, Object data);

    public void init() throws Exception;
}
