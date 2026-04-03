package io.github.prittspadelord.riordanwikidiscord.stickers;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Message;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class StickerHandler {

    private GatewayDiscordClient client;

    public Mono<Message> handleSticker(String message) {
        return null;
    }
}
