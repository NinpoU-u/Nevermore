package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Nevermore;
import com.mygdx.game.sprites.Bird;

public class PlayState extends State {
    private Bird bird;

    //создаем экземпляр птицы в конструкторе
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, Nevermore.WIDTH / 2, Nevermore.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        //передаем из класса bird текстуру + позицию
        sb.draw(bird.getBird(),bird.getPosition().x, bird.getPosition().y);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
