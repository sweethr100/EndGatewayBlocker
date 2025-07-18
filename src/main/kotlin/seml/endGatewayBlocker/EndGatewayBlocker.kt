package seml.endGatewayBlocker

import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.PortalType
import org.bukkit.advancement.Advancement
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.entity.EntityPortalEnterEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.ChatColor

class EndGatewayBlocker : JavaPlugin(), Listener {
    private lateinit var config: FileConfiguration
    private var blockMessage: String = "엔드 게이트웨이가 차단되었습니다."  // 기본 메시지

    override fun onEnable() {
        saveDefaultConfig()
        config = getConfig()
        blockMessage = ChatColor.translateAlternateColorCodes('&', config.getString("block-message", blockMessage)!!)

        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onEntityPortalEnter(event: EntityPortalEnterEvent) {
        if (event.portalType == PortalType.END_GATEWAY) {
            // 텔레포트 취소
            event.isCancelled = true
            // 플레이어에게 메시지 전송 (선택사항)
            if (event.entity is org.bukkit.entity.Player) {
                (event.entity as org.bukkit.entity.Player).sendMessage(blockMessage)
            }
        }
    }
}
