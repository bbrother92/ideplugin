package org.example.ideplugin

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.impl.DaemonCodeAnalyzerEx
import com.intellij.codeInsight.daemon.impl.DaemonCodeAnalyzerImpl
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.impl.EditorImpl
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import java.util.function.Supplier


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

    val editor = event.getRequiredData(CommonDataKeys.EDITOR);



//EditorGutterComponentImpl
//        LineMarkerGutterIconRenderer
//        GutterIconRenderer.getAccessibleName()
        //gutterIconRenderer
        // LineMarkerInfo.LineMarkerGutterIconRenderer

        val daemonCodeAnalyzer = DaemonCodeAnalyzerEx.getInstanceEx(project)
        logger.warn("daemonCodeAnalyzer: ${daemonCodeAnalyzer.fileStatusMap}")

        val lineMarkers = DaemonCodeAnalyzerImpl.getLineMarkers(editor.getDocument(), project)


//        for (marker in lineMarkers) {
//            if (marker is LineMarkerInfo<*>) {
//                val renderer = marker.createGutterRenderer()
//                if (renderer is LineMarkerInfo.LineMarkerGutterIconRenderer<*>) {
//                    renderers.add(renderer)
//                }
//            }
//        }

        if (lineMarkers.isNotEmpty()) {
            for (lineMarkerInfo in lineMarkers) {
                val icon = lineMarkerInfo.icon
//                 val test =  lineMarkerInfo.LineMarkerGutterIconRenderer
//                val accessibleNameProvider: Supplier<String>? = lineMarkerInfo.getAccessibleNameProvider()

                Messages.showMessageDialog(
                    project, "Gutter Icons in '${file.name}':\n${lineMarkerInfo.element.toString()}", "Gutter Icons", icon
                )
            }
        } else {
            Messages.showMessageDialog(
                project, "No gutter icons found in '${file.name}'.", "Gutter Icons", null
            )
        }

val tet = "Тhе Рrоgrеssivе Rоаd tо Rеаding\n"
//        val psiFile = PsiManager.getInstance(project).findFile(file) ?: return
//        val editor = com.intellij.openapi.fileEditor.FileEditorManager.getInstance(project)
//            .allEditors
//            .filterIsInstance<com.intellij.openapi.fileEditor.TextEditor>()
//            .firstOrNull { it.editor.document == psiFile.viewProvider.document }
//            ?.editor
//

    }


}