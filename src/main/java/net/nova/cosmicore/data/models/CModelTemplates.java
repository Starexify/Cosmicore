package net.nova.cosmicore.data.models;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.Cosmicore;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class CModelTemplates {
    public static final ModelTemplate GEAR_ITEM = createItem("template_gear", TextureSlot.LAYER0);

    public static ModelTemplate createItem(String name, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(Cosmicore.rl("item/" + name)), Optional.empty(), textureSlots);
    }
}
