package com.ustinovauliana.citiesapp.presentation

import androidx.compose.foundation.background
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
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.ustinovauliana.citiesapp.domain.models.City
import com.ustinovauliana.citiesapp.presentation.integration.MainComponent
import me.sample.library.resources.Res
import me.sample.library.resources.clear_icon
import me.sample.library.resources.error_icon
import me.sample.library.resources.error_text
import me.sample.library.resources.keyboard_icon
import me.sample.library.resources.nothing_found
import me.sample.library.resources.question_mark_icon
import me.sample.library.resources.search_icon
import me.sample.library.resources.start_typing
import me.sample.library.resources.template_country_population
import org.jetbrains.compose.resources.stringResource


@Composable
fun SearchMainView(component: MainComponent) {

    val model by component.models.subscribeAsState()

    val queryState = component.rememberEditableUserInputState()

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 5.dp )
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchFieldView(queryState.queryState,queryState::updateText, queryState::clearText)
        when (model.citiesResult) {
            is CitiesResult.Error -> {
                ErrorView((model.citiesResult as CitiesResult.Error).exception)
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
private fun ErrorView(exception: Exception) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Error,
            modifier = Modifier.size(100.dp),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = stringResource(Res.string.error_icon)
        )
        Text(
            text = stringResource(Res.string.error_text),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
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
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = stringResource(Res.string.keyboard_icon),
        )
        Text(
            text = stringResource(Res.string.start_typing),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge
        )
    }

}

@Composable
private fun ProgressView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.secondaryContainer,
        )
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
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = stringResource(Res.string.question_mark_icon),
        )
        Text(
            text = stringResource(Res.string.nothing_found),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge
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
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = stringResource(Res.string.search_icon),
            )
        },
        trailingIcon = {
            IconButton(onClick = onQueryCleared) {
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = stringResource(Res.string.clear_icon),
                )
            }
        },
        textStyle = MaterialTheme.typography.headlineMedium,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.onBackground,
            focusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground,
            cursorColor = MaterialTheme.colorScheme.secondaryContainer,
            backgroundColor = MaterialTheme.colorScheme.onSecondaryContainer,
            ),
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
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = stringResource(Res.string.template_country_population,item.country, item.population?:"unknown"),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Divider(Modifier.padding(10.dp), color = Color.Black, thickness = 1.dp)
    }
}

