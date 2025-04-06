package com.example.demo;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.example.demo.account.service.AuthenticationService;
import com.example.demo.price.aop.PriceAop;
import com.example.demo.shell.MyCommands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AopTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.shell.interactive.enabled=false",
        "file.type=csv",
        "file.price-path=price.csv",
        "file.account-path=account.csv"
})
public class PriceAopTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private MyCommands myCommands;

    private ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    public void setUp() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        listAppender = new ListAppender<>();
        listAppender.setContext(loggerContext);
        listAppender.start();
        loggerContext.getLogger("com.example.demo.price.aop.PriceAop").addAppender(listAppender);
    }

    @Test
    public void testNotLogin() {
        assertThrows(Exception.class, () -> myCommands.city());
    }

    @Test
    public void testLogout() {
        authenticationService.login(1L, "1");
        authenticationService.logout();
        assertThrows(Exception.class, () -> myCommands.city());
    }

    @Test
    public void testLogin() {
        authenticationService.login(1L, "1");
        String cities = myCommands.city();
        assertTrue(cities.contains("동두천시"));
    }

    @Test
    public void testLoggingAspect() {
        authenticationService.login(1L, "1");

        myCommands.sector("동두천시");

        List<ILoggingEvent> logsList = listAppender.list;
        assertThat(logsList).isNotEmpty();

        assertTrue(logsList.getFirst().getFormattedMessage().contains("선도형"));
        assertTrue(logsList.getFirst().getFormattedMessage().contains("동두천시"));
    }

    @Test
    public void isAop() {

        assertTrue(AopUtils.isAopProxy(myCommands));
    }

    @Test
    public void testNonAop() {
        MyCommands nonAopCommands = AopTestUtils.getTargetObject(myCommands);
        assertFalse(AopUtils.isAopProxy(nonAopCommands));

        String cities = nonAopCommands.city();
        assertTrue(cities.contains("동두천시"));
    }
}