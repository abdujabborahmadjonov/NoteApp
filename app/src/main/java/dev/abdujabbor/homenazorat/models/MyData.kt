package dev.abdujabbor.homenazorat.models

import java.text.SimpleDateFormat
import java.util.*

class MyData {
    var id:Int? = null
    var note:String? = null
    var date = SimpleDateFormat("dd.MM.yyyy  HH:mm:ss").format(Date())
    var done:String? = null





    constructor(id: Int?, note: String?, date: String?, done: String?) {
        this.id = id
        this.note = note
        this.date = date
        this.done = done
    }


    constructor( note: String?, date: String?, done: String?) {
        this.note = note
        this.date = date
        this.done = done
    }

    constructor( note: String?, done: String?) {
        this.note = note
        this.done = done
    }




}