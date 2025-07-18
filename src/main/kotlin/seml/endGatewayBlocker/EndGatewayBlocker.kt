package seml.endGatewayBlocker

import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.PortalType
import org.bukkit.advancement.Advancement
import org.bukkit.event.entity.EntityPortalEnterEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent

class EndGatewayBlocker : JavaPlugin(), Listener {

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onEntityPortalEnter(event: EntityPortalEnterEvent) {
        if (event.portalType == PortalType.END_GATEWAY) {
            // 텔레포트 취소
            event.isCancelled = true
            // 플레이어에게 메시지 전송 (선택사항)
            if (event.entity is org.bukkit.entity.Player) {
                (event.entity as org.bukkit.entity.Player).sendActionBar("§c엔드 게이트웨이가 차단되었습니다.")  // 액션바 메시지 (색상 추가 예시)  // 액션바 메시지 (색상 추가 예시)
            }
        }
    }
}
