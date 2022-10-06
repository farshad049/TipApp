package com.example.tipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.FloatRange
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipapp.ui.components.MainTextFiled
import com.example.tipapp.ui.theme.TipAppTheme
import com.example.tipapp.ui.util.calculateTipAmount
import com.example.tipapp.ui.util.calculateTipForEachPerson
import com.example.tipapp.ui.widgets.RoundIconButton

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                val keyboardController = LocalSoftwareKeyboardController.current
                Column(
                    modifier = Modifier
                        .clickable {
                            keyboardController?.hide()  //hide keyboard when click outside space
                        },
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {



                    MainContent()


                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    TipAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}


@Composable
fun TopHeader(totalPerPerson: Double = 0.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colors.surface
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.h5,

                )

            Spacer(modifier = Modifier.height(12.dp))

            val total = "%.2f".format(totalPerPerson)
            Text(
                text = "$ $total",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier : Modifier = Modifier,
    onValChange : (String) -> Unit = {}
){
    val totalBill = remember { mutableStateOf("") }

    var splitAmount by remember { mutableStateOf(1) }

    var tipPercentage by remember { mutableStateOf(0f)}


    val tipPercentageInInt = (tipPercentage * 100).toInt()

    var tipAmount by remember { mutableStateOf(0.0)}

    var totalPerPerson by remember { mutableStateOf(0.0)}

    val keyboardController = LocalSoftwareKeyboardController.current

    TopHeader(totalPerPerson = totalPerPerson)

    Spacer(modifier = Modifier.height(12.dp))

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            ,
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colors.background,
        border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant)
    ) {
        Column(
            verticalArrangement = Arrangement.Top
        ) {

            MainTextFiled(
                modifier = Modifier.padding(12.dp),
                valueState = totalBill,
                label = "Enter Bill",
                isEnabled = true,
                isSingleLine = true ,
                keyboardType = KeyboardType.Number ,
                onAction = KeyboardActions {
                    if (totalBill.value.trim().isEmpty()) return@KeyboardActions
                    keyboardController?.hide()
                    onValChange(totalBill.value.trim())
                }
            )

            if (totalBill.value.isNotEmpty()){ // show this part only if value in entered
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.Start ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Split" ,
                        style = TextStyle(
                            fontSize = 18.sp
                        )
                    )

                    Spacer(modifier = Modifier.width(150.dp))

                    RoundIconButton(
                        imageVector = Icons.Rounded.Remove ,
                        onClick = {
                            if (splitAmount > 1 ) splitAmount -= 1

                            totalPerPerson = calculateTipForEachPerson(totalBill.value.toDouble() , tipPercentageInInt , splitAmount)
                        }
                    )
                    
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        text = splitAmount.toString() ,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    RoundIconButton(
                        onClick = {
                            splitAmount += 1

                            totalPerPerson = calculateTipForEachPerson(totalBill.value.toDouble() , tipPercentageInInt , splitAmount)
                        }
                    )

                }

            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tip" ,
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
                
                Spacer(modifier = Modifier.width(190.dp))

                Text(
                    text = "$ $tipAmount" ,
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(30.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "$tipPercentageInInt %" ,
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
                
                Slider(
                    modifier = Modifier.padding(12.dp),
                    value = tipPercentage,
                    onValueChange = {
                        tipPercentage = it
                        tipAmount = calculateTipAmount(totalBill.value.toDouble() , tipPercentageInInt )
                        totalPerPerson = calculateTipForEachPerson(totalBill.value.toDouble() , tipPercentageInInt , splitAmount)


                    } ,
                    steps = 5
                )

                

            }

                
                }else{
                    Box() {

                    }
                }




        }
    }
}




@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainContent() {

    BillForm(){

    }




}





















@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        val keyboardController = LocalSoftwareKeyboardController.current
        Column(
            modifier = Modifier
                .clickable {
                    keyboardController?.hide()  //hide keyboard when click outside space
                },
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            MainContent()


        }
    }
}