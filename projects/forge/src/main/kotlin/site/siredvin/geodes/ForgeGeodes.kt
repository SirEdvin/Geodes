package site.siredvin.geodes

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import site.siredvin.geodes.common.configuration.ConfigHolder
import site.siredvin.geodes.forge.ForgeModPlatform
import site.siredvin.geodes.forge.ForgeModRecipeIngredients
import site.siredvin.geodes.xplat.ModCommonHooks
import site.siredvin.peripheralium.ForgePeripheralium
import thedarkcolour.kotlinforforge.forge.MOD_CONTEXT

@Mod(GeodesCore.MOD_ID)
@Mod.EventBusSubscriber(modid = GeodesCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object ForgeGeodes {

    val blocksRegistry: DeferredRegister<Block> =
        DeferredRegister.create(ForgeRegistries.BLOCKS, GeodesCore.MOD_ID)
    val itemsRegistry: DeferredRegister<Item> =
        DeferredRegister.create(ForgeRegistries.ITEMS, GeodesCore.MOD_ID)
    val creativeTabRegistry: DeferredRegister<CreativeModeTab> =
        DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), GeodesCore.MOD_ID)

    init {
        ForgePeripheralium.sayHi()
        // Configure configuration
        val context = ModLoadingContext.get()
        context.registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC, "${GeodesCore.MOD_ID}.toml")
        GeodesCore.configure(ForgeModPlatform, ForgeModRecipeIngredients)
        val eventBus = MOD_CONTEXT.getKEventBus()
        // Register items and blocks
        ModCommonHooks.onRegister()
        blocksRegistry.register(eventBus)
        itemsRegistry.register(eventBus)
        creativeTabRegistry.register(eventBus)
    }
}
