package site.siredvin.geodes.forge

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import site.siredvin.geodes.ForgeGeodes
import site.siredvin.geodes.xplat.ModPlatform
import java.util.function.Supplier

object ForgeModPlatform : ModPlatform {
    override fun <T : Item> registerItem(key: ResourceLocation, item: Supplier<T>): Supplier<T> {
        return ForgeGeodes.itemsRegistry.register(key.path, item)
    }

    override fun <T : Block> registerBlock(key: ResourceLocation, block: Supplier<T>, itemFactory: (T) -> Item): Supplier<T> {
        val blockRegister = ForgeGeodes.blocksRegistry.register(key.path, block)
        ForgeGeodes.itemsRegistry.register(key.path) { itemFactory(blockRegister.get()) }
        return blockRegister
    }

    override fun registerCreativeTab(key: ResourceLocation, tab: CreativeModeTab): Supplier<CreativeModeTab> {
        return ForgeGeodes.creativeTabRegistry.register(key.path) { tab }
    }
}
