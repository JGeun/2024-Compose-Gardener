import androidx.compose.runtime.MonotonicFrameClock

/**
 *
 * @author   JGeun
 * @created  2024/08/31
 */
object MarkdownFrameClock  : MonotonicFrameClock {

	override suspend fun <R> withFrameNanos(onFrame: (frameTimeNanos: Long) -> R): R {
		return onFrame(System.nanoTime())
	}
}