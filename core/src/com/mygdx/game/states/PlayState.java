package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Nevermore;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

public class PlayState extends State {
    private Bird bird;
    private Texture bg;
    private Tube tube;

    //создаем экземпляр птицы в конструкторе
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        bg = new Texture("bg.png");
        camera.setToOrtho(false, Nevermore.WIDTH / 2, Nevermore.HEIGHT / 2);
        tube = new Tube(100);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        //передаем из класса bird текстуру + позицию
        sb.draw(bird.getBird(),bird.getPosition().x, bird.getPosition().y);
        sb.draw(tube.getTopTube(), tube.getPosBottube().x, tube.getPosToptube().y);
        sb.draw(tube.getBottomTube(), tube.getPosBottube().x, tube.getPosBottube().y);


        sb.end();

    }

    @Override
    public void dispose() {

    }
}
