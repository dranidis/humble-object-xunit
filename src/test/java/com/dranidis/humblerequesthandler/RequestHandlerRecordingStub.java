package com.dranidis.humblerequesthandler;

import com.dranidis.message.Request;
import com.dranidis.message.Response;

public class RequestHandlerRecordingStub implements RequestHandler {

    private int numberOfRequestsDone = 0;
    private boolean initializedSuccessfully = false;

    @Override
    public void initializeThread() {
        initializedSuccessfully = true;
    }

    @Override
    public boolean initializedSuccessfully() {
        return initializedSuccessfully;
    }

    @Override
    public Response processOneRequest(Request request) {
        numberOfRequestsDone++;
        return new Response();
    }

    @Override
    public int getNumberOfRequestsCompleted() {
        return numberOfRequestsDone;
    }

}
