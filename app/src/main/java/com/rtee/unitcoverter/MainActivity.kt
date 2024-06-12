package com.rtee.unitcoverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rtee.unitcoverter.ui.theme.UnitCoverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitCoverterTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters")  }
    var iexpanded by remember { mutableStateOf(false) }
    var oexpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00)}
    val oconversionFactor = remember { mutableStateOf(1.00)}

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Blue
    )

    fun convertUnits(): String {
        // ?: is the elvis operator, it means if the value is null, it will return 0.0
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0/oconversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()

        return outputValue

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Ui elements will be stacked here below each other
        Text("Unit Converter", style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {inputValue= it.toDouble().toString() },
            label = {Text("Enter Value")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Row {
            Box {

                Button(onClick = { iexpanded = true }) {
                    Text(selected)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow down"
                    )
                }
                DropdownMenu(expanded = iexpanded, onDismissRequest = { iexpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            iexpanded = false
                            selected = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            iexpanded = false
                            selected = "Meters"
                            conversionFactor.value = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iexpanded = false
                            selected = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            iexpanded = false
                            selected = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )

                }

            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oexpanded = true }) {
                    Text(outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow down"
                    )
                }
                DropdownMenu(expanded = oexpanded, onDismissRequest = { oexpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            oexpanded = false
                            outputUnit = "Centimeters"
                            oconversionFactor.value = 0.01
                            convertUnits()
                        },
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            oexpanded = false
                            outputUnit = "Meters"
                            oconversionFactor.value = 1.00
                            convertUnits()
                        }

                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oexpanded = false
                            outputUnit= "Feet"
                            oconversionFactor.value = 0.3048
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            oexpanded = false
                            outputUnit = "Millimeters"
                            oconversionFactor.value = 0.001
                            convertUnits()

                        }

                    )

                }
            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = {convertUnits()}) {
            Text(text = "Convert")
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()

}