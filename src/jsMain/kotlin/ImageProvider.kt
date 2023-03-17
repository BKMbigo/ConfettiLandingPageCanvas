import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    } ?: Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.White
        )
    }

}