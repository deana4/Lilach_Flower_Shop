package il.client.events;

import il.entities.Product;
import il.entities.Store;

import java.util.List;

public class CatalogItemsEvent {
    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    private List<Product> items;

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    private List<Store> stores;
    public CatalogItemsEvent(List<Product> items, List<Store> stores){
        this.items = items;
        this.stores = stores;
    }
}
