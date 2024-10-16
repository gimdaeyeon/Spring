package hello.core.adbanced;

import hello.core.adbanced.trace.logtrace.FieldLogTrace;
import hello.core.adbanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace(){
        return new FieldLogTrace();
    }
}
