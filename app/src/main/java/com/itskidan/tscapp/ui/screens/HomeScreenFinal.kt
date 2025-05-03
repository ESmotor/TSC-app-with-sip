package com.itskidan.tscapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itskidan.tscapp.R
import com.itskidan.tscapp.navigation.BottomNavItem
import com.itskidan.tscapp.ui.theme.AppTheme
import com.itskidan.tscapp.ui.theme.LocalPaddingValues
import com.itskidan.tscapp.ui.theme.LocalShapes

@Composable
fun HomeFinal() {
    HomeFinalContent()
}


@Composable
fun HomeFinalContent() {
    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Directory,
        BottomNavItem.Help,
        BottomNavItem.Events,
        BottomNavItem.More
    )

    var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }

    Scaffold(
        topBar = { },
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = selectedItem == item,
                        onClick = { selectedItem = item },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: Call or chat */ }) {
                Icon(Icons.Default.Call, contentDescription = "Contact")
            }
        }
    ) { paddingValues ->

        MainContent(modifier = Modifier.padding(paddingValues))
    }


}


@Composable
fun MainContent(
    modifier: Modifier = Modifier
) {
    val paddingValues = LocalPaddingValues.current


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = paddingValues.medium),
        contentPadding = PaddingValues(vertical = paddingValues.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            GreetingBar(name = "Valera Smirnov", date = "May 2, 2025")
        }

        item {
            EmergencyButton()
        }

        item {
            ServicesGrid(
                listOf(
                    "Emergency",
                    "Police",
                    "Fire",
                    "Ambulance",
                    "Police",
                    "Fire",
                    "Ambulance"
                )
            )
        }

        item {
            NewsHorizontalList(
                listOf(
                    NewsItem(
                        R.drawable.default_avatar,
                        "London is the capital of Great Britain",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                        "02.05.2025"
                    ),
                    NewsItem(R.drawable.default_avatar, "Title 2", "Description 2", "03.06.2025"),
                    NewsItem(R.drawable.default_avatar, "Title 3", "Description 3", "04.07.2025"),
                    NewsItem(R.drawable.default_avatar, "Title 4", "Description 4", "12.08.2025"),
                    NewsItem(R.drawable.default_avatar, "Title 5", "Description 5", "22.09.2025"),
                    NewsItem(R.drawable.default_avatar, "Title 6", "Description 6", "10.04.2025")
                )
            )
        }

    }
}


@Composable
fun GreetingBar(name: String, date: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Hi, $name!", style = MaterialTheme.typography.titleMedium)
            Text(date, style = MaterialTheme.typography.bodyMedium)
        }
        IconButton(onClick = { /* bell */ }) {
            Icon(Icons.Default.Notifications, contentDescription = "Notifications")
        }
    }
}

@Composable
fun EmergencyButton() {
    val shapes = LocalShapes.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color.Gray, RoundedCornerShape(shapes.medium))
    ) {
        // Here you can use the Accompanist Pager library
        Text("Emergency button", modifier = Modifier.align(Alignment.Center))
    }
}


@Composable
fun ServicesGrid(services: List<String>) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = (screenWidth - 8.dp * 2) / 3  // 2 карточки + отступы

    Column() {
        Text("Services", style = MaterialTheme.typography.titleMedium)
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),  // Фиксировано 2 строки
            modifier = Modifier
//                .background(Color.Blue)
                .height(cardWidth * 2 * 3 / 4 + 8.dp * 3),  // 2 карточки + отступы
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 4.dp),
            userScrollEnabled = true
        ) {
            items(services) { service ->
                Card(
                    modifier = Modifier
                        .width(cardWidth)
                        .aspectRatio(4f / 3f),  // Квадратные
                    shape = MaterialTheme.shapes.medium,
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(service, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}


@Composable
fun NewsHorizontalList(newsList: List<NewsItem>) {
    Column {
        Text("News", style = MaterialTheme.typography.titleMedium)
        LazyRow(
//        modifier = Modifier.background(Color.Blue),
            contentPadding = PaddingValues(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(newsList) { item ->
                NewsCard(
                    image = painterResource(item.imageRes),
                    title = item.title,
                    description = item.description,
                    date = item.date
                )
            }
        }
    }
}


@Composable
fun NewsCard(
    image: Painter,
    title: String,
    description: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(195.dp)
            .height(260.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth()
                    .height(100.dp),
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = date,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
    }
}


data class NewsItem(
    val imageRes: Int,
    val title: String,
    val description: String,
    val date: String
)


@Composable
fun NewsRow2(news: List<String>) {
    Column {
        Text("News", style = MaterialTheme.typography.titleMedium)
        LazyRow(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(news) { item ->
                Card(
                    modifier = Modifier.size(width = 200.dp, height = 100.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(item, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}


@Composable
fun CompactBottomBar(
    selectedItem: Int,
    items: List<BottomNavItem>,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier,
        tonalElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 2.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), // Less than standard 80dp
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                val selected = selectedItem == index
                IconButton(onClick = { onItemSelected(index) }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(22.dp) // slightly less than standard
                        )
                        if (selected) {
                            Text(
                                text = item.label,
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun HomeFinalPreviewLight() {
    AppTheme() {
        HomeFinalContent()
    }
}

@Composable
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
fun HomeFinalPreviewDark() {
    AppTheme() {
        HomeFinalContent()
    }
}