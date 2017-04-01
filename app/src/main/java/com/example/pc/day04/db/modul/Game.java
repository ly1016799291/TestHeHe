package com.example.pc.day04.db.modul;

/**
 * Created by pc on 2017/2/14.
 */

public class Game {
    //foreign key p -->指的是对象嵌套的外部对象
    private int p_id;
    private String gameName;
    private String gameTime;
    private int _id;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }
}
