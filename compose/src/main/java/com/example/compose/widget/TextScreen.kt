package com.example.compose.widget

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.compose.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextScreen(modifier: Modifier = Modifier) {
    val brush = Brush.linearGradient(colors = listOf(Color.Red, Color.Yellow))
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        var startIndex by remember {
            mutableStateOf(0)
        }

        var endIndex by remember {
            mutableStateOf(0)
        }

        val scope = rememberCoroutineScope()

        ClickableText(text = buildAnnotatedString {
            append("您好，请你同意")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Blue)) {
                startIndex = this.length
                append("《隐私协议》")
                endIndex = this.length
            }

            append("才能登陆")
        }) { contentIndex ->
            Log.d("Text", "contentIndex = $contentIndex")
            if (contentIndex in startIndex..endIndex) {
                Log.d("Text", "startIndex = $startIndex")
                Log.d("Text", "endIndex = $endIndex")
            }
        }


        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )

        Text(
            text = "Hello World",
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                textMotion = TextMotion.Animated
            )
        )


        // 显示文本
        Text(text = "显示文本")
        // 文本样式
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.res_text),  // 显示资源中的文字
            color = Color.Blue,  // 更改文本颜色
            fontSize = 16.sp,    // 更改文字大小
            fontStyle = FontStyle.Italic,  // 将文本设为斜体
            fontWeight = FontWeight.Bold,  // 将文本设为粗体
            textAlign = TextAlign.Justify,
            style = TextStyle(
                fontSize = 30.sp,
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(x = 3f, y = 5.0f),
                    blurRadius = 2f
                ),            // 添加阴影
                brush = brush //添加渐变
            )
        )
        // 在文本中添加多种样式
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(brush = brush, alpha = 0.3f)) {
                    append("Text in ")
                }
                withStyle(style = SpanStyle(brush = brush, alpha = 1f)) {
                    append("Compose")
                }
            }
        )
        // 添加动画滚动效果
        Text(
            text = "Learn about why it's great to use Jetpack Compose",
            modifier = Modifier.basicMarquee(velocity = 100.dp),
            fontSize = 50.sp
        )

        Text(
            buildAnnotatedString {
                withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("Hello\n")
                    }
                    withStyle(
                        style =
                        SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)
                    ) {
                        append("World\n")
                    }
                }
            }
        )

        Text(
            modifier = Modifier.background(Color.Green),
            text = "ABCDEFGfgH\nijkgpvylzg\npvykwi",
            style = LocalTextStyle.current.merge(
                TextStyle(
                    background = Color.Gray,
                    lineHeight = 2.5.em,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.LastLineBottom
                    )
                )
            )
        )

        val smallScreenAdapterParagraph =
            LineBreak.Paragraph.copy(strategy = LineBreak.Strategy.HighQuality)
        Text(
            modifier = Modifier
                .width(130.dp)
                .border(BorderStroke(width = 1.dp, color = Color.Gray)),
            text = stringResource(id = R.string.long_test_text),
            fontSize = 14.sp,
            style = TextStyle.Default.copy(lineBreak = smallScreenAdapterParagraph)
        )

        Text(
            modifier = Modifier
                .width(130.dp)
                .border(BorderStroke(width = 1.dp, color = Color.Gray)),
            text = stringResource(id = R.string.long_test_text),
            fontSize = 14.sp,
            style = TextStyle.Default
        )

        // 自定义换行符
        val lineBreak = LineBreak(
            strategy = LineBreak.Strategy.Balanced,
            strictness = LineBreak.Strictness.Strict,
            wordBreak = LineBreak.WordBreak.Phrase
        )

        Text(
            modifier = Modifier
                .width(130.dp)
                .border(BorderStroke(width = 1.dp, color = Color.Gray)),
            text = stringResource(id = R.string.long_test_text),
            fontSize = 14.sp,
            style = TextStyle.Default.copy(
                lineBreak = LineBreak.Paragraph,
                hyphens = Hyphens.Auto
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextScreenPreview() {
    TextScreen()
}