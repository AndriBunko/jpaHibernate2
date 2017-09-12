import dao.DishDao;
import daoImplm.HibernateDishDao;
import entitys.Dish;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Andrew on 09.09.2017.
 */
public class Runner {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAHibernate2");
        EntityManager em = emf.createEntityManager();

        DishDao dishDao = new HibernateDishDao(em);
        Dish dish = new Dish("Borshch", 75, 300, false);
        Dish dish1 = new Dish("Borshch2", 100, 600, false);
        Dish dish2 = new Dish("Sup", 75, 320, false);
        Dish dish3 = new Dish("Salat", 175, 50, true);

        dishDao.addDishToMenu(dish);
        dishDao.addDishToMenu(dish1);
        dishDao.addDishToMenu(dish2);
        dishDao.addDishToMenu(dish3);

        List<Dish> dishes = dishDao.getDishByPrice(70, 150);
        List<Dish> dishesWhithDiscount = dishDao.getDishWhithDiscount();
        System.out.println(dishDao.getDishByWeight());


        dishDao.close();
        emf.close();
    }
}
