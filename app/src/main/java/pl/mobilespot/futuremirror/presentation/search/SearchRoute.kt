package pl.mobilespot.futuremirror.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchRoute(viewModel: SearchViewModel = hiltViewModel()) {
    val result by viewModel.result.collectAsStateWithLifecycle()
    SearchScreen(result.names, onTextChanged = viewModel::setSearchingText)
}
