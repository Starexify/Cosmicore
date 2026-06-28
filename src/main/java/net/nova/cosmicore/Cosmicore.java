package net.nova.cosmicore;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.init.CreativeTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cosmicore implements ModInitializer {
  public static final String MODID = "cosmicore";
  public static final Logger LOGGER = LoggerFactory.getLogger(Cosmicore.class);

  @Override
  public void onInitialize() {
    CItems.init();
    CreativeTab.init();
  }

  public static Identifier rl(String path) {
    return Identifier.fromNamespaceAndPath(MODID, path);
  }
}
