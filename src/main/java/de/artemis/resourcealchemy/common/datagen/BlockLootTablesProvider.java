package de.artemis.resourcealchemy.common.datagen;

import de.artemis.resourcealchemy.common.blocks.BlossomCropBlock;
import de.artemis.resourcealchemy.common.registration.ModBlocks;
import de.artemis.resourcealchemy.common.registration.ModItems;
import de.artemis.resourcealchemy.common.registration.Registration;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class BlockLootTablesProvider extends BlockLootSubProvider {

    protected BlockLootTablesProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        add(ModBlocks.ARCANE_BLOSSOM.get(), createBlossomCropDrops(ModBlocks.ARCANE_BLOSSOM.get(), ModItems.ARCANE_BLOSSOM_PETAL.get(), ModItems.ARCANE_BLOSSOM_SEED.get()));

        add(ModBlocks.ARCANE_ORE.get(), createOreDrop(ModBlocks.ARCANE_ORE.get(), ModItems.ARCANE_CRYSTAL_SHARD.get()));
        add(ModBlocks.DEEPSLATE_ARCANE_ORE.get(), createOreDrop(ModBlocks.DEEPSLATE_ARCANE_ORE.get(), ModItems.ARCANE_CRYSTAL_SHARD.get()));

        dropOther(ModBlocks.ARCANE_SOIL.get(), Blocks.DIRT);

    }

    protected LootTable.Builder createBlossomCropDrops(Block cropBlock, Item grownCropItem, Item seedItem) {
        LootItemCondition.Builder arcaneBlossomCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(cropBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlossomCropBlock.AGE, 5));
        return applyExplosionDecay(cropBlock, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(grownCropItem).when(arcaneBlossomCondition).otherwise(LootItem.lootTableItem(seedItem)))).withPool(LootPool.lootPool().when(arcaneBlossomCondition).add(LootItem.lootTableItem(seedItem))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return Registration.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
