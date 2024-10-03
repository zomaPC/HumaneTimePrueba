package com.example.humanetime.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.humanetime.R
import com.example.humanetime.model.Empleado
import com.example.humanetime.model.menuItems
import com.example.humanetime.session.Session
import com.example.humanetime.ui.viewmodel.EmployeeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    employeeViewModel: EmployeeViewModel = koinViewModel()
) {
   LaunchedEffect(Unit) {
       employeeViewModel.searchEmployees()
   }


    val employees = employeeViewModel.employees.observeAsState(emptyList()).value

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onCloseDrawer = { scope.launch { drawerState.close() } }
            )
        },
        scrimColor = Color.White
    ) {
        Scaffold(
            topBar = {
                Surface(color = Color(0xFF6200EE)) {
                    TopAppBar(
                        title = {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Human eTime+",
                                    color = Color.Blue,
                                    style = TextStyle(fontSize = 24.sp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentSize(Alignment.Center)
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),

                                ) {

                                    Text(
                                        text = "ADMIN",
                                        color = Color.Blue,
                                        style = TextStyle(fontSize = 12.sp),
                                        modifier = Modifier.align(Alignment.CenterVertically)
                                            .padding(start = 200.dp)
                                    )
                                }
                            }
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = Color.Blue
                                )
                            }
                        }
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { },
                    containerColor = Color(0xFF6200EE) // Color morado
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(employees) { employee ->
                    UserItem(employee)
                }
            }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun DrawerContent(onCloseDrawer: () -> Unit) {
    val expandedItems = remember { mutableStateMapOf<String, Boolean>() }

    Column(modifier = Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    model = Session.avatarURL,
                    contentDescription = "Avatar",
                    placeholder = painterResource(R.drawable.baseline_person_24),
                    error = painterResource(R.drawable.baseline_person_24),
                    modifier = Modifier
                        .size(64.dp)
                        .padding(end = 8.dp)
                )

                    Text(
                        text = Session.fullName,
                        style = TextStyle(fontSize = 20.sp)
                    )

            }
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(menuItems) { menuItem ->
                val isExpanded = expandedItems[menuItem.title] ?: false

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            expandedItems[menuItem.title] = !isExpanded
                            onCloseDrawer()
                        },
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = menuItem.icon,
                        contentDescription = menuItem.title,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = menuItem.title,
                            style = TextStyle(fontSize = 18.sp)
                        )

                        if (isExpanded) {
                            Text(
                                text = menuItem.dummyData,
                                style = TextStyle(fontSize = 14.sp),
                                color = Color.Gray
                            )
                        }
                    }
                }

                if (menuItem.title == "Configuración") {
                    Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}

@Composable
fun UserItem(empleado: Empleado) {
    Text(
        text = "ID: {${empleado.idEmpleado}}",
        style = TextStyle(fontSize = 14.sp)
    )
    Text(
        text = "${empleado.nombre} ${empleado.apellidoPat} ${empleado.apellidoMat}",
        style = TextStyle(fontSize = 18.sp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
}
