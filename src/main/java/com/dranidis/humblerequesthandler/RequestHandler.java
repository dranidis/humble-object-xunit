package com.dranidis.humblerequesthandler;

import com.dranidis.message.Request;
import com.dranidis.message.Response;

public interface RequestHandler {

    void initializeThread();

    boolean initializedSuccessfully();

    Response processOneRequest(Request request);

    int getNumberOfRequestsCompleted();

}
