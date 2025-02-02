package org.example.ideplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Messages
import java.awt.Component


class SelectedUIComponentAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project
        val editor = event.getData(PlatformDataKeys.EDITOR)
        val component = event.getData(PlatformDataKeys.CONTEXT_COMPONENT)
        val toolWindowName = event.getData(PlatformDataKeys.TOOL_WINDOW)?.id

        val info = when {
            editor != null -> getEditorInfo(editor, event)
            component != null -> getComponentInfo(component, toolWindowName)
            else -> "No UI component is currently selected."
        }

        Messages.showInfoMessage(project, info, "UI Component Information")
    }

    private fun getEditorInfo(editor: Editor, event: AnActionEvent): String {
        val fileName = event.getData(CommonDataKeys.VIRTUAL_FILE)?.name
        val caretPosition = editor.caretModel.logicalPosition

        return """
        Editor Information:
        File: $fileName
        Caret: Line ${caretPosition.line + 1}, Column ${caretPosition.column + 1}
    """.trimMargin()
    }

    private fun getComponentInfo(component: Component, toolWindowName: String?): String {
        val componentClass = component.javaClass.name
        val componentName = component.name ?: "Not Available (null) name"
        val toolWindowId = toolWindowName ?: "Not Available (null) tool window ID"

        return """
        UI Component Information:
        Class: $componentClass
        Name: $componentName
        Tool Window ID: $toolWindowId
    """.trimMargin()
    }
}