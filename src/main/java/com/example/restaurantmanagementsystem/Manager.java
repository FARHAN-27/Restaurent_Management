package com.example.restaurantmanagementsystem;

import java.util.HashMap;
import java.util.Map;

import static com.example.restaurantmanagementsystem.HelloApplication.l;
import static com.example.restaurantmanagementsystem.Loader.man;

public class Manager {

    public Map<String,Integer> loyalty;
    public Menu menu;
    public Integer index;
    public Order_collection all_order_collection,offline_order_collection,online_order_collection;
    public Sales_collection all_sale_collection,offline_sale_collection,online_sale_collection;

    Integer today_sale = 0;
    Integer today_order_count = 0;
    Integer online_order_count = 0;
    Integer onsite_order_count = 0;
    Integer onsite_delivered_order_count = 0;
    Integer online_delivared_order_count = 0;
    Integer on_the_way = 0;
    Integer total_sale_onsite = 0;
    Integer total_cost_onsite = 0;
    Integer net_profit_onsite = 0;
    Integer net_loss_onsite = 0;
    Integer total_sale_online = 0;
    Integer total_cost_online = 0;
    Integer net_profit_online = 0;
    Integer net_loss_online = 0;
    public Manager(){
        loyalty= new HashMap<String,Integer>();
        menu=new Menu();
        index = 0;
        all_order_collection =new Order_collection();
        offline_order_collection =new Order_collection();
        online_order_collection =new Order_collection();

        all_sale_collection =new Sales_collection() ;
        offline_sale_collection =new Sales_collection() ;
        online_sale_collection =new Sales_collection() ;


    }
    public void take_offline_order(Integer id, Integer discount, String name, Map<Dish,Integer> dish_count){
        Offline_Customer customer=new Offline_Customer(id,discount);
        Order current_order=customer.order(dish_count,menu, name);
        all_order_collection.add_order(current_order);
        offline_order_collection.add_order(current_order);

        all_sale_collection.update(current_order.id,current_order.total,Boolean.FALSE);
        offline_sale_collection.update(current_order.id,current_order.total,Boolean.FALSE);

    }

    public void take_online_order(Integer id, Integer discount, String name, String adress, Map<Dish,Integer> dish_count){
        Online_Customer customer=new Online_Customer(id,discount,adress);
        Order current_order=customer.order(dish_count,menu, name);
        all_order_collection.add_order(current_order);
        online_order_collection.add_order(current_order);
        all_sale_collection.update(current_order.id,current_order.total,Boolean.FALSE);
        online_sale_collection.update(current_order.id,current_order.total,Boolean.FALSE);

    }

    public void update_order(Integer id){
        all_order_collection.update(id);
        System.out.println("all order collection updated");
    }


    private double profit(double a){
        return (a*(0.2));
    }
    public void calculate_sales_and_orders_insight(){
        today_sale = 0;
        today_order_count = 0;
        on_the_way = 0;
        total_sale_online = 0;
        total_sale_onsite = 0;
        total_cost_online = 0;
        total_cost_onsite = 0;
        net_profit_online = 0;
        net_profit_onsite = 0;
        net_loss_onsite = 0;
        net_loss_online = 0;
        onsite_order_count = 0;
        online_order_count = 0;
        online_delivared_order_count = 0;
        onsite_delivered_order_count = 0;
        for(int i =0; i < all_order_collection.all_orders.size(); i++){
            Order o = all_order_collection.all_orders.get(i);
            today_sale+= o.total;
            today_order_count++;
            if(o.getIsDelivered()==false)on_the_way++;
            else{
                if(o.getIsOnline()==true)online_delivared_order_count++;
                else onsite_delivered_order_count++;
            }
            if(o.getIsOnline()==true){
                total_sale_online+= o.total;
                online_order_count++;
            }
            else {
                total_sale_onsite+= o.total;
                onsite_order_count++;
            }
        }
        net_loss_onsite = net_loss_online = 0;
        net_profit_onsite = (int) profit((double)total_sale_onsite);
        net_profit_online = (int) profit((double) total_sale_online);
        total_cost_onsite = total_sale_onsite-net_profit_onsite;
        total_cost_online = total_sale_online-net_profit_online;
    }

    public void write_in_data(){
        new Unloader();
    }


}
