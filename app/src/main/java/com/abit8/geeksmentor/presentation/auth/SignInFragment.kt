package com.abit8.geeksmentor.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.abit8.geeksmentor.R
import com.abit8.geeksmentor.data.repository.LoginRepositoryImpl
import com.abit8.geeksmentor.data.viewmodel.LoginViewModel
import com.abit8.geeksmentor.data.viewmodel.SharedViewModel
import com.abit8.geeksmentor.data.viewmodel.LoginViewModelFactory
import com.abit8.geeksmentor.databinding.FragmentSignInBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        val repository = LoginRepositoryImpl()
        val loginViewModelFactory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        binding.btnEnterMain.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.login(
                    username = binding.etLoginMain.text.toString(),
                    password = binding.etPasswordMain.text.toString()
                )
            }
        }

        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                val response = result.getOrNull()
                if (response != null) {
                    sharedViewModel.setFirstName(response.firstName)
                    sharedViewModel.setLastName(response.lastName)
                    sharedViewModel.setImageProfile(response.image)
                }
                Toast.makeText(
                    requireContext(),
                    "Добро пожаловать ${viewModel.firstName.value}!",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.navigation_home)

            } else {
                Toast.makeText(
                    requireContext(),
                    "Неправильные данные, повторите попытку!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }

    private fun initClicks() {
        binding.apply {
            forgotPasswordMain.setOnClickListener {
                findNavController().navigate(R.id.passwordForgetFragment)
            }
            tvRegisterAccount.setOnClickListener {
                findNavController().navigate(R.id.logInFragment)
            }
        }
    }
}


