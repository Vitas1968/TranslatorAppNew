package geekbrains.ru.repository

import geekbrains.ru.model.data.DataModel
import geekbrains.ru.model.data.dto.SearchResultDto
import geekbrains.ru.model.room.HistoryDao

class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSourceLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(dataModel: DataModel) {
        convertDataModelSuccessToEntity(dataModel)?.let {
            historyDao.insert(it)
        }
    }
}
