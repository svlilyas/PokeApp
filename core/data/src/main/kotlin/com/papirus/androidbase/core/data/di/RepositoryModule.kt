package com.papirus.androidbase.core.data.di

import com.papirus.androidbase.core.data.client.MainClient
import com.papirus.androidbase.core.data.repository.MainRepository
import com.papirus.androidbase.core.database.db.AppDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        mainClient: MainClient,
        appDao: AppDao,
        ioDispatcher: CoroutineDispatcher
    ): MainRepository =
        MainRepository(mainClient = mainClient, appDb = appDao, ioDispatcher = ioDispatcher)
}
