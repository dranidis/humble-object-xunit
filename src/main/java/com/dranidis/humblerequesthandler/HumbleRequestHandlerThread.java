package com.dranidis.humblerequesthandler;

import java.util.LinkedList;
import java.util.Queue;

import com.dranidis.message.Request;
import com.dranidis.message.Response;

public class HumbleRequestHandlerThread extends Thread {
    private Queue<Request> requestQueue = new LinkedList<>();
    private Queue<Response> responseQueue = new LinkedList<>();

    private RequestHandler requestHandler;

    public HumbleRequestHandlerThread() {
        super();
        requestHandler = new RequestHandlerImpl();
    }

    public void setHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public void run() {
        requestHandler.initializeThread();
        processRequestsForever();
    }

    public boolean initializedSuccessfully() {
        return requestHandler.initializedSuccessfully();
    }

    void processRequestsForever() {
        Request request = nextMessage();
        do {
            Response response = requestHandler.processOneRequest(request);
            if (response != null) {
                putMsgOntoOutputQueue(response);
            }
            request = nextMessage();
        } while (request != null);
    }

    private void putMsgOntoOutputQueue(Response response) {
        responseQueue.add(response);
    }

    private Request nextMessage() {
        return requestQueue.poll();
    }

    public int getNumberOfRequestsCompleted() {
        return requestHandler.getNumberOfRequestsCompleted();
    }

    public void enqueRequest(Request request) {
        requestQueue.add(request);
    }

    public Response getResponse() {
        return responseQueue.poll();
    }
}