package common.dto;

import server.domain.BackpackItem;
import server.domain.InventoryItem;

import java.util.Arrays;

public class UserProfileStructure {

    public int id;

    public String name;

    public int level;

    public int experience;

    public int energy;

    public int rating;

    public int money;

    public BackpackItem[] backpack;

    public InventoryItem[] inventory;

    public int[] friends;

    @Override
    public String toString() {
        return "UserProfileStructure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", energy=" + energy +
                ", rating=" + rating +
                ", money=" + money +
                ", backpack=" + Arrays.toString(backpack) +
                ", inventory=" + Arrays.toString(inventory) +
                ", friends=" + Arrays.toString(friends) +
                '}';
    }
}
