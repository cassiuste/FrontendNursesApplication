import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Home(int: Int){
    Row(modifier = Modifier.size(50.dp)){
        Text(text = int.toString())
        Text(text = "El 5 es un miercoles")
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Home(5)
}