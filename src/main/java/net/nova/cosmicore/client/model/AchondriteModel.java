package net.nova.cosmicore.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.entity.Achondrite;

@OnlyIn(Dist.CLIENT)
public class AchondriteModel extends BaseMeteorModel<Achondrite> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Cosmicore.rl("achondrite"), "main");
    public static final ResourceLocation TEXTURE = Cosmicore.rl("textures/entity/meteors/achondrite.png");

    public AchondriteModel(ModelPart root) {
        super(root);
    }
}
