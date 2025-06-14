package karashokleo.fusion_smithing.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class FSConfig {
    public final ForgeConfigSpec.ConfigValue<String> injectedLootTable;

    public FSConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Fusion Smithing Configuration");
        
        injectedLootTable = builder
                .comment("The loot table to inject the fusion smithing template into")
                .define("injected_loot_table", "minecraft:chests/end_city_treasure");
    }
} 