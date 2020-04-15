package com.bw.blog.controllers;

import com.bw.blog.bd.entitys.Message;
import com.bw.blog.bd.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/message")
    public String message(Model model) {
        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "message";
    }

    @PostMapping("/addMessage")
    public String addMessage(@RequestParam(name = "name") String name,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "message") String text,
                             Model model) {

        Message message = new Message(name, email, text);
        messageRepository.saveAndFlush(message);

        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);

        return "message";
    }
    @PostMapping("/filter")
    public String filter(@RequestParam(name = "filter") String filter,
                         Model model) {
        List<Message> filterByName;
        if (filter == null | filter.isEmpty()) {
            filterByName = messageRepository.findAll();
        } else {
            filterByName = messageRepository.findByName(filter);
        }

        model.addAttribute("messages", filterByName);
        return "message";
    }
}