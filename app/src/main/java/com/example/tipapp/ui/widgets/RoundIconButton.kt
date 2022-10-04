package com.example.tipapp.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.HdrPlus
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipapp.R
import java.util.*

@Preview
@Composable
fun RoundIconButton(
    modifier : Modifier = Modifier,
    imageVector : ImageVector = Icons.Rounded.Add,
    onClick : () -> Unit = {}
){
    Surface(
        modifier = Modifier
            .clickable { onClick.invoke() }
            .size(35.dp) ,
        shape = CircleShape ,
        color = MaterialTheme.colors.background ,
        elevation = 8.dp
    ) {

        Icon(
            imageVector = imageVector,
            contentDescription = "" ,
            tint = MaterialTheme.colors.onBackground
        )
    }

}