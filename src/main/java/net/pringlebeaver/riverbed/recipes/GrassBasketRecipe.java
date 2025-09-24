package net.pringlebeaver.riverbed.recipes;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.block.ModBlocks;
import net.pringlebeaver.riverbed.block.custom.GrassBasketBlock;
import net.pringlebeaver.riverbed.block.entity.GrassBasketBlockEntity;
import net.pringlebeaver.riverbed.block.entity.ModBlockEntities;
import net.pringlebeaver.riverbed.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class GrassBasketRecipe extends CustomRecipe {

    public GrassBasketRecipe(ResourceLocation pId, CraftingBookCategory pCategory) {
        super(pId, pCategory);
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        if (!this.canCraftInDimensions(pContainer.getWidth(), pContainer.getHeight())) {
            return false;
        } else {
            for(int i = 0; i < pContainer.getContainerSize(); ++i) {
                ItemStack itemstack = pContainer.getItem(i);
                switch (i) {
                    case 1:
                    case 4:
                    case 7:
                        if (!itemstack.is(ModTags.Items.GRASS_BASKET_INGREDIENT)) {
                            return false;
                        }
                        break;
                    case 2:
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    default:
                        if (!itemstack.is(Items.AIR)) {
                            return false;
                        }
                }
            }

            return true;
        }
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        GrassBasketBlockEntity.Decorations decorations = new GrassBasketBlockEntity.Decorations(pContainer.getItem(1).getItem(), pContainer.getItem(4).getItem(), pContainer.getItem(7).getItem());
        return createGrassBasketItem(decorations);
    }

    public static ItemStack createGrassBasketItem(GrassBasketBlockEntity.Decorations pDecorations) {
        ItemStack itemstack = ModBlocks.GRASS_BASKET.get().asItem().getDefaultInstance();
        CompoundTag compoundtag = pDecorations.save(new CompoundTag());
        BlockItem.setBlockEntityData(itemstack, ModBlockEntities.GRASS_BASKET_BLOCK_ENTITY.get(), compoundtag);
        return itemstack;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {

        return pWidth == 3 && pHeight == 3;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public static class Serializer implements RecipeSerializer<GrassBasketRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(RiverbedMain.MOD_ID, "grass_basket");

        @Override
        public GrassBasketRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            return new GrassBasketRecipe(pRecipeId, CraftingBookCategory.MISC);
        }

        @Override
        public @Nullable GrassBasketRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            return new GrassBasketRecipe(pRecipeId, CraftingBookCategory.MISC);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, GrassBasketRecipe pRecipe) {

        }
    }
}
