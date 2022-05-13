package com.screening.app.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.screening.app.featureCallScreening.data.dataSource.AppDatabase
import com.screening.app.featureCallScreening.data.repository.PhoneNumberRepositoryImpl
import com.screening.app.featureCallScreening.domain.repository.PhoneNumberRepository
import com.screening.app.featureCallScreening.domain.useCase.*
import com.screening.app.featureImportContacts.data.repository.GetContactsRepositoryImpl
import com.screening.app.featureImportContacts.domain.repository.GetContactsRepository
import com.screening.app.featureInstallation.data.firebase.DeviceActivationFirebaseApiImpl
import com.screening.app.featureInstallation.data.network.ActivateServiceNetworkApiImpl
import com.screening.app.featureInstallation.domain.firebase.DeviceActivationFirebaseApi
import com.screening.app.featureInstallation.domain.network.ActivateServiceNetworkApi
import com.screening.app.featureInstallation.domain.useCases.ActivateServiceUseCase
import com.screening.app.featureInstallation.domain.useCases.DeviceActivationUseCase
import com.screening.app.featureInstallation.presentation.permissions.MainViewModel
import com.screening.app.featureSmsScreening.data.repository.ContactRepositoryImpl
import com.screening.app.featureSmsScreening.domain.repository.ContactRepository
import com.screening.app.featureSmsScreening.domain.useCase.*
import com.screening.app.network.api.TOSApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideContactsRepository(database: AppDatabase): ContactRepository {
        return ContactRepositoryImpl(database.contactsDao)
    }

    @Provides
    @Singleton
    fun providePhoneNumberRepository(database: AppDatabase): PhoneNumberRepository {
        return PhoneNumberRepositoryImpl(database.phoneNumberDao)
    }

    @Provides
    @Singleton
    fun provideContactRepository(@ApplicationContext context: Context): GetContactsRepository {
        return GetContactsRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun providesPhoneNumbersUseCases(repository: PhoneNumberRepository): PhoneNumberUseCases {
        return PhoneNumberUseCases(
            getPhoneNumbersUseCase = GetPhoneNumbersUseCase(repository),
            getPhoneNumberByPhoneNumber = GetPhoneNumberByPhoneNumber(repository),
            insertPhoneNumberUseCase = InsertPhoneNumberUseCase(repository),
            deletePhoneNumberUseCase = DeletePhoneNumberUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideContactUseCases(
        repository: ContactRepository
    ): ContactUseCases {
        return ContactUseCases(
            getContactUseCase = GetContactUseCase(repository),
            insertContactsUseCase = InsertContactsUseCase(repository),
            insertContactUseCase = InsertContactUseCase(repository),
            deleteContactUseCase = DeleteContactUseCase(repository),
            selectContactUseCase = SelectContactUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideFirebaseFireStoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideActivateServiceNetworkApi(tosApi: TOSApi): ActivateServiceNetworkApi {
        return ActivateServiceNetworkApiImpl(tosApi)
    }

    @Provides
    @Singleton
    fun provideDeviceActivationFirebaseApi(
        @ApplicationContext context: Context,
        firebaseFirestore: FirebaseFirestore
    ): DeviceActivationFirebaseApi {
        return DeviceActivationFirebaseApiImpl(context, firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideActivateServiceUseCase(activateServiceNetworkApi: ActivateServiceNetworkApi): ActivateServiceUseCase {
        return ActivateServiceUseCase(activateServiceNetworkApi)
    }

    @Provides
    @Singleton
    fun provideDeviceActivationUseCase(deviceActivationFirebaseApi: DeviceActivationFirebaseApi): DeviceActivationUseCase {
        return DeviceActivationUseCase(deviceActivationFirebaseApi)
    }

    @Provides
    @Singleton
    fun providePermissionViewModel(@ApplicationContext applicationContext: Context): MainViewModel {
        return MainViewModel(applicationContext)
    }
}