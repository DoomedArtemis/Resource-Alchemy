package de.artemis.resourcealchemy.common.datagen;

import de.artemis.resourcealchemy.common.blocks.BlossomCropBlock;
import de.artemis.resourcealchemy.common.registration.ModBlocks;
import de.artemis.resourcealchemy.common.registration.ModItems;
import de.artemis.resourcealchemy.common.registration.Registration;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTablesProvider extends BlockLootSubProvider {

    protected ModBlockLootTablesProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        add(ModBlocks.ARCANE_BLOSSOM.get(), createBlossomCropDrop(ModBlocks.ARCANE_BLOSSOM.get(), ModItems.ARCANE_BLOSSOM_PETAL.get(), ModItems.ARCANE_BLOSSOM_SEED.get()));

        add(ModBlocks.ARCANE_ORE.get(), createVariableOreDrop(ModBlocks.ARCANE_ORE.get(), ModItems.ARCANE_CRYSTAL_SHARD.get(), 2.0F, 4.0F));
        add(ModBlocks.DEEPSLATE_ARCANE_ORE.get(), createVariableOreDrop(ModBlocks.DEEPSLATE_ARCANE_ORE.get(), ModItems.ARCANE_CRYSTAL_SHARD.get(), 2.0F, 4.0F));

        dropOther(ModBlocks.ARCANE_SOIL.get(), Blocks.DIRT);

        dropSelf(ModBlocks.ARCANE_CRYSTAL_BLOCK.get());

        add(ModBlocks.BUDDING_ARCANE_CRYSTAL.get(), noDrop());

        add(ModBlocks.ARCANE_CRYSTAL_CLUSTER.get(), createSilkTouchDispatchTable(ModBlocks.ARCANE_CRYSTAL_CLUSTER.get(), LootItem.lootTableItem(ModItems.ARCANE_CRYSTAL_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(applyExplosionDecay(ModItems.ARCANE_CRYSTAL_SHARD.get(), LootItem.lootTableItem(ModItems.ARCANE_CRYSTAL_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))));

        dropWhenSilkTouch(ModBlocks.LARGE_ARCANE_CRYSTAL_BUD.get());
        dropWhenSilkTouch(ModBlocks.MEDIUM_ARCANE_CRYSTAL_BUD.get());
        dropWhenSilkTouch(ModBlocks.SMALL_ARCANE_CRYSTAL_BUD.get());
    }

    protected LootTable.@NotNull Builder createVariableOreDrop(@NotNull Block ore_block, Item ore_drop, Float from, Float to) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                ore_block,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        ore_block,
                        LootItem.lootTableItem(ore_drop)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(from, to)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    protected LootTable.Builder createBlossomCropDrop(Block cropBlock, Item grownCropItem, Item seedItem) {
        LootItemCondition.Builder arcaneBlossomCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(cropBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlossomCropBlock.AGE, 5));
        return applyExplosionDecay(cropBlock, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(grownCropItem).when(arcaneBlossomCondition).otherwise(LootItem.lootTableItem(seedItem)))).withPool(LootPool.lootPool().when(arcaneBlossomCondition).add(LootItem.lootTableItem(seedItem))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return Registration.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
