package com.example.pc.day04.db.contract;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pc.day04.db.TeachOpenHelp;
import com.example.pc.day04.db.modul.Gamer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/2/14.
 */

public class DbManager {
    private  static SQLiteDatabase sqLiteDatabase;

    private  static SQLiteDatabase getDataBase(Context context){
        if(sqLiteDatabase==null) {
            TeachOpenHelp helper = new TeachOpenHelp(context);
          sqLiteDatabase= helper.getWritableDatabase();
        }
        return sqLiteDatabase;
    }


    public  static  boolean insert(Context context,String name,int age){
        /**
         * 1获取一个数据库对象
         * 2得到要插入的数据
         * 3.执行插入语句
         */
        SQLiteDatabase dataBase = getDataBase(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(TeachContract.Gamer.NAME,name);
        contentValues.put(TeachContract.Gamer.AGE,age);
        long insertResult = dataBase.insert(TeachContract.Gamer.TABLE_NAME, null,
                contentValues);


        return  insertResult!=-1;
    }
//    public  static
    public  static List<Gamer> queryGamer(Context context){
        /**
         * 1.获取数据库对象
         * 2.确定查询条件
         * 3.执行查询
         * 4.转换结果集
         */

        SQLiteDatabase dataBase = getDataBase(context);

        Cursor cursor = dataBase.query(TeachContract.Gamer.TABLE_NAME, null, null, null, null, null, null);
        List<Gamer> gamers=new ArrayList<>();
        while(cursor.moveToNext()) {
            //将每一条数据添加到数据集合中
            Gamer gamer=new Gamer();
            //设置gamer的各项值
            gamer.setName(cursor.getString(cursor.getColumnIndex(TeachContract.Gamer.NAME)));

            gamer.setAge(cursor.getInt(cursor.getColumnIndex(TeachContract.Gamer.AGE)));
            gamers.add(gamer);
        }


        return gamers;
}
    public  static  boolean deleteGamerByName(Context context,String name){
        /**
         * 1获取一个数据库对象
         * 2.确认删除条件
         * 3.执行删除
         */
        SQLiteDatabase dataBase = getDataBase(context);
        int deleteResult = dataBase.delete(TeachContract.Gamer.TABLE_NAME, TeachContract.Gamer.NAME + " = ? ", new String[]{name});


        return  0!=deleteResult;
    }
}
