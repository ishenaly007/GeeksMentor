package com.abit8.geeksmentor.data.repository

import com.abit8.geeksmentor.data.base.BaseRepository
import com.abit8.geeksmentor.data.remote.RetrofitClient
import com.abit8.geeksmentor.data.remote.SearchApi
import com.abit8.geeksmentor.domain.model.Mentor
import com.abit8.geeksmentor.domain.repository.MentorRepository
import com.abit8.geeksmentor.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class MentorRepositoryImpl : BaseRepository(), MentorRepository {

    private val searchApi: SearchApi by lazy {
        RetrofitClient.create()
    }

    override fun getAndroidMentors() = doRequest {
        searchApi.getAndroidMentors().map { it.toMentor() }
    }

    override fun getBackEndMentors() = doRequest {
        searchApi.getBackEndMentors().map { it.toMentor() }
    }

    override fun getFrontEndMentors() = doRequest {
        searchApi.getFrontEndMentors().map { it.toMentor() }
    }

    override fun getUXUIMentors() = doRequest {
        searchApi.getUXUIMentors().map { it.toMentor() }
    }

    override fun getMentorsOfMonth() = doRequest {
        searchApi.getMentorsOfMonth().map { it.toMentor() }
    }

    override fun getMentorsBySearch(search: String): Flow<Resource<List<Mentor>>> = doRequest {
        searchApi.getMentorsBySearch(search).map { it.toMentor() }
    }
}