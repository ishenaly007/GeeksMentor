package com.abit8.geeksmentor.presentation.mentorlists

//import com.abit8.geeksmentor.data.viewmodel.MentorListViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.abit8.geeksmentor.data.model.MentorList
import com.abit8.geeksmentor.data.remote.retrofit.ApiService
import com.abit8.geeksmentor.databinding.FragmentMentorListBinding
import com.abit8.geeksmentor.presentation.mentorlists.adapter.MentorListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MentorListFragment : Fragment() {
    private lateinit var binding: FragmentMentorListBinding

    //private lateinit var viewModel: MentorListViewModel
    private lateinit var adapter: MentorListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMentorListBinding.inflate(inflater, container, false)
        /*val apiService = RetrofitClient.apiService
        val repository = MentorsRepositoryImpl(apiService)
        val viewModelFactory = MentorsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MentorListViewModel::class.java)*/

        adapter = MentorListAdapter()
        binding.rvAllMentors.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllMentors.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainApi = retrofit.create(ApiService::class.java)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val response = mainApi.getAllMentors()
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val productList: List<MentorList>? = apiResponse?.products as? List<MentorList>
                    productList // полученный список продуктов
                } else {
                    // Обработка ошибки, если запрос не был успешным
                }
            }?.let { productList ->
                val mentorList = productList as List<MentorList>
                requireActivity().runOnUiThread {
                    adapter.submitList(mentorList)
                }
            }


        }



        /*viewModel.mentors.observe(viewLifecycleOwner) { mentors ->
            // Обновление списка менторов в адаптере

        }
        viewModel.loadMentors()*/
    }
}