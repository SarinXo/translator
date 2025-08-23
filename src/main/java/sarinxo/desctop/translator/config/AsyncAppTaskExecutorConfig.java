package sarinxo.desctop.translator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Configuration
public class AsyncAppTaskExecutorConfig {

    @Bean
    public ExecutorService asyncAppTaskExecutor() {
        ThreadFactory factory = Thread.ofVirtual()
                .name("async-task-exec-", 1)
                .factory();

        return Executors.newThreadPerTaskExecutor(factory);
    }

}
