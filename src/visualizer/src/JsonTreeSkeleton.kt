import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*

data class Dummy(val number: Int)

class JsonTreeSkeleton(var json: Json) {
    private val shell: Shell = Shell(Display.getDefault())
    val tree: Tree

    init {
        shell.text = "File tree skeleton"
        shell.layout = GridLayout(2,true)

        tree = Tree(shell, SWT.SINGLE or SWT.BORDER)

        fun TreeConstructor(j: JsonValue, dad: TreeItem? = null){
            var a: TreeItem = if(dad == null)
                TreeItem(tree, SWT.NONE)
            else
                TreeItem(dad, SWT.NONE)
            when (j) {
                is JsonArray -> {
                    a.text = "(array)"
                    j.value.forEach {
                       TreeConstructor(it, a)
                    }
                }
                is JsonString -> a.text = "\""+j.value+"\""
                is JsonNull -> a.text = "null"
                is JsonNumber -> a.text = j.value.toString()
                is JsonBoolean -> a.text = j.value.toString()
                is JsonObject -> {
                    a.text = "(object)"
                    j.jsonObject.forEach {
                        TreeConstructor(it, a)
                    }
                }
                is JsonMap -> {
                    a.text = j.getKey().replace("\"", "")
                    when(val v = j.value){
                        is JsonArray -> TreeConstructor(v, a)
                        is JsonString -> a.text += ": \""+v.value+"\""
                        is JsonNull -> a.text += ": null"
                        is JsonNumber -> a.text += ": "+v.value.toString()
                        is JsonBoolean -> a.text += ": "+v.value.toString()
                        is JsonObject -> TreeConstructor(v, a)
                        is JsonMap -> TreeConstructor(v, a)
                    }

                }
            }
        }

        TreeConstructor(json.json)

        tree.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                println("selected: " + tree.selection.first().data)
            }
        })

        val label = Label(shell, SWT.NONE)
        label.text = "skeleton"

        val button = Button(shell, SWT.PUSH)
        button.text = "depth"
        button.addSelectionListener(object: SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                val item = tree.selection.first()
                label.text = item.depth().toString()
            }
        })
    }

    // auxiliar para profundidade do nó
    fun TreeItem.depth(): Int =
        if(parentItem == null) 0
        else 1 + parentItem.depth()


    fun open() {
        //... popular a arvore com TreeItem
        tree.expandAll()
        shell.pack()
        shell.open()
        val display = Display.getDefault()
        while (!shell.isDisposed) {
            if (!display.readAndDispatch()) display.sleep()
        }
        display.dispose()
    }
}

fun Tree.expandAll() = traverse { it.expanded = true }

fun Tree.traverse(visitor: (TreeItem) -> Unit) {
    fun TreeItem.traverse() {
        visitor(this)
        items.forEach {
            it.traverse()
        }
    }
    items.forEach { it.traverse() }
}