import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*


class Window {
    val shell: Shell = Shell(Display.getDefault())
    @Inject
    lateinit var setup: FrameSetup
    lateinit var tree: Tree
    @InjectAdd
    var actions = mutableListOf<Action>()
    lateinit var json: Json
    private lateinit var outerGroup: Group
    private lateinit var outerGroupButton: Group
    lateinit var label: Label

    private fun treeConstructor() {
        shell.text = setup.text
        shell.layout = GridLayout(1,false)

        outerGroup = Group(shell, SWT.NONE)
        outerGroup.layoutData = GridData(SWT.FILL, SWT.FILL, true, true)
        outerGroup.layout = setup.gridLayout

        outerGroupButton = Group(shell, SWT.NONE)
        outerGroupButton.layout = FillLayout(SWT.HORIZONTAL)

        tree = Tree(outerGroup, SWT.SINGLE or SWT.BORDER)
        fun tConstruct(j: JsonValue, dad: TreeItem? = null){
            val a: TreeItem = if(dad == null) {
                TreeItem(tree, SWT.NONE)
            }
            else
                TreeItem(dad, SWT.NONE)
            a.data = j
            when (j) {
                is JsonArray -> {
                    a.text = "(array)"
                    j.value.forEach {
                        tConstruct(it, a)
                    }
                }
                is JsonString -> a.text = "\""+j.value+"\""
                is JsonNull -> a.text = "null"
                is JsonNumber -> a.text = j.value.toString()
                is JsonBoolean -> a.text = j.value.toString()
                is JsonObject -> {
                    a.text = "(object)"
                    j.jsonObject.forEach {
                        tConstruct(it, a)
                    }
                }
                is JsonMap -> {
                    a.text = j.getKey().replace("\"", "")
                    when(val v = j.value){
                        is JsonArray -> tConstruct(v, a)
                        is JsonString -> a.text += ": \""+v.value+"\""
                        is JsonNull -> a.text += ": null"
                        is JsonNumber -> a.text += ": "+v.value.toString()
                        is JsonBoolean -> a.text += ": "+v.value.toString()
                        is JsonObject -> tConstruct(v, a)
                        is JsonMap -> tConstruct(v, a)
                    }

                }
            }
        }

        tConstruct(json.json)

        label = Label(outerGroup, SWT.BORDER)
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

    private fun addButtons(){
        actions.forEach { action ->
            val window = this
            val button = Button(outerGroupButton, SWT.NONE)
            button.text = action.name
            button.addSelectionListener(object : SelectionAdapter() {
                override fun widgetSelected(e: SelectionEvent) {
                    action.execute(window)
                }
            })
        }
    }

    fun open(json: Json) {
        this.json = json
        treeConstructor()
        addButtons()
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