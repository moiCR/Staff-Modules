package git.moiCR.staffmodules.staff.listener;

import git.moiCR.staffmodules.StaffModulesPlugin;
import git.moiCR.staffmodules.staff.data.Staff;
import git.moiCR.staffmodules.utils.ConfigVariables;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public record StaffListener(StaffModulesPlugin plugin) implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("staff")) {
            boolean startInStaffMode = ConfigVariables.STAFF_ON_JOIN;
            plugin.getStaffManager().createStaff(player, startInStaffMode);

            if (!startInStaffMode && ConfigVariables.VANISH_ON_JOIN) {
                plugin.getStaffManager().enableVanish(player);
            }

            return;
        }

        for (Staff staff : plugin.getStaffManager().getStaffs()) {
            if (!staff.isVanished()) {
                continue;
            }

            Player staffPlayer = Bukkit.getPlayer(staff.getId());
            if (staffPlayer != null && staffPlayer.isOnline()) {
                player.hidePlayer(plugin, staffPlayer);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        if (event.getPlayer().hasPermission("staff")) {
            plugin.getStaffManager().removeStaff(event.getPlayer().getUniqueId());
        }
    }
}