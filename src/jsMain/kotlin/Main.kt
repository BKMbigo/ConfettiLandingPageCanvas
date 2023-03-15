import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Typeface
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.browser.window
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.px
import org.jetbrains.skia.Data
import org.jetbrains.skia.Typeface
import org.jetbrains.skiko.SkikoPointerEvent
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
fun main() {
    onWasmReady {
        BrowserViewportWindow("SIMPLE - Landing Page") {
            val density = LocalDensity.current
            val scope = rememberCoroutineScope()
            val client = HttpClient(Js)

            var emojiFontFamily by remember { mutableStateOf<FontFamily?>(null) }
            var sansFontFamily by remember { mutableStateOf<FontFamily?>(null) }
            //var sansItalicFontFamily by remember { mutableStateOf<FontFamily?>(null) }

            val screenScrollState = rememberScrollState()

            LaunchedEffect(Unit) {
                val emojiBytes = client.getBytesProvider("/fonts/NotoColorEmoji.ttf")
                val emojiTypeface = Typeface.makeFromData(Data.makeFromBytes(emojiBytes))
                emojiFontFamily = FontFamily(Typeface(emojiTypeface))

                val sansBytes = client.getBytesProvider("/fonts/OpenSans.ttf")
                val sansTypeface = Typeface.makeFromData(Data.makeFromBytes(sansBytes))
                sansFontFamily = FontFamily(Typeface(sansTypeface))

//                    val sansItalicBytes = client.getBytesProvider("/fonts/OpenSans-italic.ttf")
//                    val sansItalicTypeface = Typeface.makeFromData(Data.makeFromBytes(sansItalicBytes))
//                    sansItalicFontFamily = FontFamily(Typeface(sansItalicTypeface))
            }


            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(color = Color(0xFF876AFD))) {
                                append("Confetti ")
                            }
                            withStyle(style = SpanStyle(fontFamily = emojiFontFamily)) {
                                append("\uD83C\uDF8A")
                            }
                        },
                        fontWeight = FontWeight.Bold,
                        fontSize = with(density) { 30.px.value.toSp() },
                        modifier = Modifier
                            .padding(start = with(density) { 15.px.value.toDp() })
                    )
                    Row(
                        modifier = Modifier
                            .padding(end = with(density) { 15.px.value.toDp() })
                            .onClick {
                                window.location.href = "https://github.com/joreilly/Confetti"
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ImageProvider(
                            provideImageBytes = {
                                client.getBytesProvider("/images/github.png")
                            },
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "GITHUB",
                            //color = Color(0x666),
                            fontSize = 13.px.value.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .scrollable(screenScrollState, Orientation.Vertical)
                        .onPointerEvent(PointerEventType.Scroll) {
                            scope.launch {
                                screenScrollState.scrollBy((it.nativeEvent as SkikoPointerEvent).deltaY.toFloat())
                            }
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0F, 0F, 0F, 0.7F)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(70.dp))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = emojiFontFamily,
                                        fontSize = with(density) { 24.px.value.toSp() }
                                    )
                                ) {
                                    append("\uD83C\uDF8A")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White,
                                        fontSize = with(density) { 36.px.value.toSp() },
                                        fontFamily = FontFamily.SansSerif
                                    )
                                ) {
                                    append(" Confetti ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = emojiFontFamily,
                                        fontSize = with(density) { 24.px.value.toSp() }
                                    )
                                ) {
                                    append("\uD83C\uDF8A")
                                }
                            },
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(Modifier.height(with(density) { 25.px.value.toDp() }))
                        Text(
                            text = "A Kotlin multiplatform conference app for iOS, Android and Wear OS.",
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Thin,
                            color = Color.White,
                            fontSize = 13.sp,
                            fontFamily = sansFontFamily
                        )
                        Spacer(Modifier.height(with(density) { 30.px.value.toDp() }))
                        ImageProvider(
                            provideImageBytes = {
                                client.getBytesProvider("/images/screens.png")
                            },
                            modifier = Modifier
                                .width(800.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )
                        Spacer(Modifier.height(with(density) { 30.px.value.toDp() }))
                    }
                    Spacer(
                        modifier = Modifier
                            .height(with(density) { 90.px.value.toDp() })
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        IconButton(
                            onClick = {
                                window.location.href =
                                    "https://play.google.com/store/apps/details?id=dev.johnoreilly.confetti"
                            },
                            modifier = Modifier.width(500.dp)
                        ) {
                            ImageProvider(
                                provideImageBytes = {
                                    client.getBytesProvider("/images/playstore.png")
                                }
                            )
                        }

                        IconButton(
                            onClick = {
                                window.location.href = "https://apps.apple.com/us/app/confetti/id1660211390"
                            },
                            modifier = Modifier.width(300.dp)
                        ) {
                            ImageProvider(
                                provideImageBytes = {
                                    client.getBytesProvider("/images/appstore.png")
                                },
                                modifier = Modifier.width(300.dp)
                            )
                        }
                    }
                }
            }
        }

    }
}

private suspend fun HttpClient.getBytesProvider(url: String): ByteArray =
    this.get { url(url) }.readBytes()