package karashokleo.fusion_smithing.recipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.stream.Stream;

public class SmithingFusionRecipe implements SmithingRecipe {
    protected final ResourceLocation id;
    protected final SmithingFusionMode mode;
    protected final Ingredient template;
    protected final Ingredient base;
    protected final Ingredient addition;

    public SmithingFusionRecipe(ResourceLocation id, SmithingFusionMode mode, Ingredient template, Ingredient base, Ingredient addition) {
        this.id = id;
        this.mode = mode;
        this.template = template;
        this.base = base;
        this.addition = addition;
    }

    @Override
    public boolean isTemplateIngredient(ItemStack stack) {
        return this.template.test(stack);
    }

    @Override
    public boolean isBaseIngredient(ItemStack stack) {
        return this.base.test(stack);
    }

    @Override
    public boolean isAdditionIngredient(ItemStack stack) {
        return this.addition.test(stack);
    }

    @Override
    public boolean matches(Container container, Level level) {
        return this.template.test(container.getItem(0)) &&
               this.base.test(container.getItem(1)) &&
               this.addition.test(container.getItem(2));
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        ItemStack baseStack = container.getItem(1).copy();
        CompoundTag additionTag = container.getItem(2).getTag();
        if (additionTag != null) this.mode.apply(baseStack, additionTag);
        return baseStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return Arrays.stream(base.getItems()).findFirst().orElse(ItemStack.EMPTY);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SmithingFusionRecipeSerializer.SMITHING_FUSION.get();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean isIncomplete() {
        return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::isEmpty);
    }

    @Override
    public boolean showNotification() {
        return true;
    }
} 