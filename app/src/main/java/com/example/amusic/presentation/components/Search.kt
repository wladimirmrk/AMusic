package com.example.amusic.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amusic.R
import com.example.amusic.ui.theme.AMusicTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    modifier: Modifier = Modifier,
    query: String = "",
    onQueryChanged: (query: String) -> Unit = {}
) {
    val textStyle = AMusicTheme.typography.body2Medium
    TextField(
        value = query,
        onValueChange = {
            onQueryChanged(it)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = AMusicTheme.colorScheme.onSurfaceVariantDisabled
            )
        },
        placeholder = {
            Text(
                stringResource(R.string.search_hint),
                style = textStyle,
                color = AMusicTheme.colorScheme.onSurfaceVariantDisabled
            )
        },
        textStyle = textStyle,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = AMusicTheme.colorScheme.surfaceVariant,
            cursorColor = AMusicTheme.colorScheme.primary,
            focusedTextColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
    )
}

@Preview
@Composable
private fun SearchPreview() {
    AMusicTheme {
        Search(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}