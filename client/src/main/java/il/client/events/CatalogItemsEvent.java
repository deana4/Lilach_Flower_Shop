package il.client.events;

import il.entities.Product;

import java.util.List;

public class CatalogItemsEvent {
    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    private List<Product> items;
    public CatalogItemsEvent(List<Product> items){
        this.items = items;
    }
}
