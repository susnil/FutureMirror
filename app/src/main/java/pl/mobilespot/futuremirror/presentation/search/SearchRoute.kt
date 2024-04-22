package pl.mobilespot.futuremirror.presentation.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchRoute(viewModel: SearchViewModel = hiltViewModel()) {
    val names by viewModel.result.collectAsState()


    SearchScreen(names, onTextChanged =  viewModel::setSearchingText)
}