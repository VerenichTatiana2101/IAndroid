package com.example.arhitecturehw.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersInfo(
    @Json(name = "results") val results: List<User>
) {
    override fun toString(): String {
        return "User: $results"
    }
}

/* модель данных пользовател
* toString переопределён для более удобного вывода данных */
@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "gender") var gender: String? = null,
    @Json(name = "name") var name: Name,
    @Json(name = "location") var location: Location,
    @Json(name = "email") var email: String? = null,
    @Json(name = "login") var login: Login,
    @Json(name = "dob") var dob: DOB,
    @Json(name = "registered") var registered: Registered,
    @Json(name = "phone") var phone: String? = null,
    @Json(name = "cell") var cell: String? = null,
    @Json(name = "id") var id: ID,
    @Json(name = "picture") var picture: Picture,
    @Json(name = "nat") var nat: String? = null
) {
    override fun toString(): String {
        return "\nGender - $gender \n$name \n$location" +
                "\nEmail - $email \n$login \n$dob \n$registered" +
                "\nPhone - $phone \nCell - $cell \n$id \n$picture \nNat - $nat"
    }
}

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "title") var title: String? = null,
    @Json(name = "first") var first: String? = null,
    @Json(name = "last") var last: String? = null
) {
    override fun toString(): String {
        return "Name - $title. $first $last"
    }
}

data class Location(
    var country: String? = null,
    var postcode: String? = null,
    var state: String? = null,
    var city: String? = null,
    var street: Street? = null,
    var coordinates: Coordinates,
    var timezone: Timezone
) {
    override fun toString(): String {
        return "Location - $country, $postcode, $state, $city, $coordinates, \nTimezone - $timezone"
    }
}

data class Login(
    var uuid: String? = null,
    var username: String? = null,
    var password: String? = null,
    var salt: String? = null,
    var md5: String? = null,
    var sha1: String? = null,
    var sha256: String? = null
) {
    override fun toString(): String {
        return " uuid - $uuid" +
                "\n login - $username,\n password - $password" +
                " salt - $salt,\n md5 - $md5,\n sha1 - $sha1,\n sha256 - \n$sha256"
    }
}

data class DOB(
    var date: String? = null,
    var age: String? = null
)

data class Registered(
    var date: String? = null,
    var age: String? = null
)

data class ID(
    var name: String? = null,
    var value: String? = null
)

@JsonClass(generateAdapter = true)
data class Picture(
    @Json(name = "large")var large: String? = null,
    @Json(name = "medium")var medium: String? = null,
    @Json(name = "thumbnail")var thumbnail: String? = null
) {
    override fun toString(): String {
        return "Photos:\nlarge\n $large \nmedium\n $medium \nthumbnail\n $thumbnail"
    }
}

data class Street(
    var number: Int? = null,
    var name: String? = null
) {
    override fun toString(): String {
        return "\nnumber $number, name $name"
    }
}

data class Timezone(
    var offset: String? = null,
    var description: String? = null
) {
    override fun toString(): String {
        return "offset $offset \ndescription - $description"
    }
}
data class Coordinates(
    var latitude: String? = null,
    var longitude: String? = null
) {
    override fun toString(): String {
        return "\nlatitude $latitude \nlongitude $longitude"
    }
}