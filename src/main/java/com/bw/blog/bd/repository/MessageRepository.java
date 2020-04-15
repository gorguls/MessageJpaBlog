package com.bw.blog.bd.repository;

import com.bw.blog.bd.entitys.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByName(String name);
}