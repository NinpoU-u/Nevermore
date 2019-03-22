package com.mygdx.game.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    //Подкласс класса веткор, который реализует простой механизм, типа "последний зашел, первый выешл"
    private Stack <State> states;

    public GameStateManager(){
        //создает пустой стек
        states = new Stack<State>();
    }

    //помещает элемент в вершину стека
    public void push(State state){
         states.push(state);
    }
    //в методе поп освобождаем ресурсы
    public void pop(){
        states.pop().dispose();
    }
    //Который удаляет из стека верзний экран, очищать ресурсы и помещать след. экран в вершину стека.
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }
    //возвращает верхний элемент не удаляя его из стека
    //обновляет игру и только то игровое состояние которое находится на вершине стека
    public void update(float dt){
        states.peek().update(dt);
    }

    //принимает состояние из верхней части стека, остовляеть его там и отрисовывать
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }



 }
