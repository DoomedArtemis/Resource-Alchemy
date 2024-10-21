package de.artemis.resourcealchemy.common;

import de.artemis.resourcealchemy.common.registration.ModBlocks;
import de.artemis.resourcealchemy.common.registration.ModItems;
import de.artemis.resourcealchemy.common.registration.Registration;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final Supplier<CreativeModeTab> CREATIVE_MODE_TAB = Registration.CREATIVE_MODE_TAB.register("creative_mode_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.COPPER_NUGGET.get()))
                    .title(Component.translatable("creativemodetab.resourcealchemy"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.COPPER_NUGGET.get());
                        output.accept(ModItems.ARCANE_BLOSSOM_SEED.get());
                        output.accept(ModItems.ARCANE_BLOSSOM_PETAL.get());
                        output.accept(ModBlocks.ARCANE_SOIL.get());
                        output.accept(ModItems.ARCANE_CRYSTAL_SHARD);
                        output.accept(ModItems.ARCANE_CRYSTAL_POWDER);
                        output.accept(ModBlocks.ARCANE_ORE);
                        output.accept(ModBlocks.DEEPSLATE_ARCANE_ORE);
                        output.accept(ModBlocks.ARCANE_CRYSTAL_BLOCK);
                        output.accept(ModBlocks.BUDDING_ARCANE_CRYSTAL);
                        output.accept(ModBlocks.SMALL_ARCANE_CRYSTAL_BUD);
                        output.accept(ModBlocks.MEDIUM_ARCANE_CRYSTAL_BUD);
                        output.accept(ModBlocks.LARGE_ARCANE_CRYSTAL_BUD);
                        output.accept(ModBlocks.ARCANE_CRYSTAL_CLUSTER);
                    }).build());

    public static void register(IEventBus eventBus) {
    }
}
