package com.ustinovauliana.citiesapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ustinovauliana.citiesapp.Greeting

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var query by rememberSaveable {
                mutableStateOf("enter")
            }
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingView(query, {text -> query = text}, {query = ""})
                }
            }
        }
    }
}

@Composable
fun GreetingView(query: String, onQueryChange: (String) -> Unit, onQueryCleared: () -> Unit, citiesList: List<String> =  listOf("City1","City2","City3")) {

    Column(
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = onQueryCleared) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "Clear Icon"
                    )
                }
            },
            textStyle = MaterialTheme.typography.titleLarge,
            singleLine = true,
            shape = RoundedCornerShape(30.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )
        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ){
            items(citiesList) {
                item ->
                CityItem(item)
            }
        }
    }
}

@Composable
private fun CityItem(item: String) {
    Column(
    ) {
        Text(
            text = item,
            style = MaterialTheme.typography.titleMedium,

            modifier = Modifier.padding(10.dp)
        )
        Text(
            "dsdsd",
            style = MaterialTheme.typography.bodySmall,

            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Divider(Modifier.padding(10.dp), color = Color.Black, thickness = 1.dp)
    }
}

@Preview(widthDp = 480, heightDp = 1080, showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        var query by rememberSaveable {
            mutableStateOf("enter")
        }
        GreetingView(query, {text -> query = text}, {query = ""})
    }
}
