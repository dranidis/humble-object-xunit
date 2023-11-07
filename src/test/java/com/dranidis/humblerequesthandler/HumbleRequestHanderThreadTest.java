package com.dranidis.humblerequesthandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dranidis.message.Request;

public class HumbleRequestHanderThreadTest {

    private static final long TWO_SECONDS = 2000L;

    @Test
    public void testLogicCalled_Sync() throws InterruptedException {
        // Setup:
        RequestHandlerRecordingStub mockHandler = new RequestHandlerRecordingStub();
        HumbleRequestHandlerThread sut = new HumbleRequestHandlerThread();
        //    Mock Installation:
        sut.setHandler(mockHandler);
        sut.start();
        // Exercise:
        sut.enqueRequest(makeSimpleRequest());
        // Verify:
        Thread.sleep(TWO_SECONDS);
        assertTrue("init", mockHandler.initializedSuccessfully());
        assertEquals(1, mockHandler.getNumberOfRequestsCompleted());
    }

    private Request makeSimpleRequest() {
        return new Request();
    }

}
