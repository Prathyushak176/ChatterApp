package com.chatter.entity;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Message {
	private String senderName;
	private String receiverName;
	private String message;
	private LocalDateTime date;
	private Status status;
	public Message(Message message) {
	
		this.senderName = message.getSenderName();
		this.receiverName = message.getReceiverName();
		this.message = message.getMessage();
		this.date = message.getDate();
		this.status = message.getStatus();
	}

	
	
	
}
