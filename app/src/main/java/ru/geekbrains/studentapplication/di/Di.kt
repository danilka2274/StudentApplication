package ru.geekbrains.studentapplication.di

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import ru.geekbrains.studentapplication.data.repository.RepositoryImpl
import ru.geekbrains.studentapplication.data.repository.datasource.DataSource
import ru.geekbrains.studentapplication.data.repository.datasource.MockDataSourceImpl
import ru.geekbrains.studentapplication.domain.repository.Repository
import ru.geekbrains.studentapplication.domain.usecases.GetExamUseCase
import ru.geekbrains.studentapplication.domain.usecases.GetHomeWorkUseCase
import ru.geekbrains.studentapplication.domain.usecases.GetLessonUseCase
import ru.geekbrains.studentapplication.ui.classes.ClassesViewModel
import ru.geekbrains.studentapplication.ui.favourite.FavouriteViewModel
import ru.geekbrains.studentapplication.ui.home.HomeViewModel
import ru.geekbrains.studentapplication.ui.list.ListViewModel

object Di {

    fun viewModelModule() = module {
        viewModel() {
            ClassesViewModel(
                getLessonUseCase = get()
            )
        }

        viewModel() {
            FavouriteViewModel()
        }

        viewModel() {
            HomeViewModel(
                getLessonUseCase = get(),
                getHomeWorkUseCase = get(),
                getExamUseCase = get()
            )
        }

        viewModel() {
            ListViewModel()
        }
    }

    fun useCasesModule() = module {
        factory<GetLessonUseCase> {
            GetLessonUseCase(repository = get())
        }
        factory<GetHomeWorkUseCase> {
            GetHomeWorkUseCase(repository = get())
        }
        factory<GetExamUseCase> {
            GetExamUseCase(repository = get())
        }
    }

    fun repositoryModule() = module {
        single<Repository>() {
            RepositoryImpl(
                dataSource = get()
            )
        }

        single<DataSource> {
            MockDataSourceImpl()
        }
    }
}