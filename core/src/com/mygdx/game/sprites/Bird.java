package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    public static final int  MOVEMENT = 120;
    public static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velosity;
    private Texture bird;
    //создали прямоугольник вокруг птицы
    private Rectangle bounds;

    //Создаем птицу, задаеи направление движения и текстуру.
    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velosity = new Vector3(0,0,0);
        bird = new Texture("bird.png");
        bounds = new Rectangle(x,y, bird.getWidth(), bird.getHeight());
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }

    public void update(float dt){
        if (position.y > 0)
            //Добавлени к константы гравити к координате y, переменной велосити(ускорение)
            velosity.add(0, GRAVITY,0);
        // затем умножаем вектор сокрости на скаляр промежутка времени дельта тайм(время)
        velosity.scl(dt);

        //затем добавляем новое значение координаты y к положению птици на экране и повторяем метод скэил для вектора скорости велосити
        position.add(MOVEMENT * dt, velosity.y,0);
        if (position.y < 0)
            position.y = 0;

        velosity.scl(1/dt);
        //таким образом скорость падения величивается со временем
        //задаем позицию для прямоугольника
        bounds.setPosition(position.x, position.y);
    }

    public void jump(){
        velosity.y = 250;
    }

    //создаем геттер для доступа к прямоугольнику из другого класса
    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose() {
        bird.dispose();
    }
}
