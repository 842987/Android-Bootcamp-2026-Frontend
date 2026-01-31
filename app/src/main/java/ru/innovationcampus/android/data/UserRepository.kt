package ru.innovationcampus.android.data

import ru.innovationcampus.android.data.source.UserInfoDataSource
import ru.innovationcampus.android.domain.entities.UserEntity

class UserRepository(
    private val userInfoDataSource: UserInfoDataSource
) {
    suspend fun getUsers(): Result<List<UserEntity>> {
        return userInfoDataSource.getUser().map { listDto ->
            listDto.mapNotNull { userDto ->
                UserEntity(
                    name = userDto.name ?: return@mapNotNull null,
                    photoUrl = userDto.photoUrl ?: return@mapNotNull null,
                    email = userDto.email ?: return@mapNotNull null,
                )
            }
        }
    }
}