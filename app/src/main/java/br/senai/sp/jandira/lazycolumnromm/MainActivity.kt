package br.senai.sp.jandira.lazycolumnromm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lazycolumnromm.components.ProductCard
import br.senai.sp.jandira.lazycolumnromm.dao.repository.ProductRepository
import br.senai.sp.jandira.lazycolumnromm.model.Product
import br.senai.sp.jandira.lazycolumnromm.ui.theme.LazyColumnRommTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnRommTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    var nameState by remember {
        mutableStateOf("")
    }

    var priceState by remember {
        mutableStateOf("")
    }

    var productsState by remember {
        mutableStateOf(listOf<Product>())
    }

    val context = LocalContext.current
    val productRepository = ProductRepository(LocalContext.current)
    productsState = productRepository.getProductsList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Listas com Jetpack Compose")

        OutlinedTextField(
            value = nameState,
            onValueChange = {
                nameState = it
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = priceState,
            onValueChange = {
                priceState = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val p = Product(
                    productName = nameState,
                    productPrice = priceState.toDouble()
                )
               val newId = productRepository.save(p)
                productsState = productRepository.getProductsList()
                Toast.makeText(context,"$newId", Toast.LENGTH_SHORT)

            },
            modifier = Modifier
        ) {
            Text(text = "Salvar")
        }

        LazyColumn(modifier = Modifier.padding(16.dp)) {
          items(productsState) {
              ProductCard(product = Product())
          }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DefaultPreview() {
    LazyColumnRommTheme {
        Greeting("Android")
    }
}