package com.os.system.di

import android.content.Context
import com.os.system.featureInstallation.data.network.ActivateServiceNetworkApiImpl
import com.os.system.featureInstallation.domain.network.ActivateServiceNetworkApi
import com.os.system.featureInstallation.domain.useCases.ActivateServiceUseCase
import com.os.system.featureInstallation.presentation.permissions.PermissionsViewModel
import com.os.system.network.api.TOSApi
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
    fun provideActivateServiceNetworkApi(tosApi: TOSApi): ActivateServiceNetworkApi {
        return ActivateServiceNetworkApiImpl(tosApi)
    }

    @Provides
    @Singleton
    fun provideActivateServiceUseCase(activateServiceNetworkApi: ActivateServiceNetworkApi): ActivateServiceUseCase {
        return ActivateServiceUseCase(activateServiceNetworkApi)
    }

    @Provides
    @Singleton
    fun providePermissionViewModel() : PermissionsViewModel {
        return PermissionsViewModel()
    }
}