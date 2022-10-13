package com.ipl_fixtures.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IPLDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListing(
        iplListingEntities: List<IPLTeamsListingEntity>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListing(
        iplTeamEntities: IPLTeamsListingEntity
    )

    @Query("DELETE FROM iplteamslistingentity")
    suspend fun clearIPListing()

    @Query(
        """
            SELECT *
            FROM iplteamslistingentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                  UPPER(:query) == symbol
        """
    )
    suspend fun searchIPLListing(query: String) : List<IPLTeamsListingEntity>

}