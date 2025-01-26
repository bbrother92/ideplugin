package ui

import com.intellij.codeInsight.daemon.GutterMark
import com.intellij.openapi.ui.DialogWrapper
import javax.swing.*

class CustomDialog(private val gutterMarksList: List<GutterMark>) : DialogWrapper(false) {

    init {
        title = "Collected Gutter Icons"
        setOKButtonText("Good")
        init()
    }

    override fun createCenterPanel(): JComponent? {
        return JPanel().apply {
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
    }
}