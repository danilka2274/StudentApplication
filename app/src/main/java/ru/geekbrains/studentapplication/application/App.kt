package ru.geekbrains.studentapplication.application

import android.app.Application
import ru.geekbrains.studentapplication.di.Di
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author Borisov Andrey on 03.07.2022
 **/
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    Di.viewModelModule(),
                    Di.useCasesModule(),
                    Di.repositoryModule()
                )
            )
        }
    }
}