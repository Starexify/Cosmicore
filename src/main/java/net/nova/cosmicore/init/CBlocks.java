package net.nova.cosmicore.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.block.AdvancedCrusher;
import net.nova.cosmicore.block.CosmicShield;
import net.nova.cosmicore.block.Crusher;

import java.util.function.Supplier;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    // Titanium Stuff
    public static DeferredBlock<Block> RAW_TITANIUM_BLOCK = registerBlock("raw_titanium_block", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_BLUE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
    ));
    public static DeferredBlock<Block> TITANIUM_BLOCK = registerBlock("titanium_block", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.PLING)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    // Meteorites
    public static DeferredBlock<Block> ACHONDRITE = registerBlock("achondrite", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GRAY)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(10.0F, 6.0F)
            .sound(SoundType.DEEPSLATE)
    ));
    public static DeferredBlock<Block> METEORITE = registerBlock("meteorite", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(20.0F, 1200.0F)
            .sound(SoundType.DEEPSLATE)
    ));
    public static DeferredBlock<Block> PALLASITE = registerBlock("pallasite", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_ORANGE)
            .instrument(NoteBlockInstrument.HAT)
            .requiresCorrectToolForDrops()
            .strength(35.0F, 600.0F)
            .sound(SoundType.AMETHYST)
            .lightLevel(light -> 9)
    ));

    // Crusher
    public static DeferredBlock<Block> CRUSHER = registerBlock("crusher", () -> new Crusher(BlockBehaviour.Properties.of()
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(6F)
            .sound(SoundType.NETHERITE_BLOCK)
    ));

    public static DeferredBlock<Block> ADVANCED_CRUSHER = registerBlock("advanced_crusher", () -> new AdvancedCrusher(BlockBehaviour.Properties.of()
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(6F)
            .sound(SoundType.NETHERITE_BLOCK)
    ));

    // Cosmic Shield
    public static DeferredBlock<Block> COSMIC_SHIELD = registerBlock("cosmic_shield", () -> new CosmicShield(BlockBehaviour.Properties.of()
            .mapColor(MapColor.METAL)
            .requiresCorrectToolForDrops()
            .strength(6F)
            .sound(SoundType.NETHERITE_BLOCK)
    ));

    // Infernium
    public static DeferredBlock<Block> INFERNIUM_CLUSTER = registerBlock("infernium_cluster", () -> new AmethystClusterBlock(
            7.0F,
            3.0F,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .forceSolidOn()
                    .noOcclusion()
                    .sound(SoundType.AMETHYST_CLUSTER)
                    .strength(1.5F)
                    .lightLevel(light -> 9)
                    .pushReaction(PushReaction.DESTROY)
                    .requiresCorrectToolForDrops()
    ));


    // Registers
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItems(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<Item> registerBlockItems(String name, DeferredBlock<T> block) {
        return CItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
