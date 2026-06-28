package net.nova.cosmicore.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public interface CToolMaterial {
  ToolMaterial TITANIUM = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1031, 9.0F, 3.5F, 15, Tags.CItemTags.TITANIUM_TOOL_MATERIALS);
  ToolMaterial LONSDALEITE = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2467, 10.0F, 4.7F, 18, Tags.CItemTags.LONSDALEITE_TOOL_MATERIALS);
}
