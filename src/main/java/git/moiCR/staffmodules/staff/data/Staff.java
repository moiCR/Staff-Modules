package git.moiCR.staffmodules.staff.data;

import git.moiCR.staffmodules.staff.layout.StaffLayout;
import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Data
public class Staff {

    private UUID id;
    private ItemStack[] inventoryContents;
    private ItemStack[] armor;
    private boolean vanished;
    private boolean staffMode;

    public Staff(UUID id){
        this.id = id;
        this.inventoryContents = new ItemStack[]{};
        this.armor = new ItemStack[]{};
        this.vanished = false;
        this.staffMode = false;
    }


    public void toggleStaffMode(Player player){
        this.staffMode = !this.staffMode;

        if (staffMode){
            setInventoryContents(player.getInventory().getContents());
            setArmor(player.getInventory().getArmorContents());
            StaffLayout.giveLayout(player);
        }else{
            player.getInventory().setContents(getInventoryContents());
            player.getInventory().setArmorContents(getArmor());
        }

        player.updateInventory();
    }
    
}
