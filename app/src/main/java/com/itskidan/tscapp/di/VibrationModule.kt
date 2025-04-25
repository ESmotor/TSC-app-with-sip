package com.itskidan.tscapp.di

import com.itskidan.domain.vibration.VibrationController
import com.itskidan.tscapp.platform.vibration.VibrationControllerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class VibrationModule {

    @Binds
    abstract fun bindVibrationController(
        impl: VibrationControllerImpl
    ): VibrationController
}