package pl.mobilespot.futuremirror.presentation.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchRoute(viewModel: SearchViewModel = hiltViewModel()) {
    val names by viewModel.findNames().collectAsStateWithLifecycle(initialValue = emptyList())

    SearchScreen(names, onTextChanged =  viewModel::setSearchingText)
}