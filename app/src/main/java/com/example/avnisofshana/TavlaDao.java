package com.example.avnisofshana;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TavlaDao {
    @Insert
    void insert(Tavla t);

    @Update
    void update (Tavla t);

    @Delete
    void delete (Tavla t);

    @Query("SELECT * FROM tavla WHERE username = :username AND password = :password")
    Tavla login(String username, String password);

    @Query("SELECT*FROM tavla")
    List<Tavla>getAllInfo();

}
