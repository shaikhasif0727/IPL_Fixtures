package com.ipl_fixtures.di

import android.app.Application
import androidx.room.Room
import com.ipl_fixtures.data.local.IPLDao
import com.ipl_fixtures.data.local.IPLDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideIPLDatabase(app: Application): IPLDatabase{
        return Room.databaseBuilder(
            app,
            IPLDatabase::class.java,
            "ipldb.db"
        ).build()
    }

}