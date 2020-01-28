package com.mashibing.design_pattern.abstract_factory;

public class ModernFactory extends AbstractFactory {


    @Override
    Food createFood() {
        return new Bread();
    }

    @Override
    Movable go() {
        return new Plane();
    }
}
