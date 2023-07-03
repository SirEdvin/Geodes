package site.siredvin.geodes.xplat

import net.minecraft.resources.ResourceLocation
import site.siredvin.geodes.GeodesCore
import site.siredvin.geodes.common.setup.Blocks
import site.siredvin.geodes.common.setup.Items
import site.siredvin.peripheralium.xplat.PeripheraliumPlatform

object ModCommonHooks {

    fun onRegister() {
        Items.doSomething()
        Blocks.doSomething()
        ModPlatform.registerCreativeTab(
            ResourceLocation(GeodesCore.MOD_ID, "tab"),
            GeodesCore.configureCreativeTab(PeripheraliumPlatform.createTabBuilder()).build(),
        )
    }
}
