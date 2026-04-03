package io.github.prittspadelord.riordanwikidiscord;

import io.github.prittspadelord.riordanwikidiscord.eventhandlers.MessageCreateEventHandler;
import io.github.prittspadelord.riordanwikidiscord.eventhandlers.ReadyEventHandler;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;

import lombok.AllArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DiscordBotLauncher implements CommandLineRunner {

    private ReadyEventHandler readyEventHandler;
    private GatewayDiscordClient client;
    private MessageCreateEventHandler messageCreateEventHandler;

    @Override
    public void run(String... args) {
        client.on(ReadyEvent.class, readyEventHandler::handleEvent).subscribe();
        client.on(MessageCreateEvent.class, messageCreateEventHandler::handleEvent).subscribe();
        client.onDisconnect().block();
    }
}