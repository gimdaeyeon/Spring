package hello.core.adbanced;

import hello.core.adbanced.trace.logtrace.LogTrace;
import hello.core.adbanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {    @Bean
    public LogTrace logTrace(){
//        return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }
}
