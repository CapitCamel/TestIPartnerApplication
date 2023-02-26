package com.example.testipartnerapplication.di

import com.example.testipartnerapplication.data.repository.SearchRepositoryImpl
import com.example.testipartnerapplication.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMovieRepository(repository: SearchRepositoryImpl): SearchRepository
}