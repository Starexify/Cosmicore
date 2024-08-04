package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.gui.crusher.CrusherMenu;

import java.util.function.Supplier;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, MODID);

    public static Supplier<MenuType<CrusherMenu>> CRUSHER_MENU = MENUS.register("crusher_menu", () -> IMenuTypeExtension.create(CrusherMenu::new));
}
