package com.core.core.web;

import com.core.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

//    private final ObjectProvider<MyLogger> loggerProvider;
    private final MyLogger logger;

    public void logic(String id) {
//        loggerProvider.getObject()
        logger.log("service id = " + id);
    }
}
