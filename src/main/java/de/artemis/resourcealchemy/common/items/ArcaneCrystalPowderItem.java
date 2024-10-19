package de.artemis.resourcealchemy.common.items;

import de.artemis.resourcealchemy.common.registration.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class ArcaneCrystalPowderItem extends Item {

    public ArcaneCrystalPowderItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Level level = context.getLevel();
        Block block = level.getBlockState(context.getClickedPos()).getBlock();
        Player player = context.getPlayer();
        BlockPos blockPos = context.getClickedPos();
        ItemStack itemStackInHand = context.getItemInHand();

        if (block == Blocks.FARMLAND) {
            level.playSound(player, blockPos, SoundEvents.AMETHYST_BLOCK_STEP, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.setBlock(blockPos, ModBlocks.ARCANE_SOIL.get().defaultBlockState(), 3);

            if (!player.isCreative() && !level.isClientSide) {
                itemStackInHand.shrink(1);
            }

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
