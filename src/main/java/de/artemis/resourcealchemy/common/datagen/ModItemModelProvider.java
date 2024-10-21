package de.artemis.resourcealchemy.common.datagen;

import de.artemis.resourcealchemy.common.ResourceAlchemy;
import de.artemis.resourcealchemy.common.registration.ModBlocks;
import de.artemis.resourcealchemy.common.registration.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ResourceAlchemy.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.COPPER_NUGGET.get());
        basicItem(ModItems.ARCANE_BLOSSOM_SEED.get());
        basicItem(ModItems.ARCANE_BLOSSOM_PETAL.get());
        basicItem(ModItems.ARCANE_CRYSTAL_SHARD.get());
        basicItem(ModItems.ARCANE_CRYSTAL_POWDER.get());

        basicBlockItem(ModBlocks.ARCANE_CRYSTAL_CLUSTER.get());

        basicBlockFromDirectory(ModBlocks.ARCANE_SOIL.get());
        basicBlockFromDirectory(ModBlocks.ARCANE_ORE.get());
        basicBlockFromDirectory(ModBlocks.DEEPSLATE_ARCANE_ORE.get());
        basicBlockFromDirectory(ModBlocks.ARCANE_CRYSTAL_BLOCK.get());
        basicBlockFromDirectory(ModBlocks.BUDDING_ARCANE_CRYSTAL.get());

        blockWithModel(ModBlocks.LARGE_ARCANE_CRYSTAL_BUD.get(), ResourceAlchemy.MODID + ":generation/arcane_crystal_bud");
        blockWithModel(ModBlocks.MEDIUM_ARCANE_CRYSTAL_BUD.get(), ResourceAlchemy.MODID + ":generation/arcane_crystal_bud");
        blockWithModel(ModBlocks.SMALL_ARCANE_CRYSTAL_BUD.get(), ResourceAlchemy.MODID + ":generation/arcane_crystal_bud");

    }

    private void basicBlockFromDirectory(Block block) {
        withExistingParent(ModDataProvider.getRegistryName(block), ResourceLocation.fromNamespaceAndPath(this.modid, "item/" +
                ModDataProvider.getRegistryNamePath(block) + "_inventory"));
    }

    private void blockWithModel(Block block, String model) {
        withExistingParent(ModDataProvider.getRegistryName(block), model).texture("texture", ResourceLocation.fromNamespaceAndPath(this.modid, "block/" +
                ModDataProvider.getRegistryNamePath(block)));
    }

    private void basicBlockItem(Block block) {
        withExistingParent(ModDataProvider.getRegistryName(block), "item/generated").texture("layer0", ResourceLocation.fromNamespaceAndPath(this.modid, "block/" +
                ModDataProvider.getRegistryNamePath(block)));
    }
}
