package io.github.prittspadelord.riordanwikidiscord.messagerenderables.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RoleRenderableEmote extends RenderableEmote {
    private String name;
    private long id;
    private long roleId;

    @Override
    public String toString() {
        return String.format("<:%s:%s>", this.name, this.id);
    }
}