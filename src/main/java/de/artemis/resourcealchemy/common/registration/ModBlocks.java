package de.artemis.resourcealchemy.common.registration;

import de.artemis.resourcealchemy.common.blocks.ArcaneSoilBlock;
import de.artemis.resourcealchemy.common.blocks.BlossomCropBlock;
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
