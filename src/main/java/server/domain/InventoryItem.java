package server.domain;

public class InventoryItem {

    public short itemId;

    public short durability;

    public InventoryItem() {
    }

    public InventoryItem(short itemId, short durability) {
        this.itemId = itemId;
        this.durability = durability;
    }

    public InventoryItem(String encodedValue) {
        this.itemId = Short.parseShort(encodedValue.substring(0, encodedValue.indexOf(":")));
        this.durability = Short.parseShort(encodedValue.substring(encodedValue.indexOf(":") + 1));
    }

    public String encodeAsString() {
        return String.format("%s:%s", itemId, durability);
    }

    @Override
    public String toString() {
        return "{" +
                "itemId=" + itemId +
                ", durability=" + durability +
                '}';
    }
}
