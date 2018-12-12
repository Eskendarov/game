package ru.eskendarov;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import lombok.Getter;

@Getter
public class Resources {

    private final Texture ownShipImage = new Texture(Gdx.files.internal("textures/gunship.png"));
    private final Texture enemyShipImage1 = new Texture(Gdx.files.internal("textures/raptor.png"));
    private final Texture enemyShipImage2 = new Texture(Gdx.files.internal("textures/attackCruiser.png"));
    private final Texture starImage = new Texture(Gdx.files.internal("textures/star.png"));
    private final Texture backgroundImage = new Texture(Gdx.files.internal("textures/space.jpg"));
    private final Texture bulletImage = new Texture(Gdx.files.internal("textures/laser.png"));

    private final Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/Ship_Loop.wav"));
    private final Sound blasterSound = Gdx.audio.newSound(Gdx.files.internal("sounds/blaster3.wav"));
    private final Sound enemyDetectSound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemy-detected.wav"));

    public void initSound() {
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(2f);
        backgroundMusic.play();
        System.out.println("init background sounds");
    }

    public void dispose() {
        backgroundImage.dispose();
        backgroundMusic.dispose();
        blasterSound.dispose();
        enemyDetectSound.dispose();
        ownShipImage.dispose();
        starImage.dispose();
        enemyShipImage1.dispose();
        enemyShipImage2.dispose();
        System.out.println("resources dispose");
    }

}