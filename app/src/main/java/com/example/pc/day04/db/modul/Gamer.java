package com.example.pc.day04.db.modul;

/**
 * Created by pc on 2017/2/14.
 */

public class Gamer {
    private  int _id;
    private int age;
    private  String name;
    private  Game game;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
