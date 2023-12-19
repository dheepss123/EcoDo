package com.bangkit.ecodo.di

import android.app.Application
import androidx.room.Room
import com.bangkit.ecodo.data.database.EcodoDatabase
import com.bangkit.ecodo.data.repository.TrashRepository
import com.bangkit.ecodo.data.retrofit.ApiService
import com.bangkit.ecodo.data.retrofit.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApi(): ApiService {
        return RetrofitFactory.makeRetrofitService()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Application): EcodoDatabase {
        return Room.databaseBuilder(
            context, EcodoDatabase::class.java, "ecodo_db",
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTrashRepository(apiService: ApiService, database: EcodoDatabase): TrashRepository {
        return TrashRepository(database.trashDao(), apiService)
    }
}