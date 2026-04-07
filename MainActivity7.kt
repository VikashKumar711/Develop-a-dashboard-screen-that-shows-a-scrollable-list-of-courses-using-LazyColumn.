package com.example.sem6

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sem6.ui.theme.Sem6Theme

data class Course(
    val title: String,
    val instructor: String,
    var progress: Float
)

class MainActivity7 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashboardScreen()
        }
    }
}

@Composable
fun DashboardScreen() {

    val context = LocalContext.current

    var courseList by remember {
        mutableStateOf(
            listOf(
                Course("Android Dev", "John", 0.7f),
                Course("DSA", "Alice", 0.5f),
                Course("AI/ML", "Bob", 0.3f),
                Course("Web Dev", "Smith", 0.9f)
            )
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {

        // 🔹 LIST
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(courseList) { course ->
                CourseItem(course) {
                    Toast.makeText(
                        context,
                        "Clicked: ${course.title}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 🔹 RESET BUTTON
        Button(onClick = {
            courseList = courseList.map {
                it.copy(progress = 0f)
            }
            Toast.makeText(context, "Progress Reset!", Toast.LENGTH_SHORT).show()
        }) {
            Text("Reset Progress")
        }
    }
}

@Composable
fun CourseItem(course: Course, onClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() }
    ) {

        Text(text = course.title, style = MaterialTheme.typography.titleMedium)

        Text(text = "Instructor: ${course.instructor}")

        Spacer(modifier = Modifier.height(5.dp))

        LinearProgressIndicator(progress = course.progress)

        Spacer(modifier = Modifier.height(10.dp))
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    Sem6Theme {
        DashboardScreen()
    }
}