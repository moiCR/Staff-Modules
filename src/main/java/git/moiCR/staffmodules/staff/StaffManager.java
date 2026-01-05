package git.moiCR.staffmodules.staff;

import git.moiCR.staffmodules.StaffModulesPlugin;
import git.moiCR.staffmodules.staff.data.Staff;
import git.moiCR.staffmodules.staff.listener.StaffListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class StaffManager {

    private final StaffModulesPlugin plugin;
    private final Set<Staff> staffs;

    public StaffManager(StaffModulesPlugin plugin){
        this.plugin = plugin;
        this.staffs = new HashSet<>();
        Bukkit.getPluginManager().registerEvents(new StaffListener(plugin), plugin);
    }

    public boolean isStaff(Player player){
        return this.staffs.stream().anyMatch(staff -> staff.getId().equals(player.getUniqueId()));
    }

    public Staff getStaff(Player player){
        return this.staffs.stream()
                .filter(staff -> staff.getId().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);
    }

    public void removeStaff(UUID uuid) {
        this.staffs.removeIf(staff -> staff.getId().equals(uuid));
    }

    public Staff createStaff(Player player, boolean enableModules){
        Staff existing = getStaff(player);
        if (existing != null) return existing;

        var newStaff = new Staff(player.getUniqueId());
        this.staffs.add(newStaff);

        if (enableModules) {
            newStaff.setStaffMode(true);
            enableVanish(player);
        }

        return newStaff;
    }

    public void toggleStaffMode(Player player){
        if (!player.hasPermission("staff")){
            return;
        }

        var staff = getStaff(player);
        if (staff == null) {
            staff = createStaff(player, false);
        }

        staff.toggleStaffMode(player);

        if (staff.isStaffMode()){
            enableVanish(player);
            player.sendMessage("§aStaff Mode activado (Vanish ON).");
        } else {
            disableVanish(player);
            player.sendMessage("§cStaff Mode desactivado (Vanish OFF).");
        }
    }

    public void enableVanish(Player staffPlayer) {
        var staff = getStaff(staffPlayer);

        if (staff.isVanished()) return;
        staff.setVanished(true);

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (!target.hasPermission("staff")) {
                target.hidePlayer(this.plugin, staffPlayer);
            }
        }
    }

    public void disableVanish(Player staffPlayer) {
        var staff = getStaff(staffPlayer);

        if (!staff.isVanished()) return;
        staff.setVanished(false);

        for (Player target : Bukkit.getOnlinePlayers()) {
            target.showPlayer(this.plugin, staffPlayer);
        }
    }
}