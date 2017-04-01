package com.example.pc.day04.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.pc.day04.db.contract.TeachContract;


/**
 * Created by pc on 2017/2/14.
 */

public class TeachOpenHelp extends SQLiteOpenHelper {
    
    public static final String CREATE_TABLE_GAMER="create table "+TeachContract.Gamer.TABLE_NAME+" ( "+TeachContract.Gamer._ID+
            " integer primary key autoincrement,"+TeachContract.Gamer.NAME+
            "  varchar(16), "+TeachContract.Gamer.AGE+" int" +
         //   ", "+TeachContract.Gamer.GAMENAME + " varchar(16)"+
           ");";
public static final String CREATE_TABLE_GAME =  "create table "+ TeachContract.Game.TABLE_NAME+" ("
        + TeachContract.Game._ID+" integer primary key autoincrement,"
        + TeachContract.Game.GAME_NAME+" varchar(32),"
        + TeachContract.Game.GAME_TIME+" varchar(16),"
        + TeachContract.Game.P_ID +" integer,foreign key("+ TeachContract.Game.P_ID+") " +
        "references "+ TeachContract.Gamer.TABLE_NAME+"("+TeachContract.Gamer._ID+"));";

    public static final String CREATE_TABLE_TEMP="create table tempTable ( "+TeachContract.Gamer._ID+
            " integer primary key autoincrement,"+TeachContract.Gamer.NAME+
            "  varchar(16), "+TeachContract.Gamer.AGE+" int, "+TeachContract.Gamer.GAMENAME +
            " varchar(16) );";
    private static final String TAG =TeachOpenHelp.class.getSimpleName() ;

    public TeachOpenHelp(Context context) {
        super(context, TeachContract.DATTABASE_NAME,null,TeachContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate: "+CREATE_TABLE_GAMER );
        db.execSQL(CREATE_TABLE_GAMER);
        db.execSQL(CREATE_TABLE_GAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Log.e(TAG, "onUpgrade: " );
//        //如果不需要旧数据的话,我们可以这样做
//        db.execSQL("drop table "+ TeachContract.Gamer.TABLE_NAME);
//        //直接删除旧表
//        Log.e(TAG, "onUpgrade: " );
//        onCreate(db);
//        // 再创建新表

        /**
         * 在数据库升级的时候,要保留原数据,
         * 1.将升级的表备份,创建一个新表,数据也一样
         * 2.删除原表
         * 3.创建新表,并将数据返回
         *
         * 数据库中的事务
         *      事务可以保证事务中的流程完全执行或者完全不执行
         */
        db.beginTransaction();
        //1
        db.execSQL(CREATE_TABLE_TEMP);
        db.execSQL("insert into tempTable select * from Gamer;");
        //2
        db.execSQL("drop table "+ TeachContract.Gamer.TABLE_NAME);
        //3
        db.execSQL(CREATE_TABLE_GAMER);
        db.execSQL("insert into Gamer (\"name\",\"age\") select name,age from tempTable;");

        db.setTransactionSuccessful();
        db.endTransaction();

    }
}
