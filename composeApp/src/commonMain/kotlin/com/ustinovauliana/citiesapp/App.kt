package com.ustinovauliana.citiesapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.ustinovauliana.citiesapp.di.DiTree.instance

@Composable
fun App() {

    val citiesRepository = instance<CitiesRepository>()
    /*
    CoroutineScope(Dispatchers.IO).launch {

        val cities2 = citiesRepository.searchCities("moscow")
        withContext(Dispatchers.Main) {
            println("cities = $cities2")
        }
    }

     */
    MaterialTheme {

        val root = MainComponent(
            storeFactory = DefaultStoreFactory(),
            repository = citiesRepository)

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            SearchMainView(root)
        }

    }
}

@Composable
fun SearchMainView(component: MainComponent
    /*query: String, onQueryChange: (String) -> Unit, onQueryCleared: () -> Unit, citiesList: List<City>*/ ) {

    val model by component.models.subscribeAsState()



    Column(
    ) {
        SearchFieldView(model.query, component::onQueryChange, component::onQueryCleared)
        if (model.cities!=null) {
            CitiesColumnView(model.cities!!)
        } else
            Text ("Nothing found")
    }
}

@Composable
private fun CitiesColumnView(citiesList: List<City>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(citiesList) { item ->
            CityItem(item)
        }
    }
}

@Composable
private fun SearchFieldView(
    query: String,
    onQueryChange: (String) -> Unit,
    onQueryCleared: () -> Unit
) {
    var query1 by remember { mutableStateOf("") }
    TextField(
        value = query1,
        onValueChange = { onQueryChanged: String ->
            query1 = onQueryChanged
            onQueryChange(query1)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                query1 = ""
                onQueryCleared()
            }) {
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = "Clear Icon"
                )
            }
        },
        textStyle = MaterialTheme.typography.h3,
        singleLine = true,
        shape = RoundedCornerShape(30.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    )
}

@Composable
private fun CityItem(item: City) {
    Column(
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.subtitle1,

            modifier = Modifier.padding(10.dp)
        )
        Text(
            item.country,
            style = MaterialTheme.typography.body2,

            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Divider(Modifier.padding(10.dp), color = Color.Black, thickness = 1.dp)
    }
}

