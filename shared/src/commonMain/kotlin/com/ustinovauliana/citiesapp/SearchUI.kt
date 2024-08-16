package com.ustinovauliana.citiesapp

import androidx.compose.runtime.Composable

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


