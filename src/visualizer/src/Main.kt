import java.awt.*
import java.awt.Point
import java.util.*
import javax.swing.JButton
import javax.swing.JFrame
import java.awt.GridLayout
import java.awt.LayoutManager


interface FrameSetup {
    val title: String
    val layoutManager: LayoutManager
}

interface Action {
    val name: String
    fun execute(window: Window)
}

class Window {
    private val frame = JFrame()

    // 1) eliminar dependencia de DefaultSetup;
    @Inject
    private lateinit var setup: FrameSetup

    // 2) eliminar dependencias das acoes concretas (Center, Size);
    //@InjectAdd
    private val actions = mutableListOf<Action>(Move(), Size())

    init {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(300, 200)
    }

    val location get() = frame.location
    val dimension get() = frame.size

    fun open() {
        frame.title = setup.title
        frame.layout = setup.layoutManager
        actions.forEach { action ->
            val button = JButton(action.name)
            button.addActionListener { action.execute(this) }
            frame.add(button)
        }
        frame.isVisible = true
    }

    fun move(x: Int, y: Int) {
        frame.location = Point(x, y)
    }

    fun setSize(width: Int, height: Int) {
        require(width > 0)
        require(height > 0)
        frame.size = Dimension(width, height)
    }
}

fun main () {
    val w = Injector.create(Window::class) // substituir por criacao com injecao
    //val w = Window()
    w.open()
}




//-------- Actions.kt

class DefaultSetup : FrameSetup {
    override val title: String
        get() = "Test"
    override val layoutManager: LayoutManager
        get() = GridLayout(2, 1)
}

class DefaultSetup2 : FrameSetup {
    override val title: String
        get() = "Test2"
    override val layoutManager: LayoutManager
        get() = GridLayout(1, 2)
}

class Move : Action {
    override val name: String
        get() = "center"

    override fun execute(window: Window) {
        window.move( 500, 500)
    }
}

class Size : Action {
    override val name: String
        get() = "change size"

    override fun execute(window: Window) {
        window.setSize(500, 500)
    }
}


class OtherAction : Action {
    override val name: String
        get() = "other"

    override fun execute(window: Window) {
        window.move(0, 0)
    }
}