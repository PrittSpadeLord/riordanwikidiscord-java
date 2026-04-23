package io.github.prittspadelord.riordanwikidiscord.eventhandlers;

import io.github.prittspadelord.riordanwikidiscord.commands.CommandDispatcher;
import io.github.prittspadelord.riordanwikidiscord.stickers.StickerHandler;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class MessageCreateEventHandler {

    private CommandDispatcher commandDispatcher;
    private StickerHandler stickerHandler;

    public Mono<Void> handleEvent(MessageCreateEvent event) {
        Message message = event.getMessage();

        if(message.getAuthor().isEmpty()) return Mono.empty();
        if(message.getAuthor().get().isBot()) return Mono.empty();

        String content = message.getContent();

        if(content.startsWith("$")) {
            //perhaps simply return embed
            return commandDispatcher.handleCommand(content.substring(1)).then();
        }

        if(content.startsWith(";") && content.endsWith(";")) {
            return stickerHandler.handleSticker(content.substring(1, content.length() - 1)).then();
        }

        return Mono.empty();
    }
}