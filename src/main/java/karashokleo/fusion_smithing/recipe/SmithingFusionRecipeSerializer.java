package karashokleo.fusion_smithing.recipe;

import com.google.gson.JsonObject;
import karashokleo.fusion_smithing.FusionSmithing;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SmithingFusionRecipeSerializer implements RecipeSerializer<SmithingFusionRecipe> {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = 
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FusionSmithing.MOD_ID);

    public static final RegistryObject<RecipeSerializer<SmithingFusionRecipe>> SMITHING_FUSION = 
            RECIPE_SERIALIZERS.register("smithing_fusion", SmithingFusionRecipeSerializer::new);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }

    @Override
    public SmithingFusionRecipe fromJson(ResourceLocation id, JsonObject json) {
        SmithingFusionMode mode = SmithingFusionMode.valueOf(GsonHelper.getAsString(json, "mode"));
        Ingredient template = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "template"));
        Ingredient base = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "base"));
        Ingredient addition = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"));
        return new SmithingFusionRecipe(id, mode, template, base, addition);
    }

    @Override
    public SmithingFusionRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
        SmithingFusionMode mode = buffer.readEnum(SmithingFusionMode.class);
        Ingredient template = Ingredient.fromNetwork(buffer);
        Ingredient base = Ingredient.fromNetwork(buffer);
        Ingredient addition = Ingredient.fromNetwork(buffer);
        return new SmithingFusionRecipe(id, mode, template, base, addition);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, SmithingFusionRecipe recipe) {
        buffer.writeEnum(recipe.mode);
        recipe.template.toNetwork(buffer);
        recipe.base.toNetwork(buffer);
        recipe.addition.toNetwork(buffer);
    }
} 