package com.ryanjames.jetpackmobileordering.repository


import com.ryanjames.jetpackmobileordering.db.AppDatabase
import com.ryanjames.jetpackmobileordering.db.VenueEntityType
import com.ryanjames.jetpackmobileordering.network.MobilePosApi
import com.ryanjames.jetpackmobileordering.network.networkBoundResource
import com.ryanjames.jetpackmobileordering.ui.toDomain
import com.ryanjames.jetpackmobileordering.ui.toEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VenueRepository(
    private val mobilePosApi: MobilePosApi,
    private val roomDb: AppDatabase
) : AbsVenueRepository {

    @ExperimentalCoroutinesApi
    override fun getFeaturedVenues() = networkBoundResource(
        fetchFromApi = { mobilePosApi.getFeaturedVenues() },
        queryDb = { roomDb.venueDao().getHomeVenues() },
        savetoDb = { homeResponse -> roomDb.venueDao().insertVenues(homeResponse.toEntity()) },
        shouldFetchFromApi = { databaseModel -> databaseModel.isEmpty() },
        onFetchFailed = { },
        mapToDomainModel = { dbList ->
            val featuredList = dbList.filter { it.venue.type == VenueEntityType.HOME_FEATURED }.map { it.toDomain() }
            val restaurantList = dbList.filter { it.venue.type == VenueEntityType.HOME_RESTAURANT_LIST }.map { it.toDomain() }
            Pair(featuredList, restaurantList)
        }
    )

    override fun getVenueById(id: String) = networkBoundResource(
        fetchFromApi = { mobilePosApi.getVenueById(id) },
        queryDb = { roomDb.venueDao().getVenueById(id) },
        savetoDb = { roomDb.venueDao().insertVenues(listOf(it.toEntity(""))) },
        shouldFetchFromApi = { it == null },
        onFetchFailed = { it.printStackTrace() },
        mapToDomainModel = { it?.toDomain() }
    )

    override suspend fun getCurrentVenueId() = roomDb.globalDao().getGlobalValues()?.currentVenue

    override fun getCurrentVenueIdFlow(): Flow<String?> {
        return roomDb.globalDao().getGlobalValuesFlow().map { it?.currentVenue }
    }
}