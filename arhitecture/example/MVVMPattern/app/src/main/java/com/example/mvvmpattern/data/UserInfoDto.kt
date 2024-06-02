package com.example.mvvmpattern.data

import com.example.mvvmpattern.entity.UserInfo

/*
класс, в котором будет дисериализация json
получаемая от сервисв
 */
class UserInfoDto(
    override val name: String,
    override val surname: String,
    override val phone: String,
    override val email: String,
    override val bio: String
): UserInfo