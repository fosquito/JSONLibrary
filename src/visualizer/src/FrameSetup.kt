import org.eclipse.swt.layout.GridLayout

interface FrameSetup {
    var text: String
    var gridLayout: GridLayout
}

class OneColumnSetup : FrameSetup{
    override var text = "Json Tree Skeleton"
    override var gridLayout = GridLayout(1, true)
}

class TwoColumnSetup : FrameSetup{
    override var text = "Json Tree Skeleton"
    override var gridLayout = GridLayout(2, true)
}