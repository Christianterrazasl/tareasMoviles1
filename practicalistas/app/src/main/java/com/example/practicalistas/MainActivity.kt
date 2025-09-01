package com.example.practicalistas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicalistas.R
import com.example.practicalistas.ui.theme.PracticaListasTheme
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaListasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Contactlist(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier) {
    val nameList = arrayListOf(
        "Ana", "Juan", "Pedro", "Maria", "Luis", "Carmen", "Jose", "Lucia", "Miguel", "Sofia"
    )
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(nameList) { it ->
            Text(text = it, modifier = modifier.padding(8.dp))
        }
    }
}

@Composable
fun Contactlist(
    modifier: Modifier = Modifier
) {
    val contacts = getContactList()
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(contacts) { it ->
            ContactItem(it)
            HorizontalDivider(thickness = 1.dp)
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Spacer(modifier = Modifier.size(8.dp))
    Row {
        Image(
            painter = rememberAsyncImagePainter(model = contact.imageUrl),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Text(
            contact.name + " " + contact.lastName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
    Spacer(modifier = Modifier.size(8.dp))

}

fun getContactList(): ArrayList<Contact> {
    return arrayListOf(
        Contact(1, "Ana", "Perez", "123456789", "https://placehold.net/avatar.png"),
        Contact(2, "Juan", "Gomez", "987654321", "https://placehold.net/avatar-2.png"),
        Contact(3, "Pedro", "Lopez", "456789123", "https://placehold.net/avatar-3.png"),
        Contact(4, "Maria", "Garcia", "789123456", "https://placehold.net/avatar-4.png"),
        Contact(5, "Luis", "Martinez", "321654987", "https://placehold.net/avatar-5.png"),
        Contact(6, "Carmen", "Sanchez", "654987321", "https://placehold.net/avatar.png")
    )
}

@Preview(showBackground = true)
@Composable
fun ContactListPreview() {
    PracticaListasTheme {
        Contactlist()
    }
}

@Preview(showBackground = true)
@Composable
fun ContactItemPreview() {
    PracticaListasTheme {
        ContactItem(Contact(1, "Ana", "Perez", "123456789", "https://placehold.net/avatar.png"))
    }
}


@Preview(showBackground = true)
@Composable
fun MessageListPreview() {
    PracticaListasTheme {
        MessageList()
    }
}