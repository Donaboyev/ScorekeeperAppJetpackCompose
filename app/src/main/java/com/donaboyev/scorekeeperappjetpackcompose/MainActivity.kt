package com.donaboyev.scorekeeperappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.donaboyev.scorekeeperappjetpackcompose.ui.theme.FoulTextColor
import com.donaboyev.scorekeeperappjetpackcompose.ui.theme.ScoreTextColor
import com.donaboyev.scorekeeperappjetpackcompose.ui.theme.ScorekeeperAppJetpackComposeTheme


enum class Team {
    First, Second
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScorekeeperAppJetpackComposeTheme {
                ScorekeeperApp()
            }
        }
    }
}

@Composable
fun ScorekeeperApp() {
    var score1 by remember {
        mutableIntStateOf(0)
    }
    var score2 by remember {
        mutableIntStateOf(0)
    }
    var foul1 by remember {
        mutableIntStateOf(0)
    }
    var foul2 by remember {
        mutableIntStateOf(0)
    }

    Surface(color = Color.Black, modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Scores(score1 = score1, score2 = score2, addScore = { team, score ->
                when (team) {
                    Team.First -> score1 += score
                    Team.Second -> score2 += score
                }
            })
            Fouls(foul1 = foul1, foul2 = foul2, addFoul = { team ->
                when (team) {
                    Team.First -> foul1++
                    Team.Second -> foul2++
                }
            })
            Button(onClick = {
                score1 = 0
                score2 = 0
                foul1 = 0
                foul2 = 0
            }, modifier = Modifier.padding(bottom = 24.dp)) {
                Text(text = stringResource(R.string.reset))
            }
        }
    }
}

@Composable
fun Scores(score1: Int, score2: Int, modifier: Modifier = Modifier, addScore: (Team, Int) -> Unit) {
    Row(modifier = modifier.padding(top = 24.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.weight(1F)) {
            Text(text = stringResource(R.string.team_1), fontSize = 30.sp, color = Color.White)
            Text(
                text = "$score1",
                color = ScoreTextColor,
                fontSize = 40.sp,
                modifier = modifier.padding(vertical = 24.dp)
            )
            Button(onClick = { addScore(Team.First, 1) }) {
                Text(text = stringResource(R.string.plus_one))
            }
            Button(onClick = { addScore(Team.First, 2) }) {
                Text(text = stringResource(R.string.plus_two))
            }
            Button(onClick = { addScore(Team.First, 3) }) {
                Text(text = stringResource(R.string.plus_three))
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.weight(1F)) {
            Text(text = stringResource(R.string.team_2), fontSize = 30.sp, color = Color.White)
            Text(
                text = "$score2",
                color = ScoreTextColor,
                fontSize = 40.sp,
                modifier = modifier.padding(vertical = 24.dp)
            )
            Button(onClick = { addScore(Team.Second, 1) }) {
                Text(text = stringResource(R.string.plus_one))
            }
            Button(onClick = { addScore(Team.Second, 2) }) {
                Text(text = stringResource(R.string.plus_two))
            }
            Button(onClick = { addScore(Team.Second, 3) }) {
                Text(text = stringResource(R.string.plus_three))
            }
        }
    }
}

@Composable
fun Fouls(foul1: Int, foul2: Int, modifier: Modifier = Modifier, addFoul: (Team) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { addFoul(Team.First) }) {
            Text(text = stringResource(id = R.string.plus_one))
        }
        Text(
            text = "$foul1",
            color = FoulTextColor,
            fontSize = 24.sp,
            modifier = modifier.padding(horizontal = 24.dp)
        )
        Text(text = stringResource(R.string.fouls), color = Color.White, fontSize = 20.sp)
        Text(
            text = "$foul2",
            color = FoulTextColor,
            fontSize = 24.sp,
            modifier = modifier.padding(horizontal = 24.dp)
        )
        Button(onClick = { addFoul(Team.Second) }) {
            Text(text = stringResource(id = R.string.plus_one))
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ScorekeeperAppJetpackComposeTheme {
        ScorekeeperApp()
    }
}