package site.siredvin.geodes.data

import site.siredvin.peripheralium.data.language.TextRecord
import site.siredvin.geodes.GeodesCore

enum class ModText : TextRecord {
    CREATIVE_TAB,
    ;

    override val textID: String by lazy {
        String.format("text.%s.%s", GeodesCore.MOD_ID, name.lowercase())
    }
}
