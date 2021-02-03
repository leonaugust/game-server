package server.domain;

import common.dto.UserProfileStructure;
import java.util.Date;
import platform.domain.IUser;
import server.common.ProfileState;

import java.util.List;
import java.util.Set;

public class UserProfile implements IUser {

    public final int id;

    private String name;

    private int level;

    private int experience;

    private int energy;

    private int rating;

    private int money;

    private List<BackpackItem> backpack;

    private List<InventoryItem> inventory;

    private Set<Integer> friends;

    private ProfileState state = ProfileState.MAIN_MENU;

    private Date nameChangedDate;

    public UserProfile(int id) {
        this.id = id;
    }

    public UserProfile(int id, String name, int level, int experience, int energy, int rating, int money, List<BackpackItem> backpack, List<InventoryItem> inventory, Set<Integer> friends, Date nameChangedDate) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.energy = energy;
        this.rating = rating;
        this.money = money;
        this.backpack = backpack;
        this.inventory = inventory;
        this.friends = friends;
        this.nameChangedDate = nameChangedDate;
    }

    public UserProfileStructure serialize() {
        var dto = new UserProfileStructure();
        dto.id = id;
        dto.name = name;
        dto.level = level;
        dto.experience = experience;
        dto.energy = energy;
        dto.rating = rating;
        dto.money = money;
        dto.backpack = backpack.toArray(new BackpackItem[0]);
        dto.inventory = inventory.toArray(new InventoryItem[0]);
        dto.friends = friends.stream().mapToInt(i -> i).toArray();
        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<BackpackItem> getBackpack() {
        return backpack;
    }

    public void setBackpack(List<BackpackItem> backpack) {
        this.backpack = backpack;
    }

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryItem> inventory) {
        this.inventory = inventory;
    }

    public Set<Integer> getFriends() {
        return friends;
    }

    public void setFriends(Set<Integer> friends) {
        this.friends = friends;
    }

    public ProfileState getState() {
        return state;
    }

    public void setState(ProfileState state) {
        this.state = state;
    }

    public Date getNameChangedDate() {
        return nameChangedDate;
    }

    public void setNameChangedDate(Date nameChangedDate) {
        this.nameChangedDate = nameChangedDate;
    }

    @Override
    public int id() {
        return id;
    }
}
