package net.enderkitty.util;

import net.enderkitty.ArcaneDimensions;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ArcaneDimsTagRegistries {

    public static class ItemTags {
        public static final TagKey<Item> SENIOREM_LOGS = createTag("seniorem_logs");
        public static final TagKey<Item> STICKS = createTag("sticks");
        public static final TagKey<Item> ELDER_TOOLS = createTag("elder_tool");



        private static TagKey<Item> createTag(String id) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(ArcaneDimensions.MOD_ID, id));
        }
    }

    public static class BlockTags {
        public static final TagKey<Block> SENIOREM_LOGS = createTag("seniorem_logs");



        private static TagKey<Block> createTag(String id) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(ArcaneDimensions.MOD_ID, id));
        }
    }

}
