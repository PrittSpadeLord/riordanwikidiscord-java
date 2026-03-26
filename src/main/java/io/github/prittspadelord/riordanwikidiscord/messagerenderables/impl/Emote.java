package io.github.prittspadelord.riordanwikidiscord.messagerenderables.impl;

import io.github.prittspadelord.riordanwikidiscord.messagerenderables.MessageRenderable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Emote implements MessageRenderable {
    private String name;
    private long id;

    @Override
    public String toString() {
        return String.format("<:%s:%s>", this.name, this.id);
    }
}