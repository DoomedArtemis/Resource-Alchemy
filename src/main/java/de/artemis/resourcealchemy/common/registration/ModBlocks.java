package de.artemis.resourcealchemy.common.registration;

import de.artemis.resourcealchemy.common.blocks.*;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredBlock<BlossomCropBlock> ARCANE_BLOSSOM = registerBlock("arcane_blossom",
            () -> new BlossomCropBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY), ModItems.ARCANE_BLOSSOM_SEED::get, ModItems.ARCANE_BLOSSOM_PETAL::get));

    public static final DeferredBlock<ArcaneSoilBlock> ARCANE_SOIL = registerBlock("arcane_soil",
            () -> new ArcaneSoilBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT).randomTicks().strength(0.6F).sound(SoundType.GRAVEL).isViewBlocking(ModBlocks::always).isSuffocating(ModBlocks::always)));

    public static final DeferredBlock<DropExperienceBlock> ARCANE_ORE = registerBlock("arcane_ore",
            () -> new DropExperienceBlock(UniformInt.of(1, 3),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_ARCANE_ORE = registerBlock("deepslate_arcane_ore",
            () -> new DropExperienceBlock(UniformInt.of(1, 3),
                    BlockBehaviour.Properties.ofLegacyCopy(ARCANE_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<ArcaneCrystalBlock> ARCANE_CRYSTAL_BLOCK = registerBlock("arcane_crystal_block",
            () -> new ArcaneCrystalBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE).strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));

    public static final DeferredBlock<BuddingArcaneCrystalBlock> BUDDING_ARCANE_CRYSTAL = registerBlock("budding_arcane_crystal",
            () -> new BuddingArcaneCrystalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).randomTicks().strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));

    public static final DeferredBlock<ArcaneClusterBlock> ARCANE_CRYSTAL_CLUSTER = registerBlock("arcane_crystal_cluster",
            () -> new ArcaneClusterBlock(7, 3, BlockBehaviour.Properties.of().noOcclusion().randomTicks().strength(1.5F).sound(SoundType.AMETHYST_CLUSTER).requiresCorrectToolForDrops().lightLevel((p_152632_) -> 5)));

    public static final DeferredBlock<ArcaneClusterBlock> LARGE_ARCANE_CRYSTAL_BUD = registerBlock("large_arcane_crystal_bud",
            () -> new ArcaneClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(ARCANE_CRYSTAL_CLUSTER.get()).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel((p_152632_) -> 4)));

    public static final DeferredBlock<ArcaneClusterBlock> MEDIUM_ARCANE_CRYSTAL_BUD = registerBlock("medium_arcane_crystal_bud",
            () -> new ArcaneClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(ARCANE_CRYSTAL_CLUSTER.get()).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel((p_152632_) -> 2)));

    public static final DeferredBlock<ArcaneClusterBlock> SMALL_ARCANE_CRYSTAL_BUD = registerBlock("small_arcane_crystal_bud",
            () -> new ArcaneClusterBlock(3, 3, BlockBehaviour.Properties.ofFullCopy(ARCANE_CRYSTAL_CLUSTER.get()).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel((p_152632_) -> 1)));




    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = Registration.BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        Registration.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    public static void register(IEventBus eventBus) {
    }
}
