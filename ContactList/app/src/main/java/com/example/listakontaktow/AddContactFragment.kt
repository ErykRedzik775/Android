package com.example.listakontaktow

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.listakontaktow.data.ContactItem
import com.example.listakontaktow.data.Contacts
import com.example.listakontaktow.databinding.FragmentAddContactBinding

class AddContactFragment : Fragment() {

    val args: AddContactFragmentArgs by navArgs()
    private lateinit var binding: FragmentAddContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener{ saveContact() }
        binding.addInputName.setText(args.contactToEdit?.name)
        binding.addInputSurname.setText(args.contactToEdit?.surname)
        binding.addInputBirthday.setText(args.contactToEdit?.birthday)
        binding.addInputNumber.setText(args.contactToEdit?.number)
        val tmp: Int = (0..15).random()
        binding.image.setImageResource(R.drawable.avatar_1 + args.contactToEdit?.image!!)



    }

    private fun saveContact() {
        var name: String = binding.addInputName.text.toString()
        var surname: String = binding.addInputSurname.text.toString()
        var birthday: String = binding.addInputBirthday.text.toString()
        var number: String = binding.addInputNumber.text.toString()

        if(name.isEmpty()) name = getString(R.string.default_contact_name) + "${Contacts.ITEMS.size + 1}"
        if(surname.isEmpty()) surname = getString(R.string.default_contact_surname) + "${Contacts.ITEMS.size + 1}"
        if(birthday.isEmpty()) birthday = "01-01-1900"
        if(number.isEmpty()) number = "775775775"

        val contactItem = ContactItem(
            ((Contacts.ITEMS.size + 1)%16),
            name,
            surname,
            birthday,
            number
        )

        if(!args.edit)
        {
            Contacts.addContact(contactItem)

        }
        else
        {
            Contacts.updateContacts(args.contactToEdit, contactItem)
        }

        val inputMethodManager: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)

        findNavController().popBackStack(R.id.contactFragment2, false)

    }


}