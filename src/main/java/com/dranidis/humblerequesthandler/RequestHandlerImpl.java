package com.dranidis.humblerequesthandler;

import java.util.Random;

import com.dranidis.message.Request;
import com.dranidis.message.Response;

public class RequestHandlerImpl implements RequestHandler {

    private int numberOfRequestsCompleted;
    private boolean initializedSuccessfully;
    private static final int TIME_TO_SLEEP_IN_MILLIS = 2500;

    @Override
    public void initializeThread() {
        // wait for random time between 0 and 2 seconds
        // to simulate initialization time
        try {
            Thread.sleep(new Random().nextInt(TIME_TO_SLEEP_IN_MILLIS));
        } catch (InterruptedException e) {
            // ignore
        }
        initializedSuccessfully = true;
    }

    @Override
    public boolean initializedSuccessfully() {
        return initializedSuccessfully;
    }

    @Override
    public Response processOneRequest(Request request) {
        // wait for random time between 0 and 2 seconds
        // and then return a response
        try {
            Thread.sleep(new Random().nextInt(TIME_TO_SLEEP_IN_MILLIS));
        } catch (InterruptedException e) {
            // ignore
        }
        numberOfRequestsCompleted++;
        return new Response();
    }

    @Override
    public int getNumberOfRequestsCompleted() {
        return this.numberOfRequestsCompleted;
    }

}
