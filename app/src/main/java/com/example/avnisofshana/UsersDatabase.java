package com.example.avnisofshana;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities= {Tavla.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {
    private static UsersDatabase instance;

    public abstract TavlaDao  tavlaDao();

    public static synchronized UsersDatabase getInstance(Context context){
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),UsersDatabase.class,"users_database").allowMainThreadQueries().build();
        }
        return instance;
    }
}
