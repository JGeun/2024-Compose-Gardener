data class MarkdownNode(
    val text: String? = null,
    val list: Boolean = false,
	var index: Int? = null,
) {
    val children = mutableListOf<MarkdownNode>()

	override fun toString() = buildString {
		if (text != null) {
			val index = index
			if (index != null) append("${index + 1}. ")
			append(text)
		}

		append(children.joinToString("\n"))
	}
}
