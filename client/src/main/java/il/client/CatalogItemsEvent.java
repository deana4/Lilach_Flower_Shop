package il.client;

import il.entities.Flower;

import java.util.List;

public class CatalogItemsEvent {
    public List<Flower> getItems() {
        return items;
    }

    public void setItems(List<Flower> items) {
        this.items = items;
    }

    private List<Flower> items;
    public CatalogItemsEvent(List<Flower> items){
        this.items = items;
    }
}
