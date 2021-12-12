package com.barion.dungeons_enhanced;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DELootGenerator extends LootTableProvider {
    public DELootGenerator(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(Pair.of(DEStructureLootTables::new, LootContextParamSets.CHEST));
    }

    @Override @ParametersAreNonnullByDefault
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
        map.forEach((x, y) -> LootTables.validate(validationContext, x, y));
    }

    public static class DEStructureLootTables extends ChestLoot {
        @Override @ParametersAreNonnullByDefault
        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> lootTable) {
            super.accept(lootTable);
            lootTable.accept(location("flying_dutchman"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(lootNumber(7, 9))
                            .add(lootItem(Items.SKULL_BANNER_PATTERN, 1, lootNumber(1)))
                            .add(lootItem(Items.NAUTILUS_SHELL, 2, lootNumber(1)))
                            .add(lootItem(Items.TURTLE_EGG, 1, lootNumber(1)))
                            .add(lootItem(Items.DIAMOND, 2, lootNumber(1)))
                            .add(lootItem(Items.ROTTEN_FLESH, 8, lootNumber(2, 5)))
                            .add(lootItem(Items.BONE, 8, lootNumber(1, 4)))
                            .add(lootItem(Items.FIRE_CHARGE, 4, lootNumber(1, 3)))
                            .add(lootItem(Items.EMERALD, 4, lootNumber(1, 3)))
                            .add(lootItem(Items.COOKED_COD, 5, lootNumber(1, 3)))
                            .add(lootItem(Items.COOKED_SALMON, 5, lootNumber(1, 3)))
                            .add(lootItem(Items.ARROW, 4, lootNumber(2, 4)))
                            .add(lootItem(Items.STRING, 5, lootNumber(1, 4)))
                            .add(enchantedLootItem(Items.BOOK, 1, lootNumber(6, 14), lootNumber(1)))
                            .add(lootItem(Items.KELP, 8, lootNumber(2, 5)))
                            .add(lootItem(Items.GOLD_INGOT, 3, lootNumber(1, 2)))
                            .add(lootItem(Items.GOLD_BLOCK, 1, lootNumber(1)))
                            .add(lootItem(Items.SUSPICIOUS_STEW, 3, lootNumber(1, 2)))
                            .add(lootItem(Items.SPYGLASS, 1, lootNumber(1)))));

            lootTable.accept(location("monster_maze/church"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(lootNumber(5, 7))
                            .add(lootItem(Items.DIAMOND, 1, lootNumber(1, 2)))
                            .add(lootItem(Items.BONE, 4, lootNumber(1, 3)))
                            .add(lootItem(Items.PUMPKIN_SEEDS, 6, lootNumber(2, 4)))
                            .add(lootItem(Items.BOOK, 2, lootNumber(1, 3)))
                            .add(lootItem(Items.ROTTEN_FLESH, 4, lootNumber(1, 3)))
                            .add(lootItem(Items.EGG, 3, lootNumber(1, 3)))
                            .add(lootItem(Items.SUGAR, 4, lootNumber(1, 3)))
                            .add(lootItem(Items.SUGAR_CANE, 2, lootNumber(1, 2)))
                            .add(lootItem(Items.GOLD_NUGGET, 4, lootNumber(4, 10)))
                            .add(lootItem(Items.GOLD_BLOCK, 1, lootNumber(1)))
                            .add(lootItem(Items.PUMPKIN, 2, lootNumber(1)))
                            .add(enchantedLootItem(Items.BOOK, 1, lootNumber(6, 14), lootNumber(1)))
                            .add(lootItem(Items.GOLD_INGOT, 4, lootNumber(2, 3)))));

            lootTable.accept(location("monster_maze/treasure"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(lootNumber(4, 9))
                            .add(lootItem(Items.BONE, 3, lootNumber(2, 4)))
                            .add(lootItem(Items.ROTTEN_FLESH, 4, lootNumber(1, 2)))
                            .add(lootItem(Items.STRING, 3, lootNumber(1, 2)))
                            .add(lootItem(Items.EXPERIENCE_BOTTLE, 1, lootNumber(2, 3)))
                            .add(lootItem(Items.COBWEB, 2, lootNumber(1, 2)))
                            .add(lootItem(Items.SLIME_BALL, 2, lootNumber(1, 2)))
                            .add(lootItem(Items.CLOCK, 1, lootNumber(1)))
                            .add(lootItem(Items.COMPASS, 1, lootNumber(1)))
                            .add(lootItem(Items.MAP, 2, lootNumber(1, 2)))
                            .add(lootItem(Items.EMERALD, 2, lootNumber(1, 2)))
                            .add(lootItem(Items.IRON_NUGGET, 2, lootNumber(2, 5)))
                            .add(lootItem(Items.GOLD_NUGGET, 2, lootNumber(2, 5))))
                    .withPool(LootPool.lootPool().setRolls(lootNumber(3, 5))
                            .add(lootItem(Items.GOLD_INGOT, 2, lootNumber(2, 4)))
                            .add(lootItem(Items.DIAMOND, 2, lootNumber(1)))
                            .add(lootItem(Items.GOLD_BLOCK, 2, lootNumber(1, 2)))
                            .add(enchantedLootItem(Items.BOOK, 2, lootNumber(10, 22), lootNumber(1)))
                            .add(enchantedLootItem(Items.BOW, 1, lootNumber(0, 12), lootNumber(1)))
                            .add(enchantedLootItem(Items.CROSSBOW, 1, lootNumber(0, 12), lootNumber(1)))
                            .add(enchantedLootItem(Items.DIAMOND_HELMET, 1, lootNumber(0, 12), lootNumber(1)))
                            .add(enchantedLootItem(Items.DIAMOND_CHESTPLATE, 1, lootNumber(0, 12), lootNumber(1)))
                            .add(enchantedLootItem(Items.DIAMOND_LEGGINGS, 1, lootNumber(0, 12), lootNumber(1)))
                            .add(enchantedLootItem(Items.DIAMOND_BOOTS, 1, lootNumber(0, 12), lootNumber(1))))
                    .withPool(LootPool.lootPool().setRolls(lootNumber(0, 1))
                            .add(lootItem(Items.MUSIC_DISC_11, 1, lootNumber(1)))
                            .add(lootItem(Items.MUSIC_DISC_13, 1, lootNumber(1)))
                            .add(lootItem(Items.MUSIC_DISC_BLOCKS, 1, lootNumber(1)))
                            .add(lootItem(Items.MUSIC_DISC_CAT, 1, lootNumber(1)))
                            .add(lootItem(Items.MUSIC_DISC_CHIRP, 1, lootNumber(1)))
                            .add(lootItem(Items.MUSIC_DISC_FAR, 1, lootNumber(1)))
                            .add(lootItem(Items.MUSIC_DISC_MALL, 1, lootNumber(1)))
                            .add(lootItem(Items.MUSIC_DISC_MELLOHI, 1, lootNumber(1)))
                            .add(lootItem(Items.MUSIC_DISC_STAL, 1, lootNumber(1)))
                            .add(lootItem(Items.MUSIC_DISC_STRAD, 1, lootNumber(1)))
                            .add(lootItem(Items.MUSIC_DISC_WARD, 1, lootNumber(1)))));

            lootTable.accept(location("tower_of_the_undead/treasure"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(lootNumber(10, 18))
                            .add(lootItem(Items.GOLD_NUGGET, 5, lootNumber(1, 2)))
                            .add(lootItem(Items.GOLD_INGOT, 3, lootNumber(1)))
                            .add(lootItem(Items.IRON_NUGGET, 4, lootNumber(1, 2)))
                            .add(lootItem(Items.IRON_INGOT, 2, lootNumber(1)))
                            .add(lootItem(Items.GOLDEN_CARROT, 2, lootNumber(1)))
                            .add(lootItem(Items.WHEAT_SEEDS, 8, lootNumber(1, 3)))
                            .add(lootItem(Items.WHEAT, 6, lootNumber(1, 3)))
                            .add(lootItem(Items.STRING, 6, lootNumber(1, 3)))
                            .add(lootItem(Items.BONE, 7, lootNumber(1, 3)))
                            .add(lootItem(Items.ROTTEN_FLESH, 10, lootNumber(1, 3)))
                            .add(lootItem(Items.IRON_AXE, 1, lootNumber(1)))
                            .add(lootItem(Items.IRON_SWORD, 1, lootNumber(1)))
                            .add(lootItem(Items.CROSSBOW, 1, lootNumber(1)))
                            .add(lootItem(Items.COBWEB, 4, lootNumber(1)))
                            .add(lootItem(Items.GOLDEN_APPLE, 2, lootNumber(1)))
                            .add(lootItem(Items.ARROW, 6, lootNumber(2, 3))))
                    .withPool(LootPool.lootPool().setRolls(lootNumber(1, 3))
                            .add(lootItem(Items.LEATHER_HELMET, 3, lootNumber(1)))
                            .add(lootItem(Items.LEATHER_CHESTPLATE, 3, lootNumber(1)))
                            .add(lootItem(Items.LEATHER_LEGGINGS, 3, lootNumber(1)))
                            .add(lootItem(Items.LEATHER_BOOTS, 3, lootNumber(1)))
                            .add(lootItem(Items.CHAINMAIL_HELMET, 2, lootNumber(1)))
                            .add(lootItem(Items.CHAINMAIL_CHESTPLATE, 2, lootNumber(1)))
                            .add(lootItem(Items.CHAINMAIL_LEGGINGS, 2, lootNumber(1)))
                            .add(lootItem(Items.CHAINMAIL_BOOTS, 2, lootNumber(1)))
                            .add(lootItem(Items.IRON_HELMET, 1, lootNumber(1)))
                            .add(lootItem(Items.IRON_CHESTPLATE, 1, lootNumber(1)))
                            .add(lootItem(Items.IRON_LEGGINGS, 1, lootNumber(1)))
                            .add(enchantedLootItem(Items.BOOK, 1, lootNumber(4, 10), lootNumber(1)))
                            .add(lootItem(Items.IRON_BOOTS, 1, lootNumber(1)))));
        }

        private LootPoolEntryContainer.Builder<?> lootItem(Item item, int weight, NumberProvider amount){
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(amount));
        }

        private LootPoolEntryContainer.Builder<?> enchantedLootItem(Item item, int weight, NumberProvider enchant, NumberProvider amount){
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(amount)).apply(EnchantWithLevelsFunction.enchantWithLevels(enchant));
        }

        private LootPoolEntryContainer.Builder<?> lootPotion(Item item, int weight, NumberProvider amount){
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(amount));
        }

        private NumberProvider lootNumber(int amount){
            return ConstantValue.exactly(amount);
        }

        private NumberProvider lootNumber(int minAmount, int maxAmount){
            return UniformGenerator.between(minAmount, maxAmount);
        }

        private static ResourceLocation location(String name){
            return new ResourceLocation(DungeonsEnhanced.Mod_ID, "chests/" + name);
        }
    }
}