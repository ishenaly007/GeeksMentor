package com.abit8.geeksmentor.ui.mentorprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abit8.geeksmentor.R
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

        binding.btnNotification.setOnClickListener{
            val mentorsNotification = MentorsNotificationFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.mentors_notification_fragment, mentorsNotification)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.btnEditMentorProfileInfo.setOnClickListener{
            val editMentorProfile = EditMentorProfileFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.edit_mentor_profile_info_fragment, editMentorProfile)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.btnMyData.setOnClickListener{
            val mentorProfileData = EditMentorProfileFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.mentors_profile_data_fragment, mentorProfileData)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.btnLanguage.setOnClickListener{
            val language = LanguageFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.mentors_profile_language_fragment, language)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.btnExitMentorProfile.setOnClickListener{
        //показать кастомизированный алерт диалог с проверкой на желание выйти
        }
    }
}