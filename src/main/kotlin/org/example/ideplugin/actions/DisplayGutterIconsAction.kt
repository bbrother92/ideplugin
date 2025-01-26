package org.example.ideplugin.actions

import com.intellij.codeInsight.daemon.GutterMark
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.impl.EditorImpl
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import ui.CustomDialog


class DisplayGutterIconsAction : AnAction() {

    private val logger = Logger.getInstance(DisplayGutterIconsAction::class.java)

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project
        val file = event.getData(CommonDataKeys.VIRTUAL_FILE)

        if (project == null || file == null) {
            logger.warn("No file or project in the current context.")
            return
        }

        val editor = event.getData(PlatformDataKeys.EDITOR) ?: run {
            logger.warn("No editor in the current context.")
            return
        }

        val document: Document = editor.document
        val gutterComponent = (editor as EditorImpl).gutterComponentEx
        val gutterMarksList = mutableListOf<GutterMark>()

        for (line in 0 until document.lineCount) {
            val renderers = gutterComponent.getGutterRenderers(line)
            gutterMarksList.addAll(renderers.filterIsInstance<GutterMark>())
        }

        displayGutterIcons(gutterMarksList, project)
    }

    private fun displayGutterIcons(gutterMarksList: List<GutterMark>, project: Project) {
        if (gutterMarksList.isNotEmpty()) {
            CustomDialog(gutterMarksList).showAndGet()
        } else {
            Messages.showMessageDialog(
                project, "No gutter icons found.", "Collected Gutter Icons Dialog", AllIcons.General.Warning
            )
        }
    }
}






