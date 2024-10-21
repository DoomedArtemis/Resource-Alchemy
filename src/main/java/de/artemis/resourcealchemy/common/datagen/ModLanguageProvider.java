package de.artemis.resourcealchemy.common.datagen;

import de.artemis.resourcealchemy.common.ResourceAlchemy;
import de.artemis.resourcealchemy.common.registration.ModBlocks;
import de.artemis.resourcealchemy.common.registration.ModItems;
import net.minecraft.data.PackOutput;

public class ModLanguageProvider extends net.neoforged.neoforge.common.data.LanguageProvider {

    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, ResourceAlchemy.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("creativemodetab.resourcealchemy", "Resource Alchemy");

        add(ModItems.COPPER_NUGGET.get(), "Copper Nugget");
        add(ModItems.ARCANE_BLOSSOM_SEED.get(), "Arcane Blossom Seed");
        add(ModItems.ARCANE_BLOSSOM_PETAL.get(), "Arcane Blossom Petal");
        add(ModItems.ARCANE_CRYSTAL_SHARD.get(), "Arcane Crystal Shard");
        add(ModItems.ARCANE_CRYSTAL_POWDER.get(), "Arcane Crystal Powder");

        add(ModBlocks.ARCANE_BLOSSOM.get(), "Arcane Blossom");
        add(ModBlocks.ARCANE_SOIL.get(), "Arcane Soil");
        add(ModBlocks.ARCANE_ORE.get(), "Arcane Ore");
        add(ModBlocks.DEEPSLATE_ARCANE_ORE.get(), "Deepslate Arcane Ore");
        add(ModBlocks.ARCANE_CRYSTAL_BLOCK.get(), "Arcane Crystal Block");
        add(ModBlocks.BUDDING_ARCANE_CRYSTAL.get(), "Budding Arcane Crystal");
        add(ModBlocks.ARCANE_CRYSTAL_CLUSTER.get(), "Arcane Crystal Cluster");
        add(ModBlocks.SMALL_ARCANE_CRYSTAL_BUD.get(), "Small Arcane Crystal Bud");
        add(ModBlocks.MEDIUM_ARCANE_CRYSTAL_BUD.get(), "Medium Arcane Crystal Bud");
        add(ModBlocks.LARGE_ARCANE_CRYSTAL_BUD.get(), "Large Arcane Crystal Bud");
    }
}
