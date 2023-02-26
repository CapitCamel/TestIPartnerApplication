package com.example.testipartnerapplication.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testipartnerapplication.data.network.ApiService
import com.example.testipartnerapplication.data.network.DrugResponseItem
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_INDEX = 1

class SearchPagingSource(
    private val api: ApiService,
    private val query: String?
) : PagingSource<Int, DrugResponseItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DrugResponseItem> {
        val position = params.key ?: STARTING_INDEX
        return try {
            val response = api.search(search = query, offset = position) //нет параметра page, поэтому использовал offset, но он не так работает как нужно

            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DrugResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

