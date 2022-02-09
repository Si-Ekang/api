package categories.handlers

import categories.models.TranslationOfCategory
import categories.models.getTranslationsOfCategory
import common.Csv
import common.HandlerContext
import common.getIdFromPath
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import translations.models.getTranslation

suspend fun HandlerContext.getTranslationsOfCategory(): Unit = getIdFromPath()
    .let { Csv getTranslationsOfCategory it }
    .takeIf(Set<TranslationOfCategory>::isNotEmpty)
    ?.mapNotNull { Csv getTranslation it.translationId }
    ?.let {
        if (it.isNotEmpty()) call.respond(HttpStatusCode.OK, it)
        else error("No translations available for this category!")
    } ?: call.respond(HttpStatusCode.NotFound)
