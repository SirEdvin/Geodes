package site.siredvin.geodes

import net.minecraft.resources.ResourceLocation
import java.util.function.Consumer

object GeodesClientCore {
    private val EXTRA_MODELS = emptyArray<String>()

    fun registerExtraModels(register: Consumer<ResourceLocation>) {
        EXTRA_MODELS.forEach { register.accept(ResourceLocation(GeodesCore.MOD_ID, it)) }
    }

    fun onInit() {
    }
}
