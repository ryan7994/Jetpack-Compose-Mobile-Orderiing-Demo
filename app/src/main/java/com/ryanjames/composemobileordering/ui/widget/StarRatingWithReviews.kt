package com.ryanjames.composemobileordering.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryanjames.composemobileordering.R
import com.ryanjames.composemobileordering.ui.theme.*

@Composable
fun StarRatingWithReviews(
    modifier: Modifier = Modifier,
    rating: String,
    numberOfRatings: String,
    ratingColor: TextColor = TextColor.DarkTextColor,
    reviewColor: TextColor = TextColor.LightTextColor
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_star), contentDescription = "Star",
            modifier = Modifier.size(13.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))

        TypeScaledTextView(label = rating, typeScale = TypeScaleCategory.Subtitle2, color = ratingColor)
        Spacer(
            modifier = Modifier
                .size(2.dp)
                .fillMaxWidth()
        )
        TypeScaledTextView(label = numberOfRatings, typeScale = TypeScaleCategory.Subtitle2, color = reviewColor)
    }
}

@Preview
@Composable
fun PreviewStarRatingWithReviews() {
    Column {
        StarRatingWithReviews(rating = "4.50", numberOfRatings = "100")

        MyComposeAppTheme(darkTheme = true) {
            Box(modifier = Modifier.background(AppTheme.colors.materialColors.background)) {
                StarRatingWithReviews(rating = "4.50", numberOfRatings = "100")
            }
        }
    }
}