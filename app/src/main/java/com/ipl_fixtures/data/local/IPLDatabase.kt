package com.ipl_fixtures.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [IPLTeamsListingEntity::class],
    version = 1
)
abstract class IPLDatabase : RoomDatabase() {

    abstract val dao: IPLDao
}