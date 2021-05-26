import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*

class JsonTreeSkeleton(json: Json) {
    private val shell: Shell = Shell(Display.getDefault())
    val tree: Tree
    lateinit var composite: Composite

    init {
        //shell.setSize(500, 500)
        shell.text = "Json tree skeleton"
        shell.layout = GridLayout(2,false)
        tree = Tree(shell, SWT.SINGLE or SWT.BORDER)

        fun treeConstructor(j: JsonValue, dad: TreeItem? = null){
            val a: TreeItem = if(dad == null)
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
        tree.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                label.requestLayout()
                println(tree.selection)
                val vis = Serialize()
                val item: JsonValue = tree.selection.first().data as JsonValue
                item.accept(vis).toString()
                label.text = vis.str
            }
        })
    }

    fun open() {
        //... popular a tree com TreeItem
        tree.expandAll()
        shell.layout = FillLayout(SWT.HORIZONTAL);
        shell.pack();
        shell.layout(true);
        shell.open();
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