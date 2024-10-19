package de.artemis.resourcealchemy.common.registration;

import de.artemis.resourcealchemy.common.ModCreativeModeTabs;
import de.artemis.resourcealchemy.common.ResourceAlchemy;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Registration {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ResourceAlchemy.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ResourceAlchemy.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ResourceAlchemy.MODID);
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registries.CONFIGURED_FEATURE, ResourceAlchemy.MODID);
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registries.PLACED_FEATURE, ResourceAlchemy.MODID);

    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
        CREATIVE_MODE_TAB.register(eventBus);
        CONFIGURED_FEATURES.register(eventBus);
        PLACED_FEATURES.register(eventBus);

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModCreativeModeTabs.register(eventBus);

    }
}
