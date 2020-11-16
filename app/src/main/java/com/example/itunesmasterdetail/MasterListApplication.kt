package com.example.itunesmasterdetail

import android.app.Application
import android.content.Context
import com.example.githubuser.network.ConnectivityInterceptor
import com.example.githubuser.network.ConnectivityInterceptorImpl
import com.example.githubuser.network.LimitExceedInterceptorImpl
import com.example.itunesmasterdetail.database.MasterListRoomDatabase
import com.example.itunesmasterdetail.repository.SearchResultRepository
import com.example.itunesmasterdetail.repository.SearchResultRepositoryImpl
import com.example.itunesmasterdetail.service.Service
import com.example.itunesmasterdetail.service.ServiceDataSource
import com.example.itunesmasterdetail.service.ServiceDataSourceImpl
import com.example.itunesmasterdetail.utils.Constants
import com.example.itunesmasterdetail.view.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MasterListApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MasterListApplication))

          bind() from singleton { MasterListRoomDatabase(instance()) }
          bind() from singleton { instance<MasterListRoomDatabase>().movieListDao() }
          bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
          bind() from singleton { Service(instance()) }
          bind<ServiceDataSource>() with singleton { ServiceDataSourceImpl(instance()) }
          bind<SearchResultRepository>() with singleton { SearchResultRepositoryImpl(instance(), instance()) }
          bind() from provider { HomeViewModelFactory(instance()) }
          bind() from provider { SaveDataPreference(getSharedPreferences(Constants.SHARED_PREFERENCE_FILENAME, MODE_PRIVATE))}

    }

}