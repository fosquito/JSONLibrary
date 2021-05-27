
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.FileDialog
import java.io.File


interface Action {
    val name: String
    fun execute(window: Window)
}

class Edit : Action {
    override val name: String
        get() = "Edit"

    override fun execute(window: Window) {
        println("Edit")
    }
}

class Save : Action {
    override val name: String
        get() = "Save"

    override fun execute(window: Window) {
        val fileSave = FileDialog(window.shell, SWT.SAVE)
        fileSave.fileName = "json.txt"
        val filename = fileSave.open()
        File(filename).writeText(window.label.text)
    }
}