package com.abit8.geeksmentor.data.local.mapper

import com.abit8.geeksmentor.data.model.MentorEntity
import com.abit8.geeksmentor.domain.model.Mentor

fun Mentor.toMentorEntity() =
    MentorEntity(
        id = id,
        image = image,
        name = firstName,
        mentoredStudents = age,
        likes = age,
        dislikes = age
    )

fun MentorEntity.toMentor() =
    Mentor(
        id = id,
        image = image,
        firstName = name,
        age = mentoredStudents,
        height = likes,
        weight = dislikes.toDouble()
    )

//fun Mentor.toMentorEntity() = MentorEntity(id, image, name, mentoredStudents, likes, dislikes)
//fun MentorEntity.toMentor() = Mentor(id, image, name, mentoredStudents, likes, dislikes)