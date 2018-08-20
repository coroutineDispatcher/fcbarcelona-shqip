package com.stavro_xhardha.fcbarcelonashqip.di.component

import android.app.Application
import com.stavro_xhardha.fcbarcelonashqip.FCBarcelonaApp
import com.stavro_xhardha.fcbarcelonashqip.di.builder.ActivityBuilder
import com.stavro_xhardha.fcbarcelonashqip.di.module.FcBarcelonaAppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (FcBarcelonaAppModule::class), (ActivityBuilder::class)])
interface FCBarcelonaAppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): FCBarcelonaAppComponent
    }

    fun inject(fcbarcelonaApp: FCBarcelonaApp)
}