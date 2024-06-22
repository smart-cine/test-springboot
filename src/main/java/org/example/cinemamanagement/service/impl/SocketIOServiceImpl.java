package org.example.cinemamanagement.service.impl;

import io.socket.client.IO;
import io.socket.client.Socket;
import jakarta.annotation.PostConstruct;
import org.example.cinemamanagement.service.SocketIOService;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class SocketIOServiceImpl implements SocketIOService {
    private Socket socket;

    @PostConstruct
    public void init() throws URISyntaxException {
        socket = IO.socket("http://khoakomlem-internal.ddns.net:9998");
        socket.connect();
    }

    public void emit(String event, Object data) {
        socket.emit(event, data);
    }
}
