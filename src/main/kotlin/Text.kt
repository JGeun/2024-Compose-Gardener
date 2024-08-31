import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode

/**
 * Text 보통 싱글 노드 형태이기 때문에 factory 인자만 가진 ComposeNode를 사용
 *
 * @param text
 */
@Composable
fun Text(
    text: String
) {
    ComposeNode<MarkdownNode, MarkdownApplier>(
        factory = { MarkdownNode(text = text, list = false) },
        update = {}
    )
}