package com.ustinovauliana.citiesapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
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


@Composable
fun SearchMainView(component: MainComponent) {

    val model by component.models.subscribeAsState()

    Column {
        SearchFieldView(model.query, component::onQueryChange, component::onQueryCleared)
        when (model.citiesResult) {
            is CitiesResult.Error -> {
                Text("error: something went wrong")
            }

            is CitiesResult.Success -> {
                CitiesColumnView((model.citiesResult as CitiesResult.Success<List<City>>).data)
            }

            is CitiesResult.Start -> {
                Text("Start typing!")
            }

            else -> {
                CircularProgressIndicator()
            }
        }

    }
}

@Composable
private fun CitiesColumnView(citiesList: List<City>) {
    if (citiesList.isEmpty()) {
        Text("Nothing found")
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(citiesList) { item ->
                CityItem(item)
            }
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
    Column {
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

