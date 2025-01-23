package org.example.ideplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Messages
import java.awt.Component


class SelectedUIComponentAction : AnAction("Display Selected UI") {
    override fun actionPerformed(event: AnActionEvent) {

        val project = event.project
        val editor = event.getData(PlatformDataKeys.EDITOR)
        val component = event.getData(PlatformDataKeys.CONTEXT_COMPONENT)
        val d2 = event.getData(PlatformDataKeys.SELECTED_ITEMS)
        val d3 = event.getData(PlatformDataKeys.TOOL_WINDOW)
        val dd = event.getData(PlatformDataKeys.STATUS_BAR)
        val d4 = event.getData(PlatformDataKeys.PREDEFINED_TEXT)
        val d5 = event.getData(PlatformDataKeys.PSI_ELEMENT)
        // services psi null d4 null;   terminal d3 - yes d3d4d5 null;

        // Collect information
        val info = when {
            editor != null -> getEditorInfo(editor, event)
            component != null -> getComponentInfo(component)
            else -> "No UI component is currently selected."
        }

        Messages.showInfoMessage(project, info, "UI Component Information")
    }

    private fun getEditorInfo(editor: Editor, event: AnActionEvent): String {
        val file = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE)
        val file2 = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.PSI_FILE)

        return """
            Editor Information:
            File: ${file?.name}  ${file2?.name}
            Caret: Line ${editor.caretModel.logicalPosition.line + 1}, Column ${editor.caretModel.logicalPosition.column + 1}
        """.trimMargin()
    }

    private fun getComponentInfo(component: Component): String {
        val componentClass = component.javaClass.name
        val componentName = component.name
        return """
            UI Component Information:
            Class: $componentClass
            Name: $componentName
        """.trimMargin()
    }
}