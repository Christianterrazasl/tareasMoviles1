package com.example.practicainstagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.practicainstagram.models.Post
import com.example.practicainstagram.models.Story
import com.example.practicainstagram.ui.theme.Gray
import com.example.practicainstagram.ui.theme.PracticaInstagramTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaInstagramTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->

                    MainScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        StoryList(modifier = Modifier)
        PostList()
    }
}

@Composable
fun StoryList(modifier: Modifier) {
    val stories = arrayListOf(
        Story("Usuario 1", "https://cdn2.thecatapi.com/images/MTYxMjc1OQ.jpg"),
        Story("Usuario 2", "https://cdn2.thecatapi.com/images/dpb.jpg"),
        Story("Usuario 3", "https://cdn2.thecatapi.com/images/d2t.png"),
        Story("Usuario 4", "https://cdn2.thecatapi.com/images/d2i.jpg"),
        Story("Usuario 5", "https://cdn2.thecatapi.com/images/7pg.gif"),
        Story("Usuario 6", "https://cdn2.thecatapi.com/images/7ok.jpg"),
        Story("Usuario 7", "https://cdn2.thecatapi.com/images/a82.jpg"),
        Story("Usuario 8", "https://cdn2.thecatapi.com/images/bn9.jpg"),
        Story("Usuario 9", "https://cdn2.thecatapi.com/images/3dr.jpg"),
        Story("Usuario 10", "https://cdn2.thecatapi.com/images/384.jpg"),
    )
    LazyRow {
        items(stories) { story ->
            Story(story, modifier = modifier)
        }
    }
}

@Composable
fun Story(story: Story, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(16.dp, 6.dp)
    ) {
        AsyncImage(
            model = story.userImage,
            contentDescription = story.userImage,
            placeholder = painterResource(R.drawable.cat),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
//        Image(
//            painter = painterResource(R.drawable.cat),
//            contentDescription = "imagen",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(80.dp)
//                .clip(CircleShape)
//        )

        Text(
            story.username,
            fontSize = 10.sp,
            modifier = modifier
        )
    }
}

@Composable
fun PostList() {
    val posts = arrayListOf(
        Post(
            "Usuario 1",
            "https://cdn2.thecatapi.com/images/MTYxMjc1OQ.jpg",
            "https://cdn2.thecatapi.com/images/dpb.jpg",
            52,
            5
        ),
        Post(
            "Usuario 2",
            "https://cdn2.thecatapi.com/images/dpb.jpg",
            "https://cdn2.thecatapi.com/images/d2t.png",
            34,
            2
        ),
        Post(
            "Usuario 3",
            "https://cdn2.thecatapi.com/images/d2t.png",
            "https://cdn2.thecatapi.com/images/d2i.jpg",
            12,
            0
        ),
        Post(
            "Usuario 4",
            "https://cdn2.thecatapi.com/images/d2i.jpg",
            "https://cdn2.thecatapi.com/images/7pg.gif",
            78,
            8
        ),
        Post(
            "Usuario 5",
            "https://cdn2.thecatapi.com/images/7pg.gif",
            "https://cdn2.thecatapi.com/images/7ok.jpg",
            90,
            10
        ),
        Post(
            "Usuario 6",
            "https://cdn2.thecatapi.com/images/7ok.jpg",
            "https://cdn2.thecatapi.com/images/a82.jpg",
            23,
            1
        ),
        Post(
            "Usuario 7",
            "https://cdn2.thecatapi.com/images/a82.jpg",
            "https://cdn2.thecatapi.com/images/bn9.jpg",
            45,
            3
        ),
        Post(
            "Usuario 8",
            "https://cdn2.thecatapi.com/images/bn9.jpg",
            "https://cdn2.thecatapi.com/images/3dr.jpg",
            67,
            4
        ),
        Post(
            "Usuario 9",
            "https://cdn2.thecatapi.com/images/3dr.jpg",
            "https://cdn2.thecatapi.com/images/384.jpg",
            89,
            6
        ),
        Post(
            "Usuario 10",
            "https://cdn2.thecatapi.com/images/384.jpg",
            "https://cdn2.thecatapi.com/images/MTYxMjc1OQ.jpg",
            11,
            0
        ),
    )
    LazyColumn {
        items(posts) { post ->
            Post(post)
        }
    }
}

@Composable
fun Post(post: Post) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PostHeader(post)
//        Image(
//            painter = painterResource(R.drawable.cat),
//            contentDescription = "imagen",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxWidth()
//        )
        AsyncImage(
            model = post.postImage,
            contentDescription = "imagen",
            placeholder = painterResource(R.drawable.cat),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        PostFooter(post)
    }
}

@Composable
fun PostHeader(post: Post) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
//        Image(
//            painter = painterResource(R.drawable.cat),
//            contentDescription = "imagen",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(30.dp)
//                .clip(CircleShape)
//       )
        AsyncImage(
            model = post.userImage,
            contentDescription = post.username,
            placeholder = painterResource(R.drawable.cat),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
        )
        Text(
            post.username,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun PostFooter(post: Post) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp)
    ) {

        PostButtons()
        Text(
            "${post.likeQty} likes",
            fontSize = 12.sp,
            modifier = Modifier
                .padding(8.dp, 0.dp)
        )
        Text(
            post.username,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(8.dp, 0.dp)
        )
        Text(
            "View all ${post.commentQty} comments",
            fontSize = 12.sp,
            color = Gray,
            modifier = Modifier
                .padding(8.dp, 0.dp)
        )

    }
}

@Composable
fun PostButtons() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
        ) {
            Image(
                painter = painterResource(R.drawable.heart),
                contentDescription = "like",
                modifier = Modifier
                    .size(30.dp)
                    .padding(4.dp)
            )
            Image(
                painter = painterResource(R.drawable.chat),
                contentDescription = "comment",
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp)
            )
            Image(
                painter = painterResource(R.drawable.send),
                contentDescription = "share",
                modifier = Modifier
                    .size(29.dp)
                    .padding(4.dp)
            )
        }
        Image(
            painter = painterResource(R.drawable.save),
            contentDescription = "save",
            modifier = Modifier
                .size(30.dp)
                .padding(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StoryListPreview() {
    PracticaInstagramTheme {
        StoryList(Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun PostPreview() {
    PracticaInstagramTheme {
        Post(
            Post(
                "Usuario 1",
                "https://cdn2.thecatapi.com/images/MTYxMjc1OQ.jpg",
                "https://cdn2.thecatapi.com/images/dpb.jpg",
                52,
                5
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StoryPreview() {
    PracticaInstagramTheme {
        Story(
            Story("Usuario 1", "https://cdn2.thecatapi.com/images/MTYxMjc1OQ.jpg"),
            modifier = Modifier
        )
    }
}