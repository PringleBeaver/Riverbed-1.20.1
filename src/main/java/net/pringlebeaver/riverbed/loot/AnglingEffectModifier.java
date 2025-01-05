package net.pringlebeaver.riverbed.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditions;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.registries.ForgeRegistries;
import net.pringlebeaver.riverbed.effect.ModEffects;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class AnglingEffectModifier extends LootModifier {
    public static final Supplier<Codec<AnglingEffectModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst).and(Codec.INT
            .fieldOf("item_count").forGetter(m -> m.itemCount)).apply(inst, AnglingEffectModifier::new)));
    private final int itemCount;

    protected AnglingEffectModifier(LootItemCondition[] conditionsIn, Integer itemCount) {
        super(conditionsIn);
        this.itemCount = itemCount;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        Entity fishingPlayer = context.getParam(LootContextParams.KILLER_ENTITY);

        for(LootItemCondition condition : this.conditions){
            if(!condition.test(context)){
                return generatedLoot;
            }
        }
        if (!generatedLoot.isEmpty() && generatedLoot.get(0).is(ItemTags.FISHES) && fishingPlayer instanceof LivingEntity livingFishingPlayer) {
            int anglingLevel = livingFishingPlayer.getActiveEffectsMap().get(ModEffects.ANGLING.get()).getAmplifier() + 1;


            generatedLoot.get(0).setCount(1 + ThreadLocalRandom.current().nextInt(0, anglingLevel + 1));
        }


            return generatedLoot;
       }



    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return null;
    }
}
