package com.abit8.geeksmentor.data.model

import com.abit8.geeksmentor.domain.model.Mentor

data class MentorModel(
    val hits: List<MentorDto>
)

data class MentorDto(
    val address: Address,
    val age: Int,
    val birthDate: String,
    val bloodGroup: String,
    val domain: String,
    val ein: String,
    val email: String,
    val eyeColor: String,
    val firstName: String,
    val gender: String,
    val height: Int,
    val id: Int,
    val image: String,
    val ip: String,
    val lastName: String,
    val macAddress: String,
    val maidenName: String,
    val password: String,
    val phone: String,
    val ssn: String,
    val university: String,
    val userAgent: String,
    val username: String,
    val weight: Double
) {
    fun toMentor(): Mentor {
        return Mentor(
            address = address,
            age = age,
            birthDate = birthDate,
            bloodGroup = bloodGroup,
            domain = domain,
            ein = ein,
            email = email,
            eyeColor = eyeColor,
            firstName = firstName,
            gender = gender,
            height = height,
            id = id,
            image = image,
            ip = ip,
            lastName = lastName,
            macAddress = macAddress,
            maidenName = maidenName,
            password = password,
            phone = phone,
            ssn = ssn,
            university = university,
            userAgent = userAgent,
            username = username,
            weight = weight
        )
    }
}