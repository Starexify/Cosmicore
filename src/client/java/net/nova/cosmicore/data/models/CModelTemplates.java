package net.nova.cosmicore.data.models;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.nova.cosmicore.Cosmicore;

import java.util.Optional;

public interface CModelTemplates {
  ModelTemplate GEAR_ITEM = createItem("template_gear", TextureSlot.LAYER0);

  static ModelTemplate createItem(String name, TextureSlot... textureSlots) {
    return new ModelTemplate(Optional.of(Cosmicore.rl(name).withPrefix("item/")), Optional.empty(), textureSlots);
  }
}
