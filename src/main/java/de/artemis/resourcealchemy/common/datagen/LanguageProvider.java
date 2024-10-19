package de.artemis.resourcealchemy.common.datagen;

import de.artemis.resourcealchemy.common.ResourceAlchemy;
import de.artemis.resourcealchemy.common.registration.ModBlocks;
import de.artemis.resourcealchemy.common.registration.ModItems;
import net.minecraft.data.PackOutput;

public class LanguageProvider extends net.neoforged.neoforge.common.data.LanguageProvider {

    public LanguageProvider(PackOutput output, String locale) {
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
    }
}
