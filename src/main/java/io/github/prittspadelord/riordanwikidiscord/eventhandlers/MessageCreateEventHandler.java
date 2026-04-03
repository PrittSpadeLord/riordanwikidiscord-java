package io.github.prittspadelord.riordanwikidiscord.eventhandlers;

import io.github.prittspadelord.riordanwikidiscord.commands.CommandHandler;
import io.github.prittspadelord.riordanwikidiscord.stickers.StickerHandler;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class MessageCreateEventHandler {

    private CommandHandler commandHandler;
    private StickerHandler stickerHandler;

    public Mono<?> handleEvent(MessageCreateEvent event) {
        Message message = event.getMessage();

        if(message.getAuthor().isEmpty()) return Mono.empty();
        if(message.getAuthor().get().isBot()) return Mono.empty();

        String content = message.getContent();

        if(content.startsWith("$")) {
            return commandHandler.handleCommand(content.substring(1));
        }

        if(content.startsWith(";") && content.endsWith(";")) {
            return stickerHandler.handleSticker(content.substring(1, content.length() - 1));
        }

        return Mono.empty();
    }
}