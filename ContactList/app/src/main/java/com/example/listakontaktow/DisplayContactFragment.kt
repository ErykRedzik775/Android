package com.example.listakontaktow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.listakontaktow.data.ContactItem
import com.example.listakontaktow.databinding.FragmentDisplayContactBinding

class DisplayContactFragment : Fragment() {

    val args: DisplayContactFragmentArgs by navArgs()
    lateinit var  binding: FragmentDisplayContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDisplayContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contact: ContactItem = args.contact
        binding.displayName.text = contact.name
        binding.displaySurName.text = contact.surname
        binding.displayNumber.text = contact.number
        binding.displayBirthday.init(
            contact.birthday.split("-")[2].toInt(),
            contact.birthday.split("-")[1].toInt(),
            contact.birthday.split("-")[0].toInt()){
            view, year, month, day ->
        }
        binding.displayBirthday.isEnabled = false
        binding.displayNumber.text = contact.number
        binding
        val tmp: Int = (0..15).random()
        binding.imageView.setImageResource(R.drawable.avatar_1 + tmp)


        binding.displayEdit.setOnClickListener{
            val contactToEdit =
                DisplayContactFragmentDirections.actionDisplayContactFragmentToAddContactFragment(
                        contactToEdit = contact,
                        edit = true)

            findNavController().navigate(contactToEdit)
        }
    }

}