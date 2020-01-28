package com.mashibing.design_pattern.abstract_factory;

public class AbstractFactoryTest {
    public static void main(String[] args) {


        AbstractFactory factory = new ModernFactory();

        Food food = factory.createFood();
        Movable go = factory.go();

        System.out.println(food);
        System.out.println(go);

        factory = new MagicFactory();

        Food food1 = factory.createFood();
        Movable go1 = factory.go();
    }
}
