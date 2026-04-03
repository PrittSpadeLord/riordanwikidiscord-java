package io.github.prittspadelord.riordanwikidiscord;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;

import lombok.AllArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DiscordBotLauncher implements CommandLineRunner {

    private EventHandler eventHandler;
    private GatewayDiscordClient client;

    @Override
    public void run(String... args) {
        client.on(ReadyEvent.class, eventHandler::handleReadyEvent).subscribe();
        client.on(MessageCreateEvent.class, eventHandler::handleMessageCreateEvent).subscribe();

        client.onDisconnect().block();
    }
}