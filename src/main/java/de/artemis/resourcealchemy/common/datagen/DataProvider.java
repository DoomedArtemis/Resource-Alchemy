package de.artemis.resourcealchemy.common.datagen;

import de.artemis.resourcealchemy.common.ResourceAlchemy;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ResourceAlchemy.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataProvider {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(BlockLootTablesProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        TagsProvider.BlockTagProvider blockTagProvider = new TagsProvider.BlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagProvider);
        generator.addProvider(event.includeServer(), new TagsProvider.ItemTagProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeClient(), new LanguageProvider(packOutput, "en_us"));
        generator.addProvider(event.includeClient(), new ItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModelAndBlockStateProvider(packOutput, existingFileHelper));

        generator.addProvider(event.includeServer(), new RecipeProvider(packOutput, lookupProvider));

        generator.addProvider(event.includeServer(), new WorldGenProvider(packOutput, lookupProvider));
    }

    @SuppressWarnings("deprecation")
    public static String getRegistryName(Item item) {
        return item.builtInRegistryHolder().key().location().toString();
    }

    @SuppressWarnings("deprecation")
    public static String getRegistryName(Block block) {
        return block.builtInRegistryHolder().key().location().toString();
    }

    @SuppressWarnings("deprecation")
    public static String getRegistryNamePath(Block block) {
        return block.builtInRegistryHolder().key().location().getPath().toString();
    }

}
