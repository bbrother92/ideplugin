package org.example.ideplugin

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
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import org.jetbrains.annotations.Nullable
import javax.swing.*


class DisplayGutterIconsAction : AnAction() {

    private val logger = Logger.getInstance(DisplayGutterIconsAction::class.java)

    override fun actionPerformed(event: AnActionEvent) {

        val project = event.project
        val file = event.getData(CommonDataKeys.VIRTUAL_FILE)

        if (project == null || file == null) {
            logger.warn("No file or project in the current context.")
            return
        }
        val editor = event.getData(PlatformDataKeys.EDITOR)
        val document: Document = editor?.document ?: return

        val gutterComponent = (editor as EditorImpl).gutterComponentEx
        val gutterMarksList = mutableListOf<GutterMark>()
        for (line in 0 until document.lineCount) {
            val renderers = gutterComponent.getGutterRenderers(line)
            for (renderer in renderers) {
                if (renderer is GutterMark) {
                    gutterMarksList.add(renderer)
                }
            }
        }

        displayGutterIcons(gutterMarksList, project)
    }

    private fun displayGutterIcons(gutterMarksList: MutableList<GutterMark>, project: Project) {
        if (gutterMarksList.isNotEmpty()) {
            CustomDialog(gutterMarksList).showAndGet()
        } else {
            Messages.showMessageDialog(
                project, "No gutter icons found.", "Collected Gutter Icons Dialog", AllIcons.General.Warning
            )
        }
    }
}

class CustomDialog(gutterMarksList: List<GutterMark>) : DialogWrapper(true) {

    private val panel = JPanel().apply {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        for (gutterMark in gutterMarksList) {
            val jlabel = JLabel(gutterMark.tooltipText ?: gutterMark.javaClass.name).apply {
                icon = gutterMark.icon
            }
            add(jlabel)
            add(JSeparator())
        }
        add(JLabel("Total count: ${gutterMarksList.size}"))

    }

    init {
        init()
        title = "Collected Gutter Icons"
        setOKButtonText("Good")
    }

    @Nullable
    override fun createCenterPanel(): JComponent {
        return panel
    }


}






