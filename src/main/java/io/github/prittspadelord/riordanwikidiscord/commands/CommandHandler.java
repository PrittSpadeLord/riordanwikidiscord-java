package io.github.prittspadelord.riordanwikidiscord.commands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Message;

import reactor.core.publisher.Mono;

public interface CommandHandler {
    Mono<Message> handleCommand(GatewayDiscordClient client, String[] tokens);
}