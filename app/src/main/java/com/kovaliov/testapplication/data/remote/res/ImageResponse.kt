package com.kovaliov.testapplication.data.remote.res

data class Weight(
    var imperial: String? = null,
    var metric: String? = null
)

data class Breed(
    var adaptability: Int = 0,
    var affection_level: Int = 0,
    var alt_names: String? = null,
    var cfa_url: String? = null,
    var child_friendly: Int = 0,
    var country_code: String? = null,
    var country_codes: String? = null,
    var description: String? = null,
    var dog_friendly: Int = 0,
    var energy_level: Int = 0,
    var experimental: Int = 0,
    var grooming: Int = 0,
    var hairless: Int = 0,
    var health_issues: Int = 0,
    var hypoallergenic: Int = 0,
    var id: String? = null,
    var indoor: Int = 0,
    var intelligence: Int = 0,
    var life_span: String? = null,
    var name: String? = null,
    var natural: Int = 0,
    var origin: String? = null,
    var rare: Int = 0,
    var rex: Int = 0,
    var shedding_level: Int = 0,
    var short_legs: Int = 0,
    var social_needs: Int = 0,
    var stranger_friendly: Int = 0,
    var suppressed_tail: Int = 0,
    var temperament: String? = null,
    var vcahospitals_url: String? = null,
    var vetstreet_url: String? = null,
    var vocalisation: Int = 0,
    var weight: Weight? = null,
    var wikipedia_url: String? = null
)

data class ImageResponse(
    var breeds: List<Breed>? = null,
    var height: Int = 0,
    var id: String? = null,
    var url: String? = null,
    var width: Int = 0
)
