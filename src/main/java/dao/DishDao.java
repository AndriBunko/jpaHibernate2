package dao;

import entitys.Dish;


import javax.persistence.Query;
import java.util.List;

/**
 * Created by Andrew on 09.09.2017.
 */
public interface DishDao {
    void addDishToMenu(Dish dish);
    List<Dish> getDishByPrice(int a, int b);
    List<Dish> getDishWhithDiscount();
    List<Dish> getDishByWeight();
    void close();
}
