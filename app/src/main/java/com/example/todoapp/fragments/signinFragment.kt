package com.example.todoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentSigninBinding
import com.example.todoapp.databinding.FragmentSignupBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth





class signinFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navControl: NavController
    private lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using ViewBinding
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()

    }

    private fun init(view: View) {
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
        registerEvents()

    }

    private fun registerEvents() {

        binding.authTextView.setOnClickListener {
            navControl.navigate(R.id.action_signinFragment_to_signupFragment)
        }

        binding.nextBtn.setOnClickListener {
            val email = binding.emailEt.text.toString().trim()
            val pass = binding.passEt.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty())

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(
                    OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Logged in Successfully", Toast.LENGTH_SHORT)
                                .show()
                            // Navigate to the home screen after successful login
                            navControl.navigate(R.id.action_signinFragment_to_homeFragment)
                        } else {
                            // Show an error message
                            Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                )
            else {
                Toast.makeText(context, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}




