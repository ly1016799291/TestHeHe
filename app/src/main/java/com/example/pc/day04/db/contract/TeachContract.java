package com.example.pc.day04.db.contract;

import android.provider.BaseColumns;

public class TeachContract {
    public static final String DATTABASE_NAME="teach.db";
    public static final int DATABASE_VERSION =1;
    //用来维护Gamer表中数据
    public static  class  Gamer implements BaseColumns{
        public  static  final  String TABLE_NAME="Gamer";
        public static final String NAME="name";
        public static final String  AGE="age";
        public static final String  GAMENAME="gamename";
    }
    //用来维护Game字段
    public  static class Game implements BaseColumns{
        public static final String TABLE_NAME="Game";
        public static final String GAME_NAME="gameName";
        public static final String GAME_TIME="gameTime";
        public static final String P_ID="p_id";
    }

}
