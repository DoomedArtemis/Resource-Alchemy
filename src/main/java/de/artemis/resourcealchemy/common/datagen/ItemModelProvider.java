package de.artemis.resourcealchemy.common.datagen;

import de.artemis.resourcealchemy.common.ResourceAlchemy;
import de.artemis.resourcealchemy.common.registration.ModBlocks;
import de.artemis.resourcealchemy.common.registration.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ResourceAlchemy.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.COPPER_NUGGET.get());
        basicItem(ModItems.ARCANE_BLOSSOM_SEED.get());
        basicItem(ModItems.ARCANE_BLOSSOM_PETAL.get());
        basicItem(ModItems.ARCANE_CRYSTAL_SHARD.get());
        basicItem(ModItems.ARCANE_CRYSTAL_POWDER.get());

        basicBlockFromDirectory(ModBlocks.ARCANE_SOIL.get());
        basicBlockFromDirectory(ModBlocks.ARCANE_ORE.get());
        basicBlockFromDirectory(ModBlocks.DEEPSLATE_ARCANE_ORE.get());

    }

    private void basicBlockFromDirectory(Block block) {
        withExistingParent(DataProvider.getRegistryName(block), ResourceLocation.fromNamespaceAndPath(this.modid, "item/" +
                DataProvider.getRegistryNamePath(block) + "_inventory"));
    }
}
