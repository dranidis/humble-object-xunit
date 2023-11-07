package com.dranidis.requesthandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dranidis.message.Request;
import com.dranidis.message.Response;

public class RequestHandlerThreadTest {

    private static final long TWO_SECONDS = 2000L;

    @Test
    public void testWasInitialized_Async() throws InterruptedException {
        // Setup:
        RequestHandlerThread sut = new RequestHandlerThread();
        // Exercise:
        sut.start();
        //    Verify:
        Thread.sleep(TWO_SECONDS);
        assertTrue(sut.initializedSuccessfully());
    }

    @Test
    public void testHandleOneRequest_Async() throws InterruptedException {
        // Setup:
        RequestHandlerThread sut = new RequestHandlerThread();
        sut.start();
        // Exercise:
        sut.enqueRequest(makeSimpleRequest());
        // Verify:
        Thread.sleep(TWO_SECONDS);
        assertEquals(1, sut.getNumberOfRequestsCompleted());
        assertEquals(makeSimpleResponse(), sut.getResponse());
    }

    private Response makeSimpleResponse() {
        return new Response();
    }

    private Request makeSimpleRequest() {
        return new Request();
    }

}
