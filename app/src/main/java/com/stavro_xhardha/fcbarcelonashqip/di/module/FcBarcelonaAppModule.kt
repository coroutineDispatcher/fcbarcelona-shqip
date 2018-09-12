package com.stavro_xhardha.fcbarcelonashqip.di.module

import android.app.Application
import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.crashlytics.android.Crashlytics
import com.stavro_xhardha.fcbarcelonashqip.brain.SchedulerProvider
import com.stavro_xhardha.fcbarcelonashqip.network.ApiHelper
import com.stavro_xhardha.fcbarcelonashqip.network.AppApiHelper
import dagger.Module
import dagger.Provides
import io.fabric.sdk.android.Fabric
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class FcBarcelonaAppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

    @Provides
    @Singleton
    internal fun provideCrushLytics(): Fabric = Fabric.with(provideContext(application = Application()), Crashlytics())

    @Provides
    internal fun provideApiHelper(apiHelper: AppApiHelper): ApiHelper = apiHelper

}