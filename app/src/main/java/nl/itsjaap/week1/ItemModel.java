package nl.itsjaap.week1;

class ItemModel {

    private String name;
    private String price;

    public void ItemModel(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
