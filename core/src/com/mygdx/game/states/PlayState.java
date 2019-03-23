package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Nevermore;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture bg;

    private Array <Tube> tubes;

    //создаем экземпляр птицы в конструкторе
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        bg = new Texture("bg.png");
        camera.setToOrtho(false, Nevermore.WIDTH / 2, Nevermore.HEIGHT / 2);

        tubes = new Array<Tube>();

        for (int i = 0; i < TUBE_COUNT ; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
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
        //камера двигается вместе с птицой, что бы не улетела за экран
        camera.position.x = bird.getPosition().x + 80;

        for (Tube tube : tubes ){
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosToptube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosToptube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        //передаем из класса bird текстуру + позицию
        sb.draw(bird.getBird(),bird.getPosition().x, bird.getPosition().y);
        //прорисовка трую в цыкле.
            for (Tube tube : tubes){
                //Отрисовываем топ и боттом трубы.
                sb.draw(tube.getTopTube(), tube.getPosBottube().x, tube.getPosToptube().y);
                sb.draw(tube.getBottomTube(), tube.getPosBottube().x, tube.getPosBottube().y);
        }
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
