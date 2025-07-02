package karashokleo.fusion_smithing.recipe;

import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public record SmithingFusionRecipeProvider(
        ResourceLocation id,
        RecipeSerializer<?> type,
        SmithingFusionMode mode,
        Ingredient template,
        Ingredient base,
        Ingredient addition
) implements FinishedRecipe {
    @Override
    public void serializeRecipeData(JsonObject json) {
        json.addProperty("mode", this.mode.name());
        json.add("template", this.template.toJson());
        json.add("base", this.base.toJson());
        json.add("addition", this.addition.toJson());
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getType() {
        return this.type;
    }

    @Override
    @Nullable
    public JsonObject serializeAdvancement() {
        return null;
    }

    @Override
    @Nullable
    public ResourceLocation getAdvancementId() {
        return null;
    }
    
    public static void register(Consumer<FinishedRecipe> consumer, ResourceLocation id, SmithingFusionMode mode, 
        Ingredient template, Ingredient base, Ingredient addition) {
        consumer.accept(new SmithingFusionRecipeProvider(id, SmithingFusionRecipeSerializer.SMITHING_FUSION.get(), 
        mode, template, base, addition));
    }
} 