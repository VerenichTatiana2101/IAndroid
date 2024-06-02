package com.example.mvvmpattern.data

import com.example.mvvmpattern.entity.UserInfo
import kotlinx.coroutines.delay
import javax.inject.Inject

/*
* класс реализует логику хранения пользователей в БД,
* чтобы эти данные можно было использовать в других частях приложения
* без лишнего похода в сеть

 */
class LocalStorageRepository @Inject constructor(){

    /*
    * этот репозиторий должен работать с API для работы с БД(Room),
    * сохранять информацию полученную из userInfo,
    * либо возвращая какой-то результат либо просто заканчивая свою работу
    */
    suspend fun saveUserInfo(userInfo: UserInfo) {
        delay(3000)
    }
}