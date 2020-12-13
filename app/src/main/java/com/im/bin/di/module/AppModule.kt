package com.im.bin.di.module

import android.app.Application
import android.content.Context
import com.im.bin.firebase.FirebaseSource
import com.im.bin.firebase.FirebaseSourceImpl
import com.im.bin.respository.DatabaseRepository
import com.im.bin.respository.DatabaseRepositoryImpl
import com.im.bin.respository.UserRepository
import com.im.bin.respository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
object AppModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideFirebaseSource(firebaseSourceImpl: FirebaseSourceImpl): FirebaseSource {
        return firebaseSourceImpl
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideDatabaseRepository(databaseRepositoryImpl: DatabaseRepositoryImpl): DatabaseRepository {
        return databaseRepositoryImpl
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository {
        return userRepositoryImpl
    }
}
