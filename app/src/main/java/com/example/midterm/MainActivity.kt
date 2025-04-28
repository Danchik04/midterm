package com.example.midterm
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import androidx.room.parser.Section

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "gym") {
        composable("gym") { GymScreen(navController) }
        composable("cooking") { CookingScreen(navController) }
        composable("movies") { MoviesScreen(navController) }
    }
}

@Composable
fun GymScreen(navController: NavController) {
    val workoutPlan = mapOf(
        "Вторник" to listOf(
            "Жим лёжа в тренажёре (Смит/Хаммер) – 4×8-12",
            "Разводка в тренажёре (бабочка/pec deck) – 3×12-15",
            "Жим вверх в тренажёре (верхняя часть груди) – 3×10-12",
            "Отжимания на брусьях или французский жим – 3×10-12",
            "Разгибание рук на блоке – 3×12-15",
            "Жим Арнольда – 3×10-12",
            "Подъём гантелей перед собой – 3×12-15"
        ),
        "Четверг" to listOf(
            "Тяга верхнего блока (широкий хват) – 4×8-12",
            "Тяга горизонтального блока (нейтральный хват) – 3×10-12",
            "Тяга верхнего блока обратным хватом – 3×10-12",
            "Гиперэкстензия в тренажёре – 3×12-15",
            "Сгибание рук в тренажёре (бицепс) – 3×12-15",
            "Молотки на блоке – 3×12-15",
            "Обратные разведения на блоке – 3×12-15"
        ),
        "Суббота" to listOf(
            "Жим ногами в тренажёре – 4×12-15",
            "Разгибание ног в тренажёре – 3×12-15",
            "Сгибание ног в тренажёре – 3×12-15",
            "Присед в Гакк-машине – 3×12-15",
            "Жим вверх в тренажёре (средний пучок дельт) – 4×8-12",
            "Разведения гантелей в стороны – 3×12-15",
            "Подъём на носки в тренажёре (голень) – 3×15-20",
            "Шраги в тренажёре (трапеции) – 3×15-20"
        )
    )

    var selectedDay by remember { mutableStateOf("Вторник") }
    val checkedExercises = remember { mutableStateListOf<Boolean>() }

    LaunchedEffect(selectedDay) {
        checkedExercises.clear()
        checkedExercises.addAll(List(workoutPlan[selectedDay]?.size ?: 0) { false })
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text("Выбери день тренировки", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        var expanded by remember { mutableStateOf(false) }
        Box {
            Button(onClick = { expanded = true }) {
                Text(selectedDay)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                workoutPlan.keys.forEach { day ->
                    DropdownMenuItem(text = { Text(day) }, onClick = {
                        selectedDay = day
                        expanded = false
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        workoutPlan[selectedDay]?.forEachIndexed { index, exercise ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = checkedExercises.getOrNull(index) ?: false,
                    onCheckedChange = { isChecked ->
                        if (index < checkedExercises.size) {
                            checkedExercises[index] = isChecked
                        }
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(exercise, fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("movies") }) {

            Section.Text("Перейти к фильмам/сериалам")
        }
        Button(onClick = { navController.navigate("cooking") }) {
            Text("Перейти к кулинарии")
        }
    }
}

@Composable
fun CookingScreen(navController: NavController) {
    data class Ingredient(val name: String, val grams: Int, val calories: Int, val protein: Double, val fat: Double, val carbs: Double)

    val dishName = "Овсянка с фруктами"
    val ingredients = listOf(
        Ingredient("Овсяные хлопья", 50, 190, 6.0, 3.2, 33.0),
        Ingredient("Банан", 100, 89, 1.1, 0.3, 23.0),
        Ingredient("Молоко 2.5%", 200, 102, 6.8, 5.0, 9.6),
        Ingredient("Орехи (грецкие)", 20, 131, 2.7, 13.1, 2.7),
        Ingredient("Мёд", 10, 30, 0.0, 0.0, 8.1)
    )


    val totalCalories = ingredients.sumOf { it.calories }
    val totalProtein = ingredients.sumOf { it.protein }
    val totalFat = ingredients.sumOf { it.fat }
    val totalCarbs = ingredients.sumOf { it.carbs }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(dishName, fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))
        Text("Ингредиенты:", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

        ingredients.forEach { ingredient ->
            Text("${ingredient.name}: ${ingredient.grams}г", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text("КБЖУ на порцию:", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

        Text("Калории: $totalCalories ккал", fontSize = 16.sp)
        Text("Белки: ${"%.1f".format(totalProtein)} г", fontSize = 16.sp)
        Text("Жиры: ${"%.1f".format(totalFat)} г", fontSize = 16.sp)
        Text("Углеводы: ${"%.1f".format(totalCarbs)} г", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.navigate("gym") }) {
                Text("Вернуться в тренажерный зал")
            }

            Button(onClick = { navController.navigate("movies") }) {
                Text("Перейти к фильмам")
            }
        }

}


@Composable
fun MoviesScreen(navController: NavController) {
    data class Movie(val title: String, val description: String, val imageRes: Int)

    val movies = listOf(
        Movie("Интерстеллар", "Фантастический фильм о путешествии в червоточину ради спасения человечества.", R.drawable.interstellar),
        Movie("Джон Уик", "Бывший наёмный убийца мстит за убитую собаку и украденную машину.", R.drawable.john_wick),
        Movie("Игра престолов", "Фэнтези-сериал о борьбе за Железный трон в мире Вестероса.", R.drawable.got),
        Movie("Во все тяжкие", "Учитель химии превращается в наркобарона, создавая лучший метамфетамин.", R.drawable.breakingbad)
    )

    var selectedMovie by remember { mutableStateOf(movies.random()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val movieImage: Painter = painterResource(id = selectedMovie.imageRes)
        Image(painter = movieImage, contentDescription = "Фильм/Сериал")
        Spacer(modifier = Modifier.height(16.dp))

        Text(selectedMovie.title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        Text(selectedMovie.description, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { selectedMovie = movies.random() }) {
            Text("Выбрать случайный фильм/сериал")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("gym") }) {
            Text("Вернуться в тренажерный зал")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("cooking") }) {
            Text("Вернуться к кулинарии")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewGymScreen() {
    GymScreen(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun PreviewCookingScreen() {
    CookingScreen(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun PreviewMoviesScreen() {
    MoviesScreen(navController = rememberNavController())
}
