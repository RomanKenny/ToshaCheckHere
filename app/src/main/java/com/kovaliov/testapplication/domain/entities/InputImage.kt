package com.kovaliov.testapplication.domain.entities

import com.kovaliov.testapplication.data.remote.res.ImageResponse
import java.io.Serializable

data class InputImage(
    val imageUri: String,
    val description: String,
    val imageWidth: Int,
    val imageHeight: Int
) : Serializable

fun List<ImageResponse>.toInputImageList(): List<InputImage> {
    val resultList = ArrayList<InputImage>()
    forEach {imageResponse->
        imageResponse.toInputImage()?.let {inputImage->
            resultList.add(inputImage)
        }
    }
    return resultList
}

fun ImageResponse.toInputImage(): InputImage? {
    fun getDescription() =
        "Мы загрузили картинку с размерами [$width:$height] \n " +
                "Адрес нашей картинки: \n $url \n" + "Породы: ${breeds!!.map { it.name }}"

/*
    return if (url.isNullOrEmpty() && getDescription().isNullOrEmpty()) {
        InputImage(imageUri = " ", description = "информация не доступна",imageWidth = width, imageHeight = height)
    } else {
        InputImage(url!!, getDescription(), width, height)
    }*/
    // информация по породе
    return if (this.breeds == null || this.breeds!!.isEmpty())
    {
        InputImage(url!!,"Информация отсутствует", width, height)
    }
    else
    {
        InputImage(imageUri = url!!, description = getDescription(),imageWidth = width, imageHeight = height)
    }

}