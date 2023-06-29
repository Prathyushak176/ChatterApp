package com.chatter.entity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.chatter.controller.ChatController;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.chatter.controller.ChatController;
import com.chatter.entity.Message;

public class MessageTest {

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @InjectMocks
    private ChatController chatController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testReceiveMessage() {
//        // Create a sample message
//        Message message = new Message();
//        message.setSenderName("John");
//        message.setReceiverName("Alice");
//        message.setMessage("Hello");
//
//        // Call the receiveMessage method
//        chatController.receiveMessage(message);
//
//        // Verify that the message is sent to the "/chatroom/public" destination
//        verify(simpMessagingTemplate, times(1)).convertAndSend("/chatroom/public", message);
//    }




    @Test
    public void testRecMessage() {
       
        Message message = new Message();
        message.setSenderName("John");
        message.setReceiverName("Jane");
        message.setMessage("Hello");
        message.setDate(LocalDateTime.now());
        message.setStatus(Status.NEW);

     
        Message result = chatController.recMessage(message);

        assertEquals(message, result);

        verify(simpMessagingTemplate
        		, times(1)).convertAndSendToUser(message.getReceiverName(), "/private", message);
    }
}
