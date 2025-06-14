package karashokleo.fusion_smithing.recipe;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;

public enum SmithingFusionMode {
    OVERWRITE(ItemStack::setTag),
    MERGE((base, addition) -> base.getOrCreateTag().merge(addition)),
    ;

    private final BiConsumer<ItemStack, CompoundTag> handler;

    SmithingFusionMode(BiConsumer<ItemStack, CompoundTag> handler) {
        this.handler = handler;
    }

    public void apply(ItemStack base, CompoundTag addition) {
        handler.accept(base, addition);
    }
}