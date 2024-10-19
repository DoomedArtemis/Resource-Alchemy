package de.artemis.resourcealchemy.common.datagen;

import de.artemis.resourcealchemy.common.ResourceAlchemy;
import de.artemis.resourcealchemy.common.registration.ModBlocks;
import de.artemis.resourcealchemy.common.registration.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TagsProvider {

     public static class BlockTagProvider extends BlockTagsProvider {

         public BlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
             super(output, lookupProvider, ResourceAlchemy.MODID, existingFileHelper);
         }

         @Override
         protected void addTags(HolderLookup.@NotNull Provider provider) {

             tag(ModTags.Block.ARCANE_CROPS_GROW_ON).add(ModBlocks.ARCANE_SOIL.get()).add(Blocks.FARMLAND);
             tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ARCANE_ORE.get()).add(ModBlocks.DEEPSLATE_ARCANE_ORE.get());

         }
     }

    public static class ItemTagProvider extends ItemTagsProvider {

        public ItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, blockTags, ResourceAlchemy.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {

        }
    }
}
