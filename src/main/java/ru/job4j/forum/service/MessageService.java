package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository store;

    public MessageService(MessageRepository store) {
        this.store = store;
    }

    public void addMsg(Message message) {
        store.save(message);
    }

    public List<Message> finaById(int id) {
        return store.findById(id);
    }
}
