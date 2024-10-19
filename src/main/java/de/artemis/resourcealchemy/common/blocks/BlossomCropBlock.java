package de.artemis.resourcealchemy.common.blocks;

import de.artemis.resourcealchemy.common.registration.ModBlocks;
import de.artemis.resourcealchemy.common.registration.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class BlossomCropBlock extends CropBlock {
    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    private final Supplier<ItemLike> seed;
    private final Supplier<ItemLike> petal;
    protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 2.0D, 11.0D);

    public BlossomCropBlock(Properties properties, Supplier<ItemLike> seed, Supplier<ItemLike> petal) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
        this.seed = seed;
        this.petal = petal;
    }

    @Override
    public boolean onDestroyedByPlayer(@NotNull BlockState blockState, Level level, BlockPos blockPos, @NotNull Player player, boolean willHarvest, @NotNull FluidState fluid) {
        Block farmland = level.getBlockState(blockPos.below()).getBlock();

        if (farmland == ModBlocks.ARCANE_SOIL.get()) {
            ItemEntity itemEntity = new ItemEntity(level, blockPos.getX() + 0.5F, blockPos.getY() + 0.5F, blockPos.getZ() + 0.5F, new ItemStack(petal.get()));
            itemEntity.setDefaultPickUpDelay();
            level.addFreshEntity(itemEntity);
        }

        return super.onDestroyedByPlayer(blockState, level, blockPos, player, willHarvest, fluid);
    }

    public static VoxelShape getSHAPE() {
        return SHAPE;
    }

    @Override
    protected @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, @NotNull BlockGetter level, @NotNull BlockPos blockPos) {
        return blockState.is(ModTags.Block.ARCANE_CROPS_GROW_ON);
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
