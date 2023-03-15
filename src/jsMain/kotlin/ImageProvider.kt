import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image as SkiaImage

@Composable
fun ImageProvider(
    provideImageBytes: suspend () -> ByteArray,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    var imageBytes by remember { mutableStateOf<ByteArray?>(null) }

    LaunchedEffect(Unit) {
        imageBytes = provideImageBytes()
    }

    imageBytes?.let { bytes ->
        val bitmap = Bitmap.Companion.makeFromImage(SkiaImage.makeFromEncoded(bytes))
        Image(
            bitmap = bitmap.asComposeImageBitmap(),
            contentDescription = null,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}