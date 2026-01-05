package git.moiCR.staffmodules.staff.layout;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public enum StaffLayout {

    ARMOR{

        @Override
        public ItemStack[] getItems() {
            return new ItemStack[0];
        }

    },

    HOTBAR{

        @Override
        public ItemStack[] getItems() {
            return new ItemStack[0];
        }

    };

    public abstract ItemStack[] getItems();

    public static void giveLayout(Player player){
        player.getInventory().setArmorContents(ARMOR.getItems());
        player.getInventory().setContents(HOTBAR.getItems());
    }
}
