package com.example.a078_ucp2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.a078_ucp2.data.UIState

@Composable
fun HalamanDua(
    UIState: UIState,
    onCancelButtonClicked: () -> Unit,
    onSubmitButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        Pair(stringResource(R.string.nma_mhs), UIState.namamhs),
        Pair(stringResource(R.string.nim), UIState.nim),
        Pair(stringResource(R.string.konsen), UIState.konsen),
        Pair(stringResource(R.string.judul), UIState.judulsk),
        Pair(stringResource(R.string.dosen), UIState.dosen)
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier =
            Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement =
            Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            items.forEach { item ->
                Column {
                    Text(item.first, fontWeight = FontWeight.Bold)
                    Text(
                        text = item.second.toString(),
                        fontWeight = if (item.first == stringResource(R.string.dosen)
                        ) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        }
                    )
                }
                Divider(
                    thickness =
                    dimensionResource(R.dimen.thickness_divider)
                )
            }
            Spacer(
                modifier =
                Modifier.height(dimensionResource(R.dimen.padding_small))
            )

        }

        Row(
            modifier = Modifier
                .weight(1f, false)
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            Column(
                verticalArrangement =
                Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onSubmitButtonClicked
                ) {
                    Text(stringResource(R.string.send))
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onCancelButtonClicked
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHalamanDua() {
    val uiState = UIState()
    HalamanDua(
        UIState = uiState,
        onCancelButtonClicked = {},
        onSubmitButtonClicked = {}
    )
}