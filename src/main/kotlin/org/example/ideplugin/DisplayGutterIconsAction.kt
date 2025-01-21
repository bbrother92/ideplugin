package org.example.ideplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.editor.ex.EditorGutterComponentEx
import com.intellij.openapi.editor.ex.RangeHighlighterEx
import com.intellij.openapi.editor.impl.EditorImpl
import com.intellij.openapi.ui.getUserData
import com.intellij.util.CommonProcessors

/**
 * Action to display all gutter icons present in the currently opened file.
 */
class DisplayGutterIconsAction : AnAction() {

    private val logger = Logger.getInstance(DisplayGutterIconsAction::class.java)

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        val file = e.getData(com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE)

        if (project == null || file == null) {
            logger.warn("No project or file found in the current context.")
            return
        }

        logger.warn("Action triggered for file: ${file.name}")
        displayGutterIcons(project, file, e)
    }

    private fun displayGutterIcons(project: Project, file: VirtualFile, event: AnActionEvent) {

        val editor2 = event.getRequiredData(CommonDataKeys.EDITOR);


//EditorGutterComponentImpl
        val gutterComponent = (editor2 as EditorImpl).gutterComponentEx

//        gutterComponent.getUserData()
        val gutterIcons = mutableListOf<String>()
//        val gutterComponent = editor2.gutter as? EditorGutterComponentEx




        logger.warn("gutter:  ${editor2.gutter.toString()}")


//        val psiFile = PsiManager.getInstance(project).findFile(file) ?: return
//        val editor = com.intellij.openapi.fileEditor.FileEditorManager.getInstance(project)
//            .allEditors
//            .filterIsInstance<com.intellij.openapi.fileEditor.TextEditor>()
//            .firstOrNull { it.editor.document == psiFile.viewProvider.document }
//            ?.editor
//
//        if (editor == null) {
//            Messages.showMessageDialog(
//                project,
//                "No editor found for '${file.name}'.",
//                "Gutter Icons",
//                null
//            )
//            return
//        }
//
//        val gutterIcons = mutableListOf<String>()
//
//        // Traverse all visible highlighters and fetch gutter icons
//        editor.markupModel.allHighlighters.forEach { highlighter ->
//            val gutterRenderer = highlighter.gutterIconRenderer
//            if (gutterRenderer != null) {
//                gutterIcons.add(gutterRenderer.toString())
//            }
//        }


        // Display the collected gutter icons
        if (gutterIcons.isNotEmpty()) {
            val iconDescriptions = gutterIcons.joinToString(separator = "\n")
            Messages.showMessageDialog(
                project,
                "Gutter Icons in '${file.name}':\n$iconDescriptions",
                "Gutter Icons",
                null
            )
        } else {
            Messages.showMessageDialog(
                project,
                "No gutter icons found in '${file.name}'.",
                "Gutter Icons",
                null
            )
        }
    }


}