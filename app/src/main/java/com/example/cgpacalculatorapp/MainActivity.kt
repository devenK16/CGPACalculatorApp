package com.example.cgpacalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cgpacalculatorapp.ui.theme.CGPACalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CGPACalculatorAppTheme {

            }
        }
    }
}

@Composable
fun CGPA(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "CGPA Calculator APP",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer( modifier = Modifier.padding(top = 10.dp) )
        SubjectText(subject = "Subject 1")
        GradeTextField(grade = "hi", onValueChange = {})

    }
}

@Composable
fun SubjectText(subject : String){
    Text(
        text = subject,
        fontSize = 16.sp,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun GradeTextField(grade: String , onValueChange: (String)-> Unit){
    TextField(
        value = grade,
        onValueChange = { text ->
            onValueChange(text)
        },
        modifier = Modifier
    )
}


@Preview(showBackground = true)
@Composable
fun CGPAPreview(){
    CGPACalculatorAppTheme {
        CGPA()
    }
}
