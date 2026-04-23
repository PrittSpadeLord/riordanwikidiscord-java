package io.github.prittspadelord.riordanwikidiscord.commands.help;

import discord4j.core.GatewayDiscordClient;
import io.github.prittspadelord.riordanwikidiscord.commands.CommandHandler;

import discord4j.core.object.entity.Message;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class HelpCommandHandler implements CommandHandler {

    @Override
    public Mono<Message> handleCommand(GatewayDiscordClient unusedClient, String[] tokens) {

        if(tokens.length == 1) {
            //default help output


        }

        return null;
    }
}