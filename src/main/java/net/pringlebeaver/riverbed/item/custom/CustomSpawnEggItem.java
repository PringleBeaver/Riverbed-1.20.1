package net.pringlebeaver.riverbed.item.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.function.Supplier;

public class CustomSpawnEggItem extends ForgeSpawnEggItem {

    public CustomSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Properties props) {
        super(type, backgroundColor, highlightColor, props);
    }
}
