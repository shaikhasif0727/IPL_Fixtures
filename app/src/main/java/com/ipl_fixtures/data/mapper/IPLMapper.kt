package com.ipl_fixtures.data.mapper

import com.ipl_fixtures.data.local.IPLTeamsListingEntity
import com.ipl_fixtures.domain.model.IPLTeamsListing


fun IPLTeamsListingEntity.toIPLListing() : IPLTeamsListing{
    return IPLTeamsListing(
        teamName = name,
        teamImage = symbol,
    )
}

fun IPLTeamsListing.toIPLListingEntity() : IPLTeamsListingEntity{
    return IPLTeamsListingEntity(
        name = teamName,
        symbol = teamImage,
    )
}