package ru.eskendarov;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import lombok.Getter;

@Getter
class Resources {

    private Texture ownShipImage = new Texture(Gdx.files.internal("textures/gunship.png"));
    private Texture enemyShipImage = new Texture(Gdx.files.internal("textures/raptor.png"));
    private Texture starImage = new Texture(Gdx.files.internal("textures/star.png"));
    private Texture backgroundImage = new Texture(Gdx.files.internal("textures/space.jpg"));
    private Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/Ship_Loop.wav"));
    private Sound newGameSound = Gdx.audio.newSound(Gdx.files.internal("sounds/new_game.wav"));
    private Sound blasterSound = Gdx.audio.newSound(Gdx.files.internal("sounds/blaster3.wav"));

    void initSound() {
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(2f);
        backgroundMusic.play();
        System.out.println("init sounds");
    }

    void dispose() {
        backgroundImage.dispose();
        backgroundMusic.dispose();
        blasterSound.dispose();
        ownShipImage.dispose();
        starImage.dispose();
        enemyShipImage.dispose();
        System.out.println("resources dispose");
    }

}