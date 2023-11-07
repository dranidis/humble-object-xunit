package com.dranidis.requesthandler;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.dranidis.message.Request;
import com.dranidis.message.Response;

public class RequestHandlerThread extends Thread {
    private static final int TIME_TO_SLEEP_IN_MILLIS = 2500;
    private boolean initializationCompleted = false;
    private int numberOfRequests = 0;
    private Queue<Request> requestQueue = new LinkedList<>();
    private Queue<Response> responseQueue = new LinkedList<>();

    public void run() {
        initializeThread();
        processRequestsForever();
    }

    private void initializeThread() {
        // wait for random time between 0 and 2 seconds
        // to simulate initialization time
        try {
            Thread.sleep(new Random().nextInt(TIME_TO_SLEEP_IN_MILLIS));
        } catch (InterruptedException e) {
            // ignore
        }
        initializationCompleted = true;
    }

    public boolean initializedSuccessfully() {
        return initializationCompleted;
    }

    void processRequestsForever() {
        Request request = nextMessage();
        do {
            Response response = processOneRequest(request);
            if (response != null) {
                putMsgOntoOutputQueue(response);
            }
            request = nextMessage();
        } while (request != null);
    }

    private void putMsgOntoOutputQueue(Response response) {
        responseQueue.add(response);
    }

    private Response processOneRequest(Request request) {
        // wait for random time between 0 and 2 seconds
        // and then return a response
        try {
            Thread.sleep(new Random().nextInt(TIME_TO_SLEEP_IN_MILLIS));
        } catch (InterruptedException e) {
            // ignore
        }
        numberOfRequests++;
        return new Response();
    }

    private Request nextMessage() {
        return requestQueue.poll();
    }

    public int getNumberOfRequestsCompleted() {
        return numberOfRequests;
    }

    public void enqueRequest(Request request) {
        requestQueue.add(request);
    }

    public Response getResponse() {
        return responseQueue.poll();
    }
}