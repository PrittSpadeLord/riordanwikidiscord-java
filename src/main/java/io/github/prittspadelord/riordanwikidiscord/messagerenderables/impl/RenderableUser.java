package io.github.prittspadelord.riordanwikidiscord.messagerenderables.impl;

import io.github.prittspadelord.riordanwikidiscord.messagerenderables.MessageRenderable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RenderableUser implements MessageRenderable {
    private long id;

    @Override
    public String toString() {
        return String.format("<@%s>", this.id);
    }
}