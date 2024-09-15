package com.example.xhvy.data.repositories

import com.example.xhvy.data.entities.FullTemplate
import com.example.xhvy.data.models.Template
import com.example.xhvy.data.models.WorkoutExercise
import com.example.xhvy.domain.daos.TemplateDAO
import kotlinx.coroutines.flow.Flow

class TemplateRepository(private val templateDAO: TemplateDAO) {
    suspend fun getTemplates(): Flow<List<FullTemplate>> {
        return templateDAO.getTemplates()
    }

    suspend fun insertTemplate(template: Template, workoutExercises: List<WorkoutExercise>) {
        templateDAO.insertTemplateTransaction(template, workoutExercises)
    }

    suspend fun deleteTemplateById(templateId: Int) {
        return templateDAO.deleteTemplateById(templateId)
    }
}