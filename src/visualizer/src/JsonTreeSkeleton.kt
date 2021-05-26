import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*


data class Dummy(val number: Int)

class JsonTreeSkeleton(var json: Json) {
    val shell: Shell
    val tree: Tree

    init {
        shell = Shell(Display.getDefault())
        shell.text = "File tree skeleton"
        shell.layout = GridLayout(2,true)

        tree = Tree(shell, SWT.SINGLE or SWT.BORDER)

        fun treeConstructor(j: JsonValue, dad: TreeItem? = null){
            var a: TreeItem = if(dad == null)
                TreeItem(tree, SWT.NONE)
            else
                TreeItem(dad, SWT.NONE)
            a.data = j
            when (j) {
                is JsonArray -> {
                    a.text = "(array)"
                    j.value.forEach {
                       treeConstructor(it, a)
                    }
                }
                is JsonString -> a.text = "\""+j.value+"\""
                is JsonNull -> a.text = "null"
                is JsonNumber -> a.text = j.value.toString()
                is JsonBoolean -> a.text = j.value.toString()
                is JsonObject -> {
                    a.text = "(object)"
                    j.jsonObject.forEach {
                        treeConstructor(it, a)
                    }
                }
                is JsonMap -> {
                    a.text = j.getKey().replace("\"", "")
                    when(val v = j.value){
                        is JsonArray -> treeConstructor(v, a)
                        is JsonString -> a.text += ": \""+v.value+"\""
                        is JsonNull -> a.text += ": null"
                        is JsonNumber -> a.text += ": "+v.value.toString()
                        is JsonBoolean -> a.text += ": "+v.value.toString()
                        is JsonObject -> treeConstructor(v, a)
                        is JsonMap -> treeConstructor(v, a)
                    }

                }
            }
        }

        treeConstructor(json.json)

        val label = Label(shell, SWT.BORDER)
        label.layoutData = GridData(GridData.BEGINNING, GridData.BEGINNING, false, true)
        label.text = "skeleton"
        tree.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                println(tree.selection)
                var vis = Serialize()
                val item: JsonValue = tree.selection.first().data as JsonValue
                item.accept(vis).toString()
                label.text = vis.str
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