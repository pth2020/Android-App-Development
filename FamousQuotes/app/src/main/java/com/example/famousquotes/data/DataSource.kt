package com.example.famousquotes.data

import com.example.famousquotes.R
import com.example.famousquotes.model.Quotes

class DataSource {
    fun loadQuotes() : List<Quotes> {
        return listOf<Quotes>(
            Quotes(R.string.quote1,R.drawable.image1),
            Quotes(R.string.quote2,R.drawable.image2),
            Quotes(R.string.quote3,R.drawable.image3),
            Quotes(R.string.quote4,R.drawable.image4),
            Quotes(R.string.quote5,R.drawable.image5),
            Quotes(R.string.quote6,R.drawable.image6),
            Quotes(R.string.quote7,R.drawable.image7),
            Quotes(R.string.quote8,R.drawable.image8),
            Quotes(R.string.quote9,R.drawable.image9),
            Quotes(R.string.quote10,R.drawable.image10),
            Quotes(R.string.quote11,R.drawable.image11),
            Quotes(R.string.quote12,R.drawable.image12),
            Quotes(R.string.quote13,R.drawable.image13),
            Quotes(R.string.quote14,R.drawable.image14),
            Quotes(R.string.quote15,R.drawable.image15),
            Quotes(R.string.quote16,R.drawable.image16),
            Quotes(R.string.quote17,R.drawable.image17),
            Quotes(R.string.quote18,R.drawable.image18),
            Quotes(R.string.quote19,R.drawable.image19),
            Quotes(R.string.quote20,R.drawable.image20)
        )
    }
}
