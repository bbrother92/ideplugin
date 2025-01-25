package org.example.ideplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.ui.Messages

class PluginVersionAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val pluginId = PluginId.getId("org.jetbrains.kotlin")
        val plugin = pluginId?.let { com.intellij.ide.plugins.PluginManagerCore.getPlugin(it) }
        val version = plugin?.version ?: "Plugin is not installed."

        Messages.showMessageDialog(
            "Plugin Version: $version",
            "Plugin Version",
            null
        )
    }
}