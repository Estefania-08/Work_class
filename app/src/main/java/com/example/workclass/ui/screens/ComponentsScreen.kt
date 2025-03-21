package com.example.workclass.ui.screens


import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.workclass.R
import components.PostCardCompactComponent
import components.PostCardComponent
import data.model.MenuModel
import data.model.PostCardModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.logging.Filter
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Popup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.delay
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset

@Composable
fun ComponentsScreen(navController: NavHostController){
 val menuOptions = arrayOf(
     MenuModel(1,"Buttons", "buttons", Icons.Filled.Home),
     MenuModel(2,"Floating Buttons", "floating-buttons", Icons.Filled.AccountBox) ,
     MenuModel(3,"Progress","progress",Icons.Filled.Star),
     MenuModel(4,"Chips", "chips", Icons.Filled.Check),
     MenuModel(5,"Sliders", "sliders", Icons.Filled.AddCircle),
     MenuModel(6,"Switches", "switches", Icons.Filled.Warning),
     MenuModel(7,"Badges", "badges", Icons.Filled.Info),
     MenuModel(8,"SnackBars", "snack-bars", Icons.Filled.Delete),
     MenuModel(9,"AlertDialogs", "alert-dialogs", Icons.Filled.LocationOn),
     MenuModel(10,"Bars", "bars", Icons.Filled.Home),
     MenuModel(11, "Input Fields", "input-fields", Icons.Filled.Edit),
     MenuModel(12, "Date Pickers", "date-pickers", Icons.Filled.DateRange),
     MenuModel(13, "Pull To Refresh", "pull-to-refresh", Icons.Filled.Refresh),
     MenuModel(14, "Bottom Sheets", "bottom-sheets", Icons.Filled.Face),
     MenuModel(15, "Segmented Buttons", "segmented-buttons", Icons.Filled.List),




 )

    var option by rememberSaveable { mutableStateOf("bars") }
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                LazyColumn {
                    items(menuOptions){item ->
                      NavigationDrawerItem(
                       icon = { Icon(item.icon, contentDescription = "") },
                       label = { Text(item.title) },
                       selected = false,
                       onClick = {
                        option = item.option
                        scope.launch {
                            drawerState.apply {
                                close()
                            }
                        }
                    }
                )
            }
            }

            }
        }
    ) {
        Column {
            when(option){
                "buttons" -> {
                    Buttons()
                }
                "floating-buttons" -> {
                    FloatingButtons()
                }
                "progress" -> {
                    Progress()
                }
                "chips" -> {
                    Chips()
                }
                "sliders" -> {
                    Sliders()
                }
                "switches" -> {
                    Switches()
                }
                "badges" -> {
                    Badges()
                }
                "snack-bars" -> {
                    SnackBars()
                }
                "alert-dialogs" -> {
                    AlertDialogs()
                }
                "bars"->{
                    Bars()
                }
                "input-fields"->{
                    InputFields()
                }
                "date-pickers"->{
                    DatePickers()
                }
                "pull-to-refresh"->{
                    PullToRefresh()
                }
                "bottom-sheets"->{
                    BottomSheets()
                }
                "segmented-buttons"->{
                    SegmentedButtons()
                }
            }
        }
        Text(option)
    }
}



//@Preview(showBackground = true)
@Composable
fun Buttons(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        //Button styles
        Button(onClick = {}) {
            Text("Filled")
        }

        FilledTonalButton(onClick = {}) {
            Text("Tonal")
        }

        OutlinedButton(onClick = {}) {
            Text("Outline")
        }

        ElevatedButton(onClick = {}) {
            Text("Elevated")
        }

        TextButton(onClick = {}) {
            Text("Text")
        }
    }
}
//@Preview(showBackground = true)
@Composable
fun FloatingButtons(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        FloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Add Button")
        }
        SmallFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Add Button")
        }
        LargeFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Add Button")
        }
        //Extended floating action button can display a text
        ExtendedFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Add Button")
            Text("Button")
        }
    }
}
//@Preview(showBackground = true)
@Composable
fun Progress(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
        )
        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp)
        )
    }
}

//@Preview(showBackground = true)
@Composable
fun Chips() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        AssistChip(
            onClick = {},
            label = { Text("AssistChip") },
            leadingIcon = {
                Icon(
                    Icons.Filled.AccountBox,
                    contentDescription = "Assist Chip",
                    modifier = Modifier
                        .size(AssistChipDefaults.IconSize)
                )
            }
        )
        //mutableStateOf is used to keep an element in a particular state
        //even if there's changes or updates in the interface
        var selected by remember { mutableStateOf(false) }
        FilterChip(
            //selected defines if the button is pressed or not
            selected = selected,
            //This will switch the current state
            // of FilterChip to the opposite
            onClick = {selected = !selected},
            label = {Text("Filter Chip")},
            leadingIcon = if(selected){
                {
                    Icon(
                        Icons.Filled.AccountBox,
                        contentDescription = "Assist Chip",
                        modifier = Modifier
                            .size(AssistChipDefaults.IconSize)
                    )
                }
            }else {
                null
            }
        )
        InputChipExample("Dismiss", {})

    }
}

@Composable
fun InputChipExample(
    text:String,
    onDismiss: () -> Unit
){
    var enabled by remember{ mutableStateOf(true) }
    if(!enabled) return
    InputChip(
        label = {Text(text)},
        selected = enabled,
        onClick = {
            onDismiss()
            enabled = !enabled
        },
        avatar = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "User Icon",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        trailingIcon = {
            Icon(
                Icons.Filled.Close,
                contentDescription = "Cross Icon",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        }
    )
}
//@Preview
@Composable
fun Sliders(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ){
        var SliderPosition by remember { mutableStateOf(50f) }
        Slider(
            value = SliderPosition,
            //updates the slider state according to the users interaction with it
            onValueChange = {SliderPosition = it},
            steps = 10,
            valueRange = 0f .. 100f,
        )
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
            text = SliderPosition.toString()
        )
    }
}

//@Preview
@Composable
fun Switches() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        var checked by remember { mutableStateOf(true) }
        Switch(
            checked = checked,
            onCheckedChange = {checked = it},
        )
        var checked2 by remember{ mutableStateOf(true) }
        Switch(
            checked = checked2,
            onCheckedChange = {checked2 = it},
            //adding an icon that will show only when the switch is active
            thumbContent = if (checked2){
                {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Check Switch",
                        Modifier.size(InputChipDefaults.AvatarSize)
                    )
                }
            }else {
                null
            }
        )
        var checked3 by remember { mutableStateOf(true) }
        Checkbox(
            checked = checked3,
            onCheckedChange = {checked3 = it}
        )
    }
}
//@Preview
@Composable
fun Badges() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        var itemCount by remember { mutableStateOf(0) }
        BadgedBox(
            badge = {
                if (itemCount > 0){
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                    {
                        Text(itemCount.toString())
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Shopping Cart icon"
            )
        }
        Button(
            onClick = {itemCount++}
        ) {
            Text("Add item")
        }
    }
}
//@Preview
@Composable
fun SnackBars() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        //val = static
        //var = non static
        val snackState = remember { SnackbarHostState() }
        val snackScope = rememberCoroutineScope()

        SnackbarHost(hostState = snackState)

        fun launchSnackBar(){
            snackScope.launch { snackState.showSnackbar("The message was sent") }
        }
        //invoking the previous function in a shorter way
        Button(::launchSnackBar){
            Text("Send Message")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AlertDialogs() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        var showAlertDialog by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("") }

        //without "== true" statement, the conditional is checking for the var to be true
        if (showAlertDialog) {
            AlertDialog(
                icon = { Icon(Icons.Filled.Info, contentDescription = "Info Icon") },
                title = { Text("Confirm Deletion") },
                text = { Text("Do you really want to delete this file?") },
                //to close the dialog when clicking any part of the screen that is not the Alert Dialog
                onDismissRequest = {},
                confirmButton = {
                    TextButton(
                        onClick = {
                            selectedOption = "Confirmed"
                            showAlertDialog = false
                        }
                    ) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            selectedOption = "Canceled"
                            showAlertDialog = false
                        }
                    ) {
                        Text("No")
                    }
                }
            )
        }
        Button(onClick = { showAlertDialog = true }) {
            Text("Delete File")
        }
        Text(selectedOption)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bars(){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        LargeTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.secondary
            ),
            title = { Text("Screen Title")},
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search button")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Settings, contentDescription = "Search button")
                }
            }
        )
        /*
        val arrayPosts = arrayOf(
            PostCardModel(1,"Title 1","Text 1", R.drawable.si),
            PostCardModel(2,"Title 2","Text 2", R.drawable.si),
            PostCardModel(3,"Title 3","Text 3", R.drawable.si),
            PostCardModel(4,"Title 4","Text 4", R.drawable.si),
            PostCardModel(5,"Title 5","Text 5", R.drawable.si),
            PostCardModel(6,"Title 6","Text 6", R.drawable.si),
            PostCardModel(7,"Title 7","Text 7", R.drawable.si),
            PostCardModel(8,"Title 8","Text 8", R.drawable.si),
            PostCardModel(9,"Title 9","Text 9", R.drawable.si)
        )
        LazyVerticalGrid (
            columns = GridCells.Adaptive(minSize = 160.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ){
            items(arrayPosts){ item ->
                PostCardComponent(item.id,item.title, item.text, item.image)
            }
        }*/

        Column (
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ){
            Adaptive()
        }
        BottomAppBar (
            containerColor = Color.LightGray,
            contentColor = Color.Black
        ){
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {},
            ){
                Icon(imageVector  = Icons.Filled.Star, contentDescription = "")
            }
            IconButton(
                //modifier = Modifier,
                modifier = Modifier.weight(1f),
                onClick = {},
            ){
                Icon(imageVector  = Icons.Filled.Home, contentDescription = "")
            }
            IconButton(
                //modifier = Modifier,
                modifier = Modifier.weight(1f),
                onClick = {},
            ){
                Icon(imageVector  = Icons.Filled.Delete, contentDescription = "")
            }
            IconButton(
                //modifier = Modifier,
                modifier = Modifier.weight(1f),
                onClick = {},
            ){
                Icon(imageVector  = Icons.Filled.Add, contentDescription = "")
            }
            IconButton(
                //modifier = Modifier,
                modifier = Modifier.weight(1f),
                onClick = {},
            ){
                Icon(imageVector  = Icons.Filled.Share, contentDescription = "")
            }
        }
    }
}

@Composable
fun Adaptive(){
    var widowSize = currentWindowAdaptiveInfo().windowSizeClass
    var height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    var width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    //Compact width < 600 dp phone Portatil
    // Medium Width >= 600 dp < 840 dp Tablet Portatil
    // Expanded Width >= 840 do Tablet Landscape

    //Compact Height < 480 dp Phone Landscape
    // Medium Height >= 480 dp <900 dp Tablet en Landscape Phone Portrait
    // Expanded height >= 900 dp Tablet Portrait
    val arrayPosts = arrayOf(
        PostCardModel(1,"Title 1","Text 1", R.drawable.si),
        PostCardModel(2,"Title 2","Text 2", R.drawable.si),
        PostCardModel(3,"Title 3","Text 3", R.drawable.si),
        PostCardModel(4,"Title 4","Text 4", R.drawable.si),
        PostCardModel(5,"Title 5","Text 5", R.drawable.si),
        PostCardModel(6,"Title 6","Text 6", R.drawable.si),
        PostCardModel(7,"Title 7","Text 7", R.drawable.si),
        PostCardModel(8,"Title 8","Text 8", R.drawable.si),
        PostCardModel(9,"Title 9","Text 9", R.drawable.si)
    )
    if (width == WindowWidthSizeClass.COMPACT ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ){
            items(arrayPosts){ item ->
                PostCardComponent(item.id,item.title, item.text, item.image)
            }
        }
    } else if (height == WindowHeightSizeClass.COMPACT)
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ){
            items(arrayPosts){ item ->
                PostCardCompactComponent(item.id,item.title, item.text, item.image)
            }
        }
    }
}

@Composable
fun InputFields() {
        var text by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter text") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Filled.Close else Icons.Filled.Done,
                            contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                        )
                    }
                }
            )
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheets() {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { showBottomSheet = true }) {
            Text("Show Bottom Sheet")
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(" bottom sheet")
                    Button(onClick = { showBottomSheet = false }) {
                        Text("Close")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickers() {
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { showDatePicker = true }) {
            Icon(Icons.Filled.DateRange, contentDescription = "Date Picker")
            Text("Select Date")
        }

        Text("Selected Date: ${selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}")

        if (showDatePicker) {
            val datePickerState = rememberDatePickerState()
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                selectedDate = Instant.ofEpochMilli(millis)
                                    .atZone(ZoneOffset.UTC)
                                    .toLocalDate()
                            }
                            showDatePicker = false
                        }
                    ) {
                        Text("OK")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedButtons() {

        val options = listOf("Day", "Month", "Week")
        var selectedOption by remember { mutableStateOf(options[0]) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SingleChoiceSegmentedButtonRow {
                options.forEach { option ->
                    SegmentedButton(
                        selected = selectedOption == option,
                        onClick = { selectedOption = option },
                        shape = when (option) {
                            options.first() -> MaterialTheme.shapes.small.copy(
                                topEnd = CornerSize(0),
                                bottomEnd = CornerSize(0)
                            )
                            options.last() -> MaterialTheme.shapes.small.copy(
                                topStart = CornerSize(0),
                                bottomStart = CornerSize(0)
                            )
                            else -> MaterialTheme.shapes.small.copy(
                                topStart = CornerSize(0),
                                topEnd = CornerSize(0),
                                bottomStart = CornerSize(0),
                                bottomEnd = CornerSize(0)
                            )
                        }
                    ) {
                        Text(option)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Selected: $selectedOption",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

@Preview(showBackground = true)
@Composable
fun PreviewInputFields() {
    InputFields()
}

@Composable
fun PullToRefresh() {
    var refreshing by remember { mutableStateOf(false) }
    var items by remember { mutableStateOf((1..20).toList()) }
    val scope = rememberCoroutineScope()

    AndroidView(
        factory = { context ->
            SwipeRefreshLayout(context).apply {
                setOnRefreshListener {
                    scope.launch {
                        refreshing = true
                        delay(1500)
                        items = (21..40).toList()
                        refreshing = false
                        isRefreshing = false
                    }
                }
                addView(
                    androidx.compose.ui.platform.ComposeView(context).apply {
                        setContent {
                            LazyColumn(Modifier.fillMaxSize()) {
                                items(items.size) { index ->
                                    Text("Elemento ${items[index]}", Modifier.padding(16.dp))
                                }
                            }
                        }
                    }
                )
            }
        },
        update = { layout ->
            layout.isRefreshing = refreshing
        }
    )
}
// incluir los nuevos componentes
