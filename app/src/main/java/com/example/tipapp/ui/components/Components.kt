package com.example.tipapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun MainTextFiled(
    modifier: Modifier = Modifier,
    valueState : MutableState<String>,
    label : String,
    isEnabled : Boolean,
    isSingleLine : Boolean,
    keyboardType : KeyboardType = KeyboardType.Text,
    imeAction : ImeAction = ImeAction.Done,
    onAction : KeyboardActions = KeyboardActions.Default ,

){


    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight() ,
        enabled = isEnabled,
        value = valueState.value,
        onValueChange = { valueState.value = it} ,
        label = { Text(text = label) } ,
        singleLine = isSingleLine ,
        textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
        leadingIcon = {
        //    IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.AttachMoney , contentDescription = "textField icon")
         //   }
        } ,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType ,
            imeAction = imeAction
        ) ,
        keyboardActions = onAction




    )
}