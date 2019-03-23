package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Nevermore;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

public class PlayState extends State {

    //расстояние между трубами по ширине
    private static final int TUBE_SPACING = 125;
    //количество трую
    public static final int TUBE_COUNT = 4;
    //создаем константу для указания координаты y.
    public static final int GROUND_Y_OFFSET = -30;

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1,groundPos2;

    private Array <Tube> tubes;

    //создаем экземпляр птицы в конструкторе
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        bg = new Texture("newbg.jpg");
        //инициалзируем землю, так же создаем 2 вектора, для создания непрерывности, необходимо 2 текстуры, которые будут заменять друг - друга.
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        camera.setToOrtho(false, Nevermore.WIDTH / 2, Nevermore.HEIGHT / 2);

        tubes = new Array<Tube>();

        //создаем трубы до тех пор пока их количество не будет равно константе TUBE_COUNT.
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
        updateGround();
        bird.update(dt);
        //камера двигается вместе с птицой, что бы не улетела за экран
        camera.position.x = bird.getPosition().x + 80;

        //создание эффекта движения труб
        for (int i = 0; i < tubes.size; i++){

            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosToptube().x
                    + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosToptube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            //обрабатываем столкновениеЮ то есть при условии столкновения, мы перезапускаем игру.
            if (tube.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm));
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
                sb.draw(tube.getTopTube(), tube.getPosToptube().x, tube.getPosToptube().y);
                sb.draw(tube.getBottomTube(), tube.getPosBottube().x, tube.getPosBottube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);

        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes){
            tube.dispose();
        }
    }

    //добавление непрерывности нашим текстурам земли, затем вызываем метод в update() и dispose().
    private void updateGround(){
        //когда первая текстура полность выходит за экран, то она добовляется после 2 текстуры, то же самое для воторой текстуры.
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);

        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}
