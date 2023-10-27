package net.pringlebeaver.riverbed.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;

public class RiverbedCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RiverbedMain.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RIVERBED_TAB = CREATIVE_MOD_TABS.register("riverbed_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(RiverbedItems.COPPER_PAN.get()))
                    .title(Component.translatable("creativetab.riverbed_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(RiverbedItems.COPPER_PAN.get());


                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
