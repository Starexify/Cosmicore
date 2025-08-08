package net.nova.cosmicore.init;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.nova.cosmicore.Cosmicore;

public interface CModelLayers {
    ModelLayerLocation ACHONDRITE = new ModelLayerLocation(Cosmicore.rl("achondrite"), "main");
    ModelLayerLocation METEORITE = new ModelLayerLocation(Cosmicore.rl("meteorite"), "main");
    ModelLayerLocation TITANIUM_GOLEM = new ModelLayerLocation(Cosmicore.rl("titanium_golem"), "main");
}
