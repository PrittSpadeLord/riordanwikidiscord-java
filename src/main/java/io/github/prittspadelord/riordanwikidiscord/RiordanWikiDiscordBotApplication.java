package io.github.prittspadelord.riordanwikidiscord;

import io.github.prittspadelord.riordanwikidiscord.constants.Channels;
import io.github.prittspadelord.riordanwikidiscord.constants.Emotes;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.event.domain.lifecycle.ReadyEvent;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RiordanWikiDiscordBotApplication {
    static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(RiordanWikiDiscordBotApplication.class, args);

        GatewayDiscordClient client = context.getBean(GatewayDiscordClient.class);

        client.on(ReadyEvent.class, event -> {
            return client.getChannelById(Snowflake.of(Channels.GENERAL.getId())).cast(TextChannel.class).flatMap(channel -> {
                return channel.createMessage(String.format("%s%s%s", Emotes.EXPBAR_FULL, Emotes.EXPBAR_THREEFOURTHS, Emotes.EXPBAR_EMPTY));
            });
        }).subscribe();

        client.onDisconnect().block();
    }
}