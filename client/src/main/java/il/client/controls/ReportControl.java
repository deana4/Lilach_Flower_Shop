package il.client.controls;

import il.entities.CartProduct;
import il.entities.Complain;
import il.entities.Order;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;

import java.util.HashMap;
import java.util.LinkedList;

public class ReportControl {
    private LinkedList<Order> orders;
    private LinkedList<Complain> complains;

    private double sum;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Integer> products) {
        this.products = products;
    }

    public HashMap<String, Integer> getComplain_date() {
        return complain_date;
    }

    public void setComplain_date(HashMap<String, Integer> complain_date) {
        this.complain_date = complain_date;
    }

    private HashMap<String, Integer> products = new HashMap<String, Integer>();  //Hashmap of roots by names

    private HashMap<String, Integer> complain_date = new HashMap<String, Integer>();  //Hashmap of roots by names

    private int storeID;

    int getMonth(String date){
        String[] a = date.split("/", 3);
        return Integer.parseInt(a[1]);
    }
    int getDay(String date){
        String[] a = date.split("/", 3);
        return Integer.parseInt(a[2]);
    }
    int getYear(String date){
        String[] a = date.split("/", 3);
        return Integer.parseInt(a[0]);
    }

    //-1 => date1>date2
    //0 => date1==date2
    //1 => date1<date2
    public int check(String date1, String date2){
        if(date1.equals(date2))
            return 0;
        if(getYear(date1)>getYear(date2))
            return -1;
        if(getYear(date1)<getYear(date2))
            return 1;
        if(getMonth(date1)>getMonth(date2))
            return -1;
        if(getMonth(date1)<getMonth(date2))
            return 1;
        if(getDay(date1)>getDay(date2))
            return -1;
        if(getDay(date1)<getDay(date2))
            return 1;
        return 0;
    }

    public LinkedList<Complain> cutComplainByDate(String begin, String end, int storeID){
        LinkedList<Complain> newComplainByStore = new LinkedList<>();
        LinkedList<Complain> newComplain = new LinkedList<>();
        if(storeID>-1){
            for(Complain c : complains){
                if(c.getStore().getId()==storeID)
                    newComplainByStore.add(c);
            }
        }
        else
            newComplainByStore = complains;

        for(Complain c : newComplainByStore) {
            if (check(c.getDate_complain(), end)!=-1 && check(c.getDate_complain(), begin)==-1)
                newComplain.add(c);
        }
        return newComplain;

    }

    public LinkedList<Order> OrdercutByDate(String begin, String end, int storeID){
        LinkedList<Order> newOrdersByStore = new LinkedList<>();
        LinkedList<Order> newOrders = new LinkedList<>();
        if(storeID>-1){
            for(Order c : orders){
                if(c.getStore().getId()==storeID)
                    newOrdersByStore.add(c);
            }
        }
        else
            newOrdersByStore = orders;

        for(Order c : newOrdersByStore) {
            if (check(c.getDateOrder(), end)!=-1 && check(c.getDateOrder(), begin)==-1)
                newOrders.add(c);
        }
        return newOrders;

    }


    private void generateSum(){
        double sum=0;
        for(Order o: orders){
            if(o.getStatus()!=1)
                sum+=o.getSum();
        }
        this.sum = sum;
    }

    private void addOneProducts(String key, int amount){
        if(products.containsKey(key))
            products.put(key, products.get(key)+amount);
        products.put(key, amount);
    }
    private void addOneComplain(String key){
        if(complain_date.containsKey(key))
            complain_date.put(key, complain_date.get(key));
        complain_date.put(key, 1);
    }

    private void generateProductCount(){
        for(Order o: orders){
            if(o.getStatus()!=1){
                for(CartProduct p : o.getProducts())
                    addOneProducts(p.getName(), p.getAmount());
            }
        }
    }

    private void generateComplainByDate(){
        for(Complain c: complains){
            addOneComplain(c.getDate_complain());
        }
    }

    public int getcountDate(String date){
        if(!complain_date.containsKey(date))
            return 0;
        return complain_date.get(date);
    }
    public int getcountProduct(String product){
        if(!products.containsKey(product))
            return 0;
      // return complain_date.get(product);
        return products.get(product); //liran changed
    }


    public String fixDate(String date){
        try{
            if(getDay(date)<31)
                return date;
            String[] a = date.split("/", 3);
            String newDate = a[2]+"/"+a[1]+"/"+a[0];
            return newDate;
        }
        catch (Exception e){

        }
        return date;
    }




    public ReportControl(LinkedList<Order> orders, LinkedList<Complain> complains, String begin, String end, int storeID) {
        this.orders = orders;
        for(Order o: orders)
            o.setDateOrder(fixDate(o.getDateOrder()));
        for(Complain c:complains)
            c.setDate_complain(fixDate(c.getDate_complain()));
        this.complains= complains;
        this.orders = OrdercutByDate(begin, end, storeID);
        this.complains = cutComplainByDate(begin, end, storeID);
        generateSum();
        generateComplainByDate();
        generateProductCount();

    }
    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public LinkedList<Order> getOrders() {
        return orders;
    }

    public void setOrders(LinkedList<Order> orders) {
        this.orders = orders;
    }

    public LinkedList<Complain> getComplains() {
        return complains;
    }

    public void setComplains(LinkedList<Complain> complains) {
        this.complains = complains;
    }
}
