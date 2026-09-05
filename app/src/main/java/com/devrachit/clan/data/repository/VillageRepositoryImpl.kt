package com.devrachit.clan.data.repository

import android.content.Context
import com.devrachit.clan.domain.repository.VillageRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of [VillageRepository] that saves the JSON string to internal storage.
 */
@Singleton
class VillageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : VillageRepository {

    private val fileName = "village_data.json"

    override suspend fun saveVillageJson(json: String) {
        withContext(Dispatchers.IO) {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(json.toByteArray())
            }
        }
    }

    override suspend fun getVillageJson(): String? {
        return withContext(Dispatchers.IO) {
            val file = File(context.filesDir, fileName)
            if (file.exists()) {
                file.readText()
            } else {
                null
            }
        }
    }
}
