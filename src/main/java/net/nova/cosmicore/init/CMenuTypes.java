package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.gui.crusher.AdvancedCrusherMenu;
import net.nova.cosmicore.gui.crusher.CrusherMenu;

import java.util.function.Supplier;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, MODID);

    public static Supplier<MenuType<CrusherMenu>> CRUSHER_MENU = MENUS.register("crusher_menu", () -> IMenuTypeExtension.create(CrusherMenu::new));
    public static Supplier<MenuType<AdvancedCrusherMenu>> ADVANCED_CRUSHER_MENU = MENUS.register("advanced_crusher_menu", () -> IMenuTypeExtension.create(AdvancedCrusherMenu::new));
}
