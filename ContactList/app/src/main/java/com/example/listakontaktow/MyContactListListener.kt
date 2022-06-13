package com.example.listakontaktow

import java.text.FieldPosition

interface MyContactListListener {
    fun onItemClick(position: Int)
    fun onItemLongClick(position: Int)
}
