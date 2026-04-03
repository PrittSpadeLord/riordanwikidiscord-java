package io.github.prittspadelord.riordanwikidiscord.commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Message;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class CommandHandler {

    private GatewayDiscordClient client;

    public Mono<Message> handleCommand(String content) {

        String[] tokens = content.split("/ /g");

        if("help".equals(tokens[0])) {
            return null;
        }

        return null;
    }
}