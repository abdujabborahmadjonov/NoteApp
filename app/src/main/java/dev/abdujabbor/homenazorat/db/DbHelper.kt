package dev.abdujabbor.homenazorat.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dev.abdujabbor.homenazorat.models.MyData

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DBNAME, null, DBVERSION){
    companion object {
        const val DBNAME = "myCosntacts"
        const val DBVERSION = 1

        const val TABLE_NAME = "table_contacts"
        const val NOTE_ID = "id"
        const val NOTE_NOTE = "note"
        const val NOTE_DATE = "date"
        const val NOTE_DONE = "done"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "create table $TABLE_NAME($NOTE_ID integer not null primary key autoincrement unique,$NOTE_NOTE text not null,$NOTE_DATE text not null,$NOTE_DONE numeric not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //not yet implemented
    }

     fun addNote(note: MyData, context: Context) {
        val database = this.writableDatabase
        val contentValue = ContentValues()

        contentValue.put(NOTE_NOTE, note.note)
        contentValue.put(NOTE_DATE, note.date)
        contentValue.put(NOTE_DONE, note.done)

        database.insert(TABLE_NAME, null, contentValue)

        database.close()
    }

     fun readNote(): List<MyData> {
        val database = this.readableDatabase
        val query = "select * from $TABLE_NAME"
        val cursor = database.rawQuery(query, null)
        var list = ArrayList<MyData>()
        if (cursor.moveToFirst()) {
            do {
                val note = MyData(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )
                list.add(note)
            } while (cursor.moveToNext())
        }
        return list
    }
}