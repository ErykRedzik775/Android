package com.example.listakontaktow.data

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

object Contacts {

    val ITEMS: MutableList<ContactItem> = ArrayList()

    private val COUNT = 5

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addContact(createPlaceholderItem(i, ("123459987".toInt() + i).toString()))
        }
    }

    fun addContact(item: ContactItem) {
        ITEMS.add(item)
    }


    private fun createPlaceholderItem(position: Int, number: String): ContactItem {

        val year: Int = (1950..2022).random()
        val month: Int = (1..12).random()
        val day: Int = when(month){
            1,3,5,7,8,10,12 -> (1..31).random()
            4,6,9,11 -> (1..30).random()
            else -> (1..28).random()
        }

        val nameNumber: Int = (1..5).random()

        val name: String = when(nameNumber){
            1 -> "Ania"
            2 -> "Basia"
            3 -> "Cecylia"
            4 -> "Damian"
            5 -> "Marcin"
            else -> "Eryk"
        }

        val surnameNumber: Int = (1..5).random()
        val surname: String = when(surnameNumber){
//            when(name == '*a')
            1 -> "Nowakowski"
            2 -> "Kowalski"
            3 -> "Tyburski"
            4 -> "Piątek"
            5 -> "Ronaldo"
            else -> "Redzik"
        }

        val number: Int = (100000000..999999999).random()

        val image: Int = (1..15).random()

        return ContactItem(image, name, surname,
                day.toString() + "-" + month.toString() + "-" + year.toString(), number.toString())


    }


       private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    fun updateContacts(contactToEdit: ContactItem?, contactItem: ContactItem) {
        contactToEdit?.let { oldContact ->

            val indexOfOldContact = ITEMS.indexOf(oldContact)

            ITEMS.add(indexOfOldContact, contactItem)
            ITEMS.removeAt(indexOfOldContact+1)
        }
    }


}


data class ContactItem(val image: Int, val name: String, val surname: String, val birthday: String, val number: String) : Parcelable {

    constructor(parcel: Parcel) : this(

        parcel.readInt()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    //override fun toString(): String = title
    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeInt(image)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(birthday)
        parcel.writeString(number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContactItem> {
        override fun createFromParcel(parcel: Parcel): ContactItem {
            return ContactItem(parcel)
        }

        override fun newArray(size: Int): Array<ContactItem?> {
            return arrayOfNulls(size)
        }
    }

}