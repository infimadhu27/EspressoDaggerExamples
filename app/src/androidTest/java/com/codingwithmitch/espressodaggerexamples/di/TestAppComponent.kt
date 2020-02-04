package com.codingwithmitch.espressodaggerexamples.di

import android.app.Application
import com.codingwithmitch.espressodaggerexamples.TestBaseApplication
import com.codingwithmitch.espressodaggerexamples.ui.ListFragmentTest
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Singleton
@Component(modules = [
    FragmentModule::class,
    ViewModelModule::class,
    AppModule::class,
    FakeDataModule::class
])
interface TestAppComponent: AppComponent {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): TestAppComponent
    }

    fun inject(listFragmentTest: ListFragmentTest)

}













