package com.example.listakontaktow

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.listakontaktow.data.ContactItem
import com.example.listakontaktow.data.Contacts

import com.example.listakontaktow.databinding.FragmentItemBinding
import com.google.android.material.snackbar.Snackbar


class MyContactRecyclerViewAdapter(
    private val context: FragmentActivity?,
    private val values: List<ContactItem>,
    private val eventListener: MyContactListListener
) : RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder>(), DeleteDialogFragment.OnDeleteDialogInteractionListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val tmp: Int = (0..15).random()
        holder.contentView.text = item.name + " " + item.surname
        holder.imageView.setImageResource(R.drawable.avatar_1 + tmp)

        holder.itemContainer.setOnClickListener{
            eventListener.onItemClick(position)
        }
        holder.itemContainer.setOnLongClickListener{
            eventListener.onItemLongClick(position)
            return@setOnLongClickListener true
        }
        holder.deleteButton.setOnClickListener{
            showDeleteDialog(position)
        }
    }

    private fun showDeleteDialog(position: Int) {
        val deleteDialog = DeleteDialogFragment.newInstance(Contacts.ITEMS.get(position).name + " " + Contacts.ITEMS.get(position).surname, position,this)
        context?.let { deleteDialog.show(it.supportFragmentManager, "DeleteDialog") }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val imageView: ImageView = binding.itemImg
        val contentView: TextView = binding.name
        val itemContainer: View = binding.root
        val deleteButton: ImageButton= binding.contactDelete

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    override fun onDialogPositiveClick(pos: Int?) {
        Contacts.ITEMS.removeAt(pos!!)
        notifyDataSetChanged()
    }

    override fun onDialogNegativeClick(pos: Int?) {

    }



}