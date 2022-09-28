package spring.demo.shampoos.dao;

import spring.demo.shampoos.models.Ingredient;

import java.util.List;

public interface CustomRepository {

    public List<Ingredient> findAllWithEvenId();

    public void updateEvenIdPrice(double price);
}
