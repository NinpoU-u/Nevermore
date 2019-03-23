package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {

    //ширина трубы
    public static final int TUBE_WIDTH = 52;
    //добавим константу, это будет диапазон отклонений на котором буду создаватся трубы по высоте
    private static final int FLUCTUATION = 130;
    //харанит значение высоты просвета
    public static final int TUBE_GAP = 100;
    //хранит нижнию границу просвета
    public static final int LOWEST_OPENNING = 120;

    private Texture topTube, bottomTube;
    private Vector2 posToptube, posBottube;
    //Обьявляем обьект класса рандом, для создания трубы на разной высоте
    private Random rand;
    //создали прямоугольник вокруг нижней и верхней трубы.
    private Rectangle boundsTop, boundsBot;


    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosToptube() {
        return posToptube;
    }

    public Vector2 getPosBottube() {
        return posBottube;
    }

    public Tube(float x ){
        topTube = new Texture("wall.png");
        bottomTube = new Texture("wall.png");
        rand = new Random();
        //создаем трубы, верхняя труба создается по рандому, а нижняя всегда фиксированная высота, просвет всегда одинаков,
        // но будет находится на разной высоте
        posToptube = new Vector2(x,rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENNING);
        posBottube = new Vector2(x, posToptube.y - TUBE_GAP - bottomTube.getHeight());

        //в конструкте мы создаем данные обьекты(прямоугольники как обертка для турб)
        boundsTop = new Rectangle(posToptube.x, posToptube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBottube.x, posBottube.y, bottomTube.getWidth(), bottomTube.getHeight());

    }

    public void reposition(float x){
        posToptube.set(x,rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENNING);
        posBottube.set(x, posToptube.y - TUBE_GAP - bottomTube.getHeight());
        // в методе репозишн будем задавать их нчально расположение, совподабщее с расположением трую.
        boundsTop.setPosition(posToptube.x, posToptube.y);
        boundsBot.setPosition(posBottube.x, posBottube.y);
    }

    // метод для определениея столкновения птиц с трубами.
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
