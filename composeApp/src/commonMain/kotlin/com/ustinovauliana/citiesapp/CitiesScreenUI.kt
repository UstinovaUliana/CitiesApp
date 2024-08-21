package com.ustinovauliana.citiesapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState


@Composable
fun SearchMainView(component: MainComponent) {

    val model by component.models.subscribeAsState()

    val queryState = component.rememberEditableUserInputState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchFieldView(queryState.queryState,queryState::updateText, queryState::clearText)
        when (model.citiesResult) {
            is CitiesResult.Error -> {
                ErrorView()
            }

            is CitiesResult.Success -> {
                CitiesView((model.citiesResult as CitiesResult.Success<List<City>>).data)
            }

            is CitiesResult.Start -> {
                StartingView()
            }

            else -> {
                ProgressView()
            }
        }

    }
}

@Composable
private fun ErrorView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Error,
            modifier = Modifier.size(100.dp),
            tint = MaterialTheme.colors.onBackground,
            contentDescription = "Edit Icon"
        )
        Text(
            text = "error: something went wrong",
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
private fun StartingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Keyboard,
            modifier = Modifier.size(100.dp),
            tint = MaterialTheme.colors.onBackground,
            contentDescription = "Edit Icon"
        )
        Text(
            text = "Start typing!",
            style = MaterialTheme.typography.h5
        )
    }

}

@Composable
private fun ProgressView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun CitiesView(citiesList: List<City>) {
    if (citiesList.isEmpty()) {
        NothingView()
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
private fun NothingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.QuestionMark,
            modifier = Modifier.size(100.dp),
            tint = MaterialTheme.colors.onBackground,
            contentDescription = "Edit Icon"
        )
        Text(
            text = "Nothing found",
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
private fun SearchFieldView(
    query: String,
    onQueryChange: (String) -> Unit,
    onQueryCleared: () -> Unit
) {
    TextField(
        value = query,
        onValueChange = { onQueryChange(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = onQueryCleared) {
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = "Clear Icon"
                )
            }
        },
        textStyle = MaterialTheme.typography.h5,
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

