package net.nova.cosmicore;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cosmicore implements ModInitializer {
  public static final String MODID = "cosmicore";
  public static final Logger LOGGER = LoggerFactory.getLogger(Cosmicore.class);

  @Override
  public void onInitialize() {

  }

  public static Identifier rl(String path) {
    return Identifier.fromNamespaceAndPath(MODID, path);
  }
}
