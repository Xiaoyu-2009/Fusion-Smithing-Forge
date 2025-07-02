package karashokleo.fusion_smithing_forge.item;

import karashokleo.fusion_smithing_forge.FusionSmithing;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class FusionSmithingTemplateItem {
    private static final Component FS_TITLE_TEXT = Component.translatable(FusionSmithing.createTranslationKey("text", "title")).withStyle(ChatFormatting.GRAY);
    private static final Component FS_APPLIES_TO_TEXT = Component.translatable(FusionSmithing.createTranslationKey("text", "applies_to")).withStyle(ChatFormatting.BLUE);
    private static final Component FS_INGREDIENTS_TEXT = Component.translatable(FusionSmithing.createTranslationKey("text", "ingredients")).withStyle(ChatFormatting.BLUE);
    private static final Component FS_BASE_SLOT_DESCRIPTION_TEXT = Component.translatable(FusionSmithing.createTranslationKey("text", "base_slot_description"));
    private static final Component FS_ADDITIONS_SLOT_DESCRIPTION_TEXT = Component.translatable(FusionSmithing.createTranslationKey("text", "additions_slot_description"));

    private static final ResourceLocation EMPTY_ARMOR_SLOT_HELMET_TEXTURE = new ResourceLocation("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = new ResourceLocation("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = new ResourceLocation("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = new ResourceLocation("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_SWORD_TEXTURE = new ResourceLocation("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE_TEXTURE = new ResourceLocation("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_AXE_TEXTURE = new ResourceLocation("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL_TEXTURE = new ResourceLocation("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_HOE_TEXTURE = new ResourceLocation("item/empty_slot_hoe");

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FusionSmithing.MOD_ID);

    public static final RegistryObject<SmithingTemplateItem> FUSION_SMITHING_TEMPLATE = ITEMS.register("fusion_smithing_template", FusionSmithingTemplateItem::createTemplate);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        eventBus.addListener(FusionSmithingTemplateItem::addToCreativeTab);
    }

    private static void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(FUSION_SMITHING_TEMPLATE);
        }
    }

    public static SmithingTemplateItem createTemplate() {
        return new SmithingTemplateItem(
                FS_APPLIES_TO_TEXT,
                FS_INGREDIENTS_TEXT,
                FS_TITLE_TEXT,
                FS_BASE_SLOT_DESCRIPTION_TEXT,
                FS_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                getUpgradeEmptyBaseSlotTextures(),
                getUpgradeEmptyBaseSlotTextures()
        );
    }

    public static List<ResourceLocation> getUpgradeEmptyBaseSlotTextures() {
        return List.of(
                EMPTY_ARMOR_SLOT_HELMET_TEXTURE,
                EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE,
                EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE,
                EMPTY_ARMOR_SLOT_BOOTS_TEXTURE,
                EMPTY_SLOT_SWORD_TEXTURE,
                EMPTY_SLOT_PICKAXE_TEXTURE,
                EMPTY_SLOT_AXE_TEXTURE,
                EMPTY_SLOT_SHOVEL_TEXTURE,
                EMPTY_SLOT_HOE_TEXTURE
        );
    }
} 