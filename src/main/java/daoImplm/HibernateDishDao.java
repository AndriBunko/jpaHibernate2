package daoImplm;

import dao.DishDao;
import entitys.Dish;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 09.09.2017.
 */
public class HibernateDishDao implements DishDao{
    private EntityManager em;

    public HibernateDishDao(EntityManager em) {
        this.em = em;
    }

    public void addDishToMenu(Dish dish) {
        em.getTransaction().begin();
        try {
            em.persist(dish);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
        System.out.println(dish.getDishName() + " add to nemu");

    }

    public void addDishToMenu(List<Dish> dishes) {
        em.getTransaction().begin();

            try {
                for (Dish d : dishes)
                    em.persist(d);

                em.getTransaction().commit();
            }catch (Exception e){
                em.getTransaction().rollback();

        }
        System.out.println( "Dishes add to nemu");
    }

    public List<Dish> getDishByPrice(int a, int b) {
        Query query = em.createQuery("select d from Dish d where d.price between " + a + " and " + b);
        List<Dish> dishes = query.getResultList();
        return dishes;
    }

    public List<Dish> getDishWhithDiscount() {
        Query query = em.createQuery("select d from Dish d where d.discount = true");
        return query.getResultList();
    }

    public List<Dish> getDishByWeight() {
        Query query = em.createQuery("select d from Dish d");
        List<Dish> dishes = query.getResultList();
        List<Dish> result = new ArrayList<Dish>();
        int weight = 0;
        for (Dish d : dishes) {
            weight += d.getWeight();
            if (weight <= 1000)
                result.add(d);
            else weight -= d.getWeight();
        }
        return result;
    }

    public void close(){
        em.close();
    }
}
