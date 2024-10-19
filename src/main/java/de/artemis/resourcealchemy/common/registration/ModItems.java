package de.artemis.resourcealchemy.common.registration;

import de.artemis.resourcealchemy.common.items.ArcaneCrystalPowderItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItems {

    public static final DeferredItem<Item> COPPER_NUGGET = Registration.ITEMS.register("copper_nugget",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ARCANE_BLOSSOM_SEED = Registration.ITEMS.register("arcane_blossom_seed",
            () -> new ItemNameBlockItem(ModBlocks.ARCANE_BLOSSOM.get(), (new Item.Properties())));

    public static final DeferredItem<Item> ARCANE_BLOSSOM_PETAL = Registration.ITEMS.register("arcane_blossom_petal",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ARCANE_CRYSTAL_SHARD = Registration.ITEMS.register("arcane_crystal_shard",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<ArcaneCrystalPowderItem> ARCANE_CRYSTAL_POWDER = Registration.ITEMS.register("arcane_crystal_powder",
            () -> new ArcaneCrystalPowderItem(new Item.Properties()));


    public static void register(IEventBus eventBus) {
    }

}
