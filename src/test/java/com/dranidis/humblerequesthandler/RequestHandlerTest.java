package com.dranidis.humblerequesthandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dranidis.message.Request;
import com.dranidis.message.Response;

public class RequestHandlerTest {

    @Test
    public void testNotInitialized_Sync() throws InterruptedException {
        // Setup/ Exercise
        RequestHandler sut = new RequestHandlerImpl();

        //    Verify:
        assertFalse("init", sut.initializedSuccessfully());
    }

    @Test
    public void testWasInitialized_Sync() throws InterruptedException {
        //   Setup:
        RequestHandler sut = new RequestHandlerImpl();
        //   Exercise:
        sut.initializeThread();
        // Verify:
        assertTrue("init", sut.initializedSuccessfully());
    }

    @Test
    public void testHandleOneRequest_Sync() throws InterruptedException {
        // Setup:
        RequestHandler sut = new RequestHandlerImpl();
        // Exercise:
        Response response = sut.processOneRequest(makeSimpleRequest());
        // Verify:
        assertEquals(1, sut.getNumberOfRequestsCompleted());
        assertEquals(makeSimpleResponse(), response);
    }

    private Response makeSimpleResponse() {
        return new Response();
    }

    private Request makeSimpleRequest() {
        return new Request();
    }

}
