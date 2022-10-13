package com.ipl_fixtures.di

import com.ipl_fixtures.domain.repository.FixturesRepository
import com.ipl_fixtures.data.repository.FixturesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
   abstract fun bindFixtureRepository(
       fixturesRepositoryImpl: FixturesRepositoryImpl
   ): FixturesRepository
}