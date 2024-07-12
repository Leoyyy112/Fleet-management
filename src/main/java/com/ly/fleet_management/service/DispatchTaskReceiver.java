package com.ly.fleet_management.service;

import com.ly.fleet_management.entity.DispatchTask;
import org.springframework.stereotype.Service;

@Service
public class DispatchTaskReceiver {

    public void receiveMessage(DispatchTask dispatchTask) {
        // 处理接收到的调度任务
        System.out.println("Received <" + dispatchTask + ">");
        // 在这里可以实现任务分发逻辑
    }
}
