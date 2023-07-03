package site.siredvin.geodes.xplat

import dan200.computercraft.api.pocket.IPocketUpgrade
import dan200.computercraft.api.pocket.PocketUpgradeSerialiser
import dan200.computercraft.api.turtle.ITurtleUpgrade
import dan200.computercraft.api.turtle.TurtleUpgradeSerialiser
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import site.siredvin.peripheralium.common.items.DescriptiveBlockItem
import site.siredvin.peripheralium.data.language.ModInformationHolder
import site.siredvin.geodes.GeodesCore
import java.util.function.Supplier

interface ModPlatform : ModInformationHolder {
    companion object {
        private var _IMPL: ModPlatform? = null
        private val ITEMS: MutableList<Supplier<out Item>> = mutableListOf()
        private val BLOCKS: MutableList<Supplier<out Block>> = mutableListOf()

        val holder: ModInformationHolder
            get() = get()

        fun configure(impl: ModPlatform) {
            _IMPL = impl
        }

        private fun get(): ModPlatform {
            if (_IMPL == null) {
                throw IllegalStateException("You should init PeripheralWorks Platform first")
            }
            return _IMPL!!
        }

        fun <T : Item> registerItem(key: ResourceLocation, item: Supplier<T>): Supplier<T> {
            val registeredItem = get().registerItem(key, item)
            ITEMS.add(registeredItem)
            return registeredItem
        }

        fun <T : Item> registerItem(name: String, item: Supplier<T>): Supplier<T> {
            return registerItem(ResourceLocation(GeodesCore.MOD_ID, name), item)
        }

        fun <T : Block> registerBlock(key: ResourceLocation, block: Supplier<T>, itemFactory: (T) -> (Item)): Supplier<T> {
            return get().registerBlock(key, block, itemFactory)
        }

        fun <T : Block> registerBlock(name: String, block: Supplier<T>, itemFactory: (T) -> (Item) = { block -> DescriptiveBlockItem(block, Item.Properties()) }): Supplier<T> {
            val registeredBlock = get()
                .registerBlock(ResourceLocation(GeodesCore.MOD_ID, name), block, itemFactory)
            BLOCKS.add(registeredBlock)
            return registeredBlock
        }

        fun registerCreativeTab(key: ResourceLocation, tab: CreativeModeTab): Supplier<CreativeModeTab> {
            return get().registerCreativeTab(key, tab)
        }
    }

    override val blocks: List<Supplier<out Block>>
        get() = BLOCKS

    override val items: List<Supplier<out Item>>
        get() = ITEMS

    override val turtleSerializers: List<Supplier<TurtleUpgradeSerialiser<out ITurtleUpgrade>>>
        get() = emptyList()

    override val pocketSerializers: List<Supplier<PocketUpgradeSerialiser<out IPocketUpgrade>>>
        get() = emptyList()

    fun <T : Item> registerItem(key: ResourceLocation, item: Supplier<T>): Supplier<T>

    fun <T : Block> registerBlock(key: ResourceLocation, block: Supplier<T>, itemFactory: (T) -> (Item)): Supplier<T>

    fun registerCreativeTab(key: ResourceLocation, tab: CreativeModeTab): Supplier<CreativeModeTab>
}
