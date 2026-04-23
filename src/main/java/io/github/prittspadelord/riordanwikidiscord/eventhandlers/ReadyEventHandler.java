package io.github.prittspadelord.riordanwikidiscord.eventhandlers;

import io.github.prittspadelord.riordanwikidiscord.constants.Channels;
import io.github.prittspadelord.riordanwikidiscord.constants.Misc;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.spec.EmbedCreateSpec;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
@Slf4j
public class ReadyEventHandler {

    private GatewayDiscordClient client;

    public Mono<Void> handleEvent(ReadyEvent event) {

        log.info("Logged in as {} serving {} guilds", event.getSelf().getUsername(), event.getGuilds().size());
        log.debug("Session ID: {} | Shard: {} | Gateway Version: {}", event.getSessionId(), event.getShardInfo(), event.getGatewayVersion());

        return client.getChannelById(Snowflake.of(Channels.GENERAL.getId()))
            .cast(TextChannel.class)
            .flatMap(channel -> {

                EmbedCreateSpec spec = EmbedCreateSpec.builder()
                    .color(Misc.TERMINUS_EMBED_COLOR)
                    .description("Oops! Terminus has just been *terminated!*")
                    .build();

                return channel.createMessage(spec);
            }).then();
    }
}