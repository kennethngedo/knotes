package com.knotes.app.di

import com.knotes.app.BuildConfig
import com.knotes.app.R
import com.knotes.app.data.repositories.notes.LocalNotesRepository
import com.knotes.app.data.repositories.notes.NotesRepository
import com.knotes.app.data.repositories.notes.OnlineNotesRepository
import com.knotes.app.ui.notes.NotesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun providesBaseUrl() = BuildConfig.BASE_URL

    @Provides
    fun provideNotesRepository(): NotesRepository {
        return when (BuildConfig.BUILD_TYPE) {
            "mock" -> LocalNotesRepository()
            else -> OnlineNotesRepository()
        }
    }

    @Provides
    fun provideNotesViewModel(notesRepository: NotesRepository) = NotesViewModel(notesRepository)
}