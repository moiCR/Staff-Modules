package git.moiCR.staffmodules;

import git.moiCR.staffmodules.staff.StaffManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class StaffModulesPlugin extends JavaPlugin {

    @Getter
    private static StaffModulesPlugin instance;
    private StaffManager staffManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        this.staffManager = new StaffManager(this);
    }

    @Override
    public void onDisable() {
        
    }

}
