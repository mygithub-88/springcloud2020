package com.budi.springcloud.controller;

import com.budi.springcloud.service.impl.MessageProviderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource
    private MessageProviderImpl messageProvider;
    @GetMapping("/sendmessage")
    public String sendMessage()
    {
        return messageProvider.send();
    }
}
