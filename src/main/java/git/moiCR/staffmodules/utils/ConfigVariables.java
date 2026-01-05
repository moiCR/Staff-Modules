package git.moiCR.staffmodules.utils;

import git.moiCR.staffmodules.StaffModulesPlugin;
import lombok.experimental.UtilityClass;
import org.bukkit.configuration.file.FileConfiguration;

@UtilityClass
public class ConfigVariables {

    public boolean STAFF_ON_JOIN;
    public boolean VANISH_ON_JOIN;

    public void load(StaffModulesPlugin plugin){
        FileConfiguration config = plugin.getConfig();

        STAFF_ON_JOIN = config.getBoolean("STAFF_ON_JOIN");
        VANISH_ON_JOIN = config.getBoolean("VANISH_ON_JOIN");
    }

}
