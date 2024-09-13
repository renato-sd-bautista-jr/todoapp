package com.example.todoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth


class signupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var auth:FirebaseAuth
    private lateinit var navControl:NavController
    private lateinit var binding:FragmentSignupBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      binding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()

    }
    private fun init (view: View){
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()

    }
    private fun registerEvents(){

        binding.authTextView.setOnClickListener{
            navControl.navigate(R.id.action_signupFragment_to_signinFragment)
        }

        binding.nextBtn.setOnClickListener{
            val email = binding.emailEt.text.toString().trim()
            val pass = binding.passEt.text.toString().trim()


            if (email.isNotEmpty() && pass.isNotEmpty())

                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(
                        {
                            if (it.isSuccessful){
                                Toast.makeText(context,"Registered Successfully",Toast.LENGTH_SHORT).show()
                                navControl.navigate(R.id.action_signupFragment_to_homeFragment)

                            }else{
                                Toast.makeText(context,it.exception?.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                    )



        }
    }



}