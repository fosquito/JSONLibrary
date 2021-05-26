import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Layout

interface FrameSetup {
    var text: String
    var gridLayout: Layout
}

class OneColumnSetup : FrameSetup{
    override var text = "Json Tree Skeleton"
    override var gridLayout: Layout = GridLayout(1, true)
}

class TwoColumnSetup : FrameSetup{
    override var text = "Json Tree Skeleton"
    override var gridLayout: Layout = GridLayout(2, true)
}

class FillVertical : FrameSetup{
    override var text = "Json Tree Skeleton"
    override var gridLayout: Layout = FillLayout(SWT.VERTICAL)
}

class FillHorizontal : FrameSetup{
    override var text = "Json Tree Skeleton"
    override var gridLayout: Layout = FillLayout(SWT.HORIZONTAL)
}
