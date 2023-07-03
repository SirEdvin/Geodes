package site.siredvin.geodes

import net.minecraft.world.item.CreativeModeTab
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import site.siredvin.geodes.common.setup.Items
import site.siredvin.geodes.data.ModText
import site.siredvin.geodes.xplat.ModCommonHooks
import site.siredvin.geodes.xplat.ModPlatform
import site.siredvin.geodes.xplat.ModRecipeIngredients

object GeodesCore {
    const val MOD_ID = "geodes"

    var LOGGER: Logger = LogManager.getLogger(MOD_ID)

    fun configureCreativeTab(builder: CreativeModeTab.Builder): CreativeModeTab.Builder {
        return builder.icon { Items.TEMPLATE_ITEM.get().defaultInstance }
            .title(ModText.CREATIVE_TAB.text)
            .displayItems { _, output ->
                ModPlatform.holder.blocks.forEach { output.accept(it.get()) }
                ModPlatform.holder.items.forEach { output.accept(it.get()) }
                ModCommonHooks.registerUpgradesInCreativeTab(output)
            }
    }

    fun configure(platform: ModPlatform, ingredients: ModRecipeIngredients) {
        ModPlatform.configure(platform)
        ModRecipeIngredients.configure(ingredients)
    }
}