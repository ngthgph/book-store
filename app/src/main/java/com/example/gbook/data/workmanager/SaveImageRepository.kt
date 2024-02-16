package com.example.gbook.data.workmanager

import android.content.Context
import androidx.work.WorkInfo
import kotlinx.coroutines.flow.Flow

interface SaveImageRepository {
    val outputWorkInfo: Flow<WorkInfo>
    fun saveImage()
    fun cancelWork()
}

class WorkManagerSaveImageRepository(context: Context): SaveImageRepository {

    private var imageUri:
    override val outputWorkInfo: Flow<WorkInfo>
    override fun saveImage() {
        TODO("Not yet implemented")
    }

    override fun cancelWork() {
        TODO("Not yet implemented")
    }
}