package com.example.pc.day04;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pc.day04.db.TeachOpenHelp;
import com.example.pc.day04.db.contract.DbManager;
import com.example.pc.day04.db.contract.TeachContract;
import com.example.pc.day04.db.modul.Game;
import com.example.pc.day04.db.modul.Gamer;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库本身是支持添加和删除字段的,但是SQLite支持并不完善,需要开发者自己动手解决这个问题
 *      1.
 * 一个表中如果外键没有指明的话,外键允许重复
 *测试
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  initView();
        getData();

    }

    private void getData() {
        /**
         * 查询数据
         *  1.获取数据库对象
         *  2.确定查询条件
         *  3.执行查询操作
         *  4.转换
         */
        List<Gamer> gamers=new ArrayList<>();
        TeachOpenHelp helper = new TeachOpenHelp(this);
        SQLiteDatabase database = helper.getReadableDatabase();
        //查询Gamer表中的数据
        Cursor cursor = database.query(TeachContract.Gamer.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            //继续去Game表中查询 查询条件是P_id=Gamer.id
            String selection= TeachContract.Game.P_ID+" = ? ";
            Cursor query = database.query(TeachContract.Game.TABLE_NAME, null, selection,
                    new String[]{cursor.getInt(cursor.getColumnIndex(TeachContract.Gamer._ID))+""}, null, null, null);
            if (query.getCount()>0) {
               // Log.e(TAG, "getData: ---------------->"+query.getCount() );
                Gamer gamer = new Gamer();
                gamer.setName(cursor.getString(cursor.getColumnIndex(TeachContract.Gamer.NAME)));
                gamer.setAge(cursor.getInt(cursor.getColumnIndex(TeachContract.Gamer.AGE)));


                Game game = new Game();
                gamer.setGame(game);
                while (query.moveToNext()){
                    game.setGameName(query.getString(query.getColumnIndex(TeachContract.Game.GAME_NAME)));
                    game.setGameTime(query.getString(query.getColumnIndex(TeachContract.Game.GAME_TIME)));
                    gamers.add(gamer);
                }
//                if (query.moveToFirst()) {
//                    game.setGameName(query.getString(query.getColumnIndex(TeachContract.Game.GAME_NAME)));
//                    game.setGameTime(query.getString(query.getColumnIndex(TeachContract.Game.GAME_TIME)));
//
//                }

                query.close();
            //    gamers.add(gamer);
            }

        }
        cursor.close();
        //成功拿到List<Gamer>数据集
        Log.e(TAG, "getData: -------------->"+gamers.size() );
    }

    private void initView() {
//        for (int i = 0; i < 10; i++) {
//            boolean insert = DbManager.insert(this, "今天是个不错的日子" + i, i);
//            if (insert) {
//                Log.e(TAG, "initView: 数据插入成功");
//            }
//        }


//        List<Gamer> gamers = DbManager.queryGamer(this);
////        Log.e(TAG, "initView: "+gamers.size() );
//        for (int i = 0; i <gamers.size() ; i++) {
//            Log.e(TAG, "initView: "+gamers.get(i).getName() );
//            Log.e(TAG, "initView: "+gamers.get(i).getAge() );
//
//
//        }


        TeachOpenHelp helper = new TeachOpenHelp(this);
        SQLiteDatabase database = helper.getWritableDatabase();
        //插入数据
        ContentValues contentValues = new ContentValues();
        contentValues.put(TeachContract.Gamer.NAME,"Tom");
        contentValues.put(TeachContract.Gamer.AGE,18);
        long insertResult = database.insert(TeachContract.Gamer.TABLE_NAME, null, contentValues);
        if (insertResult!=-1) {
            //插入game数据
            String selection = TeachContract.Gamer.NAME + "= ? and " + TeachContract.Gamer.AGE+" = ?";
            Cursor cursor = database.query(TeachContract.Gamer.TABLE_NAME, null, selection, new String[]{"ROCK", "18"}, null, null, null);
            int pId=1;
            if (cursor.moveToFirst()) {
                pId = cursor.getInt(cursor.getColumnIndex(TeachContract.Gamer._ID));

            }
            cursor.close();

            ContentValues values = new ContentValues();
            values.put(TeachContract.Game.P_ID,pId);
            values.put(TeachContract.Game.GAME_NAME,"Cs");
            values.put(TeachContract.Game.GAME_TIME,"1995");
            long insert = database.insert(TeachContract.Game.TABLE_NAME, null, values);
            if (insert!=-1) {
                Log.e(TAG, "initView: 数据插入成功" );
            }
        }


    }


}
