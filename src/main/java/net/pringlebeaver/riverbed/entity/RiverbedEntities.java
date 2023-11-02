package net.pringlebeaver.riverbed.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.entity.custom.TroutEntity;

public class RiverbedEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RiverbedMain.MOD_ID);


    public static final RegistryObject<EntityType<TroutEntity>> TROUT =
            ENTITY_TYPES.register("trout", () -> EntityType.Builder.of(TroutEntity::new, MobCategory.WATER_AMBIENT).sized(0.5f, 0.5f).build("trout"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
