import androidx.compose.runtime.Applier

/**
 *
 * @author   JGeun
 * @created  2024/08/31
 */
class MarkdownApplier(
	private val root: MarkdownNode
): Applier<MarkdownNode> {

	private val stack = mutableListOf<MarkdownNode>(root)
	override var current: MarkdownNode = root

	override fun insertTopDown(index: Int, instance: MarkdownNode) {
		if (current.list) instance.index = index
		current.children.add(instance)
	}

	override fun insertBottomUp(index: Int, instance: MarkdownNode) {
		// Ignored.
	}

	override fun down(node: MarkdownNode) {
		stack += node
		current = node
	}

	override fun up() {
		// 왜 remove 인가 -> up은 root로만 이동시키면 되는 것 아닌가. 혹은 stack이 단순 순서를 저장하기 위함인가.
		stack.removeLast()
		current = stack.last()
	}

	override fun clear() {
		stack.clear()
		stack += root
		current = root
	}

	override fun move(from: Int, to: Int, count: Int) {
		// Ignored.
	}

	override fun remove(index: Int, count: Int) {
		// Ignored.
	}
}