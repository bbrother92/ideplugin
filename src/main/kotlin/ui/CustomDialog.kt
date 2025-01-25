package ui

import com.intellij.codeInsight.daemon.GutterMark
import com.intellij.openapi.ui.DialogWrapper
import org.jetbrains.annotations.Nullable
import javax.swing.*

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