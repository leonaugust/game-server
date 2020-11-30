package server.domain;

public class BackpackItem {

    public short itemId;

    public short count;

    public BackpackItem() {
    }

    public BackpackItem(short itemId, short count) {
        this.itemId = itemId;
        this.count = count;
    }

    public BackpackItem(String encodedValue) {
        this.itemId = Short.parseShort(encodedValue.substring(0, encodedValue.indexOf(":")));
        this.count = Short.parseShort(encodedValue.substring(encodedValue.indexOf(":") + 1));
    }

    public String encodeAsString() {
        return String.format("%s:%s", itemId, count);
    }

    @Override
    public String toString() {
        return "{" +
                "itemId=" + itemId +
                ", count=" + count +
                '}';
    }

}
