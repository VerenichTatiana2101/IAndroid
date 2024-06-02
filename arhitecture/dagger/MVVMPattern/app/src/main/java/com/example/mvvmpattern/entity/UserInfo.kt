package com.example.mvvmpattern.entity

/*
класс для хранения сощности представления информации о пользователе
сущность может быть интерфейсом - один из возмежных вариантов
 */
interface UserInfo {
    val name: String
    val surname: String
    val phone: String
    val email: String
    val bio: String
}