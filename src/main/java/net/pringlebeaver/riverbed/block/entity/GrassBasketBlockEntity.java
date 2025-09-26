package net.pringlebeaver.riverbed.block.entity;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DispenserMenu;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.pringlebeaver.riverbed.block.ModBlocks;
import net.pringlebeaver.riverbed.block.custom.GrassBasketBlock;
import net.pringlebeaver.riverbed.item.ModItems;
import net.pringlebeaver.riverbed.util.ModTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GrassBasketBlockEntity extends BlockEntity {

    public static final String TAG_PATTERNS = "patterns";

    private GrassBasketBlockEntity.Decorations decorations = GrassBasketBlockEntity.Decorations.EMPTY;



    public GrassBasketBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GRASS_BASKET_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.decorations = GrassBasketBlockEntity.Decorations.load(pTag);
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        this.decorations.save(pTag);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @NotNull
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    public Direction getDirection() {
        return this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
    }


    // Decoration Stuff

    public GrassBasketBlockEntity.Decorations getDecorations() {
        return this.decorations;
    }


    public void setFromItem(ItemStack pItem) {
        this.decorations = GrassBasketBlockEntity.Decorations.load(BlockItem.getBlockEntityData(pItem));
    }

    public static record Decorations(Item bottom, Item middle, Item top) {
        public static final GrassBasketBlockEntity.Decorations EMPTY = new GrassBasketBlockEntity.Decorations(ModItems.WOVEN_GRASS.get(), ModItems.WOVEN_GRASS.get(), ModItems.WOVEN_GRASS.get());

        public CompoundTag save(CompoundTag pTag) {
            ListTag listtag = new ListTag();
            this.sorted().forEach((item) -> {
                listtag.add(StringTag.valueOf(BuiltInRegistries.ITEM.getKey(item).toString()));
            });
            pTag.put("patterns", listtag);
            return pTag;
        }

        public Stream<Item> sorted() {
            return Stream.of(this.bottom, this.middle, this.top);
        }

        public static GrassBasketBlockEntity.Decorations load(@Nullable CompoundTag pTag) {
            if (pTag != null && pTag.contains("patterns", 9)) {
                ListTag listtag = pTag.getList("patterns", 8);
                return new GrassBasketBlockEntity.Decorations(itemFromTag(listtag, 0), itemFromTag(listtag, 1), itemFromTag(listtag, 2));
            } else {
                return EMPTY;
            }
        }

        private static Item itemFromTag(ListTag pTag, int pIndex) {
            if (pIndex >= pTag.size()) {
                return ModItems.WOVEN_GRASS.get();
            } else {
                Tag tag = pTag.get(pIndex);
                return BuiltInRegistries.ITEM.get(new ResourceLocation(tag.getAsString()));
            }
        }
    }
}