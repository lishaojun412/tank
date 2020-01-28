package com.mashibing.design_pattern.abstract_factory;

public class MagicFactory extends AbstractFactory {
    @Override
    Food createFood() {
        return new Mushroom();
    }

    @Override
    Movable go() {
        return new Bloom();
    }
}
