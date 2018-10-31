package com.danceUpByStas.utilities;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

class SessionListenerTest {

    @Mock
    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    void sessionCreatedSuccess() {

        SessionListener listener = new SessionListener();
        HttpSession session = Mockito.mock(HttpSession.class);
        HttpSessionEvent event = new HttpSessionEvent(session);
        listener.sessionCreated(event);
        int maxInactiveInterval = event.getSession().getMaxInactiveInterval();

    }

    @Test
    void sessionDestroyedSuccess() {
    }
}