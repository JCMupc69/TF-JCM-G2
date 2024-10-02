package com.example.starwaska.di

import com.example.starwaska.dao.RepositorioCat
import com.example.starwaska.dao.RepositorioProd
import android.content.Context
import com.example.starwaska.dao.DAO
import com.example.starwaska.dao.DataBaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabaseHelper(@ApplicationContext context: Context): DataBaseHelper {
        return DataBaseHelper(context)
    }

    @Provides
    fun provideDAO(dataBaseHelper: DataBaseHelper): DAO {
        return DAO(dataBaseHelper)
    }

    @Provides
    fun provideRepositorioCat(dao: DAO): RepositorioCat {
        return RepositorioCat(dao)
    }

    @Provides
    fun provideRepositorioProd(dao: DAO): RepositorioProd {
        return RepositorioProd(dao)
    }

}