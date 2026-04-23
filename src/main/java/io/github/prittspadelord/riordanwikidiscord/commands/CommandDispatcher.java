package io.github.prittspadelord.riordanwikidiscord.commands;

import io.github.prittspadelord.riordanwikidiscord.commands.help.HelpCommandHandler;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Message;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class CommandDispatcher {

    private GatewayDiscordClient client;

    private HelpCommandHandler helpCommandHandler;

    public Mono<Message> handleCommand(String content) {

        String[] tokens = content.split("/ /g");

        if("help".equals(tokens[0])) return helpCommandHandler.handleCommand(client, tokens);


        return Mono.empty(); //replace this with the default error message impl
    }
}