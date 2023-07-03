package site.siredvin.geodes.common.configuration

import net.minecraftforge.common.ForgeConfigSpec

object ModConfig {

    val enableSomething: Boolean
        get() = ConfigHolder.COMMON_CONFIG.ENABLE_SOMETHING.get()

    class CommonConfig internal constructor(builder: ForgeConfigSpec.Builder) {

        // Generic plugins
        var ENABLE_SOMETHING: ForgeConfigSpec.BooleanValue

        init {
            builder.push("base")
            ENABLE_SOMETHING = builder.comment("Something enabled")
                .define("enableSomething", true)
            builder.pop()
        }
    }
}
