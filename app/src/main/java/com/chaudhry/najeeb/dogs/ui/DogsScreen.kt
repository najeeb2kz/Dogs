package com.chaudhry.najeeb.dogs.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chaudhry.najeeb.dogs.data.DogEntity
import com.chaudhry.najeeb.dogs.viewmodel.DogViewModel
import coil.compose.rememberImagePainter
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import com.chaudhry.najeeb.dogs.ui.theme.PaleYellow
import com.chaudhry.najeeb.dogs.ui.theme.Purple40
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogListScreen(viewModel: DogViewModel, navController: NavController) {
    val dogsState = viewModel.dogs.observeAsState(emptyList())
    val statusState = viewModel.status.observeAsState("")

    val dogs = dogsState.value
    val status = statusState.value

    Box(modifier = Modifier
        .fillMaxSize()
        .background(PaleYellow)) {
        Column {
            TopAppBar(
                title = { Text(text = "Dogs") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple40)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Row {
                    Button(
                        onClick = { viewModel.fetchDogsFromApi() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Fetch from API")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { viewModel.fetchDogsFromDb() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Fetch from DB")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Button(
                        onClick = { viewModel.resetDb() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Clear DB")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { viewModel.clearList() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Clear List")
                    }
                }
            }
            Text(text = status, modifier = Modifier.padding(8.dp))
            LazyColumn {
                items(dogs) { dog ->
                    DogRow(dog, navController)
                }
            }
        }
    }
}

@Composable
fun DogRow(dog: DogEntity, navController: NavController) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { navController.navigate("details/${dog.id}") }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(dog.url),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(text = "Name: ${dog.name ?: "Unknown"}")
                Text(text = "Breed group: ${dog.breedGroup ?: "Unknown"}")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogDetailsScreen(dogId: String, viewModel: DogViewModel, navController: NavController) {
    val dog = viewModel.dogsFromDb.value?.find { it.id == dogId }
    dog?.let {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(PaleYellow)) {
            Column {
                TopAppBar(
                    title = { Text(text = "Dog Details") },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple40),
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
                Image(
                    painter = rememberImagePainter(it.url),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Name: ${it.name ?: "Unknown"}",
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
                )
                Text(
                    text = "Breed group: ${it.breedGroup ?: "Unknown"}",
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
                )
                Text(
                    text = "Life span: ${it.lifeSpan ?: "Unknown"}",
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
                )
                Text(
                    text = "Temperament: ${it.temperament ?: "Unknown"}",
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewDogListScreen() {
//    val repository = repository // MockDogRepository()
//    val viewModel = DogViewModel(repository)
//    val navController = rememberNavController()
//    DogListScreen(viewModel, navController)
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewDogRow() {
//    val navController = rememberNavController()
//    val dog = DogEntity(
//        id = "1",
//        name = "Buddy",
//        breedGroup = "Hound",
//        bredFor = "Companionship",
//        lifeSpan = "10-12 years",
//        temperament = "Friendly",
//        url = ""
//    )
//    DogRow(dog, navController)
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewDogDetailsScreen() {
//    val repository = MockDogRepository()
//    val viewModel = DogViewModel(repository)
//    val navController = rememberNavController()
//    DogDetailsScreen(dogId = "1", viewModel, navController)
//}