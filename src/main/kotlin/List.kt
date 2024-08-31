import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode

/**
 *
 * @author   JGeun
 * @created  2024/08/31
 */
@Composable
fun List(content: @Composable () -> Unit) {
	ComposeNode<MarkdownNode, MarkdownApplier>(
		factory = { MarkdownNode(list = true) },
		update = {},
		content = { content() }
	)
}