package com.itskidan.tscapp.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itskidan.domain.model.DrawerItem
import com.itskidan.tscapp.ui.common.getFilledIconByName
import com.itskidan.tscapp.ui.common.getOutlinedIconByName
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(
    items: List<DrawerItem>,
    onDrawerSelected: (DrawerItem) -> Unit,
    onClose: () -> Unit
) {
    val scope = rememberCoroutineScope()

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    ModalDrawerSheet(
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = {
                    Text(text = item.labelText)
                },
                selected = index == selectedItemIndex,
                onClick = {
                    selectedItemIndex = index
                    scope.launch {
                        onDrawerSelected(item)
                        onClose()
                    }
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            getFilledIconByName(item.labelText)
                        } else getOutlinedIconByName(item.labelText),
                        contentDescription = item.descriptionText
                    )
                },
                badge = {
                    item.badgeCount?.let {
                        Text(text = it.toString())
                    }
                },
            )
        }

    }

}