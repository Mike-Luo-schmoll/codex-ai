package com.schmoll.tlkw.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MidnightScheduler {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void start() {
        log.info("启动运行时间，故障时间计算...");
        scheduleNextMidnight();
    }

    private void scheduleNextMidnight() {
        LocalDateTime now = LocalDateTime.now();
        // 计算下一个凌晨 0 点 (如果是今天0点已过，则算明天0点)
        LocalDateTime nextMidnight = now.plusDays(1).toLocalDate().atStartOfDay();

        // 计算还需要等待多少秒
        long secondsToWait = ChronoUnit.SECONDS.between(now, nextMidnight);

        log.info("当前时间: {}", now);
        log.info("下次执行时间: {}", nextMidnight);
        log.info("等待秒数: {}", secondsToWait);

        // 提交任务：等待 specified 秒后执行
        scheduler.schedule(() -> {
            try {
                // ==============================
                // 👇 在这里写你需要执行的任务代码 👇
                // ==============================
                StaticClass.numberOfRefuelings=0;
                StaticClass.timeOfRefuelings =0;
                StaticClass.runTimeForDay=0;
                StaticClass.lastEndTime="";

            } catch (Exception e) {
               log.error("执行0点任务出现Error====>{}",e.getMessage());
            } finally {
                // 任务执行完（无论成功失败），立即调度下一天的任务
                scheduleNextMidnight();
            }
        }, secondsToWait, TimeUnit.SECONDS);
    }
}
