package com.xinmachong.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author meyer@HongYe
 */
@Configuration
public class TaskConfiguration {

    @Bean("logTaskExecutor")
    public Executor logTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);                                                    // 线程池创建时候初始化的线程数
        executor.setMaxPoolSize(20);                                                     // 线程池最大的线程数，只有在缓冲队列满了之后，才会申请超过核心线程数的线程
        executor.setQueueCapacity(200);                                                  // 缓冲任务队列的大小
        executor.setKeepAliveSeconds(60);                                                // 允许线程的空闲时间(60s)，超过会被销毁
        executor.setThreadNamePrefix("taskExecutor-");                                   // 线程的前缀
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 对拒绝任务的处理策略
        return executor;
    }
}
