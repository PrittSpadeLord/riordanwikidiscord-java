package io.github.prittspadelord.riordanwikidiscord;

import io.github.prittspadelord.riordanwikidiscord.commands.CommandHandler;
import io.github.prittspadelord.riordanwikidiscord.constants.Channels;
import io.github.prittspadelord.riordanwikidiscord.constants.Misc;
import io.github.prittspadelord.riordanwikidiscord.stickers.StickerHandler;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.spec.EmbedCreateSpec;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
@Slf4j
public class EventHandler {

    private CommandHandler commandHandler;
    private GatewayDiscordClient client;
    private StickerHandler stickerHandler;

    public Mono<?> handleReadyEvent(ReadyEvent event) {

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
                });
    }

    public Mono<?> handleMessageCreateEvent(MessageCreateEvent event) {
        Message message = event.getMessage();

        if(message.getAuthor().isEmpty()) return Mono.empty();
        if(message.getAuthor().get().isBot()) return Mono.empty();

        String content = message.getContent();

        if(content.startsWith("$")) {
            return commandHandler.handleCommand(content.substring(1));
        }

        if(content.startsWith(";") && content.endsWith(";")) {
            return stickerHandler.handleSticker(content.substring(1, content.length() - 1));
        }

        return Mono.empty();
    }
}