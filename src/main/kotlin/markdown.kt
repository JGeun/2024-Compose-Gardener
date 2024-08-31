import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.withRunningRecomposer
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
	println("Your Markdown is here!")
	runBlocking {
		markdown {
			Text("Hello, World!")
			List {
				Text("List1")
				Text("List2")

				 List {
					 Text("Sub List1")
					 Text("Sub List2")
				 }
				List {
					Text("List1-1")
				}
			}
		}
	}

	markdown2 {
		Text("Markdown2")
	}
}

private suspend fun markdown(content: @Composable () -> Unit) {
	val root = MarkdownNode()
	var composition: Composition? = null

	withContext(MarkdownFrameClock) {
		withRunningRecomposer { recomposer ->
			composition = Composition(
				applier = MarkdownApplier(root),
				parent = recomposer
			).apply {
				setContent {
					content()
				}
			}
		}
	}

	root.children.forEach { child ->
		println(child.toString())
	}

	composition?.dispose()
}

private fun markdown2(content: @Composable () -> Unit) = runBlocking {
	val root = MarkdownNode()

	withContext(MarkdownFrameClock) {
		withRunningRecomposer { recomposer ->
			Composition(
				applier = MarkdownApplier(root),
				parent = recomposer
			).setContent {
				content()
			}
		}
	}

	root.children.forEach { child ->
		println(child.text)
	}
}

// A MonotonicFrameClock is not available in this CoroutineContext. Callers should supply an appropriate MonotonicFrameClock using withContext.
private suspend fun errorCode1(content: @Composable () -> Unit) {
	val root = MarkdownNode()

	withRunningRecomposer { recomposer ->
		Composition(
			applier = MarkdownApplier(root),
			parent = recomposer
		).setContent {
			content()
		}
	}

	root.children.forEach { child ->
		println(child.text)
	}
}