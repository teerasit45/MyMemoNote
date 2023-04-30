package com.teera.mymemonote.ui.note_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teera.mymemonote.data.Note

@Composable
fun NoteCard(
    modifier: Modifier = Modifier
        .padding(10.dp)
        .clip(RoundedCornerShape(20.dp)),
    note: Note
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(70.dp)
            .background(Color.White),
//        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = modifier.padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(
                    text = "Title: ",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)
                )
                Text(
                    text = note.title,
                    style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    text = "Detail: ",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = note.details,
                    style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Last Updated: ${note.timestamp}",
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp
                    )
                )
                Text(
                    text = "Word: ${note.wordCount}", style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}