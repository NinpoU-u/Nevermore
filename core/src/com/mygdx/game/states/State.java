package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    //Мы храним в объекте State ссылку на GameStateManager,
    // чтобы с помощью менеджера поменять текущее состояние на другое, так же как мы делаем в MenuState для перехода в PlayState﻿

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    //Инициализируем обьекты в конструкторе
    public State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    //обновляет картинку какое-то количество раз
    public abstract void update(float db);
    //класс Спрайт батч предоставляет текстуру и координаты для рисования фигур
    public abstract void render(SpriteBatch sb);
    //уничтожает экземпляр класса, когда он уже не нужен
    public abstract void dispose();
}
