package de.artemis.resourcealchemy.common.datagen;

import de.artemis.resourcealchemy.common.ResourceAlchemy;
import de.artemis.resourcealchemy.common.registration.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static de.artemis.resourcealchemy.common.blocks.ArcaneSoilBlock.MOISTURE;
import static de.artemis.resourcealchemy.common.blocks.BlossomCropBlock.AGE;

public class ModModelAndBlockStateProvider extends BlockStateProvider {
    public ModModelAndBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ResourceAlchemy.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        cropBlockMaxStageFive(ModBlocks.ARCANE_BLOSSOM.get(), ResourceLocation.fromNamespaceAndPath(ResourceAlchemy.MODID, "block/arcane_blossom"));

        farmBlock(ModBlocks.ARCANE_SOIL.get(), ResourceLocation.fromNamespaceAndPath(ResourceAlchemy.MODID, "block/arcane_soil"), ResourceLocation.fromNamespaceAndPath(ResourceAlchemy.MODID, "block/arcane_soil_moist"), ResourceLocation.withDefaultNamespace("block/dirt"));

        simpleBlock(ModBlocks.ARCANE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_ARCANE_ORE.get());
        simpleBlock(ModBlocks.ARCANE_CRYSTAL_BLOCK.get());
        simpleBlock(ModBlocks.BUDDING_ARCANE_CRYSTAL.get());

        crossBlock(ModBlocks.ARCANE_CRYSTAL_CLUSTER.get(), ResourceLocation.fromNamespaceAndPath(ResourceAlchemy.MODID, "block/arcane_crystal_cluster"));
        crossBlock(ModBlocks.LARGE_ARCANE_CRYSTAL_BUD.get(), ResourceLocation.fromNamespaceAndPath(ResourceAlchemy.MODID, "block/large_arcane_crystal_bud"));
        crossBlock(ModBlocks.MEDIUM_ARCANE_CRYSTAL_BUD.get(), ResourceLocation.fromNamespaceAndPath(ResourceAlchemy.MODID, "block/medium_arcane_crystal_bud"));
        crossBlock(ModBlocks.SMALL_ARCANE_CRYSTAL_BUD.get(), ResourceLocation.fromNamespaceAndPath(ResourceAlchemy.MODID, "block/small_arcane_crystal_bud"));
    }

    public void cropBlockMaxStageFive(Block block, ResourceLocation texture) {
        ModelFile crop_stage_0 = models().withExistingParent(ModDataProvider.getRegistryName(block.asItem()) + "_stage_0",
                ResourceLocation.withDefaultNamespace("block/cross")).renderType("cutout").texture("cross", texture + "_0");
        ModelFile crop_stage_1 = models().withExistingParent(ModDataProvider.getRegistryName(block.asItem()) + "_stage_1",
                ResourceLocation.withDefaultNamespace("block/cross")).renderType("cutout").texture("cross", texture + "_1");
        ModelFile crop_stage_2 = models().withExistingParent(ModDataProvider.getRegistryName(block.asItem()) + "_stage_2",
                ResourceLocation.withDefaultNamespace("block/cross")).renderType("cutout").texture("cross", texture + "_2");
        ModelFile crop_stage_3 = models().withExistingParent(ModDataProvider.getRegistryName(block.asItem()) + "_stage_3",
                ResourceLocation.withDefaultNamespace("block/cross")).renderType("cutout").texture("cross", texture + "_3");
        ModelFile crop_stage_4 = models().withExistingParent(ModDataProvider.getRegistryName(block.asItem()) + "_stage_4",
                ResourceLocation.withDefaultNamespace("block/cross")).renderType("cutout").texture("cross", texture + "_4");
        ModelFile crop_stage_5 = models().withExistingParent(ModDataProvider.getRegistryName(block.asItem()) + "_stage_5",
                ResourceLocation.withDefaultNamespace("block/cross")).renderType("cutout").texture("cross", texture + "_5");

        cropBlockMaxStageFive(block, crop_stage_0, crop_stage_1, crop_stage_2, crop_stage_3, crop_stage_4, crop_stage_5);
    }

    public void cropBlockMaxStageFive(Block block, ModelFile crop_stage_0, ModelFile crop_stage_1, ModelFile crop_stage_2, ModelFile crop_stage_3, ModelFile crop_stage_4, ModelFile crop_stage_5) {
        getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(AGE);
            ModelFile finalModel = switch (age) {
                case 0 -> crop_stage_0;
                case 1 -> crop_stage_1;
                case 2 -> crop_stage_2;
                case 3 -> crop_stage_3;
                case 4 -> crop_stage_4;
                default -> crop_stage_5;
            };
            return ConfiguredModel.builder().modelFile(finalModel).build();
        });
    }

    public void farmBlock(Block block, ResourceLocation texture_top, ResourceLocation texture_top_moist, ResourceLocation texture_side) {
        ModelFile block_model = models().withExistingParent(ModDataProvider.getRegistryName(block.asItem()),
                ResourceLocation.withDefaultNamespace("block/template_farmland")).texture("dirt", texture_side).texture("top", texture_top);
        ModelFile block_model_moist = models().withExistingParent(ModDataProvider.getRegistryName(block.asItem()) + "_moist",
                ResourceLocation.withDefaultNamespace("block/template_farmland")).texture("dirt", texture_side).texture("top", texture_top_moist);

        farmBlock(block, block_model, block_model_moist);
    }

    public void farmBlock(Block block, ModelFile block_model, ModelFile block_model_moist) {
        getVariantBuilder(block).forAllStates(state -> {
            ModelFile finalModel = state.getValue(MOISTURE) == 7 ? block_model_moist : block_model;

            return ConfiguredModel.builder().modelFile(finalModel).build();
        });
    }

    public void crossBlock(Block block, ResourceLocation texture) {
        ModelFile block_model = models().withExistingParent(ModDataProvider.getRegistryName(block.asItem()),
                ResourceLocation.withDefaultNamespace("block/cross")).renderType("cutout").texture("cross", texture);

        directionalBlock(block, block_model);
    }
}
