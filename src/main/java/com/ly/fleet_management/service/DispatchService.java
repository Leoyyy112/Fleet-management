package com.ly.fleet_management.service;

import com.ly.fleet_management.entity.DispatchTask;
import com.ly.fleet_management.repository.DispatchTaskRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ly.fleet_management.config.RabbitMQConfig.QUEUE_NAME;

@Service
public class DispatchService {

    @Autowired
    private DispatchTaskRepository dispatchTaskRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<DispatchTask> getAllDispatchTasks() {
        return dispatchTaskRepository.findAll();
    }

    public Optional<DispatchTask> getDispatchTaskById(Long id) {
        return dispatchTaskRepository.findById(id);
    }

    public DispatchTask saveDispatchTask(DispatchTask dispatchTask) {
        DispatchTask savedTask = dispatchTaskRepository.save(dispatchTask);
        sendDispatchTaskToQueue(savedTask);
        return savedTask;
    }

    public void deleteDispatchTask(Long id) {
        dispatchTaskRepository.deleteById(id);
    }

    private void sendDispatchTaskToQueue(DispatchTask dispatchTask) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, dispatchTask);
    }
}
