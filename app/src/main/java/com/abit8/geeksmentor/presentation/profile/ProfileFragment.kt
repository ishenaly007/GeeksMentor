package com.abit8.geeksmentor.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abit8.geeksmentor.databinding.FragmentMentorProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentMentorProfileBinding
    val navController = findNavController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.btnLanguage.setOnClickListener {
//            navController.navigate(R.id.mentors_profile_language_fragment)
//        }
//        binding.btnMyData.setOnClickListener {
//            navController.navigate(R.id.profile_data_fragment)
//        }
//        binding.btnBecomeMentor.setOnClickListener {
//            navController.navigate(R.id.become_mentor_fragment)
//        }
//        binding.btnEditMentorProfileInfo.setOnClickListener {
//            navController.navigate(R.id.edit_profile_info_fragment)
//        }
    }
}