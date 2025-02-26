package io.github.gabznavas.livechatms.controller;

import io.github.gabznavas.livechatms.domain.ChatInput;
import io.github.gabznavas.livechatms.domain.ChatOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class LiveChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/new-message/{chatId}")
    public void newMessage(@DestinationVariable String chatId, ChatInput input) {
        final String message = HtmlUtils.htmlEscape(input.user() + ":" + input.message());
        messagingTemplate.convertAndSend("/topics/livechat/" + chatId, new ChatOutput(message));
    }
}
