package com.abit8.geeksmentor.presentation.onboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.abit8.geeksmentor.databinding.ItemOnboardBinding
import com.abit8.geeksmentor.data.model.OnBoard

@Suppress("DEPRECATION")
class OnBoardAdapter(
    private val viewPager: ViewPager2,
    private val onClick: () -> Unit,
    private val onLoginClickListener: () -> Unit,
    private val onRegisterClickListener: () -> Unit
) : Adapter<OnBoardAdapter.OnBoardViewHolder>() {

    private val onboardScreens = arrayListOf(
        OnBoard("Найди ментора мечты или стань им"),
        OnBoard("Только лучшие ментора"),
        OnBoard("")
    )

    inner class OnBoardViewHolder(private val binding: ItemOnboardBinding) : ViewHolder(binding.root){
        fun onBind(onBoard: OnBoard) {
            binding.firstTitle.text = onBoard.title
            binding.secondTitle.text = onBoard.title
            val frameLayout1 = binding.onboardFirst
            val frameLayout2 = binding.onboardSecond
            val frameLayout3 = binding.onboardThird
            when(position){
                0 -> {
                    frameLayout1.visibility = View.VISIBLE
                    frameLayout2.visibility = View.GONE
                    frameLayout3.visibility = View.GONE
                }
                1 -> {
                    frameLayout1.visibility = View.GONE
                    frameLayout2.visibility = View.VISIBLE
                    frameLayout3.visibility = View.GONE
                }
                2 -> {
                    frameLayout1.visibility = View.GONE
                    frameLayout2.visibility = View.GONE
                    frameLayout3.visibility = View.VISIBLE
                }
            }
            with(binding) {
                btnBoarding1.setOnClickListener {
                    viewPager.currentItem = adapterPosition + 1
                }
                btnBoarding2.setOnClickListener {
                    viewPager.currentItem = adapterPosition + 1
                }
                arrowBoard2.setOnClickListener {
                    viewPager.currentItem = adapterPosition - 1
                }
                arrowBoard3.setOnClickListener {
                    viewPager.currentItem = adapterPosition - 1
                }
                btnBoarding3.setOnClickListener {
                    onClick()
                }
                enterBoard3.setOnClickListener {
                    onLoginClickListener()
                }
                registerBoard3.setOnClickListener {
                    onRegisterClickListener()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        return OnBoardViewHolder(ItemOnboardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.onBind(onboardScreens[position])
    }

    override fun getItemCount() = onboardScreens.size
}