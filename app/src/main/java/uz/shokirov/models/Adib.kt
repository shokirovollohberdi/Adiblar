package uz.shokirov.models

import java.io.Serializable

class Adib:Serializable {
    var name: String? = null
    var birthYear: String? = null
    var deadYear: String? = null
    var type: Int? = null
    var description: String? = null
    var imageUrl: String? = null


    constructor(
        name: String?,
        birthYear: String?,
        deadYear: String?,
        type: Int?,
        description: String?,
        imageUrl: String?
    ) {
        this.name = name
        this.birthYear = birthYear
        this.deadYear = deadYear
        this.type = type
        this.description = description
        this.imageUrl = imageUrl
    }

    constructor()

    override fun toString(): String {
        return "Adib(name=$name, birthYear=$birthYear, deadYear=$deadYear, type=$type, description=$description, imageUrl=$imageUrl)"
    }

}