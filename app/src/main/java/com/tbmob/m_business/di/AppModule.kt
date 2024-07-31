package com.tbmob.m_business.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.tbmob.m_business.core.AppPreferences
import com.tbmob.m_business.data.local.MBusinessDatabase
import com.tbmob.m_business.data.repository.MBusinessRepository
import com.tbmob.m_business.data.repository.ProductRepositoryImpl
import com.tbmob.m_business.data.repository.SalesRepositoryImpl
import com.tbmob.m_business.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Extension property for DataStore
private val Context.dataStore by preferencesDataStore(name = "app_preferences")

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) = context.dataStore

    @Provides
    @Singleton
    fun provideAppPreferences(dataStore: DataStore<Preferences>): AppPreferences {
        return AppPreferences(dataStore)
    }

    @Provides
    @Singleton
    fun provideMBusinessDatabase(app: Application): MBusinessDatabase {
        return Room.databaseBuilder(
            app,
            MBusinessDatabase::class.java,
            MBusinessDatabase.NAME
        )
            .fallbackToDestructiveMigrationFrom()
            .build()
    }

    @Provides
    @Singleton
    fun provideMBusinessRepository(db: MBusinessDatabase): MBusinessRepository {
        return MBusinessRepository(
            productRepository = ProductRepositoryImpl(db.productDao),
            userRepository = UserRepositoryImpl(db.userDao),
            salesRepository = SalesRepositoryImpl(db.salesDao)
        )
    }
}