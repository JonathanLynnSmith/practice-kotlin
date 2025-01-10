package com.example.helloworldapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.imePadding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloWorldAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CenteredGreetingWithInput(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CenteredGreetingWithInput(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("World") }
    var userInput by remember { mutableStateOf(TextFieldValue()) }

    Box(modifier = modifier.fillMaxSize()) {
        // Center content inside the Box both vertically and horizontally
        Column(
            modifier = Modifier
                .imePadding()
                .align(Alignment.Center).padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally // Center all items horizontally
        ) {
            GreetingText(text)
            InputField(userInput) { userInput = it }
            ChangeGreetingButton(userInput.text) { text = it }
        }

    }
}

@Composable
fun GreetingText(text: String) {
    Text(
        text = "Hello $text!",
        modifier = Modifier.padding(bottom = 50.dp),
        fontSize = 30.sp
    )
}

@Composable
fun InputField(userInput: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    TextField(
        value = userInput,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
        textStyle = TextStyle(
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text
        ),
    )
}

@Composable
fun ChangeGreetingButton(userInputText: String, onButtonClick: (String) -> Unit) {
    Button(onClick = { onButtonClick(userInputText) }) {
        Text("Change Greeting")
    }
}

@Preview(showBackground = true)
@Composable
fun CenteredGreetingWithInputPreview() {
    HelloWorldAppTheme {
        CenteredGreetingWithInput()
    }
}
