package com.abit8.geeksmentor.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abit8.geeksmentor.R
import com.abit8.geeksmentor.databinding.FragmentLogInBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }

    private fun initClicks() {
        binding.apply {
            arrowSignup.setOnClickListener {
                findNavController().navigateUp()
            }
            goToSignInFragment.setOnClickListener {
                findNavController().navigate(R.id.signInFragment)
            }
            btnRegistrationSignup.setOnClickListener {
                findNavController().navigate(R.id.logInFragment)
            }
        }
    }
}