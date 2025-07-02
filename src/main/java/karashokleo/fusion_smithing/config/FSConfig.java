package karashokleo.fusion_smithing.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.List;

public class FSConfig {
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> lootTables;

    public FSConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Fusion Smithing Configuration");
        
        lootTables = builder
                .comment("The loot tables to inject the fusion smithing template into",
                "[\"minecraft:chests/end_city_treasure\", \"minecraft:chests/bastion_treasure\"]")
                .defineList("loot_tables",
                        Arrays.asList("minecraft:chests/end_city_treasure"),
                        obj -> obj instanceof String);
    }
}