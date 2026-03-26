package io.github.prittspadelord.riordanwikidiscord;

import io.github.prittspadelord.riordanwikidiscord.constants.Channels;
import io.github.prittspadelord.riordanwikidiscord.constants.Emotes;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;

import org.springframework.boot.SpringApplication;

import reactor.core.publisher.Mono;

public class RiordanWikiDiscordBotApplication {
    static void main(String[] args) {
        SpringApplication.run(RiordanWikiDiscordBotApplication.class, args);

        DiscordClient client = DiscordClient.create(System.getenv("DISCORD_TOKEN"));

        //Just some sanity tests, will be replaced with something consisting of more structure

        client.withGateway((gateway) -> {
            Mono<Void> readyListener = gateway.on(ReadyEvent.class, event -> {

                return client.getChannelById(Snowflake.of(Channels.GENERAL.getId())).createMessage(String.format("%s%s%s", Emotes.EXPBAR_FULL, Emotes.EXPBAR_ONEFOURTH, Emotes.EXPBAR_EMPTY));
            }).then();

            return Mono.when(readyListener);
        }).block();
    }
}