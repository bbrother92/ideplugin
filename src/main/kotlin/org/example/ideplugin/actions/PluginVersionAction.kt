package org.example.ideplugin.actions

import com.intellij.icons.AllIcons
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.ui.Messages

class PluginVersionAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val pluginId = PluginId.getId("org.example.ideplugin")
        val plugin = PluginManagerCore.getPlugin(pluginId)
        val version = plugin?.version ?: "Plugin is not installed."

        Messages.showMessageDialog(
            e.project,
            "Plugin Version: $version",
            "Plugin Version",
            AllIcons.Plugins.PluginLogo
        )
    }
}