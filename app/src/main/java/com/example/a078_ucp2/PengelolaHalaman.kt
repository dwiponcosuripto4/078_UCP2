package com.example.a078_ucp2

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a078_ucp2.data.SumberData.dosen

enum class PengelolaHalaman {
    Home,
    Formulir,
    Summary
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun _078_UCP2Appbar(
    bisaNavigasiBack: Boolean,
    navigasiUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor =
            MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (bisaNavigasiBack) {
                IconButton(onClick = navigasiUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.btn_back)
                    )
                }
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun _078_UCP2App(
    viewModel: ViewModel = ViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            _078_UCP2Appbar(
                bisaNavigasiBack = false,
                navigasiUp = { /*TODO: implement back navigation */
                }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.stateUI.collectAsState() // Perbaikan di sini
        NavHost(
            navController = navController,
            startDestination = PengelolaHalaman.Home.name,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = PengelolaHalaman.Home.name) {
                HalamanHome(
                    onNextButtonClicked = {
                        navController.navigate(PengelolaHalaman.Formulir.name)
                    }
                )
            }
            composable(route = PengelolaHalaman.Formulir.name) {
                HalamanSatu(
                    pilihanDosen = dosen.map { stringResource(it) },
                    onSelectionChanged = { viewModel.setDosen(it) },
                    onSubmitButtonClick = { data ->
                        viewModel.setData(data)
                        navController.navigate(PengelolaHalaman.Summary.name)
                    },
                    onCancelButtonClicked = { navController.popBackStack() }
                )
            }
            composable(route = PengelolaHalaman.Summary.name) {
                HalamanDua(
                    UIState = uiState,

                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToData(navController) },
                    onSubmitButtonClicked = {
                        // Aksi yang akan diambil ketika tombol submit ditekan
                        navController.popBackStack(PengelolaHalaman.Home.name, inclusive = false)
                    }
                )
            }
        }
    }
}


private fun cancelOrderAndNavigateToHome(
    viewModel: ViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(PengelolaHalaman.Home.name, inclusive = false)
}

private fun cancelOrderAndNavigateToData(
    navController: NavHostController
) {
    navController.popBackStack(PengelolaHalaman.Formulir.name, inclusive = false)
}