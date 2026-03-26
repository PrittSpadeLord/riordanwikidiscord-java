package io.github.prittspadelord.riordanwikidiscord;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import io.github.prittspadelord.riordanwikidiscord.constants.Channels;
import io.github.prittspadelord.riordanwikidiscord.constants.Emotes;
import org.springframework.boot.SpringApplication;
import reactor.core.publisher.Mono;

public class RiordanWikiDiscordBotApplication {
    static void main(String[] args) {
        SpringApplication.run(RiordanWikiDiscordBotApplication.class, args);

        DiscordClient client = DiscordClient.create(System.getenv("DISCORD_TOKEN"));

        //Just some sanity tests, will be replaced with something consisting of more structure

        client.withGateway((gateway) -> {
            Mono<Void> readyListener = gateway.on(ReadyEvent.class, event -> {

                return client.getChannelById(Snowflake.of(Channels.MOD.getId())).createMessage(String.format("%s %s %s %s", Emotes.OGYGIA, Emotes.DUAT, Emotes.LABYRINTH, Emotes.MIDGARD));
            }).then();

            return Mono.when(readyListener);
        }).block();
    }
}