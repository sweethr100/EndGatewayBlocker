package seml.endGatewayBlocker

import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityTeleportEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.Location
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerTeleportEvent

class EndGatewayBlocker : JavaPlugin(), Listener {

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
        logger.info("End Gateway Blocker 플러그인 활성화됨 - 다리 건설 방지 추가")
    }

    @EventHandler
    fun onPlayerTeleport(event: PlayerTeleportEvent) {
        if (event.cause == PlayerTeleportEvent.TeleportCause.END_GATEWAY) {
            val toLocation: Location? = event.to
            if (toLocation != null && toLocation.world.name == "world_the_end") {
                if (Math.abs(toLocation.x) > 250 || Math.abs(toLocation.z) > 250) {
                    event.isCancelled = true
                    event.player.sendMessage("엔드 시티로의 이동이 제한되었습니다!")
                }
            }
        }
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val location: Location = event.block.location
        if (location.world.name == "world_the_end") {
            // 메인 섬 밖(외곽 영역)에서 블록 놓기 방지
            if (Math.abs(location.x) > 250 || Math.abs(location.z) > 250) {
                event.isCancelled = true
                event.player.sendMessage("이 영역에서 건설이 제한되었습니다! (엔드 시티 접근 방지)")
            }
        }
    }
}
