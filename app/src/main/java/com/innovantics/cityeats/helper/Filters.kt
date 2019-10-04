package com.innovantics.cityeats.helper

fun dietaryOption():ArrayList<String>{
    val filterList: ArrayList<String> = ArrayList()
    filterList.add("Vegetarian friendly")
    filterList.add("Vegan options")
    filterList.add("Gluten free options")
    return filterList
}
fun establishmentOption():ArrayList<String>{
    val filterList: ArrayList<String> = ArrayList()
    filterList.add("Hotel restaurant")
    filterList.add("Restaurant")
    filterList.add("Cafe & Coffee Shop")
    filterList.add("Lounge Bar")
    filterList.add("Buka & Street Food")
    return filterList
}
fun cuisineOption():ArrayList<String>{
    val filterList: ArrayList<String> = ArrayList()
    filterList.add("International")
    filterList.add("European")
    filterList.add("Mediterranean")
    filterList.add("Chinese")
    filterList.add("Middle Eastern")
    filterList.add("Italian")
    filterList.add("Japanese")
    filterList.add("Mexican")
    filterList.add("American")
    filterList.add("Indian")
    return filterList
}
fun daysOption():ArrayList<String>{
    val dayList: ArrayList<String> = ArrayList()
    dayList.add("Sunday")
    dayList.add("Monday")
    dayList.add("Tuesday")
    dayList.add("Wednesday")
    dayList.add("Thursday")
    dayList.add("Friday")
    dayList.add("Saturday")
    return dayList
}