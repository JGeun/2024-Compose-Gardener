data class MarkdownNode(
    val text: String? = null,
    val list: Boolean = false
) {
    val children = mutableListOf<MarkdownNode>()
}
