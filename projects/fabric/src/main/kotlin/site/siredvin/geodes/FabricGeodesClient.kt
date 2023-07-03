package site.siredvin.geodes

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import java.util.function.Consumer

object FabricGeodesClient : ClientModInitializer {
    override fun onInitializeClient() {
        GeodesClientCore.onInit()
        ModelLoadingRegistry.INSTANCE.registerModelProvider { _: ResourceManager, out: Consumer<ResourceLocation> ->
            GeodesClientCore.registerExtraModels(out)
        }
    }
}
