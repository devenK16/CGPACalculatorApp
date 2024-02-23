package com.example.cgpacalculatorapp

import android.os.Bundle
import android.text.style.SuggestionRangeSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cgpacalculatorapp.ui.theme.CGPACalculatorAppTheme
data class Semester( val grade: String , val credit: Int )
class MainActivity : ComponentActivity() {
    private var semesters: MutableList<Semester> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CGPACalculatorAppTheme {
                CGPA(semesters = semesters)
            }
        }
    }
}

@Composable
fun CGPA(semesters :MutableList<Semester>){
    var grade1 by remember{
        mutableStateOf("")
    }
    var credit1 by remember{
        mutableStateOf<Int?>(null)
    }
    var grade2 by remember{
        mutableStateOf("")
    }
    var credit2 by remember{
        mutableStateOf<Int?>(null)
    }
    var grade3 by remember{
        mutableStateOf("")
    }
    var credit3 by remember{
        mutableStateOf<Int?>(null)
    }
    var grade4 by remember{
        mutableStateOf("")
    }
    var credit4 by remember{
        mutableStateOf<Int?>(null)
    }
    var cgpa by remember{
        mutableStateOf(0.0)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
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
        GradeTextField(grade = grade1) {grade1 = it}
        Spacer8dp()
        CreditTextField(credit = credit1) {credit1 = it}
        Spacer8dp()
        SubjectText(subject = "Subject 2")
        GradeTextField(grade = grade2){grade2 = it}
        Spacer8dp()
        CreditTextField(credit = (credit2)) {credit2 = it}
        Spacer8dp()
        SubjectText(subject = "Subject 3")
        GradeTextField(grade = grade3){grade3 = it}
        Spacer8dp()
        CreditTextField(credit = (credit3)) {credit3 = it}
        Spacer8dp()
        SubjectText(subject = "Subject 4")
        GradeTextField(grade = grade4){grade4 = it}
        Spacer8dp()
        CreditTextField(credit = (credit4)) {credit4 = it}
        Spacer8dp()
        Row() {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        val semester = Semester( grade1 , credit1 ?: 0)
                        semesters.add(semester)
                        val totalCredit = semesters.sumOf { it.credit }
                        val totalGrade = semesters.sumOf{ calculateGradePoints(it.grade , it.credit)}

                        if( totalCredit > 0 ){
                            cgpa = totalGrade / totalCredit.toDouble()
                        } else {
                            cgpa = 0.0
                        }
                        grade1 = ""
                        credit1 = null
                        grade2 = ""
                        credit2 = null
                        grade3 = ""
                        credit3 = null
                        grade4 = ""
                        credit4 = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFFBEABE0)
                    ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        text = "Calculate CGPA",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
                
                Surface(
                    modifier = Modifier
                        .width(160.dp)
                        .wrapContentHeight(),
                    color = Color(0xFF263238),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        text = "Yout All time \n CGPA : $cgpa ",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color(0xFFFFFFFF)
                        )
                    )
                }
            }
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp),
                color = Color(0xFF263238),
                shape = RoundedCornerShape(15.dp)
            ) {
                Column() {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Previous Semester CGPA",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color(0xFFFFFFFF)
                        )
                    )

                    if( semesters.isNotEmpty()){
                        for ( semester in semesters )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),

                            text = "Grade: ${semester.grade}, Credit: ${semester.credit} ",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color(0xFFFFFFFF)
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
        }
    }
}

fun calculateGradePoints(grade: String, credit: Int) : Double {
    return when( grade.uppercase() ){
        "A" -> 4.0
        "B" -> 3.0
        "C" -> 2.0
        "D" -> 1.0
        else -> 0.0
    } * credit

}

@Composable
fun Spacer8dp(){
    Spacer(
        modifier = Modifier
            .padding(top = 8.dp)
    )
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
            .fillMaxWidth()
            .height(47.dp),
        label = {
            Text(
                text = "Enter Grade",
                color = Color.White,
                fontSize = 12.sp
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color(0xFF7E57C2)
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(
            fontSize = 12.sp,
            color = Color.White
        )
    )
}

@Composable
fun CreditTextField(credit: Int?, onValueChange: (Int?)-> Unit){
    TextField(
        value = credit?.toString() ?: "",
        onValueChange = { text ->
            onValueChange(text.toIntOrNull())
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp),
        label = {
            Text(
                text = "Enter Credit",
                color = Color.Black,
                fontSize = 12.sp
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color(0xFFD8CCED)
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(
            fontSize = 12.sp,
            color = Color.Black
        )
    )
}


//@Preview(showBackground = true)
//@Composable
//fun CGPAPreview(){
//    CGPACalculatorAppTheme {)
//    }
//}
