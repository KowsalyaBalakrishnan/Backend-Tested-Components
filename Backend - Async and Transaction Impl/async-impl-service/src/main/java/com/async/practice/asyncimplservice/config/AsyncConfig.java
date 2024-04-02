package com.async.practice.asyncimplservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public Executor getAsyncExecutor() {
        /*
        * The main idea is that when a task is submitted, the executor will first try to use a free thread if the number of active threads is currently less than the core size.
        * If the core size has been reached, then the task will be added to the queue as long as its capacity has not yet been reached.
        * Only then, if the queue’s capacity has been reached, will the executor create a new thread beyond the core size.
        * If the max size has also been reached, then the executor will reject the task.
        *  By default, the queue is unbounded, but this is rarely the desired configuration, because it can lead to OutOfMemoryErrors if enough tasks are added to that queue while all pool threads are busy.
        * Furthermore, if the queue is unbounded, then the max size has no effect at all.
        * Since the executor will always try the queue before creating a new thread beyond the core size, a queue must have a finite capacity for the thread pool to grow beyond the core size (this is why a fixed size pool is the only sensible case when using an unbounded queue).
        * */
        threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(2);
        threadPoolTaskExecutor.setMaxPoolSize(2);
        threadPoolTaskExecutor.setQueueCapacity(1);
        threadPoolTaskExecutor.setThreadNamePrefix("Async-Thread::");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
