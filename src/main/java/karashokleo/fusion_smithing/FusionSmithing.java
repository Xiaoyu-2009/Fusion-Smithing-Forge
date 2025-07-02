package karashokleo.fusion_smithing;

import karashokleo.fusion_smithing.config.FSConfig;
import karashokleo.fusion_smithing.item.FusionSmithingTemplateItem;
import karashokleo.fusion_smithing.recipe.SmithingFusionRecipeSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;

/**
 * Fusion Smithing Mod[融锻]
 * 原作者: Karashok-Leo
 * Forge版本移植作者: Xiaoyu_2009
 */
@Mod(FusionSmithing.MOD_ID)
public class FusionSmithing {
    public static final String MOD_ID = "fusion_smithing";
    public static final String RESOURCE_PREFIX = "fusion_smithing";

    public static ForgeConfigSpec CONFIG_SPEC;
    public static FSConfig CONFIG;

    public FusionSmithing() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        setupConfig();

        FusionSmithingTemplateItem.register(modEventBus);
        SmithingFusionRecipeSerializer.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(this::onLootTableLoad);
    }

    private void setupConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        CONFIG = new FSConfig(builder);
        CONFIG_SPEC = builder.build();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CONFIG_SPEC);
    }

    private void onLootTableLoad(LootTableLoadEvent event) {
        String lootTableName = event.getName().toString();
        List<? extends String> configuredLootTables = CONFIG.lootTables.get();

        if (configuredLootTables.contains(lootTableName)) {
            LootPool pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(FusionSmithingTemplateItem.FUSION_SMITHING_TEMPLATE.get()))
                    .build();
            event.getTable().addPool(pool);
        }
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static String createTranslationKey(String type, String path) {
        return type + '.' + RESOURCE_PREFIX + '.' + path;
    }
}