package site.siredvin.geodes
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry
import net.fabricmc.api.ModInitializer
import net.minecraftforge.fml.config.ModConfig
import site.siredvin.peripheralium.FabricPeripheralium
import site.siredvin.geodes.common.configuration.ConfigHolder
import site.siredvin.geodes.fabric.FabricModPlatform
import site.siredvin.geodes.fabric.FabricModRecipeIngredients
import site.siredvin.geodes.xplat.ModCommonHooks

@Suppress("UNUSED")
object FabricGeodes : ModInitializer {

    override fun onInitialize() {
        // Register configuration
        FabricPeripheralium.sayHi()
        GeodesCore.configure(FabricModPlatform, FabricModRecipeIngredients)
        // Register items and blocks
        ModCommonHooks.onRegister()
        // Pretty important to setup configuration after integration loading!
        ForgeConfigRegistry.INSTANCE.register(GeodesCore.MOD_ID, ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC)
    }
}
