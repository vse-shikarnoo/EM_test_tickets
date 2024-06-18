package test.em.tickets.utils

fun priceRefactor(price: String): String{

    return buildString {
        for (i in price.length-1 downTo 0){
            append(price[i])
            if ((price.length-i)%3==0){
                append(' ')
            }
        }
        reverse()
    }

}