package com.abit8.geeksmentor.presentation.mentorprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abit8.geeksmentor.R
import com.abit8.geeksmentor.SplashActivity
import com.abit8.geeksmentor.databinding.FragmentMentorProfileBinding

class MentorProfileFragment : Fragment() {

    private lateinit var binding: FragmentMentorProfileBinding

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

        binding.btnBecomeMentor.setOnClickListener {
            findNavController().navigate(R.id.becomeMentorFragment)
        }

        binding.btnEditMentorProfileInfo.setOnClickListener {
            findNavController().navigate(R.id.edit_mentor_profile_info_fragment)
        }

        binding.btnMyData.setOnClickListener {
            findNavController().navigate(R.id.mentors_profile_data_fragment)
        }

        binding.btnLanguage.setOnClickListener {
            findNavController().navigate(R.id.mentors_profile_language_fragment)
        }

        binding.btnExitMentorProfile.setOnClickListener {
            //показать кастомизированный алерт диалог с проверкой на желание выйти
            requireActivity().finish()
            SplashActivity().finish()
        }
    }
}