package io.github.prittspadelord.riordanwikidiscord.configurations;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordClientConfig {

    @Bean
    public GatewayDiscordClient discordClient() {
        return DiscordClientBuilder.create(System.getenv("DISCORD_TOKEN"))
            .build()
            .gateway()
            .login()
            .block();
    }
}